package designPattern.memento.example;

public class Client {
    public static void main(String [] args) {
        GameRole lixiaoyao = new GameRole();
        lixiaoyao.getInitState();
        lixiaoyao.stateDisplay();

        RoleStateCareTaker stateAdmin = new RoleStateCareTaker();
        stateAdmin.setRoleStateMemento(lixiaoyao.saveState());

        lixiaoyao.fight();
        lixiaoyao.stateDisplay();

        lixiaoyao.recoveryState(stateAdmin.getRoleStateMemento());
        lixiaoyao.stateDisplay();
    }
}
