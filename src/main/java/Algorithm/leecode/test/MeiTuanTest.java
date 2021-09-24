package Algorithm.leecode.test;

import java.util.*;

public class MeiTuanTest {
    /**
     * 找到出现次数大于一半的数字
     */
    public int majorityElement(int [] nums) {
        Integer candidate=null;
        int count = 0;
        for(int i = 1;i<nums.length;i++) {
            if(count == 0) {
                candidate = nums[i];
            } else {
                count +=nums[i]==candidate?1:-1;
            }
        }
        return candidate;
    }

    public void sql() {

        //查出每门课都大于90分的学生姓名(grade表，字段：姓名，科目，成绩)
        String sql = "select name from student group by name having min(grade)>90";

        //查询一个表中5到10行数据
        String sql2 = "select top 5 * from table where id not in(select top 5 id from table order by id) order by id";

        //查询一个表中各科成绩都不低于80分的学生姓名
        String sql3 = "select name from student group by name having min(grade)>=80";

        //数学成绩最差的三个人的姓名
        String sql4="select s.name from student s where id in(select top 3 from grade where cid in (select cid from course where name='数学')order by score asc)";

        //总成绩大于300的学生
        String sql5="select * from student group by id having sum(score)>300";

        //student表，有省份和学生名称，找出学生来自最多的省份
        String sql6 = "select to 1 province,count(id) as c from student group by province order by c";

        //以name创建一个索引
        String sql7 = "create index indexname on table(name)";

        //sql查找排名前三的学生姓名
        String sql8 = "select top 3 name from student group by id order by sum(score)";

        //给定三个表，班级表(class)，学生表(student)，班级学生表(sc)，查询某个班级学生成绩大于80的学生
        String sql9 = "select name from student where id in (select id from sc where cid in (select cid from class where name='classname') and score>80)";

        //找出平均分大于60分的学生ID和平均分
        String sql20="select id,avg(score) from student group by id having avg(score)>60";

        //两个表，学生表，成绩表，先按姓名排序，然后查找平均成绩
        String sql21 = "select * from student order by name";
        String sql22 = "select avg(score) from grade g inner join student s on s.id=g.id";

        //三个表，学生表，课程表，学生成绩表
        //查询平均成绩大于60分的学生信息和平均成绩
        String sql23 = "select s.*,avg(score) from student s inner join grade g on s.id = g.id and avg(score)>60";
        //查询语文成绩比数学成绩高的学生成绩和信息
        String s1l24 = "select s.*,tempMath.score as math,tempChinese.score as chinese from student s," +
                "(select * from sc where cid=(select id from course where name='math')) as tempChinese," +
                "(select * from sc where cid=(select id from course where name='chinese')) as tempChinese " +
                "where s.id=tempChinese.sid and tempChinese.sid=tempMath.sid and tempMath.score>tempChinese.score";

        //学生表（姓名，学号，性别，班级，年龄），分数表（姓名，学号，课程，分数）
        //查看学生中年龄排名前三的的学生名字
        String sql25 = "select name from student order by age desc limit 3";
        //查找课程大于80分的女生名字
        String sql26="select s.name from student s inner join grade g on s.id=g.id group by name having sex='f' and min(socre)>80";

        //查找数学成绩最差的三个学生姓名
        String sql27 = "select s.name from student where cname='math' and grade in (select grade from student where cname='math' order by grade asc limit 3";
    }

    public ListNode oddEvenList(ListNode head) {
        if(head == null || head.next == null) {
            return head;
        }

        ListNode oddHead = new ListNode(0);
        ListNode oddCur = oddHead;

        ListNode evenHead = new ListNode(0);
        ListNode evenCur = evenHead;
        ListNode cur = head;
        while(cur != null && cur.next != null) {
            oddCur.next = cur;
            evenCur.next = cur.next;
            oddCur = oddCur.next;
            evenCur = evenCur.next;
            cur = evenCur.next;
        }
        if(cur != null) {
            oddCur.next = cur;
            oddCur = oddCur.next;
        }
        evenCur.next = null;
        oddCur.next = evenHead.next;
        return oddHead.next;
    }

