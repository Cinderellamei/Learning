package designPattern.chainOfResponsibility.example;

public class CommonManager extends Manager {
    public CommonManager(String name) {
        super(name);
    }
    @Override
    public void requestApplication(Request request) {
        if(request.getRequestType().equals("请假")&&request.getNumber()<=2) {
            System.out.println(name+":"+request.getRequestContent()+"请求被批准:"+request.getNumber());
        } else {
            if(superior != null) {
                superior.requestApplication(request);
            }
        }
    }
}
