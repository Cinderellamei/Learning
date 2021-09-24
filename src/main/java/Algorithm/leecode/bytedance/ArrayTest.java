package Algorithm.leecode.bytedance;

import com.sun.jmx.remote.internal.ArrayQueue;

import java.util.*;

public class ArrayTest {
    /**
     * 合并有序数组
     *
     * 给你两个有序整数数组 nums1 和 nums2，请你将 nums2 合并到 nums1 中，使 nums1 成为一个有序数组。
     *
     * 初始化 nums1 和 nums2 的元素数量分别为 m 和 n 。你可以假设 nums1 的空间大小等于 m + n，这样它就有足够的空间保存来自 nums2 的元素
     * 思路：双指针，因为都是排好序的，所以从后向前遍历，将nums1和nums2中较大的数字放到nums1的结尾
     * 时间复杂度O(m+n)，空间复杂度O(1)
     */
    public void merge(int [] nums1,int m,int [] nums2,int n) {
        int i = nums1.length;
        while(n>0) {
            if(m>0&&nums1[m-1]>nums2[n-1]) {
                nums1[--i]=nums1[--m];
            } else {
                nums1[--i] = nums2[--n];
            }
        }
    }

    /**
     * 使用最小花费爬楼梯
     * 数组的每个下标作为一个阶梯，第 i 个阶梯对应着一个非负数的体力花费值 cost[i]（下标从 0 开始）。
     * 每当你爬上一个阶梯你都要花费对应的体力值，一旦支付了相应的体力值，你就可以选择向上爬一个阶梯或者爬两个阶梯。
     * 请你找出达到楼层顶部的最低花费。在开始时，你可以选择从下标为 0 或 1 的元素作为初始阶梯。
     * cost 的长度范围是 [2, 1000]
     *
     * 思路：假设数组 \textit{cost}cost 的长度为 nn，则 nn 个阶梯分别对应下标 00 到 n-1n−1，楼层顶部对应下标 nn，问题等价于计算达到下标 nn 的最小花费。可以通过动态规划求解。
     * 创建长度为 n+1n+1 的数组 \textit{dp}dp，其中 \textit{dp}[i]dp[i] 表示达到下标 ii 的最小花费。
     * 由于可以选择下标 00 或 11 作为初始阶梯，因此有 \textit{dp}[0]=\textit{dp}[1]=0dp[0]=dp[1]=0。
     * 当 2 \le i \le n2≤i≤n 时，可以从下标 i-1i−1 使用 \textit{cost}[i-1]cost[i−1] 的花费达到下标 ii，或者从下标 i-2i−2 使用 \textit{cost}[i-2]cost[i−2] 的花费达到下标 ii。为了使总花费最小，\textit{dp}[i]dp[i] 应取上述两项的最小值
     *
     */
    public int minCostClimbingStairs(int [] cost) {
        int n = cost.length;
        int pre = 0;
        int cur = 0;
        for(int i = 2;i<=n;i++) {
            int next = Math.min(cur+cost[i-1],pre+cost[i-2]);
            pre = cur;
            cur = next;
        }
        return cur;
    }

    /**
     * 排序数组
     * 给你一个整数数组 nums，请你将该数组升序排列
     */

    /**
     * 多数元素
     * 给定一个大小为 n 的数组，找到其中的多数元素。多数元素是指在数组中出现次数 大于 ⌊ n/2 ⌋ 的元素。
     * 你可以假设数组是非空的，并且给定的数组总是存在多数元素。
     * 要求：复杂度为 O(n)、空间复杂度为 O(1)
     *
     * Boyer-Moore 投票算法
     * 我们维护一个候选众数 candidate 和它出现的次数 count。初始时 candidate 可以为任意值，count 为 0；
     *
     * 我们遍历数组 nums 中的所有元素，对于每个元素 x，在判断 x 之前，如果 count 的值为 0，我们先将 x 的值赋予 candidate，随后我们判断 x：
     *
     * 如果 x 与 candidate 相等，那么计数器 count 的值增加 1；
     *
     * 如果 x 与 candidate 不等，那么计数器 count 的值减少 1。
     *
     * 在遍历完成后，candidate 即为整个数组的众数。
     */
    public int majorityElement(int [] nums) {
        int count = 0;
        Integer candidate = null;
        for(int num:nums) {
            if(count == 0) {
                candidate = num;
            }
            count+=(num == candidate)?1:-1;
        }
        return candidate;
    }