    /**
     * 3的幂
     * 给定一个整数，写一个函数来判断它是否是3的幂次方。如果是，返回 true ；否则，返回 false 。
     * 整数n是3的幂次方需满足：存在整数 x 使得 n == 3^x
     */
    public static boolean isPowerOfThree(int n) {
        if(n<3) {
            return false;
        }
        while(n%3 == 0) {
            n = n/3;
        }
        return n==1;
    }

    /**
     * 合并两个有序数组
     */
    public void mergeArray(int [] num1,int m,int [] num2,int n) {
        int index = num1.length;
        while(n>0) {
            if(m>0 && num1[m-1]>num2[n-1]){
                num1[--index] = num1[--m];
            } else {
                num1[--index] = num2[--n];
            }
        }
    }

    /**
     * 冒泡排序
     */
    public static void bubbleSort(int [] nums) {
        for (int i = 0; i < nums.length; i++) {
            for (int j = 0; j < nums.length - i - 1; j++) {
                if (nums[j] > nums[j + 1]) {
                    int temp = nums[j];
                    nums[j] = nums[j + 1];
                    nums[j + 1] = temp;
                }
            }
        }
    }

    /**
     * 快速排序
     */
    public int [] quickSort(int [] nums) {
        quick(nums,0,nums.length-1);
        return nums;
    }

    public void quick(int [] nums,int start,int end) {
        if(start>end) {
            return;
        }
        int temp = nums[start];
        int i = start;
        int j = end;
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
        quick(nums,start,i-1);
        quick(nums,i+1,end);
    }

    /**
     * 组合总数
     * 给定一个无重复元素的正整数数组 candidates 和一个正整数 target ，找出 candidates 中所有可以使数字和为目标数 target 的唯一组合。
     * candidates 中的数字可以无限制重复被选取。如果至少一个所选数字数量不同，则两种组合是唯一的。 
     * 对于给定的输入，保证和为 target 的唯一组合数少于 150 个
     */
    public static List<List<Integer>> combinationSum(int[] candidates, int target) {
        List<List<Integer>> result = new ArrayList<>();
        List<Integer> path = new ArrayList<>();
        dfs(candidates,0,target,result,path);
        return result;
    }

    public static void dfs(int [] candidates,int index,int target,List<List<Integer>> result,List<Integer> path) {
        if(target<0) {
            return;
        }
        if(target == 0) {
            result.add(new ArrayList(path));
            return ;
        }

        for(int i = index;i<candidates.length;i++) {
            if(candidates[i]<=target) {
                path.add(candidates[i]);
                dfs(candidates,i,target-candidates[i],result,path);
                path.remove(path.size()-1);
            }
        }
    }

    /**
     * 三个数的最大乘积
     * 给你一个整型数组 nums ，在数组中找出由三个数组成的最大乘积，并输出这个乘积。
     */
    public int maximumProduct(int [] nums) {
        int min1 = Integer.MAX_VALUE;
        int min2 = Integer.MAX_VALUE;
        int max1 = Integer.MIN_VALUE;
        int max2 = Integer.MIN_VALUE;
        int max3 = Integer.MIN_VALUE;
        for(int num:nums) {
            if(num<min1) {
                min2 = min1;
                min1 = num;
            } else if(num<min2) {
                min2 = num;
            }
            if(num>max1) {
                max3= max2;
                max2=max1;
                max1=num;
            } else if(num>max2) {
                max3=max2;
                max2=num;
            } else if(num>max3) {
                max3=num;
            }
        }
        return Math.max(min1*min2*max1,max1*max2*max3);
    }

    /**
     * 数组中和为target的下标对
     */
    public static int [] findSumTarget(int [] nums,int target) {
        HashMap<Integer,Integer> map = new HashMap<>();
        for(int i = 0;i<nums.length;i++) {
            int num = nums[i];
            if(map.containsKey(target-num)) {
                return new int[]{map.get(target-num),i};
            } else {
                map.put(num,i);
            }
        }
        return new int[]{-1,-1};
    }

    /**
     * 输入一个数，是否是2的n次方
     */
    public static boolean isTwo(int n) {
        if(n == 0) {
            return false;
        }
        while(n%2 == 0) {
            n = n/2;
        }
        return n==1;
    }

