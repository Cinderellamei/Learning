package Algorithm;

import BasicClasses.RandomListNode;

import java.util.ArrayList;
import java.util.Stack;

public class ListNodeTest {
    /*
    给定一个链表，实现从尾到头打印连标
    思路：用一个栈存储链表，再输出
     */
    public static ArrayList<Integer> printListFromTailToHead(ListNode listNode) {
        ArrayList<Integer> arrayList = new ArrayList<Integer>();
        Stack<Integer> stack = new Stack<Integer>();
        if(listNode == null) return arrayList ;
        while(listNode != null) {
            stack.push(listNode.val);
            listNode = listNode.next ;
        }
        while(!stack.isEmpty()) {
            arrayList.add(stack.pop());
        }
        return arrayList ;
    }

    /*
    输入一个链表，输出该链表中倒数第k个结点
    思路：两个指针，一个指针先走K步，第二个指针再走，等第一个指针走到尾节点的时候，第二个指针所指的节点就是倒数第k个节点
     */
    public static ListNode findKthToTail(ListNode head,int k) {
        if(head == null || k<0) return null;
        ListNode pre = head;
        ListNode last = head;
        for(int i = 1;i<k;i++) {
            if(pre.next != null) pre = pre.next;
            if(pre.next == null) return null;
        }
        while(pre.next != null) {
            pre = pre.next;
            last = last.next;
        }
        return last;
    }

    /*
    输入一个链表，反转链表后，输出新链表的表头
    思路：
     */
    public static ListNode reverseList(ListNode head) {
        if(head == null) return null;
        ListNode pre = null;
        ListNode node = head;
        ListNode newHead = null;
        while(node != null) {
            ListNode next = node.next;
            if(next == null) {
                newHead = node;
            }
            node.next = pre;
            pre = node ;
            node = next;
        }
        return newHead;
    }

    /*
    输入两个单调递增的链表，输出两个链表合成后的链表，当然我们需要合成后的链表满足单调不减规则。
     */
    public static ListNode mergeList(ListNode list1,ListNode list2) {
        if(list1.next == null) return list2;
        if(list2.next == null) return list1;
        ListNode newHead = null;
        if(list1.val<=list2.val) {
            newHead = list1;
            newHead.next = mergeList(list1.next,list2);
        } else if(list1.val > list2.val) {
            newHead = list2;
            newHead.next = mergeList(list1,list2.next);
        }
        return newHead;
    }

    /*
    复杂链表的复制
    输入一个复杂链表（每个节点中有节点值，以及两个指针，一个指向下一个节点，另一个特殊指针random指向一个随机节点），请对此链表进行深拷贝，
    并返回拷贝后的头结点。（注意，输出结果中请不要返回参数中的节点引用，否则判题程序会直接返回空）
    深拷贝：拷贝对象的值，源对象的值发生变化时，拷贝对象的值不受影响
    浅拷贝：拷贝对象的地址，源对象的值发生变化时，拷贝对象的值也会发生变化
    思路：第一步：原链表中复制新结点，第二步：复制原节点的随机结点，第三步：将链表进行拆分，拆分为原链表和复制之后的链表
     */
    public RandomListNode cloneRandomListNode(RandomListNode pHead) {
        if(pHead == null) return null;
        RandomListNode currentNode = pHead;
        //复制结点
        while(currentNode != null) {
            RandomListNode cloneNode = new RandomListNode(currentNode.label);
            RandomListNode nextNode = currentNode.next;
            currentNode.next = cloneNode;
            cloneNode.next = nextNode;
            currentNode = nextNode;
        }
        currentNode = pHead;
        //复制结点的随机结点
        while(currentNode != null) {
            currentNode.next.random = currentNode.random == null ? null : currentNode.random.next;
            currentNode = currentNode.next.next;
        }
        //拆分链表
        currentNode = pHead;
        RandomListNode pCloneHead = pHead.next;
        while(currentNode != null) {
            RandomListNode cloneNode = currentNode.next;
            currentNode.next = cloneNode.next;
            cloneNode.next = cloneNode.next == null ? null : cloneNode.next.next;
            currentNode = currentNode.next;
        }
        return pCloneHead;
    }

    /*
    输入两个链表，找出他们的第一个公共结点
    思路：由于链表是后面部分是公共结点，因此先将两个链表存入两个栈，然后同时出栈，最有一个相同的结点就是两个链表的第一个公共结点
     */
    public static ListNode findFirstCommonNode(ListNode pHead1,ListNode pHead2) {
        if(pHead1 == null || pHead2 == null) return null;
        Stack<ListNode> stack1 = new Stack<ListNode>();
        Stack<ListNode> stack2 = new Stack<ListNode>();
        while(pHead1 != null) {
            stack1.push(pHead1);
            pHead1 = pHead1.next;
        }
        while(pHead2 != null) {
            stack2.push(pHead2);
            pHead2 = pHead2.next;
        }
        ListNode commonNode = null;
        while(!stack1.isEmpty() && !stack2.isEmpty() && stack1.peek() == stack2.peek()) {
            commonNode = stack1.pop();
            stack2.pop();
        }
        return commonNode;
    }

