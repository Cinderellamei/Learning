package Algorithm.leecode.hot100;

import java.util.HashMap;

public class IntegerTest {
    /**
     * 只出现一次的数字
     *
     * 给定一个非空整数数组，除了某个元素只出现一次以外，其余每个元素均出现两次。找出那个只出现了一次的元素。
     * 使用额外空间的方法
     */
    public int singleNumber(int [] nums) {
        HashMap<Integer,Integer> map = new HashMap<>();
        for(int i = 0;i<nums.length;i++) {
            if(map.containsKey(nums[i])) {
                map.put(nums[i],map.get(nums[i])+1);
            } else {
                map.put(nums[i],1);
            }
        }
        for(Integer num:map.keySet()) {
            if(map.get(num) == 1) {
                return num;
            }
        }
        return -1;
    }

    /**
     * 不使用额外空间的方法
     * 交换律：a ^ b ^ c <=> a ^ c ^ b
     *
     * 任何数与0异或为任何数 0 ^ n => n
     *
     * 相同的数异或为0: n ^ n => 0
     */
    public int singleNumber1(int [] nums) {
        int result = 1;
        for(int i = 0;i<nums.length;i++) {
            result ^= nums[i];
        }
        return result;
    }

    /**
     * 三个数的最大乘积
     */
    public int maximumProduct(int[] nums) {
        int min1 = Integer.MAX_VALUE;
        int min2 = Integer.MAX_VALUE;

        int max1 = Integer.MIN_VALUE;
        int max2 = Integer.MIN_VALUE;
        int max3 = Integer.MIN_VALUE;

        for(int x:nums) {
            if(x<min1) {
                min2 = min1;
                min1 = x;
            } else if(x<min2) {
                min2 = x;
            }

            if(x>max1) {
                max3 = max2;
                max2 = max1;
                max1 = x;
            } else if(x>max2) {
                max3 = max2;
                max2 = x;
            } else if(x>max3) {
                max3 = x;
            }
        }
        return Math.max(min1*min2*max1,max1*max2*max3);
    }



}
