package Algorithm.leecode.test;

import java.util.Scanner;

public class TriangleTest {
    /**
     * 输入3条边，判断能否组成三角形，判断是什么三角形
     */
    public static void main(String [] args) {
        Scanner sc = new Scanner(System.in);
        int a = 0;
        int b = 0;
        int c = 0;
        int i = 0;
        a=sc.nextInt();
        b = sc.nextInt();
        c = sc.nextInt();
        if(a+b>c&&b+c>a&&a+c>b) {
            System.out.println("可以构成三角形");
            if(a == b && b == c) {
                System.out.println("可以构成等边三角形");
            } else if(a ==b || b == c|| a == c) {
                System.out.println("可以构成等腰三角形");
            } else if(a*a+b*b==c*c||b*b+c*c==a*a||a*a+c*c==b*b) {
                System.out.println("可以构成直角三角形");
            }
        } else {
            System.out.println("不可以构成三角形");
        }
    }

}
