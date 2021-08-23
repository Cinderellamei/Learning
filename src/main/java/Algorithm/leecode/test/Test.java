package Algorithm.leecode.test;

public class Test {

    public static int [] exchange(int [] nums) {
        int [] result = new int[nums.length];
        int x = 0;
        int count = 0;
        for(int i = 0;i<nums.length;i++) {
            if(nums[i]%2 == 1) {
                count++;
            }
        }

        int y = count;
        for(int i = 0;i<nums.length;i++) {
            if(nums[i]%2 == 1) {
                result[x++] = nums[i];
            } else if(nums[i]%2 == 0) {
                result[y++] = nums[i];
            }
        }
        return result;
    }

    public static int test(int [] nums) {
        int result = 0;
        for(int i = 0;i<nums.length;i++) {
            result ^= nums[i];
        }
        return result;
    }
}
