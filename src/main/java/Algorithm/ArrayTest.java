package Algorithm;

import java.util.*;

public class ArrayTest {

    /*在一个二维数组中（每个一纬数组的长度一致），每一行按照从做到右递增的顺序，每一列按照从上到下递增的顺序，给定一个二维数组和一个整数，判断这个整数是否在这个二维数组中
    思路：从右上角开始找
     */
    public static boolean findKInArray(int [][] arr,int target) {
        int row = arr.length ;
        int col = arr[0].length ;
        if(row == 0 || col == 0) return false ;
        if(target < arr[0][0] || target > arr[row-1][col-1]) return false ;
        int i = 0 ;
        int j = col - 1 ;
        while(i < row && j >= 0) {
            if(target < arr[i][j]) {
                j-- ;
            } else if(target > arr[i][j]) {
                i++ ;
            } else if(target == arr[i][j]) {
                return true;
            }
        }
        return false;
    }

    /*
    旋转数组的最小数值(把一个数组最开始的若干个元素搬到末尾，成为数组的旋转)
    把一个数组最开始的若干个元素搬到数组的末尾，我们称之为数组的旋转。输入一个非递减排序的数组的一个旋转，输出旋转数组的最小元素。
    例如数组{3,4,5,1,2}为{1,2,3,4,5}的一个旋转，该数组的最小值为1。
    NOTE：给出的所有元素都大于0，若数组大小为0，请返回0。
    思路：二分查找法
     */
    public static int minNumberInRotateArray(int [] array) {
        if(array.length == 0) return -1;
        int low = 0;
        int high = array.length-1;
        while(low < high) {
            int mid = (low+high)/2;
            if(array[mid] < array[high]) {
                high = mid;
            } else if(array[mid] == array[high]) {
                high--;
            } else if(array[mid] > array[high]) {
                low = mid + 1;
            }
        }
        return array[low];
    }

    /*
    输入一个整数数组，使得奇数位于偶数的前面，且不改变数字原有的顺序
     */
    public static void reOrderArray(int [] array) {
        if(array.length == 0) return ;
        int [] result = new int[array.length];
        int count = 0;
        for(int i = 0;i<array.length;i++) {
            if(array[i]%2 == 1) count++;
        }
        int begin = 0;
        for(int i = 0;i<array.length;i++) {
            if(array[i]%2 == 1) {
                result[begin++] = array[i];;
            } else if(array[i]%2 == 0) {
                result[count++] = array[i];
            }
        }
        for(int i = 0;i<result.length;i++) {
            array[i] = result[i];
        }
    }

    /*
    输入一个矩阵，按照从外向里以顺时针的顺序依次打印出每一个数字，
    例如，如果输入如下4 X 4矩阵： 1 2 3 4 5 6 7 8 9 10 11 12 13 14 15 16 则依次打印出数字1,2,3,4,8,12,16,15,14,13,9,5,6,7,11,10.
     */
    public static ArrayList printMatrix(int [][] matrix) {
        ArrayList<Integer> result = new ArrayList<Integer>();
        if(matrix == null || matrix.length == 0) {
            return result;
        }
        print(matrix,0,0,matrix.length-1,matrix[0].length-1,result);
        return result;
    }

    public static void print(int [][] matrix,int startRow,int startCol,int endRow,int endCol,ArrayList<Integer> result) {
        if(startRow<endRow && startCol<endCol) {
            for(int j = startCol;j<=endRow-1;j++) {
                result.add(matrix[startRow][j]);
            }
            for(int i = startRow;i<=endRow-1;i++) {
                result.add(matrix[i][endCol]);
            }
            for(int j = endCol;j>=startCol+1;j--) {
                result.add(matrix[endRow][j]);
            }
            for(int i = endRow;i>=startRow+1;i--) {
                result.add(matrix[i][startCol]);
            }
            print(matrix,startRow+1,startCol+1,endRow-1,endCol-1,result);
        } else if(startRow<endRow && startCol == endCol) {
            for(int i = startRow;i<=endRow;i++) {
                result.add(matrix[i][endCol]);
            }
        } else if(startRow == endRow && startCol < endCol) {
            for(int j = startCol ;j<=endCol;j++) {
                result.add(matrix[startRow][j]);
            }
        } else if(startRow == endRow && startCol == endCol) {
            result.add(matrix[endRow][endCol]);
        } else {
            return;
        }
    }

