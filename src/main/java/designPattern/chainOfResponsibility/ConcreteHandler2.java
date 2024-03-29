package designPattern.chainOfResponsibility;

public class ConcreteHandler2 extends Handler {

    @Override
    public void handleRequest(int request) {
        if(request>=10&&request<20) {
            System.out.println(this.getClass().getName()+"处理请求："+request);
        } else if(successor != null) {
            successor.handleRequest(request);
        }
    }
}
