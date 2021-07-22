package designPattern.Composite.example;

public class FinanceDepartment extends Company{
    public FinanceDepartment(String name) {
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
        System.out.println("公司财务部门收支管理:"+name);
    }
}