    /*
    数组中有一个数字出现的次数超过数组长度的一半，请找出这个数字。例如输入一个长度为9的数组{1,2,3,2,2,2,5,4,2}。由于数字2在数组中出现
    了5次，超过数组长度的一半，因此输出2。如果不存在则输出0
    思路：hashMap，遍历数组，将数字作为key，数字出现的次数作为value，存入hashMap中，然后遍历该map，找出value大于数组长度一半的值，
    输出该数字（缺点：需要hashMap存储，占用存储空间较大）
     */
    public static int moreThanHalf(int [] array) {
        HashMap<Integer,Integer> map = new HashMap<Integer,Integer>();
        for(int i = 0;i<array.length;i++) {
            if(map.containsKey(array[i])) {
                map.put(array[i],map.get(array[i])+1);
            } else {
                map.put(array[i],1);
            }
        }
        int result = 0;
        for(Integer key:map.keySet()) {
            if(map.get(key)>array.length/2) {
                result = key;
            }
        }
        return result;
    }

    //改进：每次更新值之后判断当前数字出现的次数是否大于数组长度的一半，不再遍历一次map
    public class Solution {
        public int MoreThanHalfNum_Solution(int [] array) {
            HashMap<Integer,Integer> map = new HashMap<Integer,Integer>();
            for(int i = 0;i<array.length;i++) {
                if(map.containsKey(array[i])) {
                    map.put(array[i],map.get(array[i])+1);
                } else {
                    map.put(array[i],1);
                }
                if(map.get(array[i])>array.length/2) {
                    return array[i];
                }
            }
            return 0;
        }
    }

    /*输入n个整数，找出其中最小的K个数。例如输入4,5,1,6,2,7,3,8这8个数字，则最小的4个数字是1,2,3,4。
    思路：利用冒泡排序的思想，每次将最小的数换到数组最后面
     */

    public static ArrayList<Integer> getLeastNumber(int [] input,int k) {
        ArrayList<Integer> result = new ArrayList<Integer>();
        if(input.length == 0 || k > input.length) return result;
        for(int i = 0;i<k;i++) {
            for(int j = 0;j<input.length-i-1;j++) {
                if(input[j]<input[j+1]) {
                    int temp = input[j];
                    input[j] = input[j+1];
                    input[j+1] = temp;
                }
            }
            result.add(input[input.length-i-1]);
        }
        return result;
    }

    /*
    给一个数组，返回它的最大连续子序列的和（需要考虑有负数的情况）
    思路：对一个数A来说，若A的左边累加非负，那么加上A能使得值不小于A，认为累加值对整体和有贡献，若前几项累加值为负数，则认为有害于总和，total记录当前值
    此时，若和大于maxSum，则用maxSum记录下来
     */
    public static int finGreatestSumOfSubArray(int [] array) {
        if(array.length == 0) return 0;
        int total = array[0];
        int maxSum = array[0];
        for(int i = 1;i<array.length;i++) {
            if(total>=0) {
                total += array[i];
            } else if(total < 0) {
                total = array[i];
            }
            if(total > maxSum) {
                maxSum = total;
            }
        }
        return maxSum;
    }

    /*
    求出任意非负整数区间中1出现的次数（从1 到 n 中1出现的次数）
    思路：
     */
    public static int numberOfOneBetweenOneAndN(int n) {
        int result = 0;
        return result;
    }

    /*
    输入一个正整数数组，把数组里所有数字拼接起来排成一个数，打印能拼接出的所有数字中最小的一个。例如输入数组{3，32，321}，
    则打印出这三个数字能排成的最小数字为321323
    思路：自定义一个比较大小的函数，比较两个字符串s1, s2大小的时候，先将它们拼接起来，比较s1+s2,和s2+s1那个大，如果s1+s2大，
    那说明s2应该放前面，所以按这个规则，s2就应该排在s1前面
     */
    public static String printMinNumber(int [] array) {
        String str = "";
        for(int i = 0;i<array.length;i++) {
            for(int j = i+1;j<array.length;j++) {
                int a = Integer.valueOf(array[i]+""+array[j]);
                int b = Integer.valueOf(array[j]+""+array[i]);
                if(a > b) {
                    int temp = array[i];
                    array[i] = array[j];
                    array[j] = temp;
                }
            }
        }
        for(int i = 0;i < array.length;i++) {
            str += String.valueOf(array[i]);
        }
        return str;
    }

