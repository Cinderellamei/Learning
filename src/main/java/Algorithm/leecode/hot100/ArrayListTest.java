package Algorithm.leecode.hot100;

import java.util.*;

public class ArrayListTest {

        /**
         * 两数之和
         * 判断数组中是否有两个数字的和等于给定target
         */
        public  int [] twoSum(int [] nums,int target) {
            HashMap<Integer,Integer> map = new HashMap<>();
            for(int i = 0;i<nums.length;i++) {
                if(map.containsKey(target-nums[i])) {
                    return new int[]{map.get(target-nums[i]),i};
                } else {
                    map.put(nums[i],i);
                }
            }
            return new int [0];
        }

        /**
         * 长度最小的子数组
         *
         * 给定一个含有 n 个正整数的数组和一个正整数 target 。
         *
         * 找出该数组中满足其和 ≥ target 的长度最小的 连续子数组 [numsl, numsl+1, ..., numsr-1, numsr] ，并返回其长度。如果不存在符合条件的子数组，返回 0 。
         * 滑动窗口
         */
        public int minSubArrayLen(int target,int [] nums) {
            int n = nums.length;
            if(n == 0) {
                return 0;
            }
            int start = 0;
            int end = 0;
            int sum = 0;
            int ans = Integer.MAX_VALUE;
            while(end < n) {
                sum += nums[end];
                while(sum >= target) {
                    ans = Math.min(ans,end-start+1);
                    sum -= nums[start];
                    start++;
                }
                end++;
            }
            return ans == Integer.MAX_VALUE?0:ans;
        }


        /**
         * 最大子序和
         * 给定一个整数数组 nums ，找到一个具有最大和的连续子数组（子数组最少包含一个元素），返回其最大和。
         */
        public int maxSubArray(int [] nums) {
            int ans = nums[0];
            int total = 0;
            for(int i = 0;i<nums.length;i++) {
                if(total>0) {
                    total += nums[i];
                } else {
                    total = nums[i];
                }
                ans = Math.max(ans,total);
            }
            return ans;
        }

        /**
         * 买卖股票的最佳时机
         */
        public int maxProfit(int [] prices) {
            int minPrice = Integer.MAX_VALUE;
            int maxProfit = 0;
            for(int i = 0;i<prices.length;i++) {
                if(prices[i]<minPrice) {
                    minPrice = prices[i];
                } else if(prices[i]-minPrice>maxProfit) {
                    maxProfit = prices[i]-minPrice;
                }
            }
            return maxProfit;
        }

        /**
         * 买卖股票的最佳时机二
         *
         * 给定一个数组 prices ，其中 prices[i] 是一支给定股票第 i 天的价格。
         * 设计一个算法来计算你所能获取的最大利润。你可以尽可能地完成更多的交易（多次买卖一支股票）。
         * 注意：你不能同时参与多笔交易（你必须在再次购买前出售掉之前的股票）。
         * 方法一：动态规划
         *
         */
        public int maxProfit1(int [] prices) {
            int n = prices.length;
            int [][] dp = new int [n][2];
            dp[0][0] = 0;
            dp[0][1] = -prices[0];
            for(int i = 1;i<n;i++) {
                dp[i][0]= Math.max(dp[i-1][0],dp[i-1][1]+prices[i]);
                dp[i][1] = Math.max(dp[i-1][1],dp[i-1][0]-prices[i]);
            }
            return dp[n-1][0];
        }

        /**
         * 方法二：贪心算法
         *
         */
        public int maxProfit2(int [] prices) {
            int result = 0;
            int n = prices.length;
            for(int i = 1;i<n;i++) {
                result += Math.max(0,prices[i]-prices[i-1]);
            }
            return result;
        }


        /**
         * 移动零
         * 给定一个数组 nums，编写一个函数将所有 0 移动到数组的末尾，同时保持非零元素的相对顺序。
         *
         * 必须在原数组上操作，不能拷贝额外的数组。
         * 尽量减少操作次数
         *
         * 这里参考了快速排序的思想，快速排序首先要确定一个待分割的元素做中间点x，然后把所有小于等于x的元素放到x的左边，大于x的元素放到其右边。
         * 这里我们可以用0当做这个中间点，把不等于0(注意题目没说不能有负数)的放到中间点的左边，等于0的放到其右边。
         * 这的中间点就是0本身，所以实现起来比快速排序简单很多，我们使用两个指针i和j，只要nums[i]!=0，我们就交换nums[i]和nums[j]
         */
        public  void moveZeroes(int [] nums) {
            if(nums == null) {
                return;
            }
            int j = 0;
            for(int i = 0;i<nums.length;i++) {
                if(nums[i] != 0) {
                    int temp = nums[i];
                    nums[i] = nums[j];
                    nums[j++] = temp;
                }
            }
        }

