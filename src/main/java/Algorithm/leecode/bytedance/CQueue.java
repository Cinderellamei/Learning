package Algorithm.leecode.bytedance;

import java.util.Stack;

/**
 * 用两个栈实现队列
 *
 * 1.stack1只负责进入
 * 2.stack2只负责取出
 * 3.只有stack2为空时才把stack1的所有元素倾倒进stack2中，这样顺序就不会乱了
 */
public class CQueue {
    Stack<Integer> stack1;
    Stack<Integer> stack2;

    public CQueue() {
        stack1 = new Stack<>();
        stack2 = new Stack<>();
    }

    public void appendTail(int value) {
        stack1.push(value);
    }

    public int deleteHead() {
        if(stack2.isEmpty()) {
            while(!stack1.isEmpty()) {
                stack2.push(stack1.pop());
            }
        }
        if(stack2.isEmpty()) {
            return -1;
        } else {
            int deleteItem = stack2.pop();
            return deleteItem;
        }
    }
}