    /*
    把只包含质因子2、3和5的数称作丑数（Ugly Number）。例如6、8都是丑数，但14不是，因为它包含质因子7。
    思路：习惯上我们把1当做是第一个丑数。求按从小到大的顺序的第N个丑数。
    首先从丑数的定义我们知道，一个丑数的因子只有2,3,5，那么丑数p = 2 ^ x * 3 ^ y * 5 ^ z，换句话说一个丑数一定由另一个丑数乘以2或者乘以3或者乘以5得到，那么我们从1开始乘以2,3,5，就得到2,3,5三个丑数，在从这三个丑数出发乘以2,3,5就得到4，6,10,6，9,15,10,15,25九个丑数，我们发现这种方***得到重复的丑数，而且我们题目要求第N个丑数，这样的方法得到的丑数也是无序的。那么我们可以维护三个队列：
（1）丑数数组： 1
乘以2的队列：2
乘以3的队列：3
乘以5的队列：5
选择三个队列头最小的数2加入丑数数组，同时将该最小的数乘以2,3,5放入三个队列；
（2）丑数数组：1,2
乘以2的队列：4
乘以3的队列：3，6
乘以5的队列：5，10
选择三个队列头最小的数3加入丑数数组，同时将该最小的数乘以2,3,5放入三个队列；
（3）丑数数组：1,2,3
乘以2的队列：4,6
乘以3的队列：6,9
乘以5的队列：5,10,15
选择三个队列头里最小的数4加入丑数数组，同时将该最小的数乘以2,3,5放入三个队列；
（4）丑数数组：1,2,3,4
乘以2的队列：6，8
乘以3的队列：6,9,12
乘以5的队列：5,10,15,20
选择三个队列头里最小的数5加入丑数数组，同时将该最小的数乘以2,3,5放入三个队列；
（5）丑数数组：1,2,3,4,5
乘以2的队列：6,8,10，
乘以3的队列：6,9,12,15
乘以5的队列：10,15,20,25
选择三个队列头里最小的数6加入丑数数组，但我们发现，有两个队列头都为6，所以我们弹出两个队列头，同时将12,18,30放入三个队列；
……………………
疑问：
1.为什么分三个队列？
丑数数组里的数一定是有序的，因为我们是从丑数数组里的数乘以2,3,5选出的最小数，一定比以前未乘以2,3,5大，同时对于三个队列内部，按先后顺序乘以2,3,5分别放入，所以同一个队列内部也是有序的；
2.为什么比较三个队列头部最小的数放入丑数数组？
因为三个队列是有序的，所以取出三个头中最小的，等同于找到了三个队列所有数中最小的。
实现思路：
我们没有必要维护三个队列，只需要记录三个指针显示到达哪一步；“|”表示指针,arr表示丑数数组；

     */
    public static int getUglyNumber(int index) {
        if(index < 7) return index;
        int [] result = new int[index];
        int count = 0;
        int p2 = 0;
        int p3 = 0;
        int p5 = 0;

        result[0] = 1;
        int temp = 1;
        while(count < index-1) {
            temp = min(result[p2]*2,min(result[p3]*3,result[p5]*5));
            if(temp == result[p2]*2) p2++;
            if(temp == result[p3]*3) p3++;
            if(temp == result[p5]*5) p5++;
            result[++count] = temp;
        }
        return result[index-1];
    }

    public static int min(int a,int b) {
        return (a<b)?a:b;
    }

    /*
    数组中的逆序对
     */
    public static int InversePairs(int [] arr) {
        return 0;
    }

