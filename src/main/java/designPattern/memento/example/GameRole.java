package designPattern.memento.example;

public class GameRole {
    private int vit;
    private int atk;
    private int def;
    public RoleStateMemento saveState() {
        return new RoleStateMemento(vit,atk,def);
    }

    public void recoveryState(RoleStateMemento roleStateMemento) {
        this.vit = roleStateMemento.getVitality();
        this.atk = roleStateMemento.getAttack();
        this.def = roleStateMemento.getDefense();
    }

    public void stateDisplay() {
        System.out.println("角色当前的状态:体力："+this.vit+"攻击力："+this.atk+"防御力："+this.def);
    }

    public void getInitState() {
        this.vit = 100;
        this.atk = 100;
        this.def = 100;
    }

    public void fight() {
        this.vit = 0;
        this.atk = 0;
        this.def = 0;
    }
}
