package designPattern.Iterator;

public abstract class Iterator {
    public abstract Object First();

    public abstract Object Next();

    public abstract boolean isDone();

    public abstract Object currentItem();

}