    /*
    给定一个数组和一个数，去找数组中和为这个数的两个数
    思路：用hashMap，key存储数组的值，value存储
     */
    public static int [] findSum(int [] arr,int target) {
        int [] result = new int[]{-1,-1};
        if(arr.length == 0 ||arr.length <2) {
            return result;
        }
        HashMap<Integer,Integer> map = new HashMap<Integer,Integer>();
        for(int i = 0;i<arr.length;i++) {
            if(map.containsKey(target-arr[i])) {
                result[0] = map.get(target-arr[i]);
                result[1] = i;
                break;
            }
            map.put(arr[i],i);
        }
        return result;
    }

    /*
    输入一个递增排序的数组和一个数字s，在数组中查找两个数，使得它们的和正好是s。如果有多对数字的和等于s，则输出任意一对即可。
    思路：如果是无序数组，使用HashMap遍历即可，时间复杂度为O(N)，由于题目给定是排序数组，可以使用双指针进行优化,可将时间复杂度降低至O(1)
    使用双指针，比较nums[i]和nums[j]和和target的大小，若比target小，i加1，若比target大，j-1，若和target相等，则返回
     */
    public static int [] twoSum(int [] nums,int target) {
        int [] result = new int []{-1,-1};
        if(nums.length == 0 || nums.length <2) {
            return result;
        }
        int i = 0;
        int j = nums.length-1;
        while(i < j) {
            int sum = nums[i]+nums[j];
            if(sum>target) {
                j--;
            }
            else if(sum < target) {
                i++;
            }
            else if(sum == target) {
                result[0] = nums[i];
                result[1] = nums[j];
                break;
            }
        }
        return result;
    }

    /*
    统计一个数字在升序数组中出现的次数
    思路：由于是升序数组，则找出K第一次出现的位置和K最后一次出现的位置，即可得出K在数组中出现的次数
    看到生序就考虑用二分法查找
     */
    public int GetNumberOfK(int [] array , int k) {
        if(array.length == 0) return 0;
        int first = getFirst(array,k,0,array.length-1);
        int last = getLast(array,k,0,array.length-1);
        if(first>-1 && last>-1) {
            return last-first+1;
        } else {
            return 0;
        }
    }
    public int getFirst(int[] array,int k,int start,int end) {
        while(start<=end) {
            int mid = (start+end)/2;
            if(array[mid] == k) {
                if(mid == 0 || array[mid-1]!=k) {
                    return mid;
                } else {
                    end = mid-1;
                }
            }else if(array[mid]>k) {
                end = mid-1;
            } else if(array[mid]<k) {
                start = mid+1;
            }
        }
        return -1;
    }
    public int getLast(int[] array,int k,int start,int end) {
        while(start<=end) {
            int mid = (start+end)/2;
            if(array[mid] == k) {
                if(mid == array.length-1 || array[mid+1]!=k) {
                    return mid;
                } else {
                    start = mid+1;
                }
            } else if(array[mid]>k) {
                end = mid-1;
            } else if(array[mid]<k) {
                start = mid+1;
            }
        }
        return -1;
    }


    /*一个整型数组里除了两个数字之外，其他的数字都出现了两次。请写程序找出这两个只出现一次的数字。
    思路：用map存储，key存储数字，value存储该数字出现的次数
     */
    public static void findNumAppereanceOne(int [] arr,int [] num1,int [] num2) {
        if(arr.length == 0) return;
        HashMap<Integer,Integer> map = new HashMap<Integer,Integer>();
        for(int i = 0;i<arr.length;i++) {
            if(map.containsKey(arr[i])) {
                map.put(arr[i],map.get(arr[i])+1);
            } else {
                map.put(arr[i],1);
            }
        }
        int count = 0;
        for(Integer key:map.keySet()) {
            if(map.get(key) == 1 && count == 0) {
                num1[count++] = key;
            } else if(map.get(key) == 1 && count == 1) {
                num2[--count] = key;
                break;
            }
        }
    }