    /**
     * 有序数组的平方
     * 给你一个按 非递减顺序 排序的整数数组 nums，返回 每个数字的平方 组成的新数组，要求也按 非递减顺序 排序。
     *
     * 思路：因为数组有序，如果全部都是非负数，平方后应该也是有序，但需要考虑存在负数情况，所以使用双指针，一个从前向后遍历，一个从后向前遍历，
     * 平方后比较，将大的数放入数组的后面，
     *
     */
    public int [] sortedSquares(int [] nums) {
        int n = nums.length;
        int [] result = new int[n];
        for(int i = 0,j=n-1,pos=n-1;i<=j;) {
            if(nums[i]*nums[i]>nums[j]*nums[j]) {
                result[pos--] = nums[i]*nums[i];
                ++i;
            } else {
                result[pos--] = nums[j]*nums[j];
                --j;
            }
        }
        return result;
    }

    /**
     * 有序数组两数之和
     *
     * 给定一个已按照 升序排列  的整数数组 numbers ，请你从数组中找出两个数满足相加之和等于目标数 target 。
     *
     * 函数应该以长度为 2 的整数数组的形式返回这两个数的下标值。numbers 的下标 从 1 开始计数 ，所以答案数组应当满足 1 <= answer[0] < answer[1] <= numbers.length 。
     *
     * 你可以假设每个输入只对应唯一的答案，而且你不可以重复使用相同的元素。
     *
     * 思路：双指针，一个指针从前往后，一个指针从后往前
     */
    public int [] twoSum(int [] numbers,int target) {
       int low = 0;
       int high = numbers.length-1;
       while(low<high) {
           int sum = numbers[low]+numbers[high];
           if(sum == target) {
               return new int[]{low+1,high+1};
           } else if(sum<target) {
               low++;
           } else if(sum>target) {
               high--;
           }
       }
       return new int [] {-1,-1};
    }

    /**
     * 排序矩阵查找
     * 给定M×N矩阵，每一行、每一列都按升序排列，请编写代码找出某元素。
     *
     * 从右上角开始查找
     */
    public boolean searchMatrix(int [][] matrix,int target) {
        if(matrix == null || matrix.length == 0 || matrix[0].length == 0) {
            return false;
        }
        int row = matrix.length;
        int col = matrix[0].length;
        if(target<matrix[0][0] || target > matrix[row-1][col-1]) {
            return false;
        }
        int i = 0;
        int j = col-1;
        while(i<row && j>=0) {
            if(target > matrix[i][j]) {
                i++;
            } else if(target < matrix[i][j]) {
                j--;
            } else if(target == matrix[i][j]) {
                return true;
            }
        }
        return false;
    }

    /**
     * 和为k的子数组
     * 给定一个整数数组和一个整数 k，你需要找到该数组中和为k的连续的子数组的个数。
     */
    public int subarraySum(int [] nums,int k) {
        int count = 0;
        for(int start = 0;start<nums.length;start++) {
            int sum = 0;
            for(int end = start;end>=0;end--) {
                sum += nums[end];
                if(sum == k) {
                    count++;
                }
            }
        }
        return count;
    }

    /**
     * 找到数组中消失的数字
     */
    public List<Integer> findDisappearedNumbers(int [] nums) {
        int n = nums.length;
        for(int num:nums) {
            int x = (num-1)%n;//找到数字num存放在数组中的下标
            nums[x] += n;
        }
        List<Integer> result = new ArrayList<>();
        for(int i = 0;i<n;i++) {
            if(nums[i]<=n) {
                result.add(i+1);
            }
        }
        return result;
    }


