package designPattern.chainOfResponsibility;

public class Client {
    public static void main(String [] args) {
        /*Manager jinli = new Manager("jinli");
        Manager zongjian = new Manager("zongjian");
        Manager zhongjingli = new Manager("zongjingli");

        Request request = new Request();
        request.setRequestType("加薪");
        request.setRequestContent("小菜请求加薪");
        request.setNumber(1000);

        jinli.getResult("经理",request);
        zongjian.getResult("总监",request);
        zhongjingli.getResult("总经理",request);

        Request request1 = new Request();
        request.setRequestType("请假");
        request.setRequestContent("小菜请求请假");
        request.setNumber(3);

        jinli.getResult("经理",request);
        zongjian.getResult("总监",request);
        zhongjingli.getResult("总经理",request);*/

        Handler h1 = new ConcreteHandler1();
        Handler h2 = new ConcreteHandler2();
        Handler h3 = new ConcreteHandler3();

        h1.setSuccessor(h2);
        h2.setSuccessor(h3);

        int request[] = {2,3,14,22,18,3,27,20};

        for(int i = 0;i<request.length;i++) {
            h1.handleRequest(request[i]);
        }

    }
}
