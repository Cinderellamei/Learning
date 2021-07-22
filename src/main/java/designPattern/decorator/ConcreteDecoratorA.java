package designPattern.decorator;

public class ConcreteDecoratorA extends Decorator {
    private String addedState;

    Component base;

    @Override
    public void operation() {
        base.operation();
        addedState = "New State";
        System.out.printf("具体装饰对象A的操作");
    }
}