    /**
     * 找到数组中出现次数最多的数
     */
    public static int maxCountNumber(int [] nums) {
        HashMap<Integer,Integer> map = new HashMap<>();
        for(int num:nums) {
            if(map.containsKey(num)) {
                map.put(num,map.get(num)+1);
            } else {
                map.put(num,1);
            }
        }

        int maxNumber = 0;
        int maxCount = 0;
        for(int num:map.keySet()) {
            if(map.get(num)>maxCount) {
                maxCount = map.get(num);
                maxNumber = num;
            }
        }
        return maxNumber;
    }

    /**
     * 两数之和
     */
    public static int [] twoSum(int [] nums,int target) {
        HashMap<Integer,Integer> map = new HashMap<>();
        for(int i=0;i<nums.length;i++) {
            if(map.containsKey(target-nums[i])) {
                return new int[]{map.get(target-nums[i]),i};
            } else {
                map.put(nums[i],i);
            }
        }
        return new int[]{-1,-1};
    }

    /**
     * 字符串反转
     * 说明：原地反转，不能使用额外的空间
     */
    public void reverseString(char[] s) {
        int left = 0;
        int right = s.length-1;
        while(left<=right) {
            char temp = s[left];
            s[left] = s[right];
            s[right] = temp;
            left++;
            right--;
        }
    }

    /**
     * 最长不连续子串
     */

    /**
     * 最长连续序列
     * 给定一个未排序的整数数组nums，找出数字连续的最长序列（不要求序列元素在原数组中连续）的长度。
     * 请你设计并实现时间复杂度为 O(n) 的算法解决此问题。
     */
    public int longestConsecutive(int[] nums) {
        Set<Integer> set = new HashSet<>();
        for(int i = 0;i<nums.length;i++) {
            set.add(nums[i]);
        }

        int longestStreak = 0;
        for(int num:set) {
            if(!set.contains(num-1)) {
                int currentNum = num;
                int currentStreak = 1;
                while(set.contains(currentNum+1)) {
                    currentNum += 1;
                    currentStreak += 1;
                }
                longestStreak = Math.max(longestStreak,currentStreak);
            }
        }
        return longestStreak;
    }

