package Algorithm.leecode.hot100;

import java.util.Stack;

public class MinStack1 {
    /**
     * 实现最小栈，方法二
     * 使用两个栈同时存数据，第一个栈存当前如栈的元素，第二个栈存当前最小的元素
     */
    Stack<Integer> stack ;
    Stack<Integer> minStack ;
    public MinStack1() {
        stack = new Stack<>();
        minStack = new Stack<>();
    }

    public void push(int val) {
        stack.push(val);
        //minStack.isEmpty容易忘记，当最小栈中没有元素的时候，需要把val放进去
        if(minStack.isEmpty() || val < minStack.peek()) {
            minStack.push(val);
        } else {
            minStack.push(minStack.peek());
        }
    }

    public void pop() {
        stack.pop();
        minStack.pop();
    }

    public int top() {
        return stack.peek();
    }

    public int getMin() {
        return minStack.peek();
    }
}