    /**
     * 调整数据顺序使奇数在前偶数在后
     * 输入一个整数数组，实现一个函数来调整该数组中数字的顺序，使得所有奇数位于数组的前半部分，所有偶数位于数组的后半部分。
     * 方法一：新建一个数组，两个指针分别指向头和尾，奇数从头插入，偶数从尾插入
     */
    public int [] exchange(int [] nums) {
        int [] result = new int[nums.length];
        int x = 0;
        int y = nums.length-1;
        for(int i = 0;i<nums.length;i++) {
            if(nums[i]%2 == 1) {
                result[x++] = nums[i];
            } else if(nums[i]%2 == 0) {
                result[y--] = nums[i];
            }
        }
        return result;
    }

    /**
     *
     *方法二：首尾双指针
     * left指向头，right指向尾，left一直向右移，直到他指向的数为偶数，right一直向左移，直到他指向的数为奇数
     * 交换nums[left]和nums[right]
     * 重复上述操作，直到left=right
     */
    public int [] exchange1(int [] nums) {
        int left = 0;
        int right = nums.length-1;
        while(left<right) {
            if(nums[left]%2 == 1) {
                left++;
                continue;
            }
            if(nums[right] % 2 == 0) {
                right--;
                continue;
            }
            int temp = nums[left];
            nums[left] = nums[right];
            nums[right] = temp;
        }
        return nums;
    }


    /**
     * 长度为n的整数数组，给定一个小于n的整数k，找到出现次数大于n/k的数字
     * 思路：用hashmap，遍历一次数组，每次从数组中删除k个不同的数字，当删除了n/k次后，仍在数组中的数字至少出现次数大于n/k
     * j解法还有点问题，部分case跑不过
     */
    public static int [] timesMoreThan(int [] nums,int n,int k) {
        Map<Integer,Integer> map = new HashMap<>();
        for(int i = 0;i<nums.length;i++) {
            if(map.containsKey(nums[i])) {
                map.put(nums[i],map.get(nums[i])+1);
            } else {
                map.put(nums[i],1);
            }
        }

        int count = 1;
        while(count <= n/k) {
            if(map.isEmpty()) {
                return new int[]{-1};
            }
            for(int num:map.keySet()) {
                if(map.get(num) == 0) {
                    map.remove(num);
                } else {
                    map.put(num,map.get(num)-1);
                    if(map.get(num) == 0) {
                        map.remove(num);
                    }
                }
            }
            count+=1;
        }

        int length = map.size();
        if(length == 0) {
            return new int[]{-1};
        }
        int [] result = new int[length];
        int index = 0;
        for(int num:map.keySet()) {
            result[index++] = num;
        }
        return result;
    }

    /**
     * 排序数组
     * 给你一个整数数组 nums，请你将该数组升序排列。
     *
     * 方法一：选择排序，每次从未排序数中选择一个最小的数，放到序列的起始位置，直到全部待排序的数字排序完成
     * 时间复杂度：O(n2)
     * 空间复杂度：O(1)
     * 不稳定的
     */
    public int[] sortArray(int[] nums) {
        int length = nums.length;
        for(int i = 0;i<length;i++) {
            int minIndex = i;
            for(int j = i+1;j<length;j++) {
                if(nums[j]<nums[minIndex]) {
                    minIndex = j;
                }
            }
            int temp = nums[minIndex];
            nums[minIndex] = nums[i];
            nums[i] = temp;
        }
        return nums;
    }

