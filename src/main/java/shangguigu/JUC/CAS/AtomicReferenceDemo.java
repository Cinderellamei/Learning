package shangguigu.JUC.CAS;

import java.util.concurrent.atomic.AtomicReference;

class User{
    String userName;
    int age;

    public User(String userName,int age) {
        this.userName = userName;
        this.age = age;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    @Override
    public String toString() {
        return "User{" +
                "userName='" + userName + '\'' +
                ", age=" + age +
                '}';
    }

}
public class AtomicReferenceDemo {
    public static void main(String [] args) {
        User user1 = new User("zhangsan",14);
        User user2 = new User("lisi",25);

        //创建原子引用包装类
        AtomicReference<User> atomicReference = new AtomicReference<>();

        //主物理内存共享变量为user1
        atomicReference.set(user1);

        //比较并交换
        System.out.println(atomicReference.compareAndSet(user1,user2)+"\t"+atomicReference.get().toString());

        //比较并交换,因为物理内存的值被改过了，所以交换失败
        System.out.println(atomicReference.compareAndSet(user1,user2)+"\t"+atomicReference.get().toString());
    }
}
