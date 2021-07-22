package Algorithm.leecode.bytedance;

import java.util.*;

public class BinaryTreeTest {
    /**
     * 二叉树最大宽度
     *
     * 给定一个二叉树，编写一个函数来获取这个树的最大宽度。树的宽度是所有层中的最大宽度。这个二叉树与满二叉树（full binary tree）结构相同，但一些节点为空。
     * 每一层的宽度被定义为两个端点（该层最左和最右的非空节点，两端点间的null节点也计入长度）之间的长度
     *
     * 思路：每一层的宽度被定义为两个端点（该层最左和最右的非空节点，两端点间的null节点也计入长度）之间的长度
     *      方法一：取巧
     *      题目中定义的宽度，刚好对应完全二叉树的特性，每一层的层宽，等于完全二叉树中对应节点的编号差
     *      很明显 层宽 = 每一层的最右侧编号 - 最左侧编号 + 1
     *      下面的代码，投机取巧的地方在于，直接原地修改节点的 val 用来存储满二叉树中的编号
     *      使用双端队列Deque
     */
    public int widthOfBinaryTree(TreeNode root) {
        if (root == null) {
            return 0;
        }
        Deque<TreeNode> queue = new LinkedList<>();
        root.val = 0;
        queue.add(root);
        int sum;
        int ans = 0;
        while (!queue.isEmpty()) {
            sum = queue.size();
            ans = Math.max(ans, queue.getLast().val - queue.getFirst().val + 1);
            while (sum > 0) {
                TreeNode temp = queue.remove();
                if (temp.left != null) {
                    queue.add(temp.left);
                    temp.left.val = temp.val * 2 + 1;
                }
                if (temp.right != null) {
                    queue.add(temp.right);
                    temp.right.val = temp.val * 2 + 2;
                }
                sum--;
            }
        }
        return ans;
    }


    /**
     * 二叉树前序遍历
     */
    public List<Integer> preOrderTraversal(TreeNode root) {
        List<Integer> result = new ArrayList<>();
        if(root == null) {
            return result;
        }
        preOrder(root,result);
        return result;
    }

    public void preOrder(TreeNode root,List<Integer> list) {
        if(root == null) {
            return;
        }
        list.add(root.val);
        preOrder(root.left,list);
        preOrder(root.right,list);
    }

    /**
     * 二叉树中序遍历
     */
    public List<Integer> inOrderTraversal(TreeNode root) {
        List<Integer> result = new ArrayList<>();
        if(root == null) {
            return result;
        }
        inOrder(root,result);
        return result;
    }

    public void inOrder(TreeNode root,List<Integer> list) {
        if(root == null) {
            return ;
        }
        inOrder(root.left,list);
        list.add(root.val);
        inOrder(root.right,list);
    }
    /**
     * 二叉树后续遍历
     */
    public List<Integer> postOrderTraversal(TreeNode root) {
        List<Integer> result = new ArrayList<>();
        if(root == null) {
            return result;
        }
        postOrder(root,result);
        return result;
    }

    public void postOrder(TreeNode root,List<Integer> list) {
        if(root == null) {
            return;
        }
        postOrder(root.left,list);
        postOrder(root.right,list);
        list.add(root.val);
    }

    /**
     * 二叉树中和为某一值的路径
     */

    public List<List<Integer>> pathSum(TreeNode root, int target) {
        List<List<Integer>> array = new ArrayList<List<Integer>>();
        if(root == null) {
            return array;
        }
        List<Integer> tempArray = new ArrayList<Integer>();
        int sum = 0;
        find(root,target,array,tempArray,sum);
        return array;
    }

    public void find(TreeNode root,int target,List<List<Integer>> array,List<Integer> tempArray,int sum) {
        if(root == null) return ;
        sum +=root.val;
        if(root.left == null && root.right == null) {
            if(sum == target) {
                tempArray.add(root.val);
                array.add(new ArrayList<Integer>(tempArray));
                tempArray.remove(tempArray.size()-1);
            }
            return ;
        }
        tempArray.add(root.val);
        find(root.left,target,array,tempArray,sum);
        find(root.right,target,array,tempArray,sum);
        tempArray.remove(tempArray.size()-1);
    }

