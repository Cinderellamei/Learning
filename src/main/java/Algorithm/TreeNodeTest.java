package Algorithm;

import java.util.*;

public class TreeNodeTest {
    public static void main(String [] args) {
        /*int [] pre = {1,2,4,7,3,5,6,8};
        int [] in = {4,7,2,1,5,3,8,6};
        TreeNode node = reConstructBinaryTree(pre,in);
        System.out.println(node.val);*/
        ArrayList<Integer> temp = new ArrayList<Integer>();
        ArrayList<ArrayList<Integer>> array = new ArrayList<ArrayList<Integer>>();
        temp.add(1);
        temp.add(2);
        temp.add(3);
        array.add(new ArrayList<Integer>(temp));
        System.out.println(array);
    }

    /*
    给定一个二叉树，根据前序遍历和中序遍历，重建该二叉树并返回
     */
    public static TreeNode reConstructBinaryTree(int [] pre,int [] in) {
        if(pre.length == 0 || in.length == 0 || pre.length != in.length) return null ;
        TreeNode root= new TreeNode(pre[0]);
        for(int i = 0 ;i<in.length;i++) {
            if(pre[0] == in[i]) {
               root.left = reConstructBinaryTree(Arrays.copyOfRange(pre,1,i+1),Arrays.copyOfRange(in,0,i));
               root.right = reConstructBinaryTree(Arrays.copyOfRange(pre,i+1,pre.length),Arrays.copyOfRange(in,i+1,in.length));
            }
        }
        return root ;
    }

    /*输入两棵二叉树A，B，判断B是不是A的子结构。（ps：我们约定空树不是任意一个树的子结构）
     */
    public static boolean hasSubTree(TreeNode root1,TreeNode root2) {
        boolean result = false;
        if(root1 != null && root2 != null) {
            if(root1.val == root2.val) {
                result = doesHasTree(root1,root2);
            }
            if(!result) {
                result = doesHasTree(root1.left,root2);
            }
            if(!result) {
                result = doesHasTree(root1.right,root2);
            }
        }
        return result;
    }

    public static boolean doesHasTree(TreeNode node1,TreeNode node2) {
        if(node1 == null) return false;
        if(node2 == null) return true;
        if(node1.val != node2.val) return false;
        return doesHasTree(node1.left,node2.left) && doesHasTree(node1.right,node2.right);
    }

    /*
    操作给定的二叉树，将其变换为源二叉树的镜像。
    思路：借助栈交换节点的左右子节点
     */
    public static void mirror(TreeNode root) {
        if(root == null) return ;
        Stack<TreeNode> stack = new Stack<TreeNode>();
        stack.push(root);
        while(!stack.isEmpty()) {
            TreeNode node = stack.pop();
            if(node.left != null || node.right != null) {
                TreeNode temp = node.left;
                node.left = node.right;
                node.right = temp;
            }
            if(node.left != null) stack.push(node.right);
            if(node.right != null) stack.push(node.left);
        }
    }

    /*
    从上往下打印出二叉树的每个节点，同层节点从左至右打印。
    思路：二叉树层次遍历，借助队列实现
     */
    public static ArrayList<Integer> printFromTopToBottom(TreeNode root) {
        ArrayList<Integer> result = new ArrayList<Integer>();
        ArrayList<TreeNode> deque = new ArrayList<TreeNode>();
        if(root == null) {return result;}
        deque.add(root);
        while(!deque.isEmpty()) {
            TreeNode node = deque.remove(0);
            result.add(node.val);
            if(node.left != null) deque.add(node.left);
            if(node.right != null) deque.add(node.right);
        }
        return result;
    }

    /*
    输入一个整数数组，判断该数组是不是某二叉搜索树的后序遍历的结果。如果是则输出Yes,否则输出No。假设输入的数组的任意两个数字都互不相同。
    思路：搜索二叉树特点：若一个节点的左子节点不为空，则左子节点的值一定小于该节点，右子节点不为空，则右子节点的值一定大于该节点
     */
    public static boolean verifySequenceBST(int [] sequence) {
       if(sequence.length == 0) return false;
       return isTreeBST(sequence,0,sequence.length-1);
    }

    public static boolean isTreeBST(int [] array,int start,int end) {
        if(end <= start) return true;
        int i = start;
        for(;i<end;i++) {
            if(array[i]>array[end]) break;
        }
        for(int j = i;j<end;j++) {
            if(array[j] < array[end]) {
                return false;
            }
        }
        return isTreeBST(array,start,i-1) && isTreeBST(array,i,end-1);
    }

    /*
    输入一颗二叉树的根节点和一个整数，按字典序打印出二叉树中结点值的和为输入整数的所有路径。路径定义为从树的根结点开始往下一直到叶结点所经过的结点形成一条路径。
    题目抽象：给定一颗二叉树，找出从跟节点到叶子节点和为target的所有路径（深度优先遍历）
     */
    public static ArrayList<ArrayList<Integer>> findPath(TreeNode root,int target) {
        ArrayList<ArrayList<Integer>> array = new ArrayList<ArrayList<Integer>>();
        ArrayList<Integer> tempArray = new ArrayList<Integer>();
        if(root == null) return array;
        int sum = 0;
        printPath(root,target,array,tempArray,sum);
        return array;
    }

