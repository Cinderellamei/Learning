package Algorithm.leecode.hot100;

public class LinkedListTest {
    public ListNode removeNthFromEnd(ListNode head,int n) {
        ListNode newHead = new ListNode(0);
        newHead.next = head;
        ListNode fast = newHead;
        ListNode slow = newHead;
        ListNode pre = null;

        for(int i = 0;i<n;i++) {
            fast = fast.next;
        }
        while(fast != null) {
            pre =slow;
            fast = fast.next;
            slow = slow.next;
        }
        pre.next = slow.next;
        slow.next = null;
        return newHead.next;
    }

    /**
     * 排序链表
     * 给你链表的头结点 head ，请将其按 升序 排列并返回 排序后的链表 。
     * 方法一：自顶向下归并排序
     * 1。找到链表的中点，以中点为分界，将链表拆分成两个子链表。寻找链表的中点可以使用快慢指针的做法，快指针每次移动2步，
     * 慢指针每次移动1步，当快指针到达链表末尾时，慢指针指向的链表节点即为链表的中点。
     * 2.对两个子链表分别排序。
     * 3.将两个排序后的子链表合并，得到完整的排序后的链表。可以使用「21. 合并两个有序链表」的做法，将两个有序的子链表进行合并。
     */
    public ListNode sortList(ListNode head) {
        if(head == null || head.next == null) {
            return head;
        }
        ListNode fast = head;
        ListNode slow = head;
        while(fast.next != null && fast.next.next != null) {
            fast = fast.next.next;
            slow = slow.next;
        }
        ListNode temp = slow.next;
        slow.next = null;
        ListNode left = sortList(head);
        ListNode right = sortList(temp);

        ListNode newHead = new ListNode(0);
        ListNode cur = newHead;
        while(left != null && right != null) {
            if(left.val<right.val) {
                cur.next = left;
                left = left.next;
            } else {
                cur.next = right;
                right = right.next;
            }
            cur = cur.next;
        }
        cur.next = left != null?left:right;
        return newHead.next;
    }

    /**
     * 环形链表二
     * 判断链表是否有环，如果有环，返回环的入口，如果没有环，返回null
     *
     * 思路：假设有环，且头节点到入口的长度为a,环的距离为b，使用快慢指针，快指针每次走两步，慢指针每次走一步，快慢指针相遇的时候，慢指针走过的路长度
     * 为s，快指针走过的路长度为f，则有f=2s，而快指针比慢指针多走的路为nb，则有f=s+nb，结合f=2s，得到s=nb，f=2nb，此时快指针和慢指针在环中一个位置
     * 相遇。假设一个指针从头节点开始走，则走a+nb步可以走到环的入口，而此时s已经走了nb步，则慢指针再走a步会到达环的入口，而从头节点到环的入口正好是a，
     * 所以此时让慢指针从头开始走，当快慢指针相遇的时候，两个指针所指的节点就是环的入口节点
     */
    public ListNode detectCycle(ListNode head) {
        ListNode fast = head;
        ListNode slow = head;
        while(fast != null && fast.next != null) {
            fast = fast.next.next;
            slow = slow.next;
            if(fast == slow) {
                fast = head;
                while(fast != slow) {
                    fast = fast.next;
                    slow = slow.next;
                }
                return slow;
            }
        }
        return null;
    }


}
