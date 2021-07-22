package designPattern.command.example;

import designPattern.command.example.Command;

import java.util.ArrayList;
import java.util.List;

public class Waiter {
    private List<Command> orders = new ArrayList<>();


    public void setOrder(Command command) {
        if(String.valueOf(command).equals("bakeChicken")) {
            System.out.println("鸡翅没有了，请点别的烧烤");
        } else {
            orders.add(command);
            System.out.println("增加订单"+command.toString());
        }
    }

    public void cancelOrder(Command command) {
        orders.remove(command);
        System.out.println("取消订单"+command.toString());
    }

    public void notifies() {
        for(Command command:orders) {
            command.excuteCommand();
        }
    }
}
