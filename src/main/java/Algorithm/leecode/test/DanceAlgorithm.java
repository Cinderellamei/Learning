package Algorithm.leecode.test;

import java.util.*;

public class DanceAlgorithm {
    /**
     * 无重复字符的最长子串
     */
    public int lengthOfLongestSubString(String str) {
        int n = str.length();
        if(n<=1) {
            return n;
        }
        Set<Character> set = new HashSet<>();
        int left =0;
        int right = 0;
        int maxLength = 1;
        while(right < n) {
            char ch = str.charAt(right);
            while(set.contains(ch)) {
                set.remove(str.charAt(left));
                left++;
            }
            maxLength = Math.max(maxLength,right-left+1);
            set.add(str.charAt(right));
            right++;
        }
        return maxLength;
    }

    /**
     * 最长公共前缀
     */
    public String longestCommonPrefix(String [] strs) {
        if(strs.length == 0) {
            return " ";
        }
        String s = strs[0];
        for(String str:strs) {
            while(!str.startsWith(s)) {
                if(s.length() == 0) {
                    return " ";
                }
                s = s.substring(0,s.length()-1);
            }
        }
        return s;
    }

    /**
     * 字符串的排列
     */
    public boolean checkInclusion(String str1,String str2) {
        return false;
    }

    /**
     * 字符串相乘
     */
    public String multiply(String num1,String num2) {
        if("0".equals(num1) || "0".equals(num2)) {
            return "0";
        }
        int m = num1.length();
        int n = num2.length();
        int [] result = new int[m+n];
        for(int i = m-1;i>=0;i--) {
            int x = num1.charAt(i)-'0';
            for(int j = n-1;j>=0;j--) {
                int y = num2.charAt(j)-'0';
                int sum = result[i+j+1]+x*y;
                result[i+j+1] = sum%10;
                result[i+j] += sum/10;
            }
        }
        StringBuilder sb = new StringBuilder();
        for(int i = 0;i<result.length;i++) {
            if(i == 0 && result[i] == 0) {
                continue;
            }
            sb.append(result[i]);
        }
        return sb.toString();
    }

    /**
     * 反转字符串中的单词
     */
    public static String reverse(String s) {
        StringBuilder sb = new StringBuilder();
        if(s.length() == 0 || s.trim().length() == 0) {
            return sb.toString();
        }
        String [] strs = s.split(" ");
        sb.append(strs[strs.length-1]);
        for(int i = strs.length-2;i>=0;i--) {
            if(strs[i].trim().length() >0) {
                sb.append(" ");
                sb.append(strs[i]);
            }
        }
        return sb.toString();
    }

    /**
     * 翻转字符串中的单词
     * 方法二：
     */
    public String reverse1(String s) {
        int length = s.length();
        Stack<String> stack = new Stack<>();
        StringBuilder word = new StringBuilder();

        for(int i = 0;i<length;i++) {
            if(s.charAt(i) != ' ') {
                word.append(s.charAt(i));
                if(i == length-1 || s.charAt(i) == ' ') {
                    stack.push(word.toString());
                    word.setLength(0);
                }
            }
        }

        StringBuilder result = new StringBuilder();
        while(!stack.isEmpty()) {
            result.append(stack.pop());
            if(!stack.isEmpty()) {
                result.append(' ');
            }
        }
        return result.toString();
    }

    /**
     * 简化路径
     */
    public String simplifyPaht(String path) {
        String [] arrayPath = path.split("/");
        Deque<String> queue = new LinkedList<>();
        for(int i = 0;i<arrayPath.length;i++) {
            String str = arrayPath[i];
            if(str.equals(".") || str.equals("")) {
                continue;
            } else if(str.equals("..")) {
                if(!queue.isEmpty()) {
                    //弹出队尾元素
                    queue.pollLast();
                }
            } else {
                //插入队头
                queue.offer(str);
            }
        }

        StringBuilder result = new StringBuilder("/");
        while(!queue.isEmpty()) {
            //弹出队头元素
            result.append(queue.poll());
            if(!queue.isEmpty()) {
                result.append("/");
            }
        }
        return result.toString();
    }