    /**
     * 二叉树的层次遍历
     */
    public List<List<Integer>> levelOrder(TreeNode root) {
        List<List<Integer>> result =  new ArrayList<List<Integer>>();
        Queue<TreeNode> queue = new LinkedList<>();
        if(root == null) {
            return result;
        }
        queue.add(root);
        while(!queue.isEmpty()) {
            int size = queue.size();
            ArrayList<Integer> path = new ArrayList<>();
            for(int i = 0;i<size;i++) {
                TreeNode tempNode = queue.poll();
                path.add(tempNode.val);
                if(tempNode.left != null) {
                    queue.add(tempNode.left);
                }
                if(tempNode.right != null) {
                    queue.add(tempNode.right);
                }
            }
            result.add(path);
        }
        return result;
    }

    /**
     * N叉树的层次遍历
     */

    public List<List<Integer>> levelOrder(Node root) {
        List<List<Integer>> result = new ArrayList<List<Integer>>();
        if(root == null) {
            return result;
        }
        Queue<Node> queue = new LinkedList<>();
        queue.add(root);
        while(!queue.isEmpty()) {
            List<Integer> path = new ArrayList<>();
            int size = queue.size();
            for(int i = 0;i<size;i++) {
                Node temp = queue.poll();
                path.add(temp.val);
                queue.addAll(temp.children);
            }
            result.add(path);
        }
        return result;
    }

    /**
     * N叉树的前序遍历
     */
    public List<Integer> preOrder(Node root) {
        List<Integer> result = new ArrayList<>();
        if(root == null) {
            return result;
        }
        preOrderN(root,result);
        return result;
    }

    public void preOrderN(Node root,List<Integer> result) {
        if(root == null) {
            return;
        }
        result.add(root.val);
        for(Node children: root.children) {
            preOrderN(children,result);
        }
    }

    /**
     * N叉树的后续遍历
     */
    public List<Integer> postOrder(Node root) {
        List<Integer> result = new ArrayList<>();
        if(root == null) {
            return result;
        }
        postOrderN(root,result);
        return result;
    }

    public void postOrderN(Node root,List<Integer> result) {
        if(root == null) {
            return;
        }
        for(Node children: root.children) {
            postOrderN(children,result);
        }
        result.add(root.val);
    }

    /**
     * 判断一棵树是不是二叉搜索树
     *
     *给定一个二叉树，判断其是否是一个有效的二叉搜索树。
     *假设一个二叉搜索树具有如下特征：
     *    节点的左子树只包含小于当前节点的数。
     *    节点的右子树只包含大于当前节点的数。
     *    所有左子树和右子树自身必须也是二叉搜索树。
     */
    public boolean isValidBST(TreeNode root) {
        return isValid(root,Long.MIN_VALUE,Long.MAX_VALUE);
    }

    public boolean isValid(TreeNode node,long lower,long upper) {
        if(node == null) {
            return true;
        }
        if(node.val <= lower || node.val >= upper) {
            return false;
        }
        return isValid(node.left,lower,node.val) && isValid(node.right,node.val,upper);
    }

    /**
     * 二叉树的最近公共祖先
     *
     * 给定一个二叉树, 找到该树中两个指定节点的最近公共祖先。
     *
     * 百度百科中最近公共祖先的定义为：“对于有根树 T 的两个结点 p、q，最近公共祖先表示为一个结点 x，满足 x 是 p、q 的祖先且 x 的深度尽
     * 可能大（一个节点也可以是它自己的祖先）。”
     *
     * 根据以上定义，若 rootroot 是 p, qp,q 的 最近公共祖先 ，则只可能为以下情况之一：
     *
     * pp 和 qq 在 rootroot 的子树中，且分列 rootroot 的 异侧（即分别在左、右子树中）；
     * p = rootp=root ，且 qq 在 rootroot 的左或右子树中；
     * q = rootq=root ，且 pp 在 rootroot 的左或右子树中；
     *
     * 递归解析：
     * 终止条件：
     * 1.当越过叶节点，则直接返回null ；
     * 2.当root等于p, q，则直接返回 root ；
     *
     * 递推工作：
     * 开启递归左子节点，返回值记为 left ；
     * 开启递归右子节点，返回值记为right ；
     *
     * 返回值： 根据left和right ，可展开为四种情况；
     *
     * 1.当left和right 同时为空 ：说明 root 的左 / 右子树中都不包含p,q ，返回null ；
     * 2.当left和right 同时不为空 ：说明 p, q分列在root的异侧(分别在 左 / 右子树），因此 root 为最近公共祖先，返回 root ；
     * 3.当left为空 ，right不为空 ：p,q都不在root 的左子树中，直接返回 right 。具体可分为两种情况：
     *    1.p,qp,q 其中一个在 root 的 右子树 中，此时 right 指向 pp（假设为 pp ）；
     *    2.p,qp,q 两节点都在 root 的 右子树 中，此时的 right 指向 最近公共祖先节点 ；
     * 4.当 left 不为空 ， right 为空 ：与情况 3. 同理；
     *
     */
    public TreeNode lowestCommonAncestor(TreeNode root,TreeNode p,TreeNode q) {
        if(root == null || root == p || root == q) {
            return root;
        }
        TreeNode left = lowestCommonAncestor(root.left,p,q);
        TreeNode right = lowestCommonAncestor(root.right,p,q);
        if(left == null && right == null) {
            return null;
        }
        if(left == null) {
            return right;
        }
        if(right == null) {
            return left;
        }
        return root;
    }

