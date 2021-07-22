package Algorithm.Sorts;

public class Sorts {
    public static void main(String [] args) {
        int [] arr={3,9,-1,10,-1};
        selectSort(arr);
        for(int i = 0;i<arr.length;i++) {
            System.out.println(arr[i]);
        }
    }

    /**
     * 插入排序
     *
     * 思想：每步将一个待排序的记录，按其关键值的大小插入前面已经排好序的文件中适当位置上，直到全部插入为止
     * 适用场景：适用于大部分数据已经排好序的场景
     * 时间复杂度：O(n2)
     * 空间复杂度：O（1）
     * 是稳定的排序方法
     */
    public static void insertionSort(int [] array) {
        for(int i = 0;i<array.length-1;i++) {
            for(int j = i+1;j>0;j--) {
                if(array[j]<array[j-1]) {
                    int temp = array[j];
                    array[j] = array[j-1];
                    array[j-1] = temp;
                }
            }
        }
    }

    /**
     * 冒泡排序
     *
     * 思想：选择排序(select sorting)也是一种简单的排序方法。它的基本思想是：第一次从arr[0]arr[n-1]中选择最小值，与arr[0]进行交换；
     * 第二次从arr[1]arr[n-1]中选取最小值，与arr[2]交换，……第i次从arr[i-1]arr[n-1]中选取最小值，与arr[i-1]交换，……第n-1次从
     * arr[n-1]arr[n-1]中选取最小值，与arr[n-2]交换，总共通过n-1次，得到一个按排序码从小到大排列的有序序列
     * 时间复杂度：O(n2)
     * 空间复杂度：O(1)
     * 适用场景：适用于少量数据的排序，每次排序可以得到一个最大值
     * 是稳定的排序方法
     */
    public static void bubbleSort(int [] array) {
        int length = array.length;
        if(length<=1) {
            return;
        }
        boolean flag = false;
        for(int i = 0;i<length;i++) {
            for(int j = 0;j<length-i-1;j++) {
                if(array[j]>array[j+1]) {
                    int temp = array[j];
                    array[j] = array[j+1];
                    array[j+1] = temp;
                    flag = true;
                }
            }
            if(flag == false) {
                break;
            }
        }
    }

    /**
     * 选择排序
     *
     * 思想：每次从待排序数组中找到一个最小（最大）元素，存放在序列的起始位置，直到全部待排序的数字排序玩完成
     * 时间复杂度：O(n2)
     * 空间复杂度：O(1)
     * 适用场景：适用于数据量较少的排序
     * 是不稳定的排序算法
     *
     */
    public static void selectSort(int [] array) {
        for(int i = 0;i<array.length-1;i++) {
            int minNum = array[i];
            int minIndex = i;
            for(int j = i+1;j<array.length;j++) {
                if (array[j] < minNum) {
                    minNum = array[j];
                    minIndex = j;
                }
            }
            if(minIndex != i) {
                array[minIndex] = array[i];
                array[i] = minNum;
            }
        }
    }

    /**
     * 希尔排序
     *
     * 思想：
     * 时间复杂度：
     * 空间复杂度：
     * 适用场景：
     * 是不稳定的
     */

    /**
     * 快速排序
     *
     * 思想：
     * 时间复杂度：
     * 空间复杂度：
     * 适用场景：
     *
     */
    public static void quickSort(int [] arr,int low,int high) {
        int i = low ;
        int j = high ;
        int temp =  arr[low] ;
        while(i<j) {
            while(i<j && arr[temp] <= arr[j]) {
                j-- ;
            }
            arr[i] = arr[j];
            while(i<j && arr[temp] >= arr[i]) {
                i++;
            }
            arr[j] = arr[i];
        }
        arr[i] = temp ;
        quickSort(arr,low,i-1) ;
        quickSort(arr,i+1,high) ;
    }
}
