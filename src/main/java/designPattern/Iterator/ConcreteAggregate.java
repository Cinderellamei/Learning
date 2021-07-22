package designPattern.Iterator;

import java.util.ArrayList;
import java.util.List;

public class ConcreteAggregate extends Aggregate{
    private List<Object> items = new ArrayList<>();

    @Override
    public Iterator createIterator() {
        return new ConcreteIterator(this);
    }

    public int count() {
        return items.size();
    }

    public Object getItems(int index) {
        return items.get(index);
    }

    public void setItems(int index,Object value) {
        items.set(index,value);
    }
}