    /**
     * 给定二叉树，判断该二叉树是否是镜像对称的
     */
    public boolean isSymmetric(TreeNode root) {
        if(root == null) {
            return true;
        }
        return recur(root.left,root.right);
    }

    public boolean recur(TreeNode left,TreeNode right) {
        if(left == null && right == null) {
            return true;
        }
        if(left == null || right == null ||left.val != right.val) {
            return false;
        }
        return recur(left.left,right.right)&&recur(left.right,right.left);
    }

    /**
     * 二叉树的镜像
     *
     * 请完成一个函数，输入一个二叉树，该函数输出它的镜像。
     * 递归，时间复杂度和空间复杂度都是O(n)
     */
    public TreeNode mirrorTree(TreeNode root) {
        if(root == null) {
            return null;
        }
        TreeNode left = mirrorTree(root.left);
        TreeNode right = mirrorTree(root.right);
        root.left = right;
        root.right = left;
        return root;
    }

    /**
     * 方法二：利用栈（或队列）遍历树的所有节点node ，并交换每个node 的左 / 右子节点
     */
    public TreeNode mirrorTree1(TreeNode root) {
        if(root == null) {
            return null;
        }
        Stack<TreeNode> stack = new Stack<>();
        stack.push(root);
        while(!stack.isEmpty()) {
            TreeNode node = stack.pop();
            if(node.left != null) {
                stack.push(node.left);
            }
            if(node.right != null) {
                stack.push(node.right);
            }
            TreeNode temp = node.left;
            node.left = node.right;
            node.right = temp;
        }
        return root;
    }

    /**
     * 打印二叉树的所有路径
     *
     * 给定一个二叉树，返回所有从根节点到叶子节点的路径。
     */
    public List<String> binaryTreePaths(TreeNode root) {
        List<String> result = new ArrayList<>();
        constructPath(root,"",result);
        return result;
    }

    public void constructPath(TreeNode root,String path,List<String> result) {
        if(root != null) {
            StringBuffer paths = new StringBuffer(path);
            paths.append(root.val);
            if(root.left == null && root.right == null) {
                result.add(paths.toString());
            } else {
                paths.append("->");
                constructPath(root.left,paths.toString(),result);
                constructPath(root.right,paths.toString(),result);
            }
        }
    }

    /**
     * 完全二叉树判断
     *
     * 给定一个二叉树，确定它是否是一个完全二叉树。
     *
     * 百度百科中对完全二叉树的定义如下：
     *
     * 若设二叉树的深度为h，除第h层外，其它各层 (1～h-1) 的结点数都达到最大个数，第h层所有的结点都连续集中在最左边，这就是完全二叉树。
     * （注：第 h 层可能包含1~ 2h个节点。）
     *
     * 思路：对二叉树进行层次遍历，对于一个完全二叉树，进行层次遍历的时候，遇到一个空节点，后面就不应该再有非空节点了
     */
    public boolean isCompleteTree(TreeNode root) {
        if(root == null) {
            return true;
        }
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        boolean reachEnd = false;
        while(!queue.isEmpty()) {
            TreeNode curNode = queue.poll();
            if(reachEnd == true && curNode != null) {
                return false;
            }
            if(curNode == null) {
                reachEnd = true;
                continue;
            }
            queue.add(curNode.left);
            queue.add(curNode.right);
        }
        return true;
    }



}