        /**
         * 方法二：两次遍历
         * 我们创建两个指针i和j，第一次遍历的时候指针j用来记录当前有多少非0元素。即遍历的时候每遇到一个非0元素就将其往数组左边挪，第一次遍历完后，j指针的下标就指向了最后一个非0元素下标。
         * 第二次遍历的时候，起始位置就从j开始到结束，将剩下的这段区域内的元素全部置为0。
         */
        public void moveZeroes1(int [] nums) {
            if(nums == null) {
                return;
            }
            int j = 0;
            for(int i = 0;i<nums.length;i++) {
                if(nums[i]!=0) {
                    nums[j++] = nums[i];
                }
            }
            for(int i = j;i<nums.length;i++) {
                nums[i] = 0;
            }
        }

        /**
         * 搜索旋转排序数组
         * 整数数组 nums 按升序排列，数组中的值 互不相同 。
         *
         * 在传递给函数之前，nums 在预先未知的某个下标 k（0 <= k < nums.length）上进行了 旋转，使数组变为 [nums[k], nums[k+1], ..., nums[n-1], nums[0], nums[1], ..., nums[k-1]]（下标 从 0 开始 计数）。例如， [0,1,2,4,5,6,7] 在下标 3 处经旋转后可能变为 [4,5,6,7,0,1,2] 。
         *
         * 给你 旋转后 的数组 nums 和一个整数 target ，如果 nums 中存在这个目标值 target ，则返回它的下标，否则返回 -1 
         *方法一：一次遍历，时间复杂度为O(n)，但题目要求时间复杂度是O(logn))，所以不可以用这个办法
         */
        public int search(int [] nums,int target) {
            for(int i = 0;i<nums.length;i++) {
                if(nums[i] == target) {
                    return i;
                }
            }
            return -1;
        }

        /**
         * 方法二：二分法
         * 对于有序数组，可以使用二分查找的方法查找元素。
         * 但是这道题中，数组本身不是有序的，进行旋转后只保证了数组的局部是有序的，这还能进行二分查找吗？答案是可以的。
         * 可以发现的是，我们将数组从中间分开成左右两部分的时候，一定有一部分的数组是有序的。拿示例来看，我们从 6 这个位置分开以后数组变成了
         * [4, 5, 6] 和 [7, 0, 1, 2] 两个部分，其中左边 [4, 5, 6] 这个部分的数组是有序的，其他也是如此。
         *
         * 这启示我们可以在常规二分查找的时候查看当前 mid 为分割位置分割出来的两个部分 [l, mid] 和 [mid + 1, r] 哪个部分是有序的，并根据有
         * 序的那个部分确定我们该如何改变二分查找的上下界，因为我们能够根据有序的那部分判断出 target 在不在这个部分：
         *
         * 如果 [l, mid - 1] 是有序数组，且 target 的大小满足[nums[l],nums[mid])，
         * 则我们应该将搜索范围缩小至 [l, mid - 1]，否则在 [mid + 1, r] 中寻找。
         * 如果 [mid, r] 是有序数组，且 target 的大小满足 [mid+1],(nums[mid+1],nums[r]]，
         * 则我们应该将搜索范围缩小至 [mid + 1, r]，否则在 [l, mid - 1] 中寻找。
         */
        public int search1(int [] nums,int target) {
            int length = nums.length;
            if(length == 0) {
                return -1;
            }
            if(length ==1)  {
                return nums[0] == target?0:-1;
            }
            int left = 0;
            int right = length-1;
            while(left <=right) {
                int mid = (left+right)/2;
                if(nums[mid] == target) {
                    return mid;
                }
                if(nums[0]<=nums[mid]) {
                    if(nums[0]<=target && target<nums[mid]) {
                        right = mid-1;
                    } else {
                        left = mid+1;
                    }
                } else {
                    if(nums[mid]<target && target<=nums[right]) {
                        left = mid+1;
                    } else {
                        right = mid-1;
                    }
                }
            }
            return -1;
        }

