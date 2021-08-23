package Algorithm.leecode.stack;

import java.util.LinkedList;
import java.util.Queue;

/**
 * 一个队列实现栈
 */
public class MyStack2 {
    Queue<Integer> stack;

    public MyStack2() {
        stack = new LinkedList<>();
    }

    public void push(int x) {
        int n = stack.size();
        stack.add(x);
        for(int i = 0;i<n;i++) {
            stack.add(stack.poll());
        }
    }

    public int pop() {
        return stack.poll();
    }

    public int top() {
        return stack.peek();
    }

    public boolean empty() {
        return stack.isEmpty();
    }
}
