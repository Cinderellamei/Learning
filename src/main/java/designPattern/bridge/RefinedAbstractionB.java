package designPattern.bridge;

public class RefinedAbstractionB extends Abstraction {
    @Override
    public void operation() {
        implementor.operation();
    }
}
