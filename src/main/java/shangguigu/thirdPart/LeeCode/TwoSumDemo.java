package shangguigu.thirdPart.LeeCode;

import javafx.scene.Parent;

import java.util.HashMap;
import java.util.Map;

/**
 * 给定一个整数数组 nums 和一个整数目标值 target，请你在该数组中找出 和为目标值 的那 两个 整数，并返回它们的数组下标。
 * 示例 1：
 *
 * 输入：nums = [2,7,11,15], target = 9
 * 输出：[0,1]
 * 解释：因为 nums[0] + nums[1] == 9 ，返回 [0, 1] 。
 * 示例 2：
 *
 * 输入：nums = [3,2,4], target = 6
 * 输出：[1,2]
 * 示例 3：
 *
 * 输入：nums = [3,3], target = 6
 * 输出：[0,1]
 *  
 * 提示：
 *
 * 2 <= nums.length <= 103
 * -109 <= nums[i] <= 109
 * -109 <= target <= 109
 * 只会存在一个有效答案
 */
public class TwoSumDemo {

    /**
     * 遍历（暴力破解）
     * 通过双重循环遍历数组中所有元素的两两组合，出现符合的时返回两个元素的下标
     */
    public static int[] twoSum1(int [] nums,int target) {
        for(int i = 0;i<nums.length;i++) {
            for(int j = i+1;j<nums.length;j++) {
                if(target-nums[i] == nums[j]) {
                    return new int[]{i,j};
                }
            }
        }
        return null;
    }

    public static int [] twoSum2(int [] nums,int target) {
        Map<Integer,Integer> map = new HashMap<>();
        for(int i = 0;i<nums.length;i++) {
            int partnerNumber = target-nums[i];
            if(map.containsKey(partnerNumber)) {
                return new int[]{map.get(partnerNumber),i};
            }
            map.put(nums[i],i);
        }
        return null;
    }

    public static void main(String [] args) {
        int [] nums = new int[]{2,7,11,15};
        int target = 22;
        int [] index = twoSum2(nums,target);
        for(int element:index) {
            System.out.println(element);
        }
    }
}
