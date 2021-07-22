package designPattern.PublishSubscribe;

public class Client {
    public static void main(String [] args) {
        Secretary xiaoli = new Secretary();

        //StockObserver observer1 = new StockObserver("laowang",xiaoli);

        //StockObserver observer2 = new StockObserver("laozhao",xiaoli);

        //xiaoli.attach(observer1);

        //xiaoli.attach(observer2);

        xiaoli.setAction("老板回来了");

        xiaoli.notifys();
    }
}
