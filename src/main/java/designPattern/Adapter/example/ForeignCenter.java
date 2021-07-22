package designPattern.Adapter.example;

public class ForeignCenter {

    private String name;

    public ForeignCenter(String name) {
        this.name = name;
    }

    public void 进攻() {
        System.out.println("后锋进攻："+name);
    }

    public void 防守() {
        System.out.println("后锋防守："+name);
    }
}
