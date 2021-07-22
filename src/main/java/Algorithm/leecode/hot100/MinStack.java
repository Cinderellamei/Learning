package Algorithm.leecode.hot100;

import java.util.Stack;

/**
 * 实现最小栈
 * 方法一：使用一个栈，栈内存数组，数组第一个元素存入栈的元素，第二个元素存当前最小的元素
 */
public class MinStack {
    private Stack<int []> stack = new Stack<>();
    public MinStack() {

    }

    public void push(int x) {
        if(stack.isEmpty()) {
            stack.push(new int []{x,x});
        } else {
            stack.push(new int[]{x,Math.min(x,stack.peek()[1])});
        }
    }

    public void pop() {
        stack.pop();
    }

    public int top() {
        return stack.peek()[0];
    }

    public int getMin() {
        return stack.peek()[1];
    }


}

