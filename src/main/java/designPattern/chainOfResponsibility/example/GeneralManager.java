package designPattern.chainOfResponsibility.example;

public class GeneralManager extends Manager {
    public GeneralManager(String name) {
        super(name);
    }

    @Override
    public void requestApplication(Request request) {
        if(request.getRequestType().equals("请假")) {
            System.out.println(name+":"+request.getRequestContent()+"请求被批准:"+request.getNumber());
        } else if(request.getRequestType().equals("加薪")&&request.getNumber()<=500) {
            System.out.println(name+":"+request.getRequestContent()+"请求被批准:"+request.getNumber());
        }
        else if(request.getRequestType().equals("加薪")&&request.getNumber()>500){
            System.out.println(name+":"+request.getRequestContent()+"再说吧:"+request.getNumber());
        }
    }
}

