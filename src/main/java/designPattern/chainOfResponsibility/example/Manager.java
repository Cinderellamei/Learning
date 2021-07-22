package designPattern.chainOfResponsibility.example;

public abstract class Manager {
    protected String name;

    protected Manager superior;

    public Manager(String name) {
        this.name = name;
    }

    public void setSuperior(Manager superior) {
        this.superior = superior;
    }

    public abstract void requestApplication(Request request);

    /*public void getResult(String managerLevel, Request request) {
        if(managerLevel.equals("总经理")) {
            if(request.getRequestType().equals("请假")&&request.getNumber()<=2) {
                System.out.println(name+":"+request.getRequestContent()+"请求被批准:"+request.getNumber());
            } else {
                System.out.println(name+":"+request.getRequestContent()+"我无权处理:"+request.getNumber());
            }
        } else if(managerLevel.equals("总监")) {
            if(request.getRequestType().equals("请假")&&request.getNumber()<=5) {
                System.out.println(name+":"+request.getRequestContent()+"请求被批准:"+request.getNumber());
            } else {
                System.out.println(name+":"+request.getRequestContent()+"我无权处理:"+request.getNumber());
            }
        } else if(managerLevel.equals("总经理")) {
            if(request.getRequestType().equals("请假")) {
                System.out.println(name+":"+request.getRequestContent()+"请求被批准:"+request.getNumber());
            } else if(request.getRequestType().equals("加薪")&&request.getNumber()<=500) {
                System.out.println(name+":"+request.getRequestContent()+"请求被批准:"+request.getNumber());
            }
            else if(request.getRequestType().equals("加薪")&&request.getNumber()>500){
                System.out.println(name+":"+request.getRequestContent()+"再说吧:"+request.getNumber());
            }
        }
    }*/
}
