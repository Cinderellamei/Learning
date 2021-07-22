package designPattern.Adapter.example;

public class Guards extends Player{

    public Guards(String name) {
        super(name);
    }

    @Override
    public void attack() {
        System.out.println("守卫进攻："+name);
    }

    @Override
    public void defense() {
        System.out.println("守卫防守："+name);
    }
}
