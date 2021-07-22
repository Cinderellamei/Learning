package designPattern.Adapter;

public class Client {
    public static void main(String [] args) {
        Target target = new Adatper();

        target.request();
    }
}
