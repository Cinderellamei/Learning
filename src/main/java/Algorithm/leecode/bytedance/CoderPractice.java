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
            char ch = s.charAt(j);
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
     * 合并两个有序链表
     */
    public ListNode mergeTwoLists(ListNode l1,ListNode l2) {
        if(l1 == null) {
            return l1;
        }
        if(l2 == null) {
            return l1;
        }
        ListNode newHead = null;
        if(l1.val <=l2.val) {
            newHead = l1;
            newHead.next = mergeTwoLists(l1.next,l2);
        } else if(l1.val >l2.val){
            newHead = l2;
            newHead.next = mergeTwoLists(l1,l2.next);
        }
        return newHead;
    }

    /**
     * 环形链表
     *
     * 给定一个链表，判断链表中是否有环。
     */


    /**
    * 买卖股票的最佳时机
    * 给定一个数组 prices ，它的第 i 个元素 prices[i] 表示一支给定股票第 i 天的价格。
    * 你只能选择 某一天 买入这只股票，并选择在 未来的某一个不同的日子 卖出该股票。设计一个算法来计算你所能获取的最大利润。
    * 返回你可以从这笔交易中获取的最大利润。如果你不能获取任何利润，返回 0 。
    **/
    public int maxProfit(int [] prices) {
        int maxProfit = 0;
        int minPrices = prices[0];
        for(int i = 1;i<prices.length;i++) {
            if(prices[i] < minPrices) {
                maxProfit = prices[i];
            }
            if(prices[i]-minPrices > maxProfit) {
                maxProfit = prices[i]-minPrices;
            }
        }
        return maxProfit;
    }

    /**
     * 二叉树的层次遍历
     *
     * 给你一个二叉树，请你返回其按 层序遍历 得到的节点值。 （即逐层地，从左到右访问所有节点
     */
    public List<List<Integer>> levelOrder(TreeNode root) {
        List<List<Integer>> result = new ArrayList<>();
        Queue<TreeNode> queue = new LinkedList<>();
        if(root == null) {
            return result;
        }
        queue.add(root);
        while(!queue.isEmpty()) {
            ArrayList<Integer> path = new ArrayList<>();
            int size = queue.size();
            for(int i = 0;i<size;i++) {
                TreeNode node = queue.poll();
                path.add(node.val);
                if(node.left != null) {
                    queue.add(node.left);
                }
                if(node.right != null) {
                    queue.add(node.right);
                }
            }
            result.add(path);
        }
        return result;
    }

    /**
     * 二叉树的锯型层次遍历
     */
    public List<List<Integer>> zigzagLevelOrder(TreeNode root) {
        List<List<Integer>> result = new ArrayList<>();
        if(root == null) {
            return result;
        }
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        boolean isOrderLeft = true;
        while(!queue.isEmpty()) {
            Deque<Integer> path = new LinkedList<>();
            int size = queue.size();
            for(int i = 0;i<size;i++) {
                TreeNode temp = queue.poll();
                if(isOrderLeft) {
                    path.addLast(temp.val);
                } else {
                    path.addFirst(temp.val);
                }
                if(temp.left != null) {
                    queue.add(temp.left);
                }
                if(temp.right != null) {
                    queue.add(temp.right);
                }
            }
            result.add(new ArrayList<>(path));
            isOrderLeft = !isOrderLeft;
        }
        return  result;
    }

    /**
     * 字符串相加
     * 给定两个字符串形式的非负整数 num1 和num2 ，计算它们的和。
     */
    public String addStrings(String nums1,String nums2) {
        int i = nums1.length()-1;
        int j = nums2.length()-1;
        int carry = 0;
        StringBuffer sb = new StringBuffer();
        while(i>=0 || j>=0) {
            int x = i>=0?nums1.charAt(i)-'0':0;
            int y = j>=0?nums2.charAt(j)-'0':0;
            int sum = x+y+carry;
            i--;
            j--;
            carry = sum/10;
            sb.append(sum%10);
        }
        if(carry >1) {
            sb.append(carry);
        }
        sb.reverse();
        return sb.toString();
    }

    /**
     * 合并两个有序的数组
     *
     * 给你两个有序整数数组 nums1 和 nums2，请你将 nums2 合并到 nums1 中，使 nums1 成为一个有序数组
     * 初始化nums1和nums2的元素数量分别为m和n。你可以假设nums1的空间大小等于m+n，这样它就有足够的空间保存来自nums2的元素。
     */
    public void merge(int [] nums1,int m,int [] nums2,int n) {

    }

    /**
     *  二叉树的最近公共祖先
     */
    public TreeNode lowestCommonAncestor(TreeNode root,TreeNode p,TreeNode q) {
        if(root == null || root == p || root == q){
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
     * 有效的括号
     * 给定一个只包括 '('，')'，'{'，'}'，'['，']' 的字符串 s ，判断字符串是否有效。
     *
     * 有效字符串需满足：
     * 左括号必须用相同类型的右括号闭合。
     * 左括号必须以正确的顺序闭合
     */
    public boolean isValid(String s) {
        Map<Character,Character> map = new HashMap<>();
        map.put('}','{');
        map.put(']','[');
        map.put(')','(');
        Stack<Character> stack = new Stack<>();
        for(int i = 0;i<s.length();i++) {
            char ch = s.charAt(i);
            if(map.containsKey(ch)) {
                if(stack.isEmpty() || !stack.peek().equals(map.get(ch))) {
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
     * 环形链表II
     *
     * 给定一个链表，返回链表开始入环的第一个节点。 如果链表无环，则返回 null。
     */
    public ListNode detectCycle(ListNode head) {
        if(head == null || head.next == null) {
            return null;
        }
        ListNode fast = head;
        ListNode slow = head;
        while(fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
            if(slow == fast) {
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
     * 二分查找
     *
     * 给定一个 n 个元素有序的（升序）整型数组 nums 和一个目标值 target  ，写一个函数搜索 nums 中的 target，
     * 如果目标值存在返回下标，否则返回 -1。
     */
    public int search(int [] nums,int target) {
        int n = nums.length;
        if(nums[0]>target || nums[n-1]<target) {
            return -1;
        }
        int left = 0;
        int right = n-1;
        while(left <= right) {
            int mid = (left+right)/2;
            if(nums[mid] == target) {
                return mid;
            } else if(nums[mid]>target) {
                right = mid-1;
            } else if(nums[mid]<target) {
                left = mid+1;
            }
        }
        return -1;
    }

    /**
     * 搜索旋转排序矩阵
     * 给你旋转后的数组nums和一个整数target，如果nums中存在这个目标值target，则返回它的下标，否则返回 -1
     */
    public int searchs(int [] nums,int target) {
        int n = nums.length;
        if(n == 0) {
            return -1;
        }
        if(n == 1) {
            return nums[0] == target?0:-1;
        }

        int left = 0;
        int right = n-1;
        while(left <= right) {
            int mid = (left+right)/2;
            if(nums[mid] == target) {
                return mid;
            }
            if(nums[0]<nums[mid]) {
                if(nums[0]<=target && target<nums[mid]) {
                    right = mid-1;
                } else {
                    left = mid+1;
                }
            } else if(nums[mid]<nums[right]) {
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
     * 岛屿的数量
     */


    /**
     * 二叉树的中序遍历
     */

    /**
     * 多数元素
     */
    public int majorityElement(int [] nums) {
        Integer candidate = null;
        int count = 0;
        for(int num:nums) {
            if(count == 0) {
                candidate = num;
            }
            count += (num==candidate)?1:-1;
        }
        return candidate;
    }

    /**
     * 最长公共前缀
     *
     * 编写一个函数来查找字符串数组中的最长公共前缀。
     * 如果不存在公共前缀，返回空字符串 ""。
     */

    /**
     * 搜索二维矩阵
     */
    public boolean searchMatrix(int [][] matrix,int target) {
        int row = matrix.length;
        int col = matrix[0].length;
        int i = 0;
        int j = col-1;
        while(i<row && j>=0) {
            if(matrix[i][j] == target) {
                return true;
            } else if(matrix[i][j]<target) {
                i++;
            } else if(matrix[i][j]>target) {
                j--;
            }
        }
        return false;
    }

    /**
     * 合并区间
     *
     * 以数组 intervals 表示若干个区间的集合，其中单个区间为 intervals[i] = [starti, endi] 。请你合并所有重叠的区间，
     * 并返回一个不重叠的区间数组，该数组需恰好覆盖输入中的所有区间。
     */
    public int [][] merge(int [][] intervals) {
        if(intervals == null) {
            return new int[0][];
        }
        Arrays.sort(intervals,(v1,v2)->v1[0]-v2[0]);
        ArrayList<int []> merged = new ArrayList<>();
        for(int i = 0;i<intervals.length;i++) {
            int left = intervals[i][0];
            int right = intervals[i][1];
            if(merged.size() == 0 || merged.get(merged.size()-1)[1]<left) {
                merged.add(new int[]{left,right});
            } else {
                merged.get(merged.size()-1)[1] = Math.max(merged.get(merged.size()-1)[1],right);
            }
        }
        return merged.toArray(new int[merged.size()][2]);
    }

    /**
     * 最长公共子序列
     *
     * 给定两个字符串 text1 和 text2，返回这两个字符串的最长公共子序列的长度。如果不存在公共子序列 ，返回 0 。
     * 一个字符串的子序列是指这样一个新的字符串：它是由原字符串在不改变字符的相对顺序的情况下删除某些字符（也可以不删除任何字符）后组成
     * 的新字符串。
     */


    /**
     * 最长重复子数组
     *
     * 给两个整数数组 A 和 B ，返回两个数组中公共的、长度最长的子数组的长度。
     */

    /**
     * 翻转字符串里的单词
     *
     * 给你一个字符串 s ，逐个翻转字符串中的所有 单词 。
     * 单词 是由非空格字符组成的字符串。s 中使用至少一个空格将字符串中的 单词 分隔开。
     * 请你返回一个翻转 s 中单词顺序并用单个空格相连的字符串。
     */

    /**
     * 缺失的数字
     *
     * 给定一个包含 [0, n] 中 n 个数的数组 nums ，找出 [0, n] 这个范围内没有出现在数组中的那个数。
     */
    public int missingNumber(int [] nums) {
        int n = nums.length;
        int count = n*(n+1)/2;
        int result = 1;
        for(int i = 0;i<n;i++) {
            result += nums[i];
        }
        return count-result;
    }

    /**
     * 最长连续递增序列
     * 给定一个未经排序的整数数组，找到最长且 连续递增的子序列，并返回该序列的长度。
     */
    public int findLengthOfLCIS(int [] nums) {
        return 0;
    }

    /**
     * 最长连续序列
     *
     * 给定一个未排序的整数数组 nums ，找出数字连续的最长序列（不要求序列元素在原数组中连续）的长度。
     * 请你设计并实现时间复杂度为 O(n) 的算法解决此问题。
     */
    public int longestConsecutive(int [] nums) {
        return 0;
    }

}
