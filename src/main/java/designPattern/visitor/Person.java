package designPattern.visitor;

public abstract class Person {
    public abstract void accept(Action visitor);
}
