package Algorithm.leecode.bytedance;

import java.util.HashSet;
import java.util.Random;

public class LinkedListTest {
    /**
     * 给你两个非空的链表，表示两个非负的整数。它们每位数字都是按照 逆序 的方式存储的，并且每个节点只能存储 一位 数字。
     * 请你将两个数相加，并以相同形式返回一个表示和的链表。你可以假设除了数字 0 之外，这两个数都不会以 0 开头
     *
     *思路：将两个链表看成是相同长度的进行遍历，如果一个链表较短则在前面补 00，比如 987 + 23 = 987 + 023 = 1010
     * 每一位计算的同时需要考虑上一位的进位问题，而当前位计算结束后同样需要更新进位值
     * 如果两个链表全部遍历完毕后，进位值为 11，则在新链表最前方添加节点 11
     * 小技巧：对于链表问题，返回结果为头结点时，通常需要先初始化一个预先指针 pre，该指针的下一个节点指向真正的头结点head。使用预先指针的目的在于链表初始化时无可用节点值，而且链表构造过程需要指针移动，进而会导致头指针丢失，无法返回结果。
     */
    public ListNode addTwoNumbers(ListNode l1,ListNode l2) {
        ListNode pre = new ListNode(0);
        ListNode cur = pre;
        int carry = 0;
        while(l1 != null || l2!=null) {
            int x = l1==null?0:l1.val;
            int y = l2 == null?0:l2.val;
            int sum = x+y+carry;

            carry = sum/10;
            sum = sum%10;
            cur.next = new ListNode(sum);

            cur = cur.next;
            if(l1 != null) {
                l1 = l1.next;
            }
            if(l2 != null) {
                l2 = l2.next;
            }
            if(carry == 1) {
                cur.next = new ListNode(carry);
            }
        }
        return pre.next;
    }

    /**
     * 判断链表中是否有环
     * 思路：快慢指针，链表如果有环，快慢指针会相遇
     */
    public boolean hasCycle(ListNode head) {
        if(head == null || head.next == null) {
            return false;
        }
        ListNode slow = head;
        ListNode fast = head.next;
        while(fast != slow) {
            if(fast == null || fast.next == null) {
                return false;
            }
            fast = fast.next.next;
            slow = slow.next;
        }
        return true;
    }

    /**
     * k个一组翻转单链表
     *
     * 给你一个链表，每 k 个节点一组进行翻转，请你返回翻转后的链表。
     *
     * k 是一个正整数，它的值小于或等于链表的长度。
     *
     * 如果节点总数不是 k 的整数倍，那么请将最后剩余的节点保持原有顺序。
     *
     * 方法一：
     * 思路：链表分区为已翻转部分+待翻转部分+未翻转部分
     * 每次翻转前，要确定翻转链表的范围，这个必须通过 k 次循环来确定
     * 需记录翻转链表前驱和后继，方便翻转完成后把已翻转部分和未翻转部分连接起来
     * 初始需要两个变量 pre 和 end，pre 代表待翻转链表的前驱，end 代表待翻转链表的末尾
     * 经过k此循环，end 到达末尾，记录待翻转链表的后继 next = end.next
     * 翻转链表，然后将三部分链表连接起来，然后重置 pre 和 end 指针，然后进入下一次循环
     * 特殊情况，当翻转部分长度不足 k 时，在定位 end 完成后，end==null，已经到达末尾，说明题目已完成，直接返回即可
     * 时间复杂度为O(n∗K) 最好的情况为O(n) 最差的情况为 O(n^2)
     *
     * 空间复杂度为 O(1) 除了几个必须的节点指针外，我们并没有占用其他空间
     * 时间复杂度较高
     *
     */
    public ListNode reverseKGroup(ListNode head, int k) {
        ListNode tempHead = new ListNode(0);
        tempHead.next = head;

        ListNode pre = tempHead;
        ListNode end = tempHead;

        while(end != null) {
            for(int i = 0;i<k && end != null;i++) {
                end = end.next;
            }
            if(end == null) {
                break;
            }
            ListNode start = pre.next;
            ListNode next = end.next;
            end.next = null;
            pre.next = reverse(start);
            start.next = next;

            pre = start;
            end = start;
        }
        return tempHead.next;
    }

    public ListNode reverse(ListNode head) {
        ListNode pre = null;
        ListNode cur = head;
        while(cur != null) {
            ListNode next = cur.next;
            cur.next = pre;
            pre = cur;
            cur = next;
        }
        return pre;
    }

