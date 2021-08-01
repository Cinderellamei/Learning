package Algorithm.leecode.bytedance;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class IntegerTest {
    /**
     * 跳台阶问题
     * 斐波拉契函数
     * 方法一：递归，时间复杂度较高
     */
    public int climbStairs(int n) {
        if(n ==1) {
            return 1;
        }
        if(n == 2) {
            return 2;
        }
        return climbStairs(n-1)+climbStairs(n-2);
    }

    /**
     * 方法二
     */
    public int climStairs1(int n) {
        if(n == 1) {
            return 1;
        }
        if(n == 2) {
            return 2;
        }
        int a = 1;
        int b = 2;
        int c = 0;
        for(int i = 3;i<=n;i++) {
            c = a+b;
            a = b;
            b = c;
        }
        return c;
    }

    /**
     * 消失的数字，寻找第一个缺少的正整数
     * 数组nums包含从0到n的所有整数，但其中缺了一个。请编写代码找出那个缺失的整数。你有办法在O(n)时间内完成吗？
     *
     * 思路：等差公式求0到n的和，减去nums总和，剩下就是缺少的
     */
    public int missingNumber(int [] nums) {
        int n = nums.length;
        int sum = 0;
        for(int i = 0;i<n;i++) {
            sum += nums[i];
        }
        return n*(n+1)/2-sum;
    }

    /**
     * 方法二：
     */
    public int missingNumber1(int [] nums) {
        boolean [] arr = new boolean[nums.length+1];

        for(int i = 0;i<nums.length;i++) {
            arr[nums[i]] = true;
        }

        for(int i = 0;i<arr.length;i++) {
            if(arr[i] == false) {
                return i;
            }
        }
        return -1;
    }

    /**
     * 方法三：二分查找
     */
    public int missingNumber2(int [] nums) {
        int left = 0;
        int right = nums.length;
        while(left <right) {
            int mid = (left+right)/2;
            if(nums[mid] == mid ) {
                left = mid+1;
            } else if(nums[mid]>mid) {
                right = mid;
            }
        }
        return left;
    }

    /**
     * 青蛙跳台阶问题
     *
     * 一只青蛙一次可以跳上1级台阶，也可以跳上2级台阶。求该青蛙跳上一个 n 级的台阶总共有多少种跳法。
     *
     * 答案需要取模 1e9+7（1000000007），如计算初始结果为：1000000008，请返回 1
     *
     */
    public int numways(int n) {
        int a = 1;
        int b = 1;
        int c = 0;
        for(int i = 0;i<n;i++) {
            c=(a+b)%1000000007;
            a = b;
            b = c;
        }
        return a;
    }


    /**
     * 数组中数字出现的次数
     * 一个整型数组 nums 里除两个数字之外，其他数字都出现了两次。请写程序找出这两个只出现一次的数字。要求时间复杂度是O(n)，空间复杂度是O(1)。
     * 思路一：不符合题目条件的，新建一个map，遍历每个数组，数组元素是key，元素出现的次数是value，然后遍历一遍map，将出现次数为1的元素输出
     */
    public int[] singleNumbers(int[] nums) {
        int [] result = new int[2];
        Map<Integer,Integer> map = new HashMap<>();
        for(int i = 0;i<nums.length;i++) {
            if(map.containsKey(nums[i])) {
                map.put(nums[i],map.get(nums[i])+1);
            } else {
                map.put(nums[i],1);
            }
        }
        int count = 0;
        for(int i = 0;i<nums.length;i++) {
            if(map.get(nums[i]) == 1) {
                result[count++] = nums[i];
            }
        }
        return result;
    }

    /**
     * 方法二：分组位运算，符合题目要求时间复杂度是O(n)，空间复杂度是O(1)的算法
     * 思路：先将所有的元素做异或操作，得到的结果是两个只出现一次元素的异或结果
     *       再用m和结果做与操作，得到两个只出现一次的数不一样的位置
     *       然后根据这个不一样的位置，将数组分成两部分，两个只出现一次的数组要出现在两个数组中
     *
     */
    public int [] singleNumbers1(int [] nums) {
        int x = 0;
        int y = 0;
        int total = 0;
        int m = 1;
        for(int i = 0;i<nums.length;i++) {
            total ^= nums[i];
        }
        while((total&m) == 0) {
            m <<=1;
        }
        for(int num:nums) {
            if((num&m) != 0) {
                x ^= num;
            } else if((num&m) == 0) {
                y ^= num;
            }
        }
        return new int[]{x,y};
    }

    /**
     * 数组中数字出现的次数II
     * 在一个数组 nums 中除一个数字只出现一次之外，其他数字都出现了三次。请找出那个只出现一次的数字。
     * 思路：巧妙的方法：先对数组排序，这个只出现一次的数字位置有三种情况，第一种是在数组第一个元素，第二种是数组最后一个元素，第三种是在数组中间
     * 针对第一种情况和第二种情况，if语句判断就可以了，第三种情况，可以用for循环遍历，如果一个元素与左右两边元素都不相等，说明就是只出现一次的数字
     */
    public static int singleNumber(int [] nums) {
        int n = nums.length;
        Arrays.sort(nums);
        if(nums[0] != nums[1]) {
            return nums[0];
        }
        if(nums[n-1] != nums[n-2]) {
            return nums[n-1];
        }
        for(int i =1;i<n-1;i++) {
            if(nums[i] != nums[i-1] && nums[i] != nums[i+1]) {
                return nums[i];
            }
        }
        return 0;
    }

}
