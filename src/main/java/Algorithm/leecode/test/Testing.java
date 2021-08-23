package Algorithm.leecode.test;


import Algorithm.leecode.tree.TreeNode;

import java.util.*;

public class Testing {
    /**
     * 最长公共子序列
     */

    /**
     * 无重复字符的最长子串
     */

    /**
     * 最大子序和
     */

    /**
     * 两个大数相加
     */

    /**
     * 买卖股票最佳时机
     */


    /**
     *买卖股票的最佳时机II
     * 可以无限次的买入和卖出，用贪心算法
     */

    /**
     * 二叉树层次遍历
     */

    /**
     * 二叉树转链表
     */

    /**
     * 二叉树寻找最近公共父节点
     */

    /**
     * 冒泡排序
     */

    /**
     * 升序整数数组，有重复数字，找到重复数字数的起始下标和结束下标
     */
    public int [] find(int [] nums,int target) {
        int left = binarySearch(nums,target);
        int right = binarySearch(nums,target+1)-1;
        if(left<=right && nums[left]== nums[right] && nums[left] == target) {
            return new int[]{left,right};
        }
        return new int[]{-1,-1};
    }

    public int binarySearch(int [] nums,int target) {
        int start =0;
        int end = nums.length-1;
        while(start<=end) {
            int mid = (start+end)/2;
            if(nums[mid]>=target) {
                end = mid-1;
            } else{
                start = mid+1;
            }
        }
        return start;
    }
}
