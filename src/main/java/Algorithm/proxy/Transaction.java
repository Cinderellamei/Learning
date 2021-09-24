package Algorithm.proxy;

public class Transaction {
    public void beginTransaction() {
        System.out.println("begin transaction");
    }

    public void commit() {
        System.out.println("commit transaction");
    }
}
