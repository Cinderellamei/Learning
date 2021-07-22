package Algorithm;

import java.util.ArrayList;

public class JosephusSolve {
    /*
    约瑟夫环问题
    思路1：数组
    思路2：环形链表
    思路3：递归
     */

    //环形链表解法：时间复杂度O（mn），空间复杂度O（n）
    public static int solve(int n,int m) {
        if(m == 1 || n<2) {
            return n;
        }
        ListNode head = createLinkedList(n);
        int count = 0;
        ListNode cur = head;
        ListNode pre = null;
        while(head.next != head) {
            if(count == m-1) {
                count = 0;
                pre.next = cur.next;
                cur = pre.next;
            } else {
                count++;
                pre = cur;
                cur = cur.next;
            }
        }
        return head.val+1;
    }

    public static ListNode createLinkedList(int n) {
        ListNode head = new ListNode(0);
        ListNode next = head;
        for(int i = 1;i<n;i++) {
            ListNode temp = new ListNode(i);
            next.next = temp;
            next = temp;
        }
        next.next = head;
        return head;
    }

    //递归解法
    public static int solve1(int n,int m) {
        if(n <= 0 || m<=0) return -1;
        return n == 1?0:(solve1(n-1,m)+m)%n;
    }

    //用list模拟环形链表
    public static int LastRemaing(int n,int m) {
        if(n < 1 || m<1) return -1;
        ArrayList<Integer> list = new ArrayList<>();
        for(int i = 0;i<n;i++) {
            list.add(i);
        }
        int cur = -1;
        while(list.size()>1) {
            for(int i = 0;i<m;i++) {
                cur++;
                if(cur == list.size()) {
                    cur = 0;
                }
            }
            list.remove(cur);
            cur--;
        }
       return list.get(0);
    }

}
