package Algorithm.leecode.test;

import java.util.*;

public class BaiduTest {
    /**
     *多数元素
     * 一个数组，其中一个元素的重复个数超过长度的一半，找出来
     */
    public int majorityElement(int [] nums) {
        Integer candidate = null;
        int count = 0;
        for(int num:nums) {
            if(count ==0) {
                candidate = num;
            }
            candidate +=(num==candidate)?1:-1;
        }
        return candidate;
    }


    /**
     * 环形链表1
     */
    public boolean hasCycle(ListNode head) {
        if(head == null || head.next == null) {
            return false;
        }
        ListNode fast = head;
        ListNode slow = head;
        while(fast != slow) {
            if(fast == null || fast.next == null) {
                return false;
            }
            slow = slow.next;
            fast = fast.next.next;
        }
        return true;
    }

    /**
     * 大小写转化
     * 将大写字母转换为小写字母
     */
    public String toLowerCase(String s) {
        char [] array = s.toCharArray();
        for(int i =0;i<array.length;i++) {
            if(array[i]>='A' && array[i]<='Z') {
                array[i] += 32;
            }
        }
        return new String(array);
    }

    /**
     * 字符串数组最长公共前缀
     */
    public String longestCommonPrexfix(String [] str) {
        if(str.length == 0) {
            return " ";
        }
        String s = str[0];
        for(String strs:str) {
            while(!strs.startsWith(s)) {
                if(s.length() == 0) {
                    return " ";
                }
                s = s.substring(0,s.length()-1);
            }
        }
        return s;
    }


    /**
     * 最长公共子序列
     */
    public int longestCommonSubsequence(String text1,String text2) {
        int m = text1.length();
        int n = text2.length();
        int [][]dp = new int[m+1][n+1];
        for(int i = 1;i<=m;i++) {
            char ch1 = text1.charAt(i-1);
            for(int j = 1;j<=n;j++) {
                char ch2 = text2.charAt(j-1);
                if(ch1 == ch2) {
                    dp[i][j] = dp[i-1][j-1]+1;
                } else {
                    dp[i][j] = Math.max(dp[i-1][j],dp[i][j-1]);
                }
            }
        }
        return dp[m][n];
    }

    /**
     * 股票的最大利润
     */
    public int maxProfit(int prices[]) {
        int minPrice = Integer.MAX_VALUE;
        int maxProfit = 0;
        for(int i = 0;i<prices.length;i++) {
            if(prices[i]<minPrice) {
                minPrice = prices[i];
            }
            if(prices[i]-minPrice>maxProfit) {
                maxProfit = prices[i]-minPrice;
            }
        }
        return maxProfit;
    }

    /**
     * 买卖股票的最佳时机II
     */
    public int maxProfit2(int [] prices) {
        int result = 0;
        int n = prices.length;
        for(int i = 1;i<n;i++) {
            result += Math.max(0,prices[i]-prices[i-1]);
        }
        return result;
    }
    /**
     * 快排
     */
    public static int [] sort(int [] nums) {
        quicksort(nums,0,nums.length-1);
        return nums;
    }

    public static void quicksort(int [] nums,int low,int high) {
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
        quicksort(nums,low,i-1);
        quicksort(nums,i+1,high);
    }

    /**
     * 翻转单链表
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
     *给定一个数组，找到里面出现次数最多的数字，如果次数相同，输出数字大的那个
     */
    public int getMax(int [] nums) {
        HashMap<Integer,Integer> map = new HashMap<>();
        for(int i=0;i<nums.length;i++) {
            if(map.containsKey(nums[i])) {
                map.put(nums[i],map.get(nums[i])+1);
            } else{
                map.put(nums[i],1);
            }
        }
        int maxNum = 0;
        int maxCount = 0;
        for(int num:map.keySet()) {
            if(map.get(num)>maxCount) {
                maxNum=num;
                maxCount = map.get(num);
            }else if(map.get(num) == maxCount) {
                if(num>maxNum) {
                    maxNum=num;
                    maxCount = map.get(num);
                }
            }
        }
        return maxNum;
    }

    /**
     * 缺失的数字
     */
    public int findMissNumber(int [] nums) {
        int n = nums.length;
        int result = 0;
        for(int i = 0;i<nums.length;i++) {
            result +=nums[i];
        }
        return n*(n+1)/2-result;
    }

    /**
     * 寻找数组中消失的数字
     */
    public List<Integer> findMissing(int [] nums) {
        List<Integer> result = new ArrayList<>();
        int n = nums.length;
        for(int num:nums) {
            int x = (num-1)%n;
            nums[x] += n;
        }

        for(int i = 0;i<nums.length;i++) {
            if(nums[i]<n) {
                result.add(i+1);
            }
        }
        return result;
    }

    /**
     * 无序数组中找出最长连续数字的个数
     */
    public int longestNumber(int [] nums) {
        HashSet<Integer> set = new HashSet<>();
        for(int i = 0;i<nums.length;i++) {
            set.add(nums[i]);
        }

        int maxLength = 0;
        for(int num:set) {
            if(!set.contains(num-1)) {
                int currentNum = num;
                int total = 1;
                while(set.contains(currentNum+1)) {
                    currentNum+=1;
                    total +=1;
                }
                maxLength = Math.max(maxLength,total);
            }
        }
        return maxLength;
    }

    /**
     * 两个栈实现队列
     */
    public class Solution{
        private Stack<Integer> stack1;
        private Stack<Integer> stack2;

