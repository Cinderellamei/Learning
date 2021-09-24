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

    /**
     * 数组中重复的元素
     */
    public int repeat(int [] nums) {
        HashMap<Integer,Integer> map = new HashMap<>();
        for(int num:nums){
            if(map.containsKey(num)) {
                return num;
            } else {
                map.put(num,1);
            }
        }
        return -1;
    }

    /**
     * 子序列的最大值
     */
    public int maxSum(int [] nums) {
        int result =0;
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
     * 最长无重复子串
     */
    public int maxLenght(String s) {
        HashMap<Character,Integer> map = new HashMap<>();
        int i = -1;
        int result = 0;
        for(int j = 0;j<s.length();j++) {
            char ch = s.charAt(j);
            if(map.containsKey(ch)) {
                i = Math.max(i,map.get(ch));
            }
            map.put(ch,j);
            result = Math.max(result,j-i);
        }
        return result;
    }

    /**
     * 比较版本号
     */
    public int compareVersion(String version1, String version2) {
        String [] arr1 = version1.split("\\.");
        String [] arr2 = version2.split("\\.");

        int len1 = arr1.length;
        int len2 = arr2.length;

        for(int n = 0;n<Math.max(len1,len2);n++) {
            int x = n<len1?Integer.parseInt(arr1[n]):0;
            int y = n<len2?Integer.parseInt(arr2[n]):0;
            if(x !=y) {
                return x>y?1:-1;
            }
        }
        return 0;
    }

    /**
     * 两个数组的交集
     * 方法：两个set
     */
    public int [] intersection(int [] nums1,int [] nums2) {
        HashSet<Integer> set1 = new HashSet<>();
        HashSet<Integer> set2 = new HashSet<>();
        for(int num1:nums1) {
            set1.add(num1);
        }

        for(int num2:nums2) {
            if(set1.contains(num2)) {
                set2.add(num2);
            }
        }

        int [] result = new int[set2.size()];
        int i = 0;
        for(int number :set2) {
            result[i++] = number;
        }
        return result;
    }

    /**
     * 两个数组的交集II)
     */
    public static int [] intersect(int [] nums1,int [] nums2) {
        HashMap<Integer,Integer> map = new HashMap<>();
        for(int num:nums1) {
            int count =map.getOrDefault(num,0)+1;
            map.put(num,count);
        }

        int index = 0;
        int [] result = new int[nums1.length];
        for(int num:nums2) {
            int count = map.getOrDefault(num,0);
            if(count>0) {
                result[index++] = num;
                count--;
                if(count>0) {
                    map.put(num,count);
                } else {
                    map.remove(num);
                }
            }
        }
        return Arrays.copyOfRange(result,0,index);
    }

    /**
     * 汉明距离
     */
    public int hanmingDistance(int x,int y) {
        int s = x^y;
        int result = 0;
        while(s!=0) {
            result += s&1;
            s>>=1;
        }
        return result;
    }

    /**
     * 输出所有回文子串
     */
    public static ArrayList<String> allPalindrome(String s) {
        ArrayList<String> result = new ArrayList<>();
        //子串长度从2到整个字符串的长度
        for(int len  =2;len<=s.length();len++) {
            //遍历所有长度为len的字符串，从第一个字符开始
            for(int i = 0;i<=s.length()-len;i++) {
                String subStr = s.substring(i,i+len);
                if(isPalindrome(subStr)) {
                    result.add(subStr);
                }
            }
        }
        return result;
    }

    public static boolean isPalindrome(String str) {
        if(str == null) {
            return false;
        }
        int i = 0;
        int j = str.length()-1;
        while(i<j) {
            if(str.charAt(i)!=str.charAt(j)) {
                return false;
            }
            i++;
            j--;
        }
        return true;
    }

    /**
     * 回文子串的个数
     */
    public int countSubstrings(String s) {
        int result1 = 0;
        int result2 = 0;
        for(int i = 0;i<s.length();i++) {
            result1 += isPalindrome(s,i,i);
            result2 += isPalindrome(s,i,i+1);
        }
        return result1+result2;
    }

    public int isPalindrome(String s,int left,int right) {
        int count = 0;
        while(left>=0 && right<s.length() && s.charAt(left) == s.charAt(right)) {
            count++;
            left--;
            right++;
        }
        return count;
    }

    /**
     * 返回前k个高频字符
     */
    public List<String> topKFrequent(String[] words, int k) {
        HashMap<String,Integer> map = new HashMap<>();
        for(String word:words) {
            if(map.containsKey(word)) {
                map.put(word,map.get(word)+1);
            } else {
                map.put(word,1);
            }
        }

        PriorityQueue<String> queue = new PriorityQueue<>(new Comparator<String>() {
            @Override
            public int compare(String s1,String s2) {
                if(map.get(s1).equals(map.get(s2))) {
                    return s2.compareTo(s1);
                } else {
                    return map.get(s1)-map.get(s2);
                }
            }
        });
        for(String key:map.keySet()) {
            queue.add(key);
            if(queue.size()>k) {
                queue.poll();
            }
        }
        List<String> result = new ArrayList<>();
        while(!queue.isEmpty()) {
            result.add(queue.poll());
        }
        Collections.reverse(result);
        return result;
    }

    /**
     * 字符串出现次数的topK问题
     */
    public String[][] topKstrings (String[] strings, int k) {
        // write code here
        HashMap<String,Integer> map = new HashMap<>();
        for(String str:strings) {
            if(map.containsKey(str)) {
                map.put(str,map.get(str)+1);
            } else {
                map.put(str,1);
            }
        }
        PriorityQueue<String> queue = new PriorityQueue<>(new Comparator<String>() {
            @Override
            public int compare(String s1,String s2) {
                if(map.get(s1).equals(map.get(s2))) {
                    return s2.compareTo(s1);
                } else {
                    return map.get(s1)-map.get(s2);
                }
            }
        });
        for(String key:map.keySet()) {
            queue.add(key);
            if(queue.size()>k) {
                queue.poll();
            }
        }
        String [][] result = new String[k][2];
        int index = k-1;
        while(!queue.isEmpty()) {
            String s = queue.poll();
            result[index][0] = s;
            result[index][1] = map.get(s)+"";
            index--;
        }
        return result;
    }


}
