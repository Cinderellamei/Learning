package Algorithm;

public class Fibonacci {
    public static void main(String [] args) {
        System.out.println(number3(3));
    }

    /*
    斐波那契非递归实现
     */
    public static int number(int n) {
        int a = 1;
        int b = 1;
        int c = 0;
        if(n<0) return -1;
        if(n == 0) return 0;
        if(n == 1 || n == 2) return 1;
        for(int i = 3;i<=n;i++) {
            c = a+b ;
            a = b;
            b = c;
        }
        return c;
    }

    /*
    斐波那契递归实现
    f(n)=f(n-1)+f(n-2)
     */
    public static int number2(int n) {
        if(n < 0) return -1;
        if(n == 0) return 0;
        if(n == 1 || n == 2) return 1;
        return number2(n-1)+number(n-2);
    }

    /*
    一只青蛙一次可以跳一级台阶，可以跳2级台阶。。。，可以跳n级台阶，一个n级的台阶，有多少种跳法
    思路：f(n) = 2*f(n-1);
     */
    public static int number3(int n) {
        if(n<0) return -1;
        if(n == 0) return 0;
        if(n == 1) return 1;
        return 2*number3(n-1);
    }
}
