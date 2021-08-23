package Algorithm.leecode.hot100;

import java.util.*;
import java.util.ArrayList;

public class StringTest {

    /**
     * 回文数
     * 给你一个整数 x ，如果 x 是一个回文整数，返回 true ；否则，返回 false 。
     *
     * 回文数是指正序（从左向右）和倒序（从右向左）读都是一样的整数。例如，121 是回文，而 123 不是。
     */
    public boolean isPalindrome(int x) {
        return false;
    }

    /**
     * 无重复字符的最长字串
     * 滑动窗口
     */
    public int lengthOfLongestSubstring(String s) {
        int n = s.length();
        if(n <= 1) {
            return n;
        }
        int maxLen = 1;
        int left = 0;
        int right = 0;
        Set<Character> set = new HashSet<>();
        while(right < n ) {
            while(set.contains(s.charAt(right))) {
                set.remove(s.charAt(left));
                left++;
            }
            maxLen = Math.max(maxLen,right-left+1);
            set.add(s.charAt(right));
            right++;
        }
        return maxLen;
    }

    /**
     * 最长公共前缀
     * 编写一个函数来查找字符串数组中的最长公共前缀。
     *
     * 如果不存在公共前缀，返回空字符串 ""。
     * 思路：先取数组中第一个字符串作为公共前缀，然后遍历数组中所有字符串，若存在不是以最长公共前缀开头的，则将公共前缀变短，若还有字符串
     * 没匹配完，但公共前缀的长度已为0了，那么说明没有公共字串，返回""即可。
     */
    public String longestCommonPrefix(String [] strs) {
        if(strs.length == 0) {
            return "";
        }
        String s = strs[0];
        for(String str:strs) {
            while(!str.startsWith(s)) {
                if(s.length() ==0) {
                    return "";
                }
                s = s.substring(0,s.length()-1);
            }
        }
        return s;
    }

    /**
     * z字符串的排列
     * 给定两个字符串s1和s2，写一个函数来判断s2是否包含s1的排列。
     * 换句话说，第一个字符串的排列之一是第二个字符串的子串 。
     *
     * 方法一：滑动窗口，先统计s1中字符出现次数，然后取一个长度为s1长度的滑动窗口，判断窗口内字符和次数是否与s1相同，如果不同，则窗口向右移动，
     * 加入右边字符，减去左边字符
     */
    public boolean checkInclusion(String s1,String s2) {
        int n = s1.length();
        int m = s2.length();

        if(n>m) {
            return false;
        }
        int [] cnt1 = new int[26];
        int [] cnt2 = new int[26];

        for(int i = 0;i<n;i++) {
            cnt1[s1.charAt(i)-'a']++;
            cnt2[s2.charAt(i)-'a']++;
        }
        if(Arrays.equals(cnt1,cnt2)) {
            return true;
        }
        for(int i = n;i<m;i++) {
            cnt2[s2.charAt(i)-'a']++;
            cnt2[s2.charAt(i-n)-'a']--;
            if(Arrays.equals(cnt1,cnt2)) {
                return true;
            }
        }
        return false;
    }

    /**
     * 双指针
     * 回顾方法一的思路，我们在保证区间长度为n的情况下，去考察是否存在一个区间使得cnt的值全为 0。
     * 反过来，还可以在保证cnt 的值不为正的情况下，去考察是否存在一个区间，其长度恰好为n。
     * 初始时，仅统计s1中的字符，则cnt 的值均不为正，且元素值之和为−n。
     * 然后用两个指针left和right 表示考察的区间[left,right]。right每向右移动一次，就统计一次进入区间的字符x。为保证cnt 的值不为正，
     * 若此时cnt[x]>0，则向右移动左指针，减少离开区间的字符的cnt 值直到cnt[x]≤0。
     * 注意到[left,right]的长度每增加1，cnt的元素值之和就增加1。当[left,right] 的长度恰好为n时，就意味着cnt的元素值之和为0。由于cnt
     * 的值不为正，元素值之和为0 就意味着所有元素均为0，这样我们就找到了一个目标子串。
     */
    public boolean checkInclusion1(String s1,String s2) {
        int [] cnt = new int [26];
        int length1 = s1.length();
        int length2 = s2.length();
        if(length1>length2) {
            return false;
        }

        for(int i = 0;i<length1;i++) {
            cnt[s1.charAt(i)-'a']--;
        }

        int left = 0;
        for(int right = 0;right<length2;right++) {
            int x = s2.charAt(right)-'a';
            cnt[x]++;
            while(cnt[x]>0) {
                cnt[s2.charAt(left)-'a']--;
                left++;
            }
            if(length1 == right-left+1) {
                return true;
            }
        }
        return false;
    }

    /**
     * 字符串相乘
     * 给定两个以字符串形式表示的非负整数 num1 和 num2，返回 num1 和 num2 的乘积，它们的乘积也表示为字符串形式。
     */
    public String multiply(String num1,String num2) {
        String result = "";
        return result;
    }

