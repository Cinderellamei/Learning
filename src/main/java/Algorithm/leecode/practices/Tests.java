package Algorithm.leecode.practices;

import java.util.HashMap;

public class Tests {

    /**
     *  数组中出现次数最多的数字
     */
    public static int test(int [] nums) {
        HashMap<Integer,Integer> map = new HashMap<>();
        for(int i = 0;i<nums.length;i++) {
            if(map.containsKey(nums[i])) {
                map.put(nums[i],map.get(nums[i])+1);
            } else {
                map.put(nums[i],1);
            }
        }

        int count = 0;
        int result = 0;
        for(int num:map.keySet()) {
            if(map.get(num)>count) {
                result = num;
                count = map.get(num);
            }
        }
        return result;
    }

    /**
     *
     * 斐波那契数列
     * 0，1，1，2，3，5
     * 第n个数
     */
    public static int test1(int n) {
        if(n == 1 || n == 2) {
            return n-1;
        }
        return test1(n-1)+test1(n-2);
    }

    public static void main(String [] args) {
        System.out.println(test1(7));
    }
}