        public void push(int value) {
            stack1.push(value);
        }

        public int pop() {
            if(stack2.isEmpty()) {
                while(!stack1.isEmpty()) {
                    stack2.push(stack1.pop());
                }
            }
            return stack2.pop();
        }
    }

    /**
     * 两数之和
     */
    public int[] twoSum(int [] nums,int target) {
        HashMap<Integer,Integer> map = new HashMap<>();
        for(int i = 0;i<nums.length;i++) {
            if(map.containsKey(target-nums[i])) {
                return new int[]{map.get(target-nums[i]),i};
            } else {
                map.put(nums[i],i);
            }
        }
        return new int[]{-1,-1};
    }

    /**
     * 删除链表中倒数第k个节点
     */
    public ListNode removeKthFromEnd(ListNode head,int k) {
        ListNode newHead = new ListNode(0);
        newHead.next = head;
        ListNode fast = newHead;
        ListNode slow = newHead;
        ListNode pre = null;

        for(int i = 0;i<k;i++) {
            fast = fast.next;
        }

        while(fast != null) {
            pre = slow;
            slow = slow.next;
            fast = fast.next;
        }

        pre.next = slow.next;
        slow.next = null;
        return newHead.next;
    }

    /**
     * 删除链表中重复的元素
     */
    public ListNode deleteDuplicates(ListNode head) {
        if(head == null || head.next == null) {
            return head;
        }
        ListNode cur = head;
        while(cur != null && cur.next != null) {
            if(cur.value == cur.next.value) {
                cur.next = cur.next.next;
            } else {
                cur = cur.next;
            }
        }
        return head;
    }

    /**
     * 删除链表中重复的节点II
     */
    public ListNode deleteDuplicates1(ListNode head) {
        if(head == null || head.next == null) {
            return head;
        }
        ListNode newHead = new ListNode(0);
        ListNode cur = head;
        while(cur.next != null && cur.next.next != null) {
            if(cur.next.value == cur.next.next.value) {
                int x = cur.next.value;
                while(cur.next != null && cur.next.value == x) {
                    cur.next = cur.next.next;
                }
            } else {
                cur = cur.next;
            }
        }
        return newHead.next;
    }

    /**
     * 无重复字符的最长子串
     */
    public int test(String s) {
        HashMap<Character,Integer> map = new HashMap<>();
        int i = -1;
        int maxLength = 0;
        for(int j=0;i<s.length();j++) {
            char ch = s.charAt(j);
            if(map.containsKey(ch)) {
                i = Math.max(i,map.get(ch));
            }
            maxLength = Math.max(maxLength,j-i);
        }
        return maxLength;
    }

    /**
     * 最长递增子序列
     */
    public int lengthOfLIS(int [] nums) {
        if(nums.length == 0) {
            return 0;
        }
        int maxLength = 0;
        int [] dp = new int[nums.length];
        dp[0] = 1;
        for(int i = 1;i<nums.length;i++) {
            for(int j = 0;j<i;j++) {
                if(nums[j]<nums[i]) {
                    dp[i] = Math.max(dp[i],dp[j]+1);
                }
            }
            maxLength = Math.max(dp[i],maxLength);
        }
        return maxLength;
    }

    /**
     * 输出数组中前k个最大的数
     */
    public static ArrayList<Integer> test(int [] nums, int k) {
        ArrayList<Integer> result = new ArrayList<>();
        PriorityQueue<Integer> queue = new PriorityQueue<>(k);
        for(int i = 0;i<k;i++) {
            queue.add(nums[i]);
        }
        for(int i = k;i<nums.length;i++) {
            if(nums[i]>queue.peek()) {
                queue.add(nums[i]);
                if(queue.size()>k) {
                    queue.poll();
                }
            }
        }
        while(!queue.isEmpty()) {
            result.add(queue.poll());
        }
        return result;
    }

    /**
     * 链表环的入口
     */
    public ListNode circle(ListNode head) {
        if(head == null || head.next == null) {
            return null;
        }
        ListNode fast = head;
        ListNode slow = head;
        while(fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
            if(fast == slow) {
                fast = head;
                while(fast != slow) {
                    slow = slow.next;
                    fast = fast.next;
                }
                return slow;
            }
        }
        return null;
    }

    /**
     * 找到数组中消失的数字
     */
    public ArrayList<Integer>  findDispareNumber(int [] nums) {
        ArrayList<Integer> result = new ArrayList<>();
        int n = nums.length;
        for(int i = 0;i<nums.length;i++) {
            int index = (nums[i]-1)%n;
            nums[index] +=n;
        }
        for(int i = 0;i<nums.length;i++) {
            if(nums[i]<=n) {
                result.add(i+1);
            }
        }
        return result;
    }


    /**
     * 有效的括号
     */
    public static boolean isValid(String s) {
        HashMap<Character,Character> map = new HashMap<>();
        map.put('}','{');
        map.put(']','[');
        map.put(')','(');

        Stack<Character> stack = new Stack<>();

        for(int i = 0;i<s.length();i++) {
            char ch = s.charAt(i);
            if(map.containsKey(ch)) {
                if(stack.isEmpty() || !map.get(ch).equals(stack.peek())) {
                    return false;
                } else {
                    stack.pop();
                }
            } else {
                stack.push(ch);
            }
        }
        return stack.isEmpty();
    }

    public static void main(String [] args) {
        String s = "{}[](*)";
        System.out.println(isValid(s));
    }


}
