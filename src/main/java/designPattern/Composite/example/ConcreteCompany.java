package designPattern.Composite.example;

import java.util.ArrayList;
import java.util.List;

public class ConcreteCompany extends Company {

    private List<Company> children = new ArrayList<>();

    public ConcreteCompany(String name) {
        super(name);
    }

    @Override
    public void add(Company c) {
        children.add(c);
    }

    @Override
    public void remove(Company c) {
        children.remove(c);
    }

    @Override
    public void display(int depth) {
        System.out.println("-depth"+depth+",name:"+name);
        for(Company c :children) {
            c.display(depth);
        }
    }

    @Override
    public void lineofDuty() {
        for(Company c:children) {
            c.lineofDuty();
        }
    }
}
