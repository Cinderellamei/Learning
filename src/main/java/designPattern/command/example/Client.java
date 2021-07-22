package designPattern.command.example;

public class Client {
    public static void main(String [] args) {
        Barbecuer boy = new Barbecuer();

        Command bakeMutton = new BakeButtonCommand(boy);
        Command bakeMutton1 = new BakeButtonCommand(boy);
        Command bakeChicken = new BakeChicken(boy);

        Waiter girl = new Waiter();

        girl.setOrder(bakeMutton);
        girl.setOrder(bakeMutton1);
        girl.setOrder(bakeChicken);

        girl.notifies();
    }
}
