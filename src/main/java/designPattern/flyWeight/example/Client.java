package designPattern.flyWeight.example;

public class Client {
    public static void main(String [] args) {
        WebsiteFactory f = new WebsiteFactory();

        Website fx = f.getWebsite("产品展示");
        fx.use(new User("小菜"));

        Website fy = f.getWebsite("产品展示");
        fy.use(new User("大鸟"));

        Website fz = f.getWebsite("产品展示");
        fz.use(new User("娇娇"));

        Website f1 = f.getWebsite("博客");
        f1.use(new User("老顽童"));

        Website f2 = f.getWebsite("博客");
        f2.use(new User("仙桃"));

        Website f3 = f.getWebsite("博客");
        f3.use(new User("恶神"));

        System.out.println("总数"+f.getWebsiteCount());
    }
}
