package Algorithm.leecode.offer;

import java.util.Stack;

public class LinkedListTest {
    /**
     * 从尾到头输出链表
     * 输入一个链表的头节点，从尾到头反过来返回每个节点的值（用数组返回）
     * 方法一：使用栈先进后出的特点去存储链表节点，然后输出
     * 时间复杂度和空间复杂度都是O(n)
     */
    public int [] reversePrint(ListNode head) {
        Stack<Integer> stack = new Stack<>();
        ListNode cur = head;
        while(cur != null) {
            stack.push(cur.val);
            cur = cur.next;
        }
        int [] result = new int[stack.size()];
        while(!stack.isEmpty()) {
            int size = 0;
            result[size++] = stack.pop();
        }
        return result;
    }


}
