package designPattern.memento;

public class Client {
    public static void main(String [] args) {
        /*GameRole lixiaoyao = new GameRole();
        lixiaoyao.getInitState();
        lixiaoyao.stateDisplay();

        GameRole backup = new GameRole();
        backup.setVitality(lixiaoyao.getVitality());
        backup.setAttack(lixiaoyao.getAttack());
        backup.setDefense(lixiaoyao.getDefense());

        lixiaoyao.fight();
        lixiaoyao.stateDisplay();

        lixiaoyao.setVitality(backup.getVitality());
        lixiaoyao.setAttack(backup.getAttack());
        lixiaoyao.setDefense(backup.getDefense());

        lixiaoyao.stateDisplay();*/

        Originator o = new Originator();
        o.setState("on");
        o.show();

        CareTaker c = new CareTaker();
        c.setMemento(o.createMemento());

        o.setState("off");
        o.show();

        o.setMemento(c.getMemento());
        o.show();


    }
}
