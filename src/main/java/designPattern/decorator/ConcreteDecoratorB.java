package designPattern.decorator;

public class ConcreteDecoratorB extends Decorator{

    Component base;

    @Override
    public void operation() {
        base.operation();
        addedBehavior();
        System.out.println("具体装饰类B的行为");
    }

    public void addedBehavior() {

    }
}
