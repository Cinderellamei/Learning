package Algorithm;

import java.util.*;

public class StringTest {
    /*
    给定一个字符串，将该字符串中每个空格替换成"%20"
    思路：先统计出空格数，然后统一插入
     */
    public static String replaceSpace(StringBuffer str) {
        int count = 0;
        for(int i = 0;i<str.length();i++) {
            if(str.charAt(i) == ' ') count++;
        }
        int oldLength = str.length();
        int newLength = oldLength+2*count;
        str.setLength(newLength) ;
        for(int i = oldLength-1;i>=0;i--) {
            if(str.charAt(i) != ' ') {
                str.setCharAt(i+2*count,str.charAt(i));
            } else if(str.charAt(i) == ' ') {
                count--;
                str.setCharAt(i+2*count,'%');
                str.setCharAt(i+2*count+1,'2');
                str.setCharAt(i+2*count+2,'0');
            }
        }
        return str.toString();
    }

    /*
    第一个只出现一次的字符
    在一个字符串(0<=字符串长度<=10000，全部由字母组成)中找到第一个只出现一次的字符,并返回它的位置, 如果没有则返回 -1（需要区分大小写）.（
    从0开始计数
    思路：用HashMap的key存储字符，value存储字符出现的次数，全部字符都遍历完后，从头到尾遍历一遍字符串，从map中获取字符出现的次数，碰到有值
    为1的，直接返回位置，就是第一个只出现一次的字符
     */
    public static int FirtNotRepeatingCharacter(String str) {
        if(str.length() == 0) return -1;
        HashMap<Character,Integer> map = new HashMap<>();
        for(int i = 0;i<str.length();i++) {
            if(map.containsKey(str.charAt(i))) {
                map.put(str.charAt(i),map.get(str.charAt(i))+1);
            } else {
                map.put(str.charAt(i),1);
            }
        }
        int i = 0;
        for(;i<str.length();i++) {
            if(map.get(str.charAt(i)) == 1) {
                return i;
            }
        }
        return -1;
    }

    /*
    汇编语言中有一种移位指令叫做循环左移（ROL），现在有个简单的任务，就是用字符串模拟这个指令的运算结果。对于一个给定的字符序列S，请
    你把其循环左移K位后的序列输出。例如，字符序列S=”abcXYZdef”,要求输出循环左移3位后的结果，即“XYZdefabc”。
    题目抽象：给定一字符串str，将str的[0...n)子串移动到[n...len)子串的后面。
     */
    //偷懒法，直接用库函数
    public static String leftRotateString(String str,int n) {
        if(str.length() == 0) return "";
        if(n>str.length()) return str;
        return str.substring(n)+str.substring(0,n);
    }

    public static String leftRotateString1(String str,int n) {
        if(n > str.length()) return str;
        String result = "";
        for(int i = n;i<str.length();i++) {
            result +=str.charAt(i);
        }
        for(int i = 0;i<n;i++) {
            result += str.charAt(i);
        }
        return result;
    }

    /*
    题目抽象：给定一个首尾可能带空格的字符串，请让你翻转该字符串。首尾不能有多余空格。如果全部是空格，请返回原字符串。
    思路：先对整个字符串进行反转，然后用两个指针变量记录每个单词的首尾，去寻找空格，当尾部碰到空格，说明现在记录内的单词需要反转，翻转过后，重新
    维护来给你个指针
     */
    public static  String ReverseSentence(String str) {
        if(str == null || str.length() == 0) return str;
        //将字符串转换为字符数组
        char [] arr = str.toCharArray();
        //先将字符串全部翻转一次
        reverse(arr,0,arr.length-1);
        //指向第一个单词
        int start = 0;
        //指向最后一个单词
        int end = 0;
        while(start < arr.length) {
            //如果start指向的是指针，则换下一个，因为指针要指向单词
            if(arr[start] == ' ') {
                start++;
                end++;
                //如果尾部是空格，或者尾部刚刚超过最后一个尾标，说明需要进行单词翻转了
                //end会超过尾标，是因为end指向的不是空格时，会end++
            } else if(end == arr.length || arr[end] == ' ') {
                reverse(arr,start,end-1);
                //翻转之后，重新维护两个指针
                end++;
                start = end;
                //说明start和end指的都不是空格，是正常单词，end应该往后指
            } else {
                end++;
            }
        }
        return str.valueOf(arr);
    }

