package designPattern.interpreter;

import java.util.ArrayList;
import java.util.List;

public class Client {
    public static void main(String [] args) {
        Context1 context = new Context1();
        List<AbstractExpression> list = new ArrayList<>();
        list.add(new TerminalExpression());
        list.add(new NonterminalExpression());
        list.add(new TerminalExpression());
        list.add(new NonterminalExpression());

        for(AbstractExpression exp :list) {
            exp.interpret(context);
        }
    }
}