    /**
     * k个一组翻转链表
     * 方法二：头插法
     */
    public ListNode reverseKGroup1(ListNode head,int k) {
        if(head == null || head.next == null || k == 1) {
            return head;
        }
        ListNode newHead = new ListNode(0);
        newHead.next = head;
        int length = 0;
        //pre是每一小段的头节点，负责连接
        ListNode pre = newHead;
        ListNode cur = head;
        ListNode next = null;

        //计算链表长度
        while(head != null) {
            length++;
            head = head.next;
        }

        //分段使用头插法将链表翻转
        //k个一组翻转需要重复length/k次
        for(int i = 0;i<length/k;i++) {
            for(int j = 1;j<k;j++) {
                //头插法
                next = cur.next;
                cur.next = next.next;
                //next.next = cur是错的，next要衔接的是子序列的头节点，不是前一个节点
                next.next = pre.next;
                pre.next = next;
            }
            //每个子序列翻转完后，pre和cur需要更新到下一个序列
            pre = cur;
            cur = cur.next;
        }
        return newHead.next;
    }



    /**
     * 相交链表
     * 给你两个单链表的头节点 headA 和 headB ，请你找出并返回两个单链表相交的起始节点。如果两个链表没有交点，返回 null 。
     *
     * 双指针法
     * 只有当链表 headA和headB都不为空时，两个链表才可能相交。因此首先判断链表headA 和headB 是否为空，如果其中至少有一个链表为空，则两个链表一定不相交，返回null。
     *
     * 当链表headA和headB 都不为空时，创建两个指针pA 和pB，初始时分别指向两个链表的头节点headA 和 headB，然后将两个指针依次遍历两个链表的每个节点。具体做法如下：
     *
     * 每步操作需要同时更新指针pA和pB；
     *
     * 如果指针pA不为空，则将指针pA 移到下一个节点；如果指针pB不为空，则将指针pB 移到下一个节点。
     *
     * 如果指针pA为空，则将指针pA 移到链表headB 的头节点；如果指针pB 为空，则将指针pB 移到链表}headA 的头节点。
     *
     * 当指针pA和pB 指向同一个节点或者都为空时，返回它们指向的节点或者null。
     *
     */
    public ListNode getIntersectionNode(ListNode headA,ListNode headB) {
        if(headA == null || headB == null) {
            return null;
        }
        ListNode pa = headA;
        ListNode pb = headB;
        while(pa != pb) {
            pa = pa == null?headB:pa.next;
            pb = pb == null?headA:pb.next;
        }
        return pa;
    }

    /**
     * 方法二，hashSet
     */
    public ListNode getIntersectionNode1(ListNode headA,ListNode headB) {
        HashSet<ListNode> set = new HashSet<>();
        ListNode temp = headA;
        while(temp != null) {
            set.add(temp);
            temp = temp.next;
        }
        temp = headB;
        while(temp != null) {
            if(set.contains(temp)) {
                return temp;
            }
            temp = temp.next;
        }
        return null;
    }


    /**
     * 链表中倒数第k个节点
     * 双指针
     */
    public ListNode getKthFromEnd(ListNode head,int k) {
        ListNode fast = head;
        ListNode slow = head;
        for(int i = 1;i<=k;i++) {
            fast = fast.next;
        }
        while(fast != null) {
            fast = fast.next;
            slow = slow.next;
        }
        return slow;
    }

    /**
     * 翻转链表的一部分
     *
     * 头插法，首先将指针指到left前一个节点，然后挨个使用头插法的方式将要翻转的节点插入左边不动节点后面，链表就被部分反转了
     */
    public ListNode reverseBetween(ListNode head,int left,int right) {
        ListNode newHead = new ListNode(0);
        newHead.next = head;
        ListNode pre = newHead;
        for(int i = 0;i<left-1;i++) {
            pre = pre.next;
        }
        ListNode cur = pre.next;
        ListNode next = null;
        for(int i = 0;i<right-left;i++) {
            next = cur.next;
            cur.next = next.next;
            next.next = pre.next;
            pre.next = next;
        }
        return newHead.next;
    }

    /**
     * 链表随机节点
     * 给定一个单链表，随机选择链表的一个节点，并返回相应的节点值。保证每个节点被选的概率一样。
     *
     * 进阶:
     * 如果链表十分大且长度未知，如何解决这个问题？你能否使用常数级空间复杂度实现？
     */
    class RandomNode{
        ListNode head;
        Random random;
        public RandomNode(ListNode head) {
            this.head = head;
            this.random = new Random();
        }

        public int getRandom() {
            int reserve = 0;
            ListNode current = head;
            int count = 0;
            while(current != null) {
                count++;
                int r = this.random.nextInt(count)+1;
                if(r == count) {
                    reserve = current.val;
                }
                current = current.next;
            }
            return reserve;
        }

    }





}