        /**
         * 领取兑换
         * 给定不同面额的硬币 coins 和一个总金额 amount。编写一个函数来计算可以凑成总金额所需的最少的硬币个数。如果没有任何一种硬币组合能组成总金额，返回 -1。
         *你可以认为每种硬币的数量是无限的。
         *
         */
        public int coinChange(int [] coins,int amount) {
            int max = amount+1;
            int [] dp = new int[amount+1];
            Arrays.fill(dp,max);
            dp[0] = 0;
            for(int i = 1;i<=amount;i++) {
                for(int j = 0;j<coins.length;j++) {
                    if(i>=coins[j]) {
                        //dp[i]的初始化值为amount+1，所以需要和dp[i-coins[j]+1进行比较，取最小的
                        dp[i] = Math.min(dp[i],dp[i-coins[j]]+1);
                    }
                }
            }
            return dp[amount]>amount?-1:dp[amount];
        }

        /**
         * 统计全为1的正方形矩阵
         * 给你一个 m * n 的矩阵，矩阵中的元素不是 0 就是 1，请你统计并返回其中完全由 1 组成的 正方形 子矩阵的个数。
         * 动态规划
         */
        public int countSquare(int [][] matrix) {
            int m = matrix.length;
            int n = matrix[0].length;
            int [][] dp = new int[m][n];
            int result = 0;
            for(int i = 0;i<m;i++) {
                for(int j = 0;j<n;j++) {
                    if( i == 0 || j == 0) {
                        dp[i][j] = matrix[i][j];
                    } else if(matrix[i][j] == 0) {
                        dp[i][j] = 0;
                    } else {
                        dp[i][j] = Math.min(Math.min(dp[i-1][j],dp[i][j-1]),dp[i-1][j-1])+1;
                    }
                    result += dp[i][j];
                }
            }
            return result;
        }

        /**
         * 最大正方形
         * 在一个由 '0' 和 '1' 组成的二维矩阵内，找到只包含 '1' 的最大正方形，并返回其面积。
         * 动态规划
         */
        public int maximalSquare(char[][] matrix) {
            int maxSide=0;
            if(matrix == null || matrix.length == 0 || matrix[0].length == 0) {
                return maxSide;
            }
            int row = matrix.length;
            int col = matrix[0].length;
            int[][] dp = new int[row][col];
            for(int i = 0;i<row;i++) {
                for(int j = 0;j<col;j++) {
                    if(matrix[i][j] == '1') {
                        if(i == 0 || j == 0) {
                            dp[i][j] = 1;
                        } else {
                            dp[i][j] = Math.min(Math.min(dp[i-1][j],dp[i][j-1]),dp[i-1][j-1])+1;
                        }
                        maxSide = Math.max(maxSide,dp[i][j]);
                    }
                }
            }
            int maxSquare = maxSide*maxSide;
            return maxSquare;
        }

        /**
         * 三角形最小路径和
         * 给定一个三角形 triangle ，找出自顶向下的最小路径和。
         *
         * 每一步只能移动到下一行中相邻的结点上。相邻的结点 在这里指的是 下标 与 上一层结点下标 相同或者等于 上一层结点下标 + 1 的两个结点。也就是说，如果正位于当前行的下标 i ，那么下一步可以移动到下一行的下标 i 或 i + 1
         *
         * 动态规划，空间优化方法，有点不太懂
         */
        public int minimumTotal(List<List<Integer>> triangle) {
            int length = triangle.size();
            int []dp = new int[length+1];
            for(int i = length-1;i>=0;i--) {
                for(int j = 0;j<=i;j++) {
                    dp[j] = Math.min(dp[j],dp[j+1])+triangle.get(i).get(j);
                }
            }
            return dp[0];
        }

