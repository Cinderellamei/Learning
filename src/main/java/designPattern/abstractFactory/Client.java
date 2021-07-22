package designPattern.abstractFactory;

public class Client {
    public static void main(String [] args) {
        User user = new User();
        Department department = new Department();
        IFactory factory = new AccessFactory();
        IUser iuser = factory.createUser();
        iuser.insert(user);
        iuser.getUser(1);

        IDepartment id = factory.createDepartment();
        id.insert(department);
        id.getDepartment(1);

        User user1 = new User();
        Department department1 = new Department();
        IUser iu1 = DataAccess.createUser();
        iu1.insert(user1);
        iu1.getUser(1);
        IDepartment id1 = DataAccess.createDepartment();
        id1.insert(department1);
        id1.getDepartment(1);
    }
}
