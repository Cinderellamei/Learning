package designPattern.Iterator;

public class Client {
    public static void main(String [] args) {
        ConcreteAggregate a = new ConcreteAggregate();
        a.setItems(0,"daniao");

        a.setItems(1,"xiaocai");

        a.setItems(2,"xingli");

        a.setItems(3,"laowai");

        a.setItems(4,"gongjiaoneibuyuangong");

        a.setItems(5,"xiaotou");

        Iterator i = new ConcreteIterator(a);

        Object item = i.First();

        while(!i.isDone()) {
            System.out.println("请买车票"+i.currentItem());
            i.Next();
        }
    }
}
