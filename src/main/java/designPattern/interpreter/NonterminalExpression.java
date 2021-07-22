package designPattern.interpreter;

public class NonterminalExpression extends AbstractExpression {
    @Override
    public void interpret(Context1 context) {
        System.out.println("非终端解释器");
    }
}
