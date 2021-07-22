package designPattern.visitor;

public class Success extends Action {
    @Override
    public void getManConclusion(Man concreteElementA) {
        System.out.println(concreteElementA.getClass()+"背后多有一个伟大的女人");
    }

    @Override
    public void getWomanConclusion(Woman concreteElementB) {

    }
}
