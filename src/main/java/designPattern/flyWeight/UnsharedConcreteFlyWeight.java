package designPattern.flyWeight;

public class UnsharedConcreteFlyWeight extends FlyWeight {
    @Override
    public void operation(int extrinsicstate) {
        System.out.println("不共享的具体flyWeight"+extrinsicstate);
    }
}
