package designPattern.interpreter;

public class TerminalExpression extends AbstractExpression {
    @Override
    public void interpret(Context1 context) {
        System.out.println("终端解释器");
    }
}