    /**
     * 方法二：插入排序：每次讲一个待排序数字插入到一个已经排好序的序列中
     * 时间复杂度：O(n2)
     * 空间复杂度：O(1)
     * 稳定的
     */
    public static int [] sortArray1(int [] nums) {
        for(int i = 0;i<nums.length-1;i++) {
            for(int j = i+1;j>0;j--) {
                if(nums[j]<nums[j-1]) {
                    int temp = nums[j-1];
                    nums[j-1] = nums[j];
                    nums[j] =temp;
                }
            }
        }
        return nums;
    }

    /**
     * 方法三：冒泡排序：外层循环每次经过两两比较，把每一轮未排序部分最大的元素放到数组末尾
     * 时间复杂度：O(n2)
     * 空间复杂度:O(1)
     * 稳定的
     */
    public static int [] sortArray2(int [] nums) {
        boolean isSorted = true;
        for(int i = nums.length-1;i>=0;i--) {
            for(int j = 0;j<i;j++) {
                if(nums[j]>nums[j+1]) {
                    int temp = nums[j];
                    nums[j] = nums[j+1];
                    nums[j+1] = temp;
                    isSorted = false;
                }
            }
            if(isSorted) {
                break;
            }
        }
        return nums;
    }

    /**
     *方法四：归并排序：借助额外空间，合并两个有序数组，得到更长的有序数组
     * 时间复杂度：O(nlogn)
     * 空间复杂度：O(n)
     * 稳定的
     */
    public static int [] sortArray3(int [] nums) {

        return nums;
    }

    /**
     * 方法五：快速排序：
     * 时间复杂度：O(nlogn)
     * 空间复杂度：O(logn)
     */
    public static int [] sortArray4(int [] nums) {
        quickSort(nums,0,nums.length-1);
        return nums;
    }

    public static void quickSort(int [] nums,int low,int high) {
        if(low>high) {
            return;
        }
        int temp = nums[low];
        int i = low;
        int j = high;
        while(i<j) {
            if(i<j && nums[j]>=temp) {
                j--;
            }
            nums[i] = nums[j];
            if(i<j && nums[i]<=temp) {
                i++;
            }
            nums[j] = nums[i];
        }
        nums[i] = temp;
        quickSort(nums,low,i-1);
        quickSort(nums,i+1,high);
    }

    /**
     * 全排列
     * 输入一个不含重复数字的数组，返回其所有可能的全排列，可以按任意顺序返回
     * 方法：回溯算法
     */
    public static  List<List<Integer>> permute(int [] nums) {
        int length = nums.length;
        List<List<Integer>> result = new ArrayList<>();
        if(length == 0) {
            return result;
        }

        boolean [] used = new boolean[length];
        Deque<Integer> path = new ArrayDeque<>(length);
        dfs(nums,length,0,path,used,result);
        return result;
    }

    private static void dfs(int [] nums,int length,int depth,Deque<Integer> path,boolean [] used,List<List<Integer>> result) {
        if(length == depth) {
            result.add(new ArrayList<>(path));
            return;
        }

        for(int i = 0;i<length;i++) {
            if(!used[i]) {
                path.addLast(nums[i]);
                used[i] = true;

                dfs(nums,length,depth+1,path,used,result);

                used[i] = false;
                path.removeLast();
            }
        }
    }


    /**
     * 回溯算法，空间复杂度高
     */
    public List<List<Integer>> permute1(int [] nums) {
        int len = nums.length;
        List<List<Integer>> result = new ArrayList<>();
        if(len == 0) {
            return result;
        }

        boolean [] used = new boolean[len];
        List<Integer> path = new ArrayList<>();

        dfs1(nums,len,0,path,used,result);
        return result;
    }

    private void dfs1(int [] nums,int length,int depth,List<Integer> path,boolean [] used,List<List<Integer>> result) {
        if(depth == length) {
            result.add(path);
            return;
        }

        for(int i = 0;i<length;i++) {
            if(!used[i]) {
                List<Integer> newPath = new ArrayList<>(path);
                newPath.add(nums[i]);

                boolean [] newUsed = new boolean[length];
                System.arraycopy(used,0,newUsed,0,length);
                newUsed[i] = true;

                dfs1(nums,length,depth+1,newPath,newUsed,result);
            }
        }
    }