    /*
    输出所有和为S的连续正数序列。序列内按照从小至大的顺序，序列间按照开始数字从小到大的顺序
    思路：采用滑动窗口的方法，1。什么是滑动窗口？顾名思义，首先是一个窗口，既然是一个窗口，就需要用窗口的左边界i和右边界j来唯一表示一个窗口，
    其次，滑动代表，窗口始终从左往右移动，这也表明左边界i和右边界j始终会往后移动，而不会往左移动
    滑动窗口的操作
    扩大窗口，j += 1
    缩小窗口，i += 1
    算法步骤：
    初始化，i=1,j=1, 表示窗口大小为0
    如果窗口中值的和小于目标值sum， 表示需要扩大窗口，j += 1
    否则，如果狂口值和大于目标值sum，表示需要缩小窗口，i += 1
    否则，等于目标值，存结果，缩小窗口，继续进行步骤2,3,4

    这里需要注意2个问题：
    什么时候窗口终止呢，这里窗口左边界走到sum的一半即可终止，因为题目要求至少包含2个数
    什么时候需要扩大窗口和缩小窗口？解释可看上述算法步骤。
    注意：每次扩大滑窗时是先计算滑窗内的和temp，然后++r，所以右边界r在滑窗的外面，
     */
    public static ArrayList<ArrayList<Integer>> findContinusSequence(int sum) {
        ArrayList<ArrayList<Integer>> list = new ArrayList<ArrayList<Integer>>();
        int left = 1;
        int right = 1;
        int temp = 0;
        while(left <= sum/2) {
            if(temp<sum) {
                temp +=right;
                right++;
            } else if(temp >sum) {
                temp -= left;
                left++;
            } else if(temp == sum) {
                ArrayList<Integer> tempList = new ArrayList<>();
                for(int k = left;k<right;k++) {
                    tempList.add(k);
                }
                list.add(tempList);
                temp -= left;
                left++;
            }
        }
        return list;
    }

    /*
    输入一个递增排序的数组和一个数字S，在数组中查找两个数，使得他们的和正好是S，如果有多对数字的和等于S，输出两个数的乘积最小的。
    对应每个测试案例，输出两个数，小的先输出。
    思路：若为无序数组，可用HashMap，考虑到是有序数组，可以采用双指针
     */
    public static ArrayList<Integer> findNumbersWithSum(int [] array,int sum) {
        ArrayList<Integer> result = new ArrayList<>();
        int i = 0;
        int j = array.length-1;
        int temp = sum*sum;
        while(i<j) {
            int count = array[i]+array[j];
            if(count > sum) {
                j--;
            } else if(count < sum) {
                i++;
            } else if(count == sum) {
                if(array[i]*array[j]<temp) {
                    temp = array[i]*(sum-array[i]);
                    result.clear();
                    result.add(array[i]);
                    result.add(array[j]);
                }
                i++;
                j--;
            }
        }
        return result;
    }

    /*
   题目抽象：给定一个长度为5（排除空vector），包含0-13的数组，判断公差是否为1.（请从输入的一个数组中判断能否构成5个一连的顺子，
   输入的数范围为0-13的整数，其中0可以表示1~13的任意整数。若能构成顺子，返回true，否则返回false。）
   思路：TreeSet是有序的，且不能存储重复的值
   简单来说就是要是5个数字，最大和最小差值在5以内，并且没有重复数值。用一个set来填充数据，0不要放进去。
   set的大小加上0的个数必须为5个。此外set中数值差值在5以内。
    */
    public static boolean isContinuous(int [] numbers) {
        if(numbers.length != 5) return false;
        int num = 0;
        TreeSet<Integer> set = new TreeSet<>();
        for(int i = 0;i<numbers.length;i++) {
            if(numbers[i] == 0) {
                num++;
            } else {
                set.add(numbers[i]);
            }
        }
        if(num+set.size() != 5) {
            return false;
        }
        if(set.last() - set.first() <5) {
            return true;
        }
        return false;
    }

    /**
     * 数组中第k个最大元素
     * 给定整数数组 nums 和整数 k，请返回数组中第 k 个最大的元素。
     * 请注意，你需要找的是数组排序后的第 k 个最大的元素，而不是第 k 个不同的元素。
     *
     * 方法一：优先队列
     */
    public int findKthLargest(int [] nums,int k) {
        int len = nums.length;
        PriorityQueue<Integer> queue = new PriorityQueue<>(k+1);
        for(int i = 0;i<len;i++) {
            queue.add(nums[i]);
            if(queue.size()>k) {
                queue.poll();
            }
        }
        return queue.peek();
    }

