package designPattern.flyWeight;

public class Client {
    public static void main(String [] args) {
        int extrinsicstate = 22;

        FlyWeightFactory f = new FlyWeightFactory();
        FlyWeight fx = f.getFlyWeight("X");
        fx.operation(--extrinsicstate);

        FlyWeight fy = f.getFlyWeight("Y");
        fx.operation(--extrinsicstate);

        FlyWeight fz = f.getFlyWeight("Z");
        fx.operation(--extrinsicstate);

        UnsharedConcreteFlyWeight uf = new UnsharedConcreteFlyWeight();
        uf.operation(--extrinsicstate);
    }
}