        /**
         * 三数之和
         *
         * 给你一个包含 n 个整数的数组 nums，判断 nums 中是否存在三个元素 a，b，c ，使得 a + b + c = 0 ？请你找出所有和为 0 且不重复的三元组。
         * 注意：答案中不可以包含重复的三元组
         * 思路：数组+双指针
         *
         * 标签：数组遍历
         * 首先对数组进行排序，排序后固定一个数nums[i]，再使用左右指针指向 nums[i]后面的两端，数字分别为nums[L] 和nums[R]，计算三个数的和
         * sum 判断是否满足为 0，满足则添加进结果集
         * 如果nums[i]大于 0，则三数之和必然无法等于 0，结束循环
         * 如果nums[i] == nums[i−1]，则说明该数字重复，会导致结果重复，所以应该跳过
         * 当 sum == 0 时，nums[L] == nnums[L+1] 则会导致结果重复，应该跳过，L++
         * 当 sum == 0 时，nums[R] == nums[R−1] 则会导致结果重复，应该跳过，R−−
         * 时间复杂度：O(n^2)O(n
         * 2
         *  )，nn 为数组长度
         *
         */
        public List<List<Integer>> threeSum(int[] nums) {
            List<List<Integer>> result = new ArrayList<>();
            int length = nums.length;
            if(nums == null || length <3) {
                return result;
            }
            Arrays.sort(nums);
            for(int i = 0;i<length;i++) {
                if(nums[i]>0) {
                    break;
                }
                if(i>0 && nums[i] == nums[i-1]) {
                    continue;
                }
                int left = i+1;
                int right = length-1;
                while(left < right) {
                    int sum = nums[i]+nums[left]+nums[right];
                    if(sum == 0) {
                        List<Integer> list = new ArrayList<>();
                        list.add(nums[i]);
                        list.add(nums[left]);
                        list.add(nums[right]);
                        result.add(list);
                        while(left<right && nums[left] == nums[left+1]) {
                            left++;
                        }
                        while(left<right && nums[right] == nums[right-1]) {
                            right--;
                        }
                        left++;
                        right--;
                    } else if(sum > 0) {
                        right--;
                    } else if(sum < 0) {
                        left++;
                    }
                }
            }
            return result;
        }

    /**
     * 在排序数组中查找元素出现的第一个和最后一个位置
     * 给定一个按照升序排列的整数数组 nums，和一个目标值 target。找出给定目标值在数组中的开始位置和结束位置。
     *
     * 如果数组中不存在目标值 target，返回 [-1, -1]。
     * 二分查找
     *
     */
    public int [] searchRange(int [] nums,int target) {
        int leftIndex = binarySearch(nums,target);
        int rightIndex = binarySearch(nums,target+1)-1;
        if(leftIndex<=rightIndex && nums[leftIndex] == nums[rightIndex] && nums[leftIndex] == target) {
            return new int[]{leftIndex,rightIndex};
        }
        return new int []{-1,-1};
    }

    //找到大于等于target的第一个元素的下标
    public int binarySearch(int [] nums,int target) {
        int left = 0;
        int right = nums.length-1;
        while(left<=right) {
            int mid = (left+right)/2;
            if(nums[mid]>=target) {
                right = mid-1;
            } else {
                left = mid+1;
            }
        }
        return left;
    }


    /**
     * 合并区间
     * 以数组 intervals 表示若干个区间的集合，其中单个区间为 intervals[i] = [starti, endi] 。请你合并所有重叠的区间，并返回一个不重叠的区间数组，该数组需恰好覆盖输入中的所有区间。
     *
     */
    public int [][] merge(int [][] intervals) {
        if(intervals.length == 0) {
            return new int[0][2];
        }
        Arrays.sort(intervals,(v1,v2)->v1[0]-v2[0]);
        List<int []> merged = new ArrayList<>();
        for(int i = 0;i<intervals.length;i++) {
            int left = intervals[i][0];
            int right = intervals[i][1];
            if(merged.size() == 0 || merged.get(merged.size()-1)[1]<left) {
                merged.add(new int []{left,right});
            } else {
                merged.get(merged.size()-1)[1] = Math.max(merged.get(merged.size()-1)[1],right);
            }
        }
        return merged.toArray(new int[merged.size()][]);
    }

    /**
     * 最长连续递增序列
     * 给定一个未经排序的整数数组，找到最长且连续递增的子序列，并返回该序列的长度。
     * 连续递增的子序列 可以由两个下标 l 和 r（l < r）确定，如果对于每个 l <= i < r，都有 nums[i] < nums[i + 1] ，
     * 那么子序列 [nums[l], nums[l + 1], ..., nums[r - 1], nums[r]] 就是连续递增子序列。
     *
     * 思路：贪心算法
     */
    public int findLengthOfLCIS(int [] nums) {
        int result = 0;
        int start = 0;
        int length = nums.length;
        for(int i = 0;i<length;i++) {
            if(i>0 && nums[i]<=nums[i-1]) {
                start = i;
            }
            result = Math.max(result,i-start+1);
        }
        return result;
    }