    public static void printPath(TreeNode root,int target,ArrayList<ArrayList<Integer>> array,ArrayList<Integer> tempArray,int sum) {
        if(root == null) return;
        sum += root.val;
        if(root.left == null && root.right == null) {
            if(sum == target) {
                tempArray.add(root.val);
                array.add(new ArrayList<Integer>(tempArray));
                tempArray.remove(tempArray.size()-1);
            }
            return;
        }
        tempArray.add(root.val);
        printPath(root.left,target,array,tempArray,sum);
        printPath(root.right,target,array,tempArray,sum);
        tempArray.remove(tempArray.size()-1);
    }

    /*
    输入一棵二叉搜索树，将该二叉搜索树转换成一个排序的双向链表。要求不能创建任何新的结点，只能调整树中结点指针的指向。
    二叉搜索树：要么是一颗空树，或者是具有如下性质的树：若左子树不为空，则左子树上所有结点均小于跟节点，若右子树不为空，则右子树上所有结点均大于根结点
    思路：递归，先将左子树转换为链表，然后将左子树与根结点链接，然后将右子树转换为链表，再将右子树链表链接到根结点上
     */

    public TreeNode Convert(TreeNode pRootOfTree) {
        if(pRootOfTree==null)
            return null;
        if(pRootOfTree.left==null&&pRootOfTree.right==null)
            return pRootOfTree;
        // 1.将左子树构造成双链表，并返回链表头节点
        TreeNode left = Convert(pRootOfTree.left);
        TreeNode p = left;
        // 2.定位至左子树双链表最后一个节点
        while(p!=null&&p.right!=null){
            p = p.right;
        }
        // 3.如果左子树链表不为空的话，将当前root追加到左子树链表
        if(left!=null){
            p.right = pRootOfTree;
            pRootOfTree.left = p;
        }
        // 4.将右子树构造成双链表，并返回链表头节点
        TreeNode right = Convert(pRootOfTree.right);
        // 5.如果右子树链表不为空的话，将该链表追加到root节点之后
        if(right!=null){
            right.left = pRootOfTree;
            pRootOfTree.right = right;
        }
        return left!=null?left:pRootOfTree;
    }

    /*
    输入一个字符串,按字典序打印出该字符串中字符的所有排列。例如输入字符串abc,则按字典序打印出由字符a,b,c所能排列出来的所有
    字符串abc,acb,bac,bca,cab和cba。
    思路：
     */
    public ArrayList<Integer> Permutation(String str) {
        ArrayList<Integer> result = new ArrayList<Integer>();
        return result;
    }

    /*
    输入一颗二叉树，求该树的深度，从根结点到叶结点依次经过的结点（含根、叶结点）形成树的一条路径，最长路径的长度为树的深度
    思路：
     */
    public static int TreeDepth(TreeNode root) {
        if(root == null) {
            return 0;
        } else {
            int left = TreeDepth(root.left);
            int right = TreeDepth(root.right);
            return left>right?(left+1):(right+1);
        }
    }

    /*
    输入一棵二叉树，判断该二叉树是否是平衡二叉树
    平衡二叉树：平衡树(Balance Tree，BT) 指的是，任意节点的子树的高度差都小于等于1
    思路：只管是否是平衡二叉树，不管排序，若左子树或右子树本身不是平衡二叉树时，直接返回，不用继续遍历
     */
    static boolean isBalanced = true;
    public static boolean isBalance(TreeNode root) {
        treeDepth(root);
        return isBalanced;
    }
    public static int treeDepth(TreeNode root) {
        if(root == null) return 0;
        int left = treeDepth(root.left);
        if(left == -1) {
            isBalanced = false;
            return -1;
        }
        int right = treeDepth(root.right);
        if(right == -1) {
            isBalanced = false;
            return -1;
        }
        if(left-right >1 || right-left >1) {
            isBalanced = false;
            return -1;
        }
        return left > right?left+1:right+1;
    }

    /**
     * 实现二叉树先序，中序和后序遍历（牛客）
     * 分别按照二叉树先序，中序和后序打印所有的节点
     */
    public ArrayList<Integer> pre = new ArrayList<>();
    public ArrayList<Integer> in = new ArrayList<>();
    public ArrayList<Integer> post = new ArrayList<>();

    public int [][] threeOrders(TreeNode root) {
        preOrder(root);
        inOrder(root);
        postOrder(root);
        int [][] result = new int[3][pre.size()];
        result[0] = toIntArray(pre);
        result[1] = toIntArray(in);
        result[2] = toIntArray(post);
        return result;

    }

    public int [] toIntArray(ArrayList<Integer> list) {
        if(list == null || list.size() < 1) {
            return new int[0];
        }
        int [] result = new int[list.size()];
        for(int i = 0;i<list.size();i++) {
            result[i] = list.get(i);
        }
        return result;
    }

    public void preOrder(TreeNode root) {
        if(root == null) {
            return ;
        }
        pre.add(root.val);
        preOrder(root.left);
        preOrder(root.right);
    }
    public void inOrder(TreeNode root) {
        if(root == null) {
            return ;
        }
        inOrder(root.left);
        in.add(root.val);
        inOrder(root.right);
    }

    public void postOrder(TreeNode root) {
        if(root == null) {
            return ;
        }
        postOrder(root.left);
        postOrder(root.right);
        post.add(root.val);
    }

    /**
     * 非递归的前、中、后序遍历
     */
    public void preOrder1(TreeNode root) {

    }

    public void inOrder1(TreeNode root) {

    }

    public void postOrder1(TreeNode root) {

    }


}
