package Algorithm;

public class IntegerTest {
    public static void main(String [] args) {
        /*double base = 4;
        int exponent = -3;
        System.out.println(Power(base,exponent));*/
    }

    /*
    给定一个数base和指数exponent，求base的exponent次方
     */
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


    /*将一个字符串转化为一个整数，要求不能使用字符串转换整数的库函数。 数值为0或者字符串不是一个合法的数值则返回0
    * 如输入：1234567，输出为：123456，输入：1a23，输出：0*/
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
}
