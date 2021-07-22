package designPattern.Composite;

public class Leaf extends Component {
    public Leaf(String name) {
       super(name);
    }

    @Override
    public void add(Component c) {
        System.out.println("can not add to a leaf");
    }

    @Override
    public void remove(Component c) {
        System.out.println("can not remove from a leaf");
    }

    @Override
    public void display(int depth) {
        System.out.println("depth:"+depth+",name:"+name);
    }
}
