package Algorithm.leecode.stack;

import java.util.LinkedList;
import java.util.List;

/**
 * 链表实现栈
 */
public class MyStack1 {
    List<Integer> stack;

    public MyStack1() {
        stack = new LinkedList<>();
    }

    public void push(int x) {
        stack.add(x);
    }

    public int pop() {
        int result = stack.get(stack.size()-1);
        stack.remove(stack.size()-1);
        return result;
    }

    public int top() {
        return stack.get(stack.size()-1);
    }

    public boolean empty() {
        return stack.size()==0;
    }
}
