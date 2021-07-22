package designPattern.Composite.example;

public class HRDepartment extends Company {
    public HRDepartment(String name) {
        super(name);
    }

    @Override
    public void add(Company c) {

    }

    @Override
    public void remove(Company c) {

    }

    @Override
    public void display(int depth) {
        System.out.println("-,depth:"+depth+",name:"+name);
    }

    @Override
    public void lineofDuty() {
        System.out.println("员工招聘培训:"+name);
    }
}