    /**
     * 方法二：先排序，然后返回倒数第k个数
     * 升序排序以后，目标元素的索引是 len - k
     *
     * 时间复杂度：O(NlogN)，这里N是数组的长度，算法的性能消耗主要在排序，JDK 默认使用快速排序，因此时间复杂度为O(NlogN)。
     * 空间复杂度：O(1)，这里是原地排序，没有借助额外的辅助空间。
     */
    public int findKthLargest1(int [] nums,int k) {
        Arrays.sort(nums);
        return nums[nums.length-k];
    }

    /**
     * 方法三：快速排序+二分法
     *
     */
    public static int findKthLargest2(int [] nums,int k) {
        return quickSort(nums,0,nums.length-1,k);
    }

    private  static int quickSort(int [] arr,int left,int right,int k) {
        int index = partition(arr,left,right);
        if(index == arr.length-k) {
            return arr[index];
        } else if(index<arr.length-k) {
            return quickSort(arr,index+1,right,k);
        } else {
            return quickSort(arr,left,index-1,k);
        }
    }

    private static int partition(int [] arr,int left,int right) {
        int key = arr[left];
        while(left < right) {
            while(left<right && arr[right]>=key) {
                right--;
            }
            arr[left] = arr[right];
            while(left<right && arr[left] <= key) {
                left++;
            }
            arr[right] = arr[left];
        }
        arr[left] = key;
        return left;
    }

    /**
     * 最长重复子数组
     * 给两个整数数组 A 和 B ，返回两个数组中公共的、长度最长的子数组的长度。
     * 方法：动态规划
     * 令dp[i][j]表示 A[i:]和B[j:]的最长公共前缀，那么答案即为所有dp[i][j]中的最大值。如果 A[i] == B[j]，那么 dp[i][j] = dp[i + 1][j + 1] + 1，否则 dp[i][j] = 0。
     */
    public static int findLength(int [] nums1,int [] nums2) {
        int m = nums1.length;
        int n = nums2.length;
        int [] [] dp = new int[m+1][n+1];
        int maxLength = 0;
        for(int i = 1;i<=m;i++) {
            for(int j = 1;j<=n;j++) {
                if(nums1[i-1] == nums2[j-1]) {
                    dp[i][j] = dp[i-1][j-1]+1;
                } else {
                    dp[i][j] = 0;
                }
                maxLength = Math.max(maxLength,dp[i][j]);
            }
        }
        return maxLength;
    }

    /**
     * 方法二：滑动窗口
     */
    public static int findLength1(int [] nums1,int [] nums2) {
        return 0;
    }


    /**
     * 二分查找（牛客）
     * 请实现有重复数字的升序数组的二分查找
     * 给定一个 元素有序的（升序）整型数组 nums 和一个目标值 target  ，写一个函数搜索 nums 中的第一个出现的target，
     * 如果目标值存在返回下标，否则返回 -1
     */
    public int search (int[] nums, int target) {
        if(nums == null || nums.length == 0) {
            return -1;
        }
        if(nums[0]>target || nums[nums.length-1]<target) {
            return -1;
        }
        int low = 0;
        int high = nums.length-1;
        while(low<=high) {
            int mid = (low+high)/2;
            if(nums[mid] == target) {
                if(mid>0 && nums[mid] == nums[mid-1]) {
                    high = mid;
                } else {
                    return mid;
                }
            } else if(nums[mid]>target) {
                high = mid-1;
            } else if(nums[mid]<target) {
                low = mid+1;
            }
        }
        return -1;
    }


    /**
     * 最长无重复子数组（牛客）
     * 给定一个数组arr，返回arr的最长无重复元素子数组的长度，无重复指的是所有数字都不相同。
     * 子数组是连续的，比如[1,3,5,7,9]的子数组有[1,3]，[3,5,7]等等，但是[1,3,7]不是子数组
     */
    public int maxLength (int[] arr) {
        HashMap<Integer,Integer> map = new HashMap<>();
        int maxLength = 0;
        int left = 0;
        int right = 0;
        while(right < arr.length) {
            if(!map.containsKey(arr[right])) {
                map.put(arr[right],right);
            } else {
                left = Math.max(left,map.get(arr[right])+1);
                map.put(arr[right],right);
            }
            maxLength = Math.max(maxLength,right-left+1);
            right++;
        }
        return maxLength;
    }

