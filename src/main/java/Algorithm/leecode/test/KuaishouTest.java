package Algorithm.leecode.test;

import java.util.ArrayList;
import java.util.Arrays;

public class KuaishouTest {
    /**
     * 给定一个整数n和目标值target，有一个1-n的数组，数组长度为n，找出数组中所有和为target的子数组
     */
    public static ArrayList<int []> findTarget(int [] nums,int target) {
        ArrayList<int []> result = new ArrayList<>();
        int left = 0;
        int right = nums.length-1;
        while(left<right) {
            int sum = nums[left]+nums[right];
            if(sum == target) {
                result.add(new int[]{nums[left],nums[right]});
                left++;
            } else if(sum<target) {
                left++;
            } else if(sum > target) {
                right--;
            }
        }
        return result;
    }

    /**
     * 最接近的三数之和
     * 给定一个数组和一个target，找出数组中三数和最接近target的值，返回这个和
     */
    public static int threeSumClosest(int [] nums,int target) {
        Arrays.sort(nums);
        int result = nums[0]+nums[1]+nums[2];
        for(int i = 0;i<nums.length;i++) {
            int left = i+1;
            int right = nums.length-1;
            while(left<right) {
                int sum = nums[i]+nums[left]+nums[right];
                if(Math.abs(target-sum)<Math.abs(target-result)) {
                    result = sum;
                } else if(sum>target) {
                    right--;
                } else if(sum<target) {
                    left++;
                } else {
                    return result;
                }
            }
        }
        return result;
    }

    /**
     * 合并两个有序数组
     * A数组有足够的空间容纳B
     */
    public int [] merge(int [] A,int m,int [] B,int n) {
        int i = A.length;
        while(n>0) {
            if(m>0 && A[m-1]>B[n-1]) {
                A[--i] = A[--m];
            } else {
                A[--i] = B[--n];
            }
        }
        return A;
    }


}
