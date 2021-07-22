package Algorithm;

import java.util.Stack;

public class StackTest {
    public static void main(String [] args) {
        int [] pushA = {1,2,3,4,5};
        int [] popA = {4,5,3,2,1};
        System.out.println(isPopOrder(pushA,popA));
    }

    /*
    用两个栈实现队列
     */
    Stack<Integer> stack1 = new Stack<Integer>();
    Stack<Integer> stack2 = new Stack<Integer>();

    public void push(int node) {
        stack1.push(node);
    }

    public int pop() {
        while(stack2.isEmpty()) {
            while(!stack1.isEmpty()) {
                stack2.push(stack1.pop());
            }
        }
        return stack2.pop();
    }


    /*
    定义栈的数据结构，请在该类型中实现一个能够得到栈中所含最小元素的min函数（时间复杂度应为O（1））
    思路：用两个栈，stack用来存储所有元素，stackTemp来存储最小元素，每次数据入stack栈时，与stackTemp的数据做比较，将较小的入stackTemp栈，这样stackTemp的
    栈顶元素永远是最小的
     */
    Stack<Integer> stack = new Stack<Integer>();
    Stack<Integer> stackTemp = new Stack<Integer>();
    public void push1(int node) {
        stack.push(node);
        if(stackTemp.size() == 0 || node<stackTemp.size()) {
            stackTemp.push(node);
        } else {
            stackTemp.push(stackTemp.peek());
        }
    }
    public void pop1() {
        if(stack.size()>0 && stackTemp.size()>0) {
            stack.pop();
            stackTemp.pop();
        }
    }
    public int top() {
        return stack.peek();
    }
    public int min() {
        if(stack.size()>0 && stackTemp.size()>0) {
            return stackTemp.peek();
        }
        return -1;
    }

    /*
    输入两个整数序列，第一个序列表示栈的压入顺序，请判断第二个序列是否可能为该栈的弹出顺序。假设压入栈的所有数字均不相等。例如序列1,2,3,4,5是某栈的压入顺序，
    序列4,5,3,2,1是该压栈序列对应的一个弹出序列，但4,3,5,1,2就不可能是该压栈序列的弹出序列。（注意：这两个序列的长度是相等的）

    思路：
     */
    public static boolean isPopOrder(int [] pushA,int [] popA) {
        if(pushA.length == 0 || popA.length == 0 || pushA.length != popA.length) return false;
        Stack<Integer> stack = new Stack<Integer>();
        int i = 0;
        int j = 0;
        stack.push(pushA[i++]);
        while(j<popA.length) {
            while(stack.peek() != popA[j]) {
                if(i == pushA.length) {
                    return false;
                }
                stack.push(pushA[i++]);
            }
            stack.pop();
            j++;
        }
        return true;
    }
}
