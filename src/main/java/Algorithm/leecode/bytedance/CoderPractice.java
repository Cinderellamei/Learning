package Algorithm.leecode.bytedance;

import java.util.*;

public class CoderPractice {
    /**
     * 反转链表
     * 给你单链表的头节点 head ，请你反转链表，并返回反转后的链表。
     */
    public ListNode reverseList(ListNode head) {
        if(head == null || head.next == null) {
            return head;
        }
        ListNode cur = head;
        ListNode pre = null;
        while(cur != null) {
            ListNode next = cur.next;
            cur.next = pre;
            pre = cur;
            cur = next;
        }
        return pre;
    }

    /**
     * 反转链表II
     * 给你单链表的头指针 head 和两个整数 left 和 right ，其中 left <= right 。请你反转从位置 left 到位置 right 的链表节点，返回 反转后的链表
     */
    public ListNode reverseBetween(ListNode head,int left,int right) {
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
     * 无重复字符的最长子串
     * 给定一个字符串 s ，请你找出其中不含有重复字符的 最长子串 的长度。
     */
    public int lengthOfLongestSubstring(String s) {
        int n = s.length();
        if(n<=1) {
            return n;
        }
        HashMap<Character,Integer> map = new HashMap<>();
        int result = 0;
        int i = -1;
        for(int j = 0;j<n;j++) {
            char ch = s.charAt(i);
            if(map.containsKey(ch)) {
                i = Math.max(i,map.get(ch));
            } else {
                map.put(ch,j);
            }
            result = Math.max(result,j-i);
        }
        return result;
    }

    /**
     * 方法二
     */
    public int lengthOfLongestSubstring1(String s) {
        int n = s.length();
        if(n<=1) {
            return n;
        }
        Set<Character> set = new HashSet<>();
        int left = 0;
        int right = 1;
        int maxLength = 1;
        while(right<n) {
            if(set.contains(s.charAt(right))) {
                set.remove(left);
                left++;
            } else {
                maxLength = Math.max(maxLength,right-left+1);
                set.add(s.charAt(right));
                right++;
            }
        }
        return maxLength;
    }

    /**
     * 数组中第k个最大元素
     */
    public int findKthLargest(int [] nums,int k) {
        PriorityQueue<Integer> queue = new PriorityQueue<>(k);
        int n = nums.length;
        if(n<k) {
            return -1;
        }
        for(int i = 0;i<k;i++) {
            queue.add(nums[i]);
            if(queue.size()>k) {
                queue.poll();
            }
        }
        return queue.peek();
    }

    /**
     * k个一组翻转链表
     * 给你一个链表，每 k 个节点一组进行翻转，请你返回翻转后的链表。
     * 思路:链表长度为n，每k个翻转一次，需要n/k次翻转，k个元素需要k-1次头插
     */
    public ListNode reverseGroup(ListNode head,int k) {
        if(head == null ||head.next == null || k==1) {
            return head;
        }

        ListNode newHead = new ListNode();
        newHead.next = head;
        ListNode cur = head;
        ListNode pre = newHead;
        ListNode next = null;

        int length = 0;
        while(head != null) {
            length++;
            head = head.next;
        }

        for(int i = 0;i<length/k;i++) {
            for(int j = 1;j<k;j++){
                next = cur.next;
                cur.next = next.next;
                next.next = pre.next;
                pre.next = next;
            }
            pre = cur;
            cur = cur.next;
        }
        return newHead.next;
    }


    /**
     *
     * 手撕快速排序
     *
     * 给你一个整数数组 nums，请你将该数组升序排列。
     */
    public int[] sortArray(int [] nums) {
        quickSort(nums,0,nums.length-1);
        return nums;
    }

    public void quickSort(int [] nums,int low,int high) {
        if(low>high) {
            return ;
        }
        int i = low;
        int j = high;
        int temp = nums[low];
        while(i<j) {
            if(i<j && nums[j]>=temp) {
                j--;
            }
            nums[i] = nums[j];
            if(i<j && nums[i]<=temp) {
                i++;
            }
            nums[j] = nums[i];
        }
        nums[i] = temp;
        quickSort(nums,0,i-1);
        quickSort(nums,i+1,high);
    }

    /**
     * 两数之和
     *
     * 给定一个整数数组nums和一个整数目标值target，请你在该数组中找出和为目标值target的那两个整数，并返回它们的数组下标。
     * 你可以假设每种输入只会对应一个答案。但是，数组中同一个元素在答案里不能重复出现。
     * 你可以按任意顺序返回答案。
     */
    public int [] twoSum(int [] nums,int target) {
        HashMap<Integer,Integer> map = new HashMap<>();
        for(int i = 0;i<nums.length;i++) {
            if(map.containsKey(target-nums[i])) {
                return new int[]{map.get(target-nums[i]),i};
            } else {
                map.put(nums[i],i);
            }
        }
        return new int[0];
    }

    /**
     * 最大子序和
     *
     * 给定一个整数数组 nums ，找到一个具有最大和的连续子数组（子数组最少包含一个元素），返回其最大和
     */
    public int maxSubArray(int [] nums) {
        int result = nums[0];
        int total = 0;
        for(int i = 0;i<nums.length;i++) {
            if(total>0) {
                total += nums[i];
            } else {
                total = nums[i];
            }
            result = Math.max(result,total);
        }
        return result;
    }

    /**
     * 三数之和
     * 给你一个包含 n 个整数的数组 nums，判断 nums 中是否存在三个元素 a，b，c ，使得 a + b + c = 0 ？请你找出所有和为 0 且不重复的三元组。
     */
    public List<List<Integer>> threeSum(int [] nums) {
        List<List<Integer>> result = new ArrayList<>();
        int n = nums.length;
        if(nums == null || n<3) {
            return result;
        }
        Arrays.sort(nums);
        for(int i = 0;i<nums.length;i++) {
            if(nums[i] >= 0) {
                break;
            }
            if(i>0 && nums[i] == nums[i-1]) {
                continue;
            }
            int left = i+1;
            int right = nums.length-1;
            while(left<right) {
                int sum = nums[i]+nums[left]+nums[right];
                if(sum == 0) {
                    ArrayList<Integer> list = new ArrayList<>();
                    list.add(nums[i]);
                    list.add(nums[left]);
                    list.add(nums[right]);
                    result.add(list);
                    while(left<right && nums[left] == nums[left+1]) {
                        left++;
                    }
                    while(left<right && nums[right] == nums[right-1]) {
                        right--;
                    }
                    left++;
                    right--;
                } else if(sum >0) {
                    right--;
                } else if(sum<0) {
                    left++;
                }
            }
        }
        return result;
    }

    /**
    * 相交链表
    * 给你两个单链表的头节点 headA 和 headB ，请你找出并返回两个单链表相交的起始节点。如果两个链表没有交点，返回 null 
    **/
    public ListNode getIntersectionNode(ListNode headA,ListNode headB) {
        if(headA == null || headB == null) {
            return null;
        }
        ListNode pA = headA;
        ListNode pB = headB;
        while(pA != pB) {
            pA = pA == null?headB:pA.next;
            pB = pB ==null?headA:pB.next;
        }
        return pA;
    }

    /**
    * 买卖股票的最佳时机
    * 给定一个数组 prices ，它的第 i 个元素 prices[i] 表示一支给定股票第 i 天的价格。
    * 你只能选择 某一天 买入这只股票，并选择在 未来的某一个不同的日子 卖出该股票。设计一个算法来计算你所能获取的最大利润。
    * 返回你可以从这笔交易中获取的最大利润。如果你不能获取任何利润，返回 0 。
    **/
    public int maxProfit(int [] prices) {
        int maxProfit = 0;
        int minPricecs = prices[0];
        for(int i = 1;i<prices.length;i++) {
            if(prices[i] < minProfit) {
                maxProfit = prices[i];
            }
            if(prices[i]-minPrices > maxProfit) {
                maxProfit = prices[i]-minPrices;
            }
        }
        return maxProfit;
    } 
}