    /**
     * 三数之和
     */
    public List<List<Integer>> threeSum(int [] nums) {
        List<List<Integer>> result = new ArrayList<>();
        int length = nums.length;
        if(nums == null || length<3) {
            return result;
        }
        Arrays.sort(nums);
        for(int i = 0;i<length;i++) {
            if(nums[i]>0) {
                continue;
            }
            if(i>0 && nums[i] == nums[i-1]) {
                continue;
            }
            int left = i+1;
            int right = length-1;
            while(left<right) {
                int total = nums[i]+nums[left]+nums[right];
                if(total == 0) {
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
                } else if(total>0) {
                    right--;
                } else if(total<0) {
                    left++;
                }
            }
        }
        return result;
    }

    /**
     * 岛屿的最大面积(还需要看，不熟)
     */
    public int maxAreaOfIslands(int [][] grid) {
        int result = 0;
        for(int i = 0;i<grid.length;i++) {
            for(int j = 0;j<grid[i].length;j++) {
                if(grid[i][j] == 1) {
                    result = Math.max(result,dfs(i,j,grid));
                }
            }
        }
        return result;
    }

    public int dfs(int i,int j,int[][] grid) {
        if(i<0 || j<0 ||i>=grid.length ||j>=grid.length||grid[i][j] == 0) {
            return 0;
        }

        int num = 1;
        grid[i][j] = 0;
        num += dfs(i-1,j,grid);
        num += dfs(i,j-1,grid);
        num += dfs(i+1,j,grid);
        num += dfs(i,j+1,grid);
        return num;
    }

    /**
     * 搜索旋转排序数组
     */
    public int search(int [] nums,int target) {
        int length = nums.length;
        if(length == 0) {
            return -1;
        }
        if(length == 1) {
            return nums[0] == target?0:-1;
        }

        int left = 0;
        int right = length-1;
        while(left<=right) {
            int mid = (left+right)/2;
            if(nums[mid] == target) {
                return mid;
            }
            if(nums[0]<=nums[mid]) {
                if(nums[0]<=target && target<nums[mid]) {
                    right = mid-1;
                } else {
                    left = mid+1;
                }
            } else if(nums[mid]<=nums[right]) {
                if(nums[mid]<target && target<=nums[right]) {
                    left = mid+1;
                } else {
                    right = mid-1;
                }
            }
        }
        return -1;
    }

    /**
     * 最长递增子序列
     */
    public static int findLengthOfLCIS(int [] nums) {
        int length = nums.length;
        if(length == 0) {
            return 0;
        }
        int start = 0;
        int maxLength = 1;
        for(int i = 1;i<length;i++) {
            if(nums[i]<=nums[i-1]) {
                start = i;
            }
            maxLength = Math.max(maxLength,i-start+1);
        }
        return maxLength;
    }

    /**
     * 数组中的第k个最大元素
     */
    public int findKthLargest(int [] nums,int k) {
        for(int i = 0;i<k;i++) {
            for(int j = 0;j<=nums.length-i-1;j++) {
                if(nums[j]>nums[j+1]) {
                    int temp = nums[j];
                    nums[j] = nums[j+1];
                    nums[j+1] = temp;
                }
            }
        }
        return nums[nums.length-k];
    }

    /**
     * 方法二：优先队列
     */
    public int findKthLargest1(int [] nums,int k) {
        PriorityQueue<Integer> queue = new PriorityQueue<>(k);
        for(int i = 0;i<nums.length;i++) {
            queue.add(nums[i]);
            if(queue.size()>k) {
                queue.poll();
            }
        }
        return queue.peek();

    }

