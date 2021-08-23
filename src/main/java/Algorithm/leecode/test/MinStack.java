package Algorithm.leecode.test;

import java.util.Stack;

public class MinStack {
    private Stack<Integer> stack;
    private Stack<Integer> minStack;

    public MinStack() {
        stack = new Stack<>();
        minStack = new Stack<>();
    }

    public void put(int value ) {
        stack.push(value);
        if(!minStack.isEmpty() || value<minStack.peek()) {
            minStack.push(value);
        } else {
            minStack.push(minStack.peek());
        }
    }

    public void pop() {
        stack.pop();
        minStack.pop();
    }

    public int getTop() {
        return stack.peek();
    }

    public int getMin() {
        return minStack.peek();
    }
}
