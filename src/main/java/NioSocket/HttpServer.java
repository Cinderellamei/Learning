package NioSocket;

import javax.xml.ws.spi.http.HttpHandler;
import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.nio.charset.Charset;
import java.util.Iterator;

public class HttpServer {
    public static void main(String [] args) throws Exception {
        //创建ServerSocketChannel，监听8080端口
        ServerSocketChannel ssc = ServerSocketChannel.open();
        ssc.socket().bind(new InetSocketAddress(8080));
        //设置为非阻塞模式
        ssc.configureBlocking(false);
        //为ssc注册选择器
        Selector selector = Selector.open();
        ssc.register(selector,SelectionKey.OP_ACCEPT);
        //创建处理器
        while(true) {
            //等待请求，每次等待阻塞3秒，超过3秒后线程继续往下运行，如果传入0或不传参数，将一直阻塞
            if(selector.select(3000) == 0) {
                continue;
            }
            //获取待处理的SelectionKey
            Iterator<SelectionKey> keyIter = selector.selectedKeys().iterator();
            while(keyIter.hasNext()) {
                SelectionKey key = keyIter.next();
                //启动新线程处理SelectionKey
                new Thread(new HttpHandler(key)).run();
                //处理完后，从待处理的SelectionKey迭代器中移除当前所使用的key
                keyIter.remove();
            }
        }
    }

    public static class HttpHandler implements Runnable {
        private int bufferSize = 1024;
        private String localCharset = "UTF-8";
        private SelectionKey key;

        public HttpHandler(SelectionKey key) {
            this.key = key;
        }

        public void handelAccept() throws IOException {
            SocketChannel clientChannel = ((ServerSocketChannel) key.channel()).accept();
            clientChannel.configureBlocking(false);
            clientChannel.register(key.selector(), SelectionKey.OP_READ, ByteBuffer.allocate(bufferSize));
        }

        public void handelRead() throws IOException {
            //获取channel
            SocketChannel sc = (SocketChannel)key.channel();
            //获取buffer并重置
            ByteBuffer buffer = (ByteBuffer)key.attachment();
            buffer.clear();
            //没有读到内容则关闭
            if(sc.read(buffer) == -1) {
                sc.close();
            } else {
                //接收请求数据
                buffer.flip();
                String receivedString = Charset.forName(localCharset).newDecoder().decode(buffer).toString();
                //控制台打印请求报文头
                String [] requestMessage = receivedString.split("\r\n");
                for(String s :requestMessage) {
                    System.out.println(s);
                    if(s.isEmpty()) {
                        break;
                    }
                }
                //控制台打印首行信息
                String [] firstLine = requestMessage[0].split("");
                System.out.println();
                System.out.println("Method:\t"+firstLine);
                System.out.println("url:\t"+firstLine[1]);
                System.out.println("HTTP Version:\t"+firstLine[2]);
                System.out.printf("");
                //返回客户端
                StringBuilder sendString = new StringBuilder();
                sendString.append("HTTP/1.1 200 OK\r\n");
                sendString.append("Content-Type:text/html;charsrt="+localCharset+"\r\n");
                sendString.append("\r\n");
                sendString.append("<html><head><title>显示报文</title></head><body>");
                sendString.append("接收到的请求报文是:<br/>");
                for(String s :requestMessage) {
                    sendString.append(s+"<br/>");
                }
                sendString.append("</body></html>");
                buffer = ByteBuffer.wrap(sendString.toString().getBytes(localCharset));
                sc.write(buffer);
                sc.close();
            }
        }

        @Override
        public void run() {
            try {
                //接收到连接请求时
                if(key.isAcceptable()) {
                    handelAccept();
                }
                //读数据
                if(key.isReadable()) {
                    handelRead();
                }
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
    }
}