    /**
     * 最长公共子串
     */
    public static int longestCommonSubsequence(String text1, String text2) {
        int m = text1.length();
        int n = text2.length();
        int [][] dp = new int[m+1][n+1];
        for(int i = 1;i<=m;i++) {
            char ch1 = text1.charAt(i-1);
            for(int j =1;j<=n;j++) {
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
     * 统计输入字符串大小写字母，数字，空格和其他字符的个数
     */
    public static void test() {
        Scanner sc = new Scanner(System.in);
        String s = sc.nextLine();
        char [] charArray = s.toCharArray();
        int number = 0;
        int lowercase = 0;
        int uppercase = 0;
        int space = 0;
        int other = 0;
        for(int i = 0;i<charArray.length;i++) {
            if(charArray[i]>'0' && charArray[i]<'9') {
                number++;
            } else if(charArray[i]>'A' && charArray[i]<'Z') {
                uppercase++;
            } else if(charArray[i]>'a' && charArray[i]<'z') {
                lowercase++;
            } else if(charArray[i] == ' ') {
                space++;
            } else {
                other++;
            }
        }
        System.out.println("the number of number is:"+number);
        System.out.println("the number of uppercase is :"+uppercase);
        System.out.println("the number of lowercase is :"+lowercase);
        System.out.printf("the number of space is :"+space);
        System.out.println("the number of other is:"+other);
    }

    /**
     * 一个数组先降序后升序，找出给定数值在数组中的索引
     */
    public int findTarget(int [] nums,int target) {
        return 0;
    }

    /**
     * 统计一个数字在排序数组中出现的次数
     */
    public int findNumber(int [] nums,int target) {
        int left = findFirst(nums,target);
        int right = findFirst(nums,target+1)-1;
        if(left<=right) {
            return right-left+1;
        }
        return -1;
    }

    public int findFirst(int [] nums,int target) {
        int left = 0;
        int right = nums.length-1;
        while(left<right) {
            int mid = (left+right)/2;
            if(nums[mid] == target) {
                right = mid-1;
            } else if(nums[mid]<target) {
                left = mid+1;
            } else if(nums[mid]>target) {
                right = mid-1;
            }
        }
        return left;
    }

    /**
     *验证一个数是否是回文数
     */
    public static boolean isPalindrome(int x) {
        String str = String.valueOf(x);
        StringBuffer sb = new StringBuffer(str);

        if(str.equals(sb.reverse().toString())) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * 方法二：将数字翻转，然后和原来的数字进行比对，如果相等，则是回文数，不相等则不是回文数
     */
    public static boolean isPalindrome1(int x) {
        if(x<0) {
            return false;
        }
        int cur = 0;
        int num = x;
        while(num!=0) {
            cur = cur*10+num%10;
            num /=10;
        }
        return cur==x;
    }

    /**
     * 有效的回文
     * 方法一：双指针
     */
    public boolean isPalindromeString(String s) {
        int left = 0;
        int right = s.length()-1;
        while(left<=right) {
            if(!Character.isLetterOrDigit(s.charAt(left))) {
                left++;
            } else if(!Character.isLetterOrDigit(s.charAt(right))) {
                right--;
            } else {
                char ch1 = Character.toLowerCase(s.charAt(left++));
                char ch2 = Character.toLowerCase(s.charAt(right--));
                if(ch1 != ch2) {
                    return false;
                }
            }
        }
        return true;
    }

    /**
     * 最长的不重复子串
     */
    public static int max(String s) {
        HashMap<Character,Integer> map = new HashMap<>();
        int i = -1;
        int maxLen= 0;
        for(int j = 0;j<s.length();j++) {
            char ch = s.charAt(j);
            if(map.containsKey(ch)) {
                i = Math.max(i,map.get(ch));
            }
            map.put(ch,j);
            maxLen = Math.max(maxLen,j-i);
        }
        return maxLen;
    }

    /**
     * java求n!
     * n!=n*(n-1)*(n-2).....*3*2*1;
     */
    public static int factorial(int x) {
        if(x<=1) {
            return x;
        } else {
            return x*factorial(x-1);
        }
    }

    /**
     * 整数反转
     */
    public int reverse(int x) {
        int result = 0;
        while(x!=0) {
            if(x>0 && result>(Integer.MAX_VALUE-x%10)/10) {
                return 0;
            }
            if(x<0 && result<(Integer.MIN_VALUE-x%10)/10) {
                return 0;
            }
            result = result*10+x%10;
            x /=10;
        }
        return result;
    }

    public static int reverse1(int x) {
        int result = 0;
        int pre = 0;
        while(x!=0) {
            pre = result;
            result = result*10+x%10;
            x /=10;
        }
        return result/10!=pre?0:result;
    }

    /**
     *  整数反转
     *  先用long接收结果，最后将long转化为int与result原本的值比对，如果溢出，返回0，没有溢出。怎返回result的值
     */
    public static int reverse2(int x) {
        long result = 0;
        while(x!=0) {
            result = result*10+x%10;
            x/=10;
        }
        return (int)result == result?(int)result:0;
    }

    /**
     * 数组中从未出现的最小正整数（牛客网）
     * 给定一个无序数组arr，找到数组中未出现的最小正整数
     * 例如arr = [-1, 2, 3, 4]。返回1
     * arr = [1, 2, 3, 4]。返回5
     * [要求]
     * 时间复杂度为O(n)O(n)，空间复杂度为O(1)O(1)
     * 前提：数组要是有序的
     *
     * 思路：temp用来记录下一步要校验的是否出现的正整数，如果哪个正整数没有出现，那么后续这个temp的值不会继续增加，最后返回的temp就是第一个没出现的正整数
     */
    public int minNumberdisappered(int [] arr) {
        int temp = 1;
        for(int i = 0;i<arr.length;i++) {
            if(arr[i] == temp) {
                temp++;
            }
        }
        return temp;
    }


    /**
     * 重排链表（牛客）
     * 思路：先用快慢指针找到链表的中间节点，然后将链表后把部分
     */
    public void reorderList(ListNode head) {
        if(head == null || head.next == null) {
            return;
        }
        ListNode fast = head;
        ListNode slow = head;
        while(fast.next != null && fast.next.next != null) {
            fast = fast.next.next;
            slow = slow.next;
        }
        ListNode second = slow.next;
        slow.next = null;
        ListNode pre = null;
        while(second != null) {
            ListNode next = second.next;
            second.next = pre;
            pre = second;
            second = next;
        }
        ListNode secondHead = pre;
        ListNode firstHead = head;
        while(firstHead != null && secondHead != null) {
            ListNode fnext = firstHead.next;
            ListNode snext = secondHead.next;
            firstHead.next = secondHead;
            secondHead.next = fnext;
            firstHead = fnext;
            secondHead = snext;
        }
    }

    /**
     * 未排序数组中累加和为给定值的最长子数组长度
     */
    public int maxlenEqualK (int[] arr, int k) {
        int[] sum = new int[arr.length];
        sum[0] = arr[0];
        for (int i = 1; i < sum.length; ++i) {
            sum[i] = sum[i - 1] + arr[i];
        }
        for (int s = arr.length - 1; s >= 1; --s) {
            for (int i = 0; i + s < arr.length; ++i) {
                if (sum[i + s] - sum[i] + arr[i] == k) {
                    return s + 1;
                }
            }
        }
        return 0;
    }

    /**
     * 方法二：空间换时间
     * len为出现和为k的子数组的最长长度
     * sum为从第一个元素到第当前元素的和
     * S[i]为从0到第i个元素的和，S[j]为从第0到第j个元素的和，那么从j+1到第i个元素的总和为S[i]-S[j]
     * 那么如果S[i]-S[j]=k，那么i-j的长度就是一个和为k的子数组的长度，len需要取len和i-j的最大值
     * map中的key为sum的值，value为sum值第一次出现的下标
     */
    public int maxlenEqualK1(int[] arr, int k) {
        // write code here
        if(arr == null || arr.length == 0) {
            return 0;
        }
        HashMap<Integer,Integer> map = new HashMap<>();
        map.put(0,-1);
        int len = 0;
        int sum = 0;
        for(int i = 0;i<arr.length;i++) {
            sum += arr[i];
            if(map.containsKey(sum-k)) {
                len = Math.max(len,i-map.get(sum-k));
            }
            if(!map.containsKey(sum)) {
                map.put(sum,i);
            }
        }
        return len;
    }


    /**
     * 字符串中第二大的数字
     */
    public int secondHighest(String s) {
        int first = -1;
        int second = -1;
        char [] strs = s.toCharArray();
        for(char str:strs) {
            if(Character.isDigit(str)) {
                int num = str-'0';
                if(num>first) {
                    second = first;
                    first = num;
                } else if(num>second && num<first) {
                    second = num;
                }
            }
        }
        return second;
    }

    /**
     * 数组中第二个的数字
     */
    public int test(int [] nums) {
        int first = -1;
        int second = -1;
        for(int num:nums) {
            if(num>first) {
                second = first;
                first = num;
            } else if(num>second) {
                second = num;
            }
        }
        return second;
    }

    /**
     * 加起来和为目标值的组合（牛客）
     */
    public ArrayList<ArrayList<Integer>> combinationSum2(int[] num, int target) {
        int len = num.length;
        ArrayList<ArrayList<Integer>> result = new ArrayList<>();
        if(len == 0) {
            return result;
        }
        Arrays.sort(num);
        ArrayList<Integer> path = new ArrayList<>(len);
        dfs(num,target,0,path,result);
        return result;
    }
    public void dfs(int [] num,int target,int begin,ArrayList<Integer> path,ArrayList<ArrayList<Integer>> result) {
        if(target == 0) {
            result.add(new ArrayList<>(path));
            return;
        }
        for(int i = begin;i<num.length;i++) {
            if(target-num[i]<0) {
                break;
            }
            //同一层如果节点重复，保留第一个节点就可以了
            if(i>begin && num[i] == num[i-1]) {
                continue;
            }
            path.add(num[i]);
            //i+1，保证不会取到重复的元素
            dfs(num,target-num[i],i+1,path,result);
            //回溯
            path.remove(path.size()-1);
        }
    }

}