    /**
     * 最长连续子序列
     * 给定一个未排序的整数数组 nums ，找出数字连续的最长序列（不要求序列元素在原数组中连续）的长度。
     * 进阶：你可以设计并实现时间复杂度为 O(n) 的解决方案吗？
     */
    public int longestConsecutive(int[] nums) {
        Set<Integer> set = new HashSet<>();
        for(int i = 0;i<nums.length;i++) {
            set.add(nums[i]);
        }

        int longestStreak = 0;
        for(int num:set) {
            if(!set.contains(num-1)) {
                int currentNum = num;
                int currentStreak = 1;
                while(set.contains(currentNum+1)) {
                    currentNum += 1;
                    currentStreak += 1;
                }
                longestStreak = Math.max(longestStreak,currentStreak);
            }
        }
        return longestStreak;
    }

    /**
    * 方法二：先排序，后遍历数组，判断最长连续子序列长度
    **/
    public int longestConsecutive1(int [] nums) {
        if(nums == null || nums.length == 0) {
            return 0;
        }
        if(nums.length == 1) {
            return 1;
        }
        Arrays.sort(nums);
        int maxLength = 1;
        int len = 1;
        for(int i = 1;i<nums.length;i++) {
            if(nums[i] == nums[i-1]) {
                continue;
            } else if(nums[i] == nums[i-1]+1) {
                len++;
            } else {
                len = 1;
            }
            maxLength = Math.max(maxLength,len);
        }
        return maxLength;
    }

    /**
     * 最长递增子序列
     * 给你一个整数数组 nums ，找到其中最长严格递增子序列的长度。
     * 子序列是由数组派生而来的序列，删除（或不删除）数组中的元素而不改变其余元素的顺序。例如，[3,6,2,7] 是数组 [0,3,1,6,2,2,7] 的子序列。
     *
     * 方法一：动态规划
     */
    public int lengthOfLIS(int [] nums) {
        if(nums.length == 0) {
            return 0;
        }
        int [] dp = new int [nums.length];
        dp[0] = 1;
        int maxLength = 1;
        for(int i = 1;i<nums.length;i++) {
            dp[i] = 1;
            for(int j = 0;j<i;j++) {
                if(nums[i]>nums[j]) {
                    dp[i] = Math.max(dp[i],dp[j]+1);
                }
            }
            maxLength = Math.max(maxLength,dp[i]);
        }
        return maxLength;
    }

    /**
     * 方法二：贪心算法+二分查找
     */
    public int lengthOfLIS1(int [] nums) {
        return 0;
    }

    /**
     * 数组中重复的数字
     * 找出数组中重复的数字。
     *
     * 在一个长度为 n 的数组 nums 里的所有数字都在 0～n-1 的范围内。数组中某些数字是重复的，但不知道有几个数字重复了，也不知道每个数字重复了几次。请找出数组中任意一个重复的数字
     * 方法一：使用hashMap
     */
    public int findRepeatNumber(int [] nums) {
        Map<Integer,Integer> map = new HashMap<>();
        for(int i = 0;i<nums.length;i++) {
            if(map.containsKey(nums[i])) {
                return nums[i];
            } else {
                map.put(nums[i],1);
            }
        }
        return -1;
    }

    /**
     * 方法二：原地置换
     *
     * 如果没有重复数字，那么正常排序后，数字i应该在下标为i的位置，所以思路是重头扫描数组，遇到下标为i的数字如果不是i的话，（假设为m),那么我们就拿与下标m的数字交换。在交换过程中，如果有重复的数字发生，那么终止返回ture
     *
     */
    public int findRepeatNumber1(int [] nums) {
        int i = 0;
        while(i<nums.length) {
            if(nums[i] == i) {
                i++;
                continue;
            }
            if(nums[nums[i]] == nums[i]) {
                return nums[i];
            }
            int temp = nums[i];
            nums[i] = nums[temp];
            nums[temp] = temp;
        }
        return -1;
    }
}
