package Algorithm.leecode.linkList;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

public class LinkListTest {

    public static void main(String [] args) {
    }

    //反转数组
    public static void reverseArray(int [] arr) {
        int left = 0;
        int right = arr.length-1;
        while(left<right) {
            int temp = arr[right];
            arr[right--] = arr[left];
            arr[left++] = temp;
        }
    }

    //反转链表
    public static ListNode reverse(ListNode head,ListNode tail,ListNode terminal) {
        ListNode cur = head;
        ListNode pre = null;
        ListNode next = null;

        while(cur != terminal) {
            next = cur.next;
            cur.next = pre;
            pre = cur;
            cur = next;
        }
        return tail;
    }

    //反转链表，前序遍历
    public static ListNode reverse1(ListNode head,ListNode pre) {
        if(head == null) {
            return pre;
        }
        ListNode next = head.next;
        head.next = pre;
        return reverse1(next,head);
    }

    //反转链表，后续遍历
    public static ListNode reverse2(ListNode head) {
        ListNode res = null;
        if(head == null || head.next == null) {
            return head;
        }
        res = reverse2(head.next);
        ListNode next = head.next;
        next.next = head;
        head.next = null;
        return res;
    }

    /**
     *  判断链表是否有环，并找出环的入口
     * 算法流程：
     * 双指针第一次相遇： 设两指针 fast，slow 指向链表头部 head，fast 每轮走 22 步，slow 每轮走 11 步；
     *
     * 第一种结果： fast 指针走过链表末端，说明链表无环，直接返回 null；
     *
     * TIPS: 若有环，两指针一定会相遇。因为每走 11 轮，fast 与 slow 的间距 +1+1，fast 终会追上 slow；
     * 第二种结果： 当fast == slow时， 两指针在环中 第一次相遇 。下面分析此时fast 与 slow走过的 步数关系 ：
     *
     * 设链表共有 a+ba+b 个节点，其中 链表头部到链表入口 有 aa 个节点（不计链表入口节点）， 链表环 有 bb 个节点（这里需要注意，aa 和 bb 是未知数，例如图解上链表 a=4a=4 , b=5b=5）；设两指针分别走了 ff，ss 步，则有：
     * fast 走的步数是slow步数的 22 倍，即 f = 2sf=2s；（解析： fast 每轮走 22 步）
     * fast 比 slow多走了 nn 个环的长度，即 f = s + nbf=s+nb；（ 解析： 双指针都走过 aa 步，然后在环内绕圈直到重合，重合时 fast 比 slow 多走 环的长度整数倍 ）；
     * 以上两式相减得：f = 2nbf=2nb，s = nbs=nb，即fast和slow 指针分别走了 2n2n，nn 个 环的周长 （注意： nn 是未知数，不同链表的情况不同）。
     * 目前情况分析：
     *
     * 如果让指针从链表头部一直向前走并统计步数k，那么所有 走到链表入口节点时的步数 是：k=a+nb（先走 aa 步到入口节点，之后每绕 11 圈环（ bb 步）都会再次到入口节点）。
     * 而目前，slow 指针走过的步数为 nbnb 步。因此，我们只要想办法让 slow 再走 aa 步停下来，就可以到环的入口。
     * 但是我们不知道 aa 的值，该怎么办？依然是使用双指针法。我们构建一个指针，此指针需要有以下性质：此指针和slow 一起向前走 a 步后，两者在入口节点重合。那么从哪里走到入口节点需要 aa 步？答案是链表头部head。
     * 双指针第二次相遇：
     *
     * slow指针 位置不变 ，将fast指针重新 指向链表头部节点 ；slow和fast同时每轮向前走 11 步；
     * TIPS：此时 f = 0f=0，s = nbs=nb ；
     * 当 fast 指针走到f = af=a 步时，slow 指针走到步s = a+nbs=a+nb，此时 两指针重合，并同时指向链表环入口 。
     * 返回slow指针指向的节点。
     *
     * 作者：jyd
     * 链接：https://leetcode-cn.com/problems/linked-list-cycle-ii/solution/linked-list-cycle-ii-kuai-man-zhi-zhen-shuang-zhi-/
     * 来源：力扣（LeetCode）
     * 著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
     */
    public static ListNode detectCycle(ListNode head) {
        ListNode fast = head;
        ListNode slow = head;
        while(fast != null&& fast.next != null) {
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

    /**
     * 合并两个有序链表
     */
    public static ListNode mergeTwoLists(ListNode l1,ListNode l2) {
        ListNode newHead = null;
        if(l1 == null) {
            return l2;
        }
        if(l2 == null) {
            return l1;
        }
        if(l1.val<l2.val) {
            newHead = l1;
            newHead.next = mergeTwoLists(l1.next,l2);
        } else if(l1.val>l2.val) {
            newHead = l2;
            newHead.next = mergeTwoLists(l1,l2.next);
        }
        return newHead;
    }



    /**
     * 存在一个按升序排列的链表，给你这个链表的头节点 head ，请你删除链表中所有存在数字重复情况的节点,只保留原始链表中没有重复出现的数字
     * 输入：head = [1,2,3,3,4,4,5]
     * 输出：[1,2,5]
     * @param head
     * @return
     */
    public static ListNode deleteDuplicates(ListNode head) {
        if(head == null || head.next == null) {
            return head;
        }
        ListNode tempNode = new ListNode();
        tempNode.next = head;
        ListNode cur = tempNode;
        while(cur.next != null && cur.next.next != null) {
            if(cur.next.val == cur.next.next.val) {
                int x = cur.next.val;
                while(cur.next != null && cur.next.val == x) {
                    cur.next = cur.next.next;
                }
            }
            else {
                cur = cur.next;
            }
        }
        return tempNode.next;
    }

    /**
     * 存在一个按升序排列的链表，给你这个链表的头节点 head ，请你删除所有重复的元素，使每个元素 只出现一次 。
     * 返回同样按升序排列的结果链表。
     * 输入：head = [1,1,2]
     * 输出：[1,2]
     */
    public static ListNode deleteDuplicate(ListNode head) {
        if(head ==null || head.next == null) {
            return head;
        }
        ListNode cur = head;
        while(cur != null && cur.next != null) {
            if(cur.val == cur.next.val) {
                cur.next = cur.next.next;
            } else {
                cur = cur.next;
            }
        }
        return head;
    }

    /**
     * 分隔链表
     * 给你一个链表的头节点 head 和一个特定值 x ，请你对链表进行分隔，使得所有 小于 x 的节点都出现在 大于或等于 x 的节点之前。
     * 你应当 保留 两个分区中每个节点的初始相对位置。
     *
     *
     *
     * 直观来说我们只需维护两个链表 \textit{small}small 和 \textit{large}large 即可，\textit{small}small 链表按顺序存储所有小于 xx 的节点，\textit{large}large 链表按顺序存储所有大于等于 xx 的节点。遍历完原链表后，我们只要将 \textit{small}small 链表尾节点指向 \textit{large}large 链表的头节点即能完成对链表的分隔。
     * 为了实现上述思路，我们设 \textit{smallHead}smallHead 和 \textit{largeHead}largeHead 分别为两个链表的哑节点，即它们的 \textit{next}next 指针指向链表的头节点，这样做的目的是为了更方便地处理头节点为空的边界条件。同时设 \textit{small}small 和 \textit{large}large 节点指向当前链表的末尾节点。开始时 \textit{smallHead}=\textit{small},\textit{largeHead}=\textit{large}smallHead=small,largeHead=large。随后，从前往后遍历链表，判断当前链表的节点值是否小于 xx，如果小于就将 \textit{small}small 的 \textit{next}next 指针指向该节点，否则将 \textit{large}large 的 \textit{next}next 指针指向该节点。
     * 遍历结束后，我们将 \textit{large}large 的 \textit{next}next 指针置空，这是因为当前节点复用的是原链表的节点，而其 \textit{next}next 指针可能指向一个小于 xx 的节点，我们需要切断这个引用。同时将 \textit{small}small 的 \textit{next}next 指针指向 \textit{largeHead}largeHead 的 \textit{next}next 指针指向的节点，即真正意义上的 \textit{large}large 链表的头节点。最后返回 \textit{smallHead}smallHead 的 \textit{next}next 指针即为我们要求的答案。
     */
    public static ListNode partition(ListNode head,int x) {
        ListNode small = new ListNode();
        ListNode smallHead = small;
        ListNode large = new ListNode();
        ListNode largeHead = large;
        while(head != null) {
            if(head.val<x) {
                small.next = head;
                small = small.next;
            } else {
                large.next = head;
                large = large.next;
            }
            head = head.next;
        }
        large.next = null;
        small.next = largeHead.next;
        return smallHead.next;

        /*ListNode small = new ListNode();
        ListNode large = new ListNode();
        ListNode smallHead = small;
        ListNode largeHead = large;
        while(head != null) {
            if(head.val <x) {
                small.next = head;
                small = small.next;
            } else {
                large.next = head;
                large = large.next;
            }
            head = head.next;
        }
        large.next = null;
        small.next = largeHead.next;
        return smallHead.next;*/
    }

    /**
     * 给你单链表的头指针 head 和两个整数 left 和 right ，其中 left <= right 。请你反转从位置 left 到位置 right 的链表节点，返回 反转后的链表
     *
     * 方法一思路：先将链表切断，将代反转的地方反转，然后将链表再接起来
     */
    public static ListNode reverseBetween(ListNode head,int left,int right) {
        ListNode tempHead = new ListNode();
        tempHead.next = head;

        ListNode pre = tempHead;
        for(int i = 0;i<left-1;i++) {
            pre = pre.next;
        }

        ListNode rightNode = pre;
        for(int i = 0;i<right-left+1;i++) {
            rightNode = rightNode.next;
        }

        ListNode leftNode = pre.next;
        ListNode curr = rightNode.next;

        pre.next = null;
        rightNode.next = null;

        reverseLinkedList(leftNode);

        pre.next = rightNode;
        leftNode.next = curr;

        return tempHead.next;
    }

    public static void reverseLinkedList(ListNode head) {
        ListNode pre = null;
        ListNode cur = head;
        while(cur != null) {
            ListNode next = cur.next;
            cur.next = pre;
            pre = cur;
            cur = next;
        }
    }

    /**
     * 方法2思路：头插法,使用3个指针实现
     * curr：指向待反转区域的第一个节点 left；
     * next：永远指向 curr 的下一个节点，循环过程中，curr 变化以后 next 会变化；
     * pre：永远指向待反转区域的第一个节点 left 的前一个节点，在循环过程中不变。
     *
     */
    public static ListNode reverseBetween2(ListNode head,int left,int right) {
        ListNode newHead = new ListNode();
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
     * 复制带随机指针的链表
     * 给你一个长度为 n 的链表，每个节点包含一个额外增加的随机指针 random ，该指针可以指向链表中的任何节点或空节点。
     * 构造这个链表的 深拷贝。 深拷贝应该正好由 n 个 全新 节点组成，其中每个新节点的值都设为其对应的原节点的值。新节点的 next 指针和 random 指针也都应指向复制链表中的新节点，并使原链表和复制链表中的这些指针能够表示相同的链表状态。复制链表中的指针都不应指向原链表中的节点 。
     */

    /**
     * 给定一个链表，判断链表中是否有环
     * 方法一：哈希表
     * 方法二：快慢指针
     */
    public static boolean hasCycle1(ListNode head) {
        Set<ListNode> nodeSet = new HashSet<>();
        while(head != null) {
            if(nodeSet.contains(head)) {
                return true;
            } else {
                nodeSet.add(head);
            }
            head = head.next;
        }
        return false;
    }

    /**
     * 方法二：快慢指针
     * 如果快慢指针能相遇，说明存在环
     * @param head
     * @return
     */
    public static boolean hasCycle(ListNode head) {
        if(head == null || head.next == null) {
            return false;
        }
        ListNode slow = head;
        ListNode fast = head.next;
        while(slow != fast) {
            if(fast == null || fast.next == null) {
                return false;
            } else {
                slow = slow.next;
                fast = fast.next.next;
            }
        }
        return true;
    }

    /**
     * 链表重排列
     * 给定一个单链表 L：L0→L1→…→Ln-1→Ln ，
     * 将其重新排列后变为： L0→Ln→L1→Ln-1→L2→Ln-2→…
     * 你不能只是单纯的改变节点内部的值，而是需要实际的进行节点交换
     * 方法一：线性表
     */
    public static void reorderList(ListNode head) {
        if(head == null) {
            return;
        }
        ArrayList<ListNode> list = new ArrayList<>();
        ListNode node = head;
        while(node != null) {
            list.add(node);
            node = node.next;
        }

        int i = 0;
        int j = list.size()-1;
        while(i<j) {
            list.get(i).next = list.get(j);
            i++;
            if(i == j) {
                break;
            }
            list.get(j).next = list.get(i);
            j--;
        }
        list.get(i).next = null;
    }

    /**
     * 方法二：将任务分成三步，第一步：找到原链表的中点，第二步：将原链表的右半段反转，第三步：将链表的两端合并
     */

    /**
     * 排序链表：给你链表的头结点 head ，请将其按 升序 排列并返回 排序后的链表
     *
     */
    public static ListNode sortList(ListNode head) {
        return head;
    }

    /**
     * 给你单链表的头节点 head ，请你反转链表，并返回反转后的链表
     * @param head
     * @return
     */
    public static ListNode reverseList(ListNode head) {
       if(head == null || head.next == null) {
           return head;
       }
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
     * 判断一个链表是否是回文链表
     * 方法一：将链表复制到数组中，然后遍历判断是否是回文链表
     * 缺点：需要额外的空间（数组）
     */
    public static boolean isPalindrome(ListNode head) {
        if(head == null || head.next == null) {
            return true;
        }
        ArrayList<Integer> list = new ArrayList<>();
        ListNode temp = head;
        while(temp != null) {
            list.add(temp.val);
            temp = temp.next;
        }
        int front = 0;
        int back = list.size()-1;
        while(front < back) {
            if(!list.get(front).equals(list.get(back))) {
                return false;
            }
            front++;
            back--;
        }
        return true;
    }

    /**
     * 判断是否是回文链表方法二：快慢指针
     *
     * 整个流程可以分为以下五个步骤：
     * 1、找到前半部分链表的尾节点。
     * 2、反转后半部分链表。
     * 3、判断是否回文。
     * 4、恢复链表。
     * 5、返回结果。
     */
    public static boolean isPalindrome2(ListNode head) {
        if(head == null || head.next == null) {
            return true;
        }
        //找到中间节点
        ListNode firstHalfEnd = findMidNode(head);
        //反转链表
        ListNode secondeHalfStart = reverseListNode(firstHalfEnd.next);


        ListNode p1 = head;
        ListNode p2 = secondeHalfStart;
        boolean result = true;
        while(result == true && p2 != null) {
            if(p1.val != p2.val) {
                result = false;
            }
            p1 = p1.next;
            p2 = p2.next;
        }
        firstHalfEnd.next = reverseListNode(secondeHalfStart);
        return result;
    }

    //找到链表中间节点
    public static ListNode findMidNode(ListNode head) {
        ListNode slow = head;
        ListNode fast = head;
        while(fast.next != null && fast.next.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }
        return slow;
    }

    public static ListNode reverseListNode(ListNode head) {
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


}
