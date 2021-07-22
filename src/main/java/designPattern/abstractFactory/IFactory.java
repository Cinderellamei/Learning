package designPattern.abstractFactory;

public interface IFactory {
    IUser createUser();
    IDepartment createDepartment();
}
