package Socket;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
    public static void main(String [] args) {
        try {
            //创建一个serverSocket监听8080端口
            ServerSocket server = new ServerSocket(8080);
            //等待请求
            Socket socket = server.accept();
            //接收到请求后使用socket通信，创建BufferdReader用于读取数据
            BufferedReader is = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            String line = is.readLine();
            System.out.printf("received form client:"+line);
            //创建printWriter，用于发送数据
            PrintWriter pw = new PrintWriter(socket.getOutputStream());
            pw.println("received data:"+line);
            pw.flush();
            //关闭资源
            pw.close();
            socket.close();
            server.close();
        } catch(Exception e) {
            e.printStackTrace();
        }
    }
}
