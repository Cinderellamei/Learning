package Algorithm.proxy;

public class PersonServiceImpl implements PersonService {
    @Override
    public void savePerson() {
        System.out.println("add");
    }

    @Override
    public void updatePerson() {
        System.out.println("update");
    }

    @Override
    public void deletePerson() {
        System.out.println("delete");
    }
}
