package Reflect;

public class Test {
    private int age;
    private String name;
    private int testint;

    public Test(int age) {
        this.age = age;
    }

    public Test(int age,String name) {
        this.age = age;
        this.name = name;
        System.out.println("hello "+name+"i am "+age);
    }

    private Test(String name) {
        this.name = name;
        System.out.println("My name is "+name);
    }

    public Test() {

    }

    private void welcome(String tips) {
        System.out.println(tips);
    }
}