    public static void reverse(char [] arr,int begin,int end) {
        while(begin < end) {
            char temp = arr[begin];
            arr[begin] = arr[end];
            arr[end] = temp;
            begin++;
            end--;
        }
    }

    /**
     * 翻转字符串（牛客）
     * 写出一个程序，接受一个字符串，然后输出该字符串反转后的字符串。（字符串长度不超过1000）
     * 方法一：使用栈先进后出的特点，进行字符串反转
     */
    public String solve (String str) {
        char [] arr = str.toCharArray();
        Stack<Character> stack = new Stack<>();
        for(int i = 0;i<arr.length;i++) {
            stack.push(arr[i]);
        }
        StringBuffer stb = new StringBuffer();
        while(!stack.isEmpty()) {
            stb.append(stack.pop());
        }
        return stb.toString();
    }

    /**
     * 方法二：
     */
    public static String solve1(String str) {
        int size = str.length();
        char [] arr = new char[size];
        for(int i = 0;i<size;i++) {
            arr[i] = str.charAt(size-i-1);
        }
        return new String(arr);
    }

    /**
     * 方法三：原地交换元素
     */
    public static String solve2(String str) {
        if(str == null || str.length() == 0) {
            return str;
        }
        char [] arr = str.toCharArray();
        int size = arr.length;
        for(int i = 0;i<size/2;i++) {
            char temp = arr[size-i-1];
            arr[size-i-1] = arr[i];
            arr[i] = temp;
        }
        return new String(arr);
    }

    /**
     * 字符串中第一个唯一字符
     * 给定一个字符串，找到它的第一个不重复的字符，并返回它的索引。如果不存在，则返回 -1。
     *
     * 方法一：使用hash表存储字符出现的次数，对字符串进行两次遍历，第一次记录字符及出现次数，第二次遍历，碰到第一个出现次数为1的字符就返回
     * 缺点：空间复杂度较高
     */
    public static int firstUniqChar(String s) {
        HashMap<Character,Integer> map = new HashMap<>();
        for(int i = 0;i<s.length();i++) {
            char ch = s.charAt(i);
            if(map.containsKey(ch)) {
                map.put(ch,map.get(ch)+1);
            } else {
                map.put(ch,1);
            }
        }

        for(int i = 0;i<s.length();i++) {
            char ch = s.charAt(i);
            if(map.get(ch) == 1) {
                return i;
            }
        }
        return -1;
    }

    /**
     * 方法二：hash表中存放字符的索引，第一次出现放索引，第二次出现变为-1
     */
    public static int firstUniqChar1(String s) {
        int len = s.length();
        HashMap<Character,Integer> map = new HashMap<>();
        for(int i = 0;i<len;i++) {
            char ch = s.charAt(i);
            if(map.containsKey(ch)) {
                map.put(ch,-1);
            } else {
                map.put(ch,i);
            }
        }

        int result = len;
        for(char num:map.keySet()) {
            int index = map.get(num);
            if(index != -1) {
                if(index < result) {
                    result = index;
                }
            }
        }
        if(result == len) {
               result = -1;
            }
        return result;
    }

    /**
    * 方法三：初始化一个长度为26的数组，遍历字符串，遍历到哪个字符，在数组中该字符串对应位置值加一，遍历完后，再遍历一次字符串
    * 将值为1的下标返回（s.charAt(i）-'a'是用字符的值减去97，这样字符的值就可以和数组的下标对应了)
    * 
    **/
    public int firstUniqChar2(String s) {
        int[] array = new int[26];
        for(int i = 0; i < s.length(); i++) {
            array[s.charAt(i) - 'a']++;
        }
        for(int i = 0; i < s.length(); i++) {
            if(array[s.charAt(i) - 'a'] == 1)
                return i;
        }
        return -1;
    }
}
