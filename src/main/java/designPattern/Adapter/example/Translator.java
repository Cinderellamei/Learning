package designPattern.Adapter.example;

public class Translator extends Player {
    private ForeignCenter wjzf = new ForeignCenter(name);

    public Translator(String name) {
        super(name);
    }

    @Override
    public void attack() {
        wjzf.进攻();
    }

    @Override
    public void defense() {
        wjzf.防守();
    }
}
