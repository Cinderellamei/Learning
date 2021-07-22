package designPattern.Adapter.example;

public class Client {
    public static void main(String [] args) {
        Player zhanmusi = new Forwards("zhanmusi");
        zhanmusi.attack();
        Player kebi = new Guards("kebi");
        kebi.attack();
        Player yaoming = new Translator("yaoming");
        yaoming.attack();
        yaoming.defense();
    }
}