    /**
     * 最长的连续序列
     */
    public int longestConsecutive(int [] nums) {
        int length = nums.length;
        if(length == 0) {
            return 0;
        }
        Set<Integer> set = new HashSet<>();
        for(int i = 0;i<length;i++) {
            set.add(nums[i]);
        }
        int maxLength = 1;
        for(int i = 0;i<length;i++) {
            if(!set.contains(nums[i]-1)) {
                int currentNum = nums[i];
                int total = 1;
                while(set.contains(currentNum+1)) {
                    total +=1;
                    currentNum +=1;
                }
                maxLength = Math.max(maxLength,total);
            }
        }
        return maxLength;
    }

    /**
     * 合并区间
     */
    public static int [][] merge(int [][] intervals) {
        if(intervals == null) {
            return new int[0][];
        }
        Arrays.sort(intervals,(v1,v2)->v1[0]-v2[0]);
        ArrayList<int []> merged = new ArrayList<>();
        for(int i = 0;i<intervals.length;i++) {
            int left = intervals[i][0];
            int right = intervals[i][1];
            int size = merged.size()-1;
            if(size == 0 || merged.get(size)[1]<left) {
                merged.add(new int[]{left,right});
            } else {
                merged.get(size)[1] = Math.max(merged.get(size)[1],right);
            }
        }
        return merged.toArray(new int[merged.size()][]);
    }

    /**
     *合并两个有序链表
     */
    public ListNode mergeTwoLists(ListNode l1,ListNode l2) {
        if(l1 == null) {
            return l1;
        }
        if(l2 == null) {
            return l1;
        }
        ListNode newHead = null;
        if(l1.value<=l2.value) {
            newHead = l1;
            newHead.next = mergeTwoLists(l1.next,l1);
        } else {
            newHead=l2;
            newHead.next = mergeTwoLists(l1,l2.next);
        }
        return newHead;
    }

    /**
     * 反转链表
     */
    public ListNode reverseList(ListNode head) {
        if(head == null || head.next == null) {
            return head;
        }
        ListNode cur = head;
        ListNode pre = null;
        while(cur != null) {
            ListNode next = pre.next;
            cur.next = pre;
            pre = cur;
            cur = next;
        }
        return pre;
    }

    /**
     * 两数相加
     */
    public ListNode addTwoNumbers(ListNode l1,ListNode l2) {
        ListNode newHead = new ListNode(0);
        ListNode cur = newHead;

        int carry = 0;

        while(l1 != null || l2 != null) {
            int x = l1 == null?0:l1.value;
            int y = l2 == null?0:l2.value;
            int result = x+y+carry;
            carry = result/10;
            cur.next = new ListNode(result%10);
            cur = cur.next;
            if(l1 != null) {
                l1 = l1.next;
            }
            if(l2 != null) {
                l2 = l2.next;
            }
        }
        if(carry >0) {
            cur.next = new ListNode(carry);
        }
        return newHead.next;
    }

