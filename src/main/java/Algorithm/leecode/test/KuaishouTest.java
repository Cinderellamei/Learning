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

    /**
     * 约瑟夫环问题
     * 编号为 11 到 nn 的 nn 个人围成一圈。从编号为 11 的人开始报数，报到 mm 的人离开。
     * 下一个人继续从 11 开始报数。
     * n-1n−1 轮结束以后，只剩下一个人，问最后留下的这个人编号是多少？
     */
    public int ysf (int n, int m) {
        // write code here
        int result= 0;
        for(int i = 2;i<=n;i++) {
            result =(result+m)%i;
        }
        return result+1;
    }

    /**
     * 验证IP地址(牛客)
     */
    public String solve (String IP) {
        // write code here
        if(isIPV4(IP)) {
            return "IPv4";
        }
        if(isIPV6(IP)) {
            return "IPv6";
        } else {
            return "Neither";
        }
    }
    public boolean isIPV4(String IP) {
        String [] parts = IP.split("\\.");
        if(parts.length!=4) {
            return false;
        }
        for(int i = 0;i<parts.length;i++) {
            String part = parts[i];
            if(part.startsWith("0") && part.length()>1) {
                return false;
            }
            try {
                int result = Integer.parseInt(part);
                if(result<0 || result>255) {
                    return false;
                }
            }catch (Exception e) {
                return false;
            }
        }
        return true;
    }

    public boolean isIPV6(String IP) {
        String [] parts = IP.split("\\:");
        if(parts.length != 8) {
            return false;
        }
        for(int i = 0;i<parts.length;i++) {
            String part = parts[i];
            if(part.length()>4) {
                return false;
            }
            if(part.equals("")||part.equals("0000")) {
                return false;
            }
        }
        return true;
    }

    /**
     * 集合的所有子集
     */
    public static ArrayList<ArrayList<Integer>> subsets(int [] S) {
        ArrayList<ArrayList<Integer>> result = new ArrayList<>();
        ArrayList<Integer> path = new ArrayList<>();
        for(int i = 0;i<=S.length;i++) {
            dfs(result,path,S,0,i);
        }
        return result;
    }

    public static void dfs(ArrayList<ArrayList<Integer>> result,ArrayList<Integer> path,int [] S,int index,int size) {
        if(size<0) {
            return;
        }
        if(size == 0) {
            result.add(new ArrayList<>(path));
            return;
        }
        for(int i = index;i<S.length;i++) {
            path.add(S[i]);
            dfs(result,path,S,i+1,size-1);
            path.remove(path.size()-1);
        }
    }

    public static void main(String [] args) {
        ArrayList<ArrayList<Integer>> result = new ArrayList<>();
        int [] S = {1,2,3};
        System.out.println(subsets(S));
    }

}