    /**
     * 翻转字符串中的单词
     * 给你一个字符串 s ，逐个翻转字符串中的所有 单词 。
     * 单词 是由非空格字符组成的字符串。s 中使用至少一个空格将字符串中的 单词 分隔开。
     * 请你返回一个翻转 s 中单词顺序并用单个空格相连的字符串。
     */
    public String reverseWords(String s) {
        StringBuffer result = new StringBuffer();
        if(s == null || s.trim().length() == 0) {
            return result.toString();
        }
        List<String> list = new ArrayList<>();
        String [] strs = s.split(" ");
        for(int i = strs.length-1;i>-1;i--) {
            if(strs[i].trim().length()>0) {
                list.add(strs[i]);
            }
        }
        result.append(list.get(0));
        for(int i = 1;i<list.size();i++) {
            result.append(" ");
            result.append(list.get(i));
        }
        return result.toString();
    }

    /**
     * 双端队列
     * @param s
     * @return
     */
    public String reverse(String s) {
        if(s.length() == 0) {
            return s;
        }

        int left = 0;
        int right = s.length()-1;
        while(left<right && s.charAt(left) == ' ') {
            left++;
        }
        while(right>0 && s.charAt(right) == ' ') {
            right--;
        }

        Deque<String> queue = new ArrayDeque<>();
        StringBuffer word = new StringBuffer();
        while(left <= right) {
            char c = s.charAt(left);
            if(word.length()>0 && c == ' ') {
                queue.offerFirst(word.toString());
                word.setLength(0);
            } else if(c != ' ') {
                word.append(c);
            }
            left++;
        }
        queue.offerFirst(word.toString());
        return String.join("",queue);
    }

    /**
     * 使用栈
     */
    public static String reverseString(String s) {
        char [] str = s.toCharArray();
        Stack<String> stack = new Stack<>();
        int n = s.length();
        StringBuffer word = new StringBuffer();
        for(int i = 0;i<n;i++) {
            if(str[i] != ' ') {
                word.append(str[i]);
                if(i == n-1 || str[i+1] == ' ') {
                    stack.push(word.toString());
                    word.setLength(0);
                }
            }
        }
        StringBuffer result = new StringBuffer();
        while(!stack.isEmpty()) {
            result.append(stack.pop());
            if(!stack.isEmpty()) {
                result.append(' ');
            }
        }
        return result.toString();
    }

    /**
     * 使用栈（更方便记忆）
     */
    public static String reverseWordss(String s) {
        int length = s.length();
        Stack<String> stack = new Stack<>();
        StringBuffer word = new StringBuffer();
        for(int i = 0;i<length;i++) {
            if(s.charAt(i) != ' ') {
                word.append(s.charAt(i));
                if(i == length-1 || s.charAt(i+1) == ' ') {
                    stack.push(word.toString());
                    word.setLength(0);
                }
            }
        }
        StringBuffer result = new StringBuffer();
        while(!stack.isEmpty()) {
            result.append(stack.pop());
            if(!stack.isEmpty()) {
                result.append(' ');
            }
        }
        return result.toString();
    }

    /**
     * 字符串相加
     *方法一：双指针法，模拟相加过程
     */
    public String addString(String num1,String num2) {
        int i = num1.length()-1;
        int j = num2.length()-1;
        int add = 0;
        StringBuffer ans = new StringBuffer();
        while(i>=0 || j>= 0 || add != 0) {
            int x = i>=0?num1.charAt(i)-'0':0;
            int y = j>=0?num2.charAt(j)-'0':0;
            int result = x+y+add;
            ans.append(result%10);
            add = result/10;
            i--;
            j--;
        }
        ans.reverse();
        return ans.toString();
    }

    /**
     * 字符串相乘
     *给定两个以字符串形式表示的非负整数 num1 和 num2，返回 num1 和 num2 的乘积，它们的乘积也表示为字符串形式。
     */
    public String multiply1(String num1,String num2) {
        return " ";
    }

    /**
     * 有效的括号
     * 给定一个只包括 '('，')'，'{'，'}'，'['，']' 的字符串 s ，判断字符串是否有效。
     * 有效字符串需满足：
     *    左括号必须用相同类型的右括号闭合。
     *    左括号必须以正确的顺序闭合。
     *
     *
     *  思路：使用栈，遇到左括号就入栈，遇到右括号时判断，如果栈为空，或者栈顶元素与右括号不匹配，直接返回false，否则就出栈
     *  一次遍历完成后，如果栈内元素为空，则返回true，如果栈不为空，则返回false，说明括号没有完全匹配
     */
    public boolean isValid(String s) {
        int n = s.length();
        if(n%2 == 1) {
            return false;
        }

        Map<Character,Character> pairs = new HashMap<>();
        Stack<Character> stack = new Stack<>();
        pairs.put(')','(');
        pairs.put(']','[');
        pairs.put('}','{');

        for(int i = 0;i<s.length();i++) {
            char ch = s.charAt(i);
            if(pairs.containsKey(ch)) {
                if(stack.isEmpty() || !stack.peek().equals(pairs.get(ch))) {
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
     * 有效的括号字符串
     */
    public boolean checkValidString(String s) {
        int low = 0;
        int high = 0;
        for(int i = 0;i<s.length();i++) {
            char ch = s.charAt(i);
            if(ch == '(') {
                low++;
                high++;
            } else if(ch == ')') {
                if(low>0) {
                    low--;
                }
                high--;
            } else if(ch == '*') {
                if(low>0) {
                    low--;
                }
                high++;
            }
            if(high<0) {
                return false;
            }
        }
        return low == 0;
    }


    /**
     * 最长回文字串
     * 给你一个字符串s，找到s中最长的回文子串。
     *
     */
    public String longestPalindrome(String s) {
        return "";
    }

}
