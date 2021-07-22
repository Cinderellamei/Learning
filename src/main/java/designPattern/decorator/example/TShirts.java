package designPattern.decorator.example;

public class TShirts extends Finery {

    Person base;

    @Override
    public void show() {
        System.out.println("大T");
        base.show();
    }
}
