package Algorithm;

import java.util.Scanner;
import java.util.Stack;

public class IntegerTest {

    /**
     * 给定一个数base和指数exponent，求base的exponent次方
     **/
    public static double Power(double base,int exponent) {
        double result = 1;
        for(int i = 0;i<Math.abs(exponent);i++) {
            result = result * base;
        }
        if(exponent<0) {
            result = 1/result;
        }
        return result;
    }

    public static int lastRemaining(int n,int m) {
        int result = 0;
        return result;
    }

    /*
    求1+2+3+...+n，要求不能使用乘除法、for、while、if、else、switch、case等关键字及条件判断语句（A?B:C)
    思路：递归变形
     */
    public static int sum(int n) {
        int sum = n;
        boolean x = n>1 && (sum +=sum(n-1))>0;
        return sum;
    }

    //写一个函数，求两个整数之和，要求在函数体内不得使用+、-、*、/四则运算符号。
    public static int Add(int num1,int num2) {
        int result = 0;
        return result;
    }


    /**将一个字符串转化为一个整数，要求不能使用字符串转换整数的库函数。 数值为0或者字符串不是一个合法的数值则返回0
     * 如输入：1234567，输出为：123456，输入：1a23，输出：0**/
    public static int StrToInt(String str) {
        if(str == null || str.length() == 0 || str == "+" || str == "-") {
            return 0;
        }
        int limit = -Integer.MAX_VALUE;
        int label = 1;
        int result = 0;
        for(int i = 0;i<str.length();i++) {
            if(i == 0 &&((str.charAt(i) == '-') || (str.charAt(i) == '+'))) {
                if(str.charAt(i) == '-') {
                    limit = Integer.MIN_VALUE;
                    label = -1;
                }
                continue;
            } else if(str.charAt(i)>'0' && str.charAt(i)<'9') {
                int num = str.charAt(i)-'0';
                int temp = result*10;
                if(result >= (limit/10) && temp>=(limit+num)) {
                    result = result*10-num;
                } else {
                    return 0;
                }
            } else {
                return 0;
            }
        }
        return label>0?-result:result;
    }

    /**
     *  求平方根
     *  实现函数 int sqrt(int x).
     * 计算并返回x的平方根（向下取整）
     */
    public static int sqrt(int x) {
        if(x <= 0) {
            return 0;
        }
        int left = 1;
        int right = x;
        int result = -1;
        while(left <= right) {
            int mid = (left + right) / 2;
            if (mid <= x / mid && (mid + 1) > x / (mid + 1)) {
                result = mid;
                return result;
            } else if (mid < x / mid) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }
        return result;
    }

    /**
     * 二进制中1的个数
     */
    public static int numberOfone(int n) {
        int result = 0;
        while(n !=0) {
            if((n&1)!=0) {
                result++;
            }
            n = n>>>1;
        }
        return result;
    }

    /**
     * 方法二：
     */
    public int NumberOf1(int n) {
        int count = 0;
        while(n!=0) {
            n = (n-1)&n;
            count++;
        }
        return count;
    }

    /**
     * 最长的重复字符子串
     */
    public static int test(String s) {
        int n = s.length();
        int result = 0;
        for(int i = n/2;i>0;i--) {
            for(int j = 0;j<n-i;j++) {
                if(s.charAt(j) == s.charAt(j+i)) {
                    result++;
                } else {
                    result = 0;
                }
                if(result == i) {
                    return 2*i;
                }
            }
        }
        return 0;
    }




}
