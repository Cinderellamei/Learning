package designPattern.Iterator;

public class ConcreteIterator extends Iterator {
    private ConcreteAggregate aggregate;

    private int current = 0;

    public ConcreteIterator(ConcreteAggregate aggregate) {
        this.aggregate = aggregate;
    }

    @Override
    public Object First() {
        return aggregate.getItems(0);
    }

    @Override
    public Object Next() {
        Object ret = null;
        current++;
        if(current < aggregate.count()) {
            ret = aggregate.getItems(current);
        }
        return ret;
    }

    @Override
    public boolean isDone() {
        return current>=aggregate.count()?true:false;
    }

    @Override
    public Object currentItem() {
        return aggregate.getItems(current);
    }

}
