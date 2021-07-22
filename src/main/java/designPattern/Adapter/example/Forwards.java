package designPattern.Adapter.example;

public class Forwards extends Player {

    public Forwards(String name) {
        super(name);
    }

    @Override
    public void attack() {
        System.out.println("前锋进攻:"+name);
    }

    @Override
    public void defense() {
        System.out.println("前锋防守:"+name);
    }
}
