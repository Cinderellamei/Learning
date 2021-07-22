package designPattern.chainOfResponsibility.example;

public class Client {
    public static void main(String [] args) {
        Manager jinli = new CommonManager("jinli");
        Manager zongjian = new Majordomo("zongjian");
        Manager zhongjingli = new GeneralManager("zongjingli");

        jinli.setSuperior(zongjian);
        zongjian.setSuperior(zhongjingli);

        Request request = new Request();
        request.setRequestType("请假");
        request.setRequestContent("小菜请求请假");
        request.setNumber(1);
        jinli.requestApplication(request);

        Request request1 = new Request();
        request.setRequestType("请假");
        request.setRequestContent("小菜请求请假");
        request.setNumber(4);
        jinli.requestApplication(request1);

        Request request2 = new Request();
        request.setRequestType("加薪");
        request.setRequestContent("小菜请求加薪");
        request.setNumber(500);
        jinli.requestApplication(request2);

        Request request3 = new Request();
        request.setRequestType("加薪");
        request.setRequestContent("小菜请求加薪");
        request.setNumber(1000);
        jinli.requestApplication(request3);
    }
}