    /*
    统计一个数字在排序数组中出现的次数
     */
    public static int getNumberOfK(int [] arr,int k) {
        int count = 0;
        for(int i = 0;i<arr.length;i++) {
            if(arr[i] == k) {
                count ++;
            }
        }
        return count;
    }

    public int GetNumberOfK(int [] array , int k) {
        if(array.length == 0) return 0;
        int first = getFirst(array,k,0,array.length-1);
        int last = getLast(array,k,0,array.length-1);
        if(first>-1 && last>-1) {
            return last-first+1;
        } else {
            return 0;
        }
    }
    public int getFirst(int[] array,int k,int start,int end) {
        while(start<=end) {
            int mid = (start+end)/2;
            if(array[mid] == k) {
                if(mid == 0 || array[mid-1]!=k) {
                    return mid;
                } else {
                    end = mid-1;
                }
            }else if(array[mid]>k) {
                end = mid-1;
            } else if(array[mid]<k) {
                start = mid+1;
            }
        }
        return -1;
    }
    public int getLast(int[] array,int k,int start,int end) {
        while(start<=end) {
            int mid = (start+end)/2;
            if(array[mid] == k) {
                if(mid == array.length-1 || array[mid+1]!=k) {
                    return mid;
                } else {
                    start = mid+1;
                }
            } else if(array[mid]>k) {
                end = mid-1;
            } else if(array[mid]<k) {
                start = mid+1;
            }
        }
        return -1;
    }

    /*
    * 判断一个链表是不是回文链表
    * 方法一：先通过快慢指针找到链表中间节点，然后将后半边链表反转，然后比较前半部分链表和后半部分链表元素是否一致
     */
    public boolean isPail (ListNode head) {
        // write code here
        if(head == null || head.next == null) {
            return true;
        }
        ListNode slow = head;
        ListNode fast = head;
        while(fast != null && fast.next != null) {
            fast = fast.next.next;
            slow = slow.next;
        }
        //如果fast不为空，说明链表长度是奇数
        if(fast != null) {
            slow = slow.next;
        }
        slow = reverse(slow);
        fast = head;
        while(slow != null) {
            if(fast.val != slow.val) {
                return false;
            }
            slow = slow.next;
            fast = fast.next;
        }
        return true;
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
    * 方法二：用栈存储链表，然后将将链表元素与出栈元素比较，如果不想等，返回false
    **/
    public boolean isPail1(ListNode head) {
        if(head == null || head.next == null) {
            return true;
        }
        Stack<ListNode> stack = new Stack<>();
        ListNode index = head;
        while(index != null) {
            stack.add(index);
            index = index.next;
        }
        index = head;
        while(index != null) {
            if(index.val != stack.pop().val) {
                return false;
            }
            index = index.next;
        }
        return true;
    }

    /**
     * 对链表进行插入排序
     * 插入排序的动画演示如上。从第一个元素开始，该链表可以被认为已经部分排序（用黑色表示）。
     * 每次迭代时，从输入数据中移除一个元素（用红色表示），并原地将其插入到已排好序的链表中
     *
     * 思路：
     * 1.首先判断给定的链表是否为空，若为空，则不需要进行排序，直接返回。
     * 2.创建哑节点 dummyHead，令 dummyHead.next = head。引入哑节点是为了便于在 head 节点之前插入节点。
     * 3.维护 lastSorted 为链表的已排序部分的最后一个节点，初始时 lastSorted = head。
     * 4.维护 curr 为待插入的元素，初始时 curr = head.next。
     * 5.比较 lastSorted 和 curr 的节点值。
     *     若 lastSorted.val <= curr.val，说明 curr 应该位于 lastSorted 之后，将 lastSorted 后移一位，curr 变成新的 lastSorted。
     *     否则，从链表的头节点开始往后遍历链表中的节点，寻找插入 curr 的位置。令 prev 为插入 curr 的位置的前一个节点，进行如下操作，完成对 curr 的插入：
     * lastSorted.next = curr.next
     * curr.next = prev.next
     * prev.next = curr
     *
     * 6.令 curr = lastSorted.next，此时 curr 为下一个待插入的元素。
     * 7.重复第 5 步和第 6 步，直到 curr 变成空，排序结束。
     * 8.返回 dummyHead.next，为排序后的链表的头节点。
     *
     **/
    public ListNode insertionSortList(ListNode head) {
        if(head == null) {
            return head;
        }
        ListNode newHead = new ListNode(0);
        newHead.next = head;
        ListNode lastSorted = head;
        ListNode currentNode = head.next;
        while(currentNode != null) {
            if(lastSorted.val <= currentNode.val) {
                lastSorted = lastSorted.next;
            } else {
                ListNode pre = newHead;
                while(pre.next.val<currentNode.val) {
                    pre = pre.next;
                }
                lastSorted.next = currentNode.next;
                currentNode.next = pre.next;
                pre.next = currentNode;
            }
            currentNode = lastSorted.next;
        }
        return newHead.next;
    }


}
