package Algorithm.proxy;

public class PersonServiceProxy implements PersonService {
    private PersonService personService;
    private Transaction transaction;

    public PersonServiceProxy(PersonService personService,Transaction transaction) {
        this.personService = personService;
        this.transaction = transaction;
    }
    @Override
    public void savePerson() {
        transaction.beginTransaction();
        personService.savePerson();
        transaction.commit();
    }

    @Override
    public void updatePerson() {
        transaction.beginTransaction();
        personService.updatePerson();
        transaction.commit();
    }

    @Override
    public void deletePerson() {
        transaction.beginTransaction();
        personService.updatePerson();
        transaction.commit();
    }
}