    /**
     * 排序链表(不熟悉，多写写)
     */
    public ListNode sortList(ListNode head) {
        if(head == null || head.next == null) {
            return head;
        }
        ListNode slow = head;
        ListNode fast = head;
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
            if(left.value<right.value) {
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
     * 环形链表II
     */
    public ListNode detectCycle(ListNode head) {
        ListNode slow = head;
        ListNode fast = head;
        while(fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
            if(slow == fast) {
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
     * 相交链表
     */
    public ListNode getIntesectionNode(ListNode headA,ListNode headB) {
        if(headA == null || headB == null) {
            return null;
        }
        ListNode p1 = headA;
        ListNode p2 = headB;
        while(p1 != p2) {
            p1 = p1 == null?headB:p1.next;
            p2 = p2 == null?headA:p2.next;
        }
        return p1;
    }

    /**
     * 二叉树的最近公共祖先
     */
    public TreeNode lowestCommonAncestor(TreeNode root,TreeNode p,TreeNode q) {
        if(root == null || root == p ||root == q) {
            return root;
        }
        TreeNode left = lowestCommonAncestor(root.left,p,q);
        TreeNode right = lowestCommonAncestor(root.right,p,q);
        if(left == null && right == null) {
            return null;
        }
        if(left == null) {
            return right;
        }
        if(right == null) {
            return left;
        }
        return root;
    }

    /**
     * 二叉树锯齿形层次遍历
     */
    public List<List<Integer>> zigzagLevelOrder(TreeNode root) {
        boolean isOrderLeft = true;
        Queue<Algorithm.leecode.test.TreeNode> queue = new LinkedList<>();
        List<List<Integer>> result = new ArrayList<>();
        if(root == null) {
            return result;
        }
        queue.add(root);
        while(!queue.isEmpty()) {
            int size = queue.size();
            Deque<Integer> path = new LinkedList<>();
            for(int i = 0;i<size;i++) {
                TreeNode node = queue.poll();
                if(isOrderLeft == true) {
                    path.addLast(node.value);
                }
                if(isOrderLeft == false) {
                    path.addFirst(node.value);
                }
                if(node.left != null) {
                    queue.add(node.left);
                }
                if(node.right != null) {
                    queue.add(node.right);
                }
            }
            //这里不要忘记了
            isOrderLeft =  !isOrderLeft;
            result.add(new ArrayList<>(path));
        }
        return result;
    }

    /**
     * 买卖股票的最佳时机
     */
    public int maxProfit(int [] prices) {
        int minPrices = 0;
        int maxProfit = 0;
        for(int i = 0;i<prices.length;i++) {
            if(prices[i]<minPrices) {
                minPrices = prices[i];
            }
            if(prices[i]-minPrices>maxProfit) {
                maxProfit = prices[i]-minPrices;
            }
        }
        return maxProfit;
    }

    /**
     * 买卖股票的最佳时机II
     */
    public int maxProfit1(int [] prices) {
        int maxProfit = 0;
        for(int i = 1;i<prices.length;i++) {
            maxProfit += Math.max(0,prices[i]-prices[i-1]);
        }
        return maxProfit;
    }

    /**
     * 最大正方形（不熟练，多做几次）
     */
    public int maximalSquare(char [][] matrix) {
        int maxSide = 0;
        int maxSquare = 0;
        int row = matrix.length;
        int col = matrix[0].length;
        int [][] dp = new int[row][col];

        for(int i = 0;i<row;i++) {
            for(int j = 0;j<col;j++) {
                if(matrix[i][j] == '1') {
                    if(i == 0 || j == 0) {
                        dp[i][j] = 1;
                    } else {
                        dp[i][j] = Math.min(Math.min(dp[i-1][j],dp[i][j-1]),dp[i-1][j-1]);
                    }
                    maxSide = Math.max(maxSide,dp[i][j]);
                }
            }
        }
        maxSquare = maxSide*maxSide;
        return maxSquare;
    }

    /**
     * 最大子序和
     */
    public int maxSubArray(int [] nums) {
        if(nums.length == 0) {
            return 0;
        }
        int total = 0;
        int result = nums[0];
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
     * 三角形最小路径和(不熟，可以多做几遍)
     */
    public int minimumTotal(List<List<Integer>> triangle) {
        int length = triangle.size();
        int [][] dp = new int[length+1][length+1];
        for(int i = length-1;i>=0;i--) {
            for(int j = 0;j<=i;j++) {
                dp[i][j] = Math.min(dp[i][j+1],dp[i+1][j])+triangle.get(i).get(j);
            }
        }
        return dp[0][0];
    }

    /**
     * x的平方根
     */
    public int mySqrt(int x) {
        if(x<=0) {
            return 0;
        }
        int result = -1;
        int left = 1;
        int right = x;
        while(left<=right) {
            int mid = (left+right)/1;
            if(mid<=x/mid && (mid+1)>x/(mid+1)) {
                result = mid;
                return result;
            } else if(mid<x/mid) {
                left = mid+1;
            } else if(mid>x/mid) {
                right = mid-1;
            }
        }
        return result;
    }

    /**
     *  查询第二高的薪水
     */
    public void salary() {
        String sql = "select ifnull((select distinct Salary from Employee order by salary desc limit 1,1),null) as SecondSalary";
    }
}
