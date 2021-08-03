package Algorithm.leecode.bytedance;

import java.util.Deque;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

public class StringTest {
    /**
     * 比较版本号
     * 给你两个版本号 version1 和 version2 ，请你比较它们。
     * <p>
     * 版本号由一个或多个修订号组成，各修订号由一个 '.' 连接。每个修订号由 多位数字 组成，可能包含 前导零 。每个版本号至少包含一个字符。修订号从左到右编号，下标从 0 开始，最左边的修订号下标为 0 ，下一个修订号下标为 1 ，以此类推。例如，2.5.33 和 0.1 都是有效的版本号。
     * <p>
     * 比较版本号时，请按从左到右的顺序依次比较它们的修订号。比较修订号时，只需比较 忽略任何前导零后的整数值 。也就是说，修订号 1 和修订号 001 相等 。如果版本号没有指定某个下标处的修订号，则该修订号视为 0 。例如，版本 1.0 小于版本 1.1 ，因为它们下标为 0 的修订号相同，而下标为 1 的修订号分别为 0 和 1 ，0 < 1 。
     * <p>
     * 返回规则如下：
     * <p>
     * 如果 version1 > version2 返回 1，
     * 如果 version1 < version2 返回 -1，
     * 除此之外返回 0。
     */
    public int compareVersion(String version1, String version2) {
        int result = 0;
        return result;
    }

    /**
     * 简化路径
     * 给你一个字符串 path ，表示指向某一文件或目录的 Unix 风格 绝对路径 （以 '/' 开头），请你将其转化为更加简洁的规范路径。
     *
     * 在 Unix 风格的文件系统中，一个点（.）表示当前目录本身；此外，两个点 （..） 表示将目录切换到上一级（指向父目录）；两者都可以是复杂
     * 相对路径的组成部分。任意多个连续的斜杠（即，'//'）都被视为单个斜杠 '/' 。 对于此问题，任何其他格式的点（例如，'...'）均被视为文件/目录名称。
     *
     * 请注意，返回的 规范路径 必须遵循下述格式：
     * 始终以斜杠 '/' 开头。
     * 两个目录名之间必须只有一个斜杠 '/' 。
     * 最后一个目录名（如果存在）不能 以 '/' 结尾。
     * 此外，路径仅包含从根目录到目标文件或目录的路径上的目录（即，不含 '.' 或 '..'）。
     * 返回简化后得到的 规范路径 。
     *
     * 思路：使用双端队列
     */
    public static String simplifyPath(String path) {
        String[] pathArray = path.split("/");
        Deque<String> queue = new LinkedList<>();
        for (int i = 0; i < pathArray.length; i++) {
            String str = pathArray[i];
            if (str.equals(".") || str.equals("")) {
                continue;
            } else if (str.equals("..")) {
                queue.pollLast();
            } else {
                queue.offer(str);
            }
        }
        StringBuilder result = new StringBuilder("/");
        while (!queue.isEmpty()) {
            result.append(queue.poll());
            if (!queue.isEmpty()) {
                result.append("/");
            }
        }
        return result.toString();
    }

    /**
     * 无重复字符的最长子串
     * 请从字符串中找出一个最长的不包含重复字符的子字符串，计算该最长子字符串的长度。
     * 思路:双指针+哈希表
     *
     * 哈希表dic统计： 指针j遍历字符s，哈希表统计字符s[j]最后一次出现的索引 。
     * 更新左指针i： 根据上轮左指针i 和dic[s[j]]，每轮更新左边界i,保证区间[i+1,j]内无重复字符且最大。
     * i=max(dic[s[j]],i)
     */
    public static int lengthOfLongestSubString(String s) {
        Map<Character,Integer> map = new HashMap<>();
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
    * 方法二：滑动窗口,窗口内是不重复的子串，如果加入右边元素后不满足要求，则将左边窗口向右收缩，若加入右边元素后满足要求，则继续将
    * 右边窗口向右扩张
    **/
    public static int lengthOfLongestSubStrings(String s) {
        int n = s.length();
        if(n <= 1) {
            return n;
        }
        
        int left = 0;
        int right = 0;
        int maxLength = 1;
        Set<Character> set = new HashSet<>();
        
        while(right < n) {
            while(set.contains(s.charAt(right))) {
                set.remove(s.charAt(left));
                left++; 
            }
            maxLength = Math.max(maxLength,right-left+1);
            set.add(s.charAt(right));
            right++;
        }
        return maxLength;
    }

    /**
     * 最长公共子串
     * 方法：动态规划
     *
     * 假设字符串text1和text2的长度分别为m和n，创建m+1 行n+1 列的二维数组dp，其中dp[i][j]表示text1[0:i]和text2[0:j] 的最长公共子序列的长度。
     * 当i=0时，text1[0:i] 为空，空字符串和任何字符串的最长公共子序列的长度都是0，因此对任意0≤j≤n，有dp[0][j]=0；
     *当j=0 时，text2[0:j] 为空，同理可得，对任意0≤i≤m，有dp[i][0]=0。
     * 当text1[i−1]=text2[j−1] 时，dp[i][j]=dp[i−1][j−1]+1
     * 当text1[i−1]！=text2[j−1] 时，dp[i][j]=Math.max(dp[i-1][j],dp[i][j-1])
     */
    public static int longestCommonSubSequence(String text1,String text2) {
        int m = text1.length();
        int n = text2.length();
        int [][] dp = new int[m+1][n+1];

        for(int i = 1;i<=m;i++) {
            char c1 = text1.charAt(i-1);
            for(int j = 1;j<=n;j++) {
                char c2 = text2.charAt(j-1);
               if(c1 == c2) {
                   dp[i][j] = dp[i-1][j-1]+1;
               } else {
                   dp[i][j] = Math.max(dp[i-1][j],dp[i][j-1]);
               }
            }
        }
        return dp[m][n];
    }

    /**
     * 大数加法（牛客）
     * 以字符串的形式读入两个数字，编写一个函数计算它们的和，以字符串形式返回。
     * （字符串长度不大于100000，保证字符串仅由'0'~'9'这10种字符组成）
     */
    public static String bigNumber(String s,String t) {
        StringBuffer str = new StringBuffer();
        int i = s.length()-1;
        int j = t.length()-1;

        int result = 0;
        int carry = 0;
        while(i>=0 || j >= 0) {
            int x = i<0?0:s.charAt(i--)-'0';
            int y = j<0?0:t.charAt(j--)-'0';
            result = x+y+carry;
            carry = result/10;
            str.insert(0,result%10);
        }
        if(carry >0) {
            str.insert(0,carry);
        }
        return str.toString();
    }

}