    /**
     * 最小的k个数（牛客）
     * 给定一个数组，找出其中最小的K个数。例如数组元素是4,5,1,6,2,7,3,8这8个数字，则最小的4个数字是1,2,3,4
     * 方法一：冒泡排序，将最小的k个数放到数组最后面
     */
    public ArrayList<Integer> GetLeastNumbers_Solution(int [] input, int k) {
        ArrayList<Integer> result = new ArrayList<Integer>();
        if(input.length == 0 || k>input.length) return result;
        for(int i = 0;i<k;i++) {
            for(int j = 0;j<input.length-i-1;j++) {
                if(input[j]<input[j+1]) {
                    int temp = input[j];
                    input[j] = input[j+1];
                    input[j+1] = temp;
                }
            }
            result.add(input[input.length-i-1]);
        }
        return result;
    }

    /**
     * 方法二：优先队列，构建大根堆，堆的大小不超过k，遍历数组中元素，堆顶的元素是最大的，如果大于堆顶元素，直接放弃，小于堆顶元素的，
     * 将堆顶元素删除，将元素插入堆中
     */
    public static ArrayList<Integer> GetLeastNumbers_Solution1(int [] input,int k) {
        ArrayList<Integer> result = new ArrayList<>();
        if(k ==0 || input.length<k) {
            return result;
        }
        //创建最大堆
        PriorityQueue<Integer> queue = new PriorityQueue<>((num1,num2)->num2-num1);
        //先往堆中放数组的前k个元素
        for(int i = 0;i<k;i++) {
            queue.add(input[i]);
        }
        for(int i = k;i<input.length;i++) {
            if(queue.peek()>input[i]) {
                queue.poll();
                queue.add(input[i]);
            }
        }
        for(int i = 0;i<k;i++) {
            result.add(queue.poll());
        }
        return result;
    }

    /**
     * 合并两个有序数组（牛客）
     * 给出一个整数数组 和有序的整数数组 ，请将数组 合并到数组 中，变成一个有序的升序数组
     * 注意：
     * 1.可以假设 数组有足够的空间存放 数组的元素， 和 中初始的元素数目分别为 和 ，的数组空间大小为 +
     * 2.不要返回合并的数组，返回是空的，将数组 的数据合并到里面就好了
     * 3.数组在[0,m-1]的范围也是有序的
     *
     * 方法：使用双指针从后往前遍历，将大的放到数组后面
     */
    public static  void merge(int [] A,int m,int [] B,int n) {
        int i = m-1;
        int j = n-1;
        int index = m+n-1;
        while(i>=0 && j>=0) {
            if(A[i]>=B[j]) {
                A[index--] = A[i--];
            } else {
                A[index--] = B[j--];
            }
        }
        //若A遍历完了B还没遍历完，直接将B内剩余的元素存入到A剩余空间内
        while(j>=0) {
            A[index--] = B[j--];
        }
    }

    /**
     * 子数组的最大累加和(牛客)
     * 给定一个数组arr，返回子数组的最大累加和
     * 例如，arr = [1, -2, 3, 5, -2, 6, -1]，所有子数组中，[3, 5, -2, 6]可以累加出最大的和12，所以返回12.
     * 题目保证没有全为负数的数据
     *
     * [要求]
     * 时间复杂度为O(n)，空间复杂度为O(1)
     *
     * 方法一：记录累加和，若加上当前元素累加和小于0，则将累加和归0
     */
    public static int maxsumofSubarray(int [] arr) {
        if(arr == null || arr.length == 0) {
            return -1;
        }
        int maxSum = Integer.MIN_VALUE;
        int total = 0;
        for(int i = 0;i<arr.length;i++) {
            total += arr[i];
            if(total<0) {
                total = 0;
            }
            maxSum = Math.max(maxSum,total);
        }
        return maxSum;
    }

}