    /**
     * 全排列二
     * 给定一个可包含重复数字的序列 nums ，按任意顺序 返回所有不重复的全排列。
     */
    public static  List<List<Integer>> permuteUnique(int [] nums) {
        int length = nums.length;
        List<List<Integer>>  result = new ArrayList<>();
        if(length == 0) {
            return result;
        }

        Arrays.sort(nums);

        boolean [] used = new boolean[length];

        Deque<Integer> path = new ArrayDeque<>(length);
        dfss(nums,length,0,used,path,result);
        return result;
    }

    private static void dfss(int [] nums,int length,int depth,boolean [] used,Deque<Integer> path,List<List<Integer>> result) {
        if(depth == length) {
            result.add(new ArrayList<>(path));
            return;
        }

        for(int i = 0;i<length;i++) {
            if(used[i]) {
                continue;
            }

            if(i>0 && nums[i] == nums[i-1] &&!used[i-1]) {
                continue;
            }

            path.addLast(nums[i]);
            used[i] = true;

            dfss(nums,length,depth+1,used,path,result);

            path.removeLast();
            used[i] = false;

        }
    }


    /**
     * 寻找数组的峰值
     * 峰值元素是指其值大于左右相邻值的元素。
     *
     * 给你一个输入数组 nums，找到峰值元素并返回其索引。数组可能包含多个峰值，在这种情况下，返回 任何一个峰值 所在位置即可。
     */
    public int findPeakElement(int [] nums) {
        for(int i = 0;i<nums.length-1;i++) {
            if(nums[i]>nums[i+1]) {
                return i;
            }
        }
        return -1;
    }

    /**
     * 删除有序数组的重复项
     * 要求：原地删除
     * 方法：双指针，指针p和q，p指向第一个元素，q指向第二个元素，当q指向的元素与p指向的元素不同时，将q所指向的元素插入到p元素后面，插入成功后
     * p加1，q加1如果q所指的元素与p所指的元素重复，则q加1
     */
    public  static int removeDuplicates(int[] nums) {
        int p = 0;
        int q = 1;
        while(q<nums.length) {
            if(nums[p]!= nums[q]) {
                if(q-p>1) {
                    nums[p+1] = nums[q];
                }
                p++;
                q++;
            } else {
                q++;
            }

        }
        return p+1;
    }

    /**
     * 岛屿的最大面积
     *
     * 给定一个包含了一些0和1的非空二维数组 grid 。
     * 一个岛屿是由一些相邻的1(代表土地)构成的组合，这里的「相邻」要求两个1必须在水平或者竖直方向上相邻。你可以假设grid的四个边缘都被0（代表水）包围着。
     * 找到给定的二维数组中最大的岛屿面积。(如果没有岛屿，则返回面积为 0 。)
     */
    public int maxAreaOfIsland(int[][] grid) {
        int result = 0;
        for(int i = 0;i<grid.length;i++) {
            for(int j = 0;j<grid[i].length;j++) {
                if(grid[i][j] == 1) {
                    result = Math.max(result,dfs(i,j,grid));
                }
            }
        }
        return result;
    }

    /**
     * 每次调用默认num是1，进入后判断，如果不是岛屿，则直接返回0，
     * 每次找到岛屿，先直接把岛屿改为0，即沉岛思想，每次碰到岛屿，将岛屿和周围的都沉没，避免再次被遍历到
     */
    public int dfs(int i,int j,int [][] grid) {
        if(i<0 || j<0 || i>=grid.length || j>=grid[i].length || grid[i][j] == 0) {
            return 0;
        }
        int num = 1;
        grid[i][j] = 0;
        num += dfs(i+1,j,grid);
        num += dfs(i,j+1,grid);
        num += dfs(i-1,j,grid);
        num += dfs(i,j-1,grid);
        return num;
    }


}
