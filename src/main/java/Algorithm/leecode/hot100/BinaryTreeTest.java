package Algorithm.leecode.hot100;

import java.util.*;

public class BinaryTreeTest {
    /**
     * 合并二叉树
     * 给定两个二叉树，想象当你将它们中的一个覆盖到另一个上时，两个二叉树的一些节点便会重叠。
     * 你需要将他们合并为一个新的二叉树。合并的规则是如果两个节点重叠，那么将他们的值相加作为节点合并后的新值，否则不为 NULL 的节点将直接作为新二叉树的节点。
     *
     * 可以使用深度优先搜索合并两个二叉树。从根节点开始同时遍历两个二叉树，并将对应的节点进行合并。
     * 两个二叉树的对应节点可能存在以下三种情况，对于每种情况使用不同的合并方式。
     * 如果两个二叉树的对应节点都为空，则合并后的二叉树的对应节点也为空；
     * 如果两个二叉树的对应节点只有一个为空，则合并后的二叉树的对应节点为其中的非空节点；
     * 如果两个二叉树的对应节点都不为空，则合并后的二叉树的对应节点的值为两个二叉树的对应节点的值之和，此时需要显性合并两个节点。
     * 对一个节点进行合并之后，还要对该节点的左右子树分别进行合并。这是一个递归的过程。
     */
    public TreeNode mergeTrees(TreeNode root1, TreeNode root2) {
        if(root1 == null) {
            return root2;
        }
        if(root2 == null) {
            return root1;
        }
        TreeNode newHead = new TreeNode(root1.val+root2.val);
        newHead.left = mergeTrees(root1.left,root2.left);
        newHead.right = mergeTrees(root1.right,root2.right);
        return newHead;
    }

    /**
     * 二叉树中序遍历
     *
     * 给定一个二叉树的根节点 root ，返回它的 中序 遍历
     */
    public List<Integer> inorderTraversal(TreeNode root) {
        ArrayList<Integer>  result = new ArrayList<>();
        if(root == null) {
            return result;
        }
        inOrder(root,result);
        return result;
    }

    public void inOrder(TreeNode root,List<Integer> result) {
        if(root == null) {
            return;
        }
        inOrder(root.left,result);
        result.add(root.val);
        inOrder(root.right,result);
    }

    /**
     * 二叉树的锯齿形层次遍历
     * 给定一个二叉树，返回其节点值的锯齿形层序遍历。（即先从左往右，再从右往左进行下一层遍历，以此类推，层与层之间交替进行）。
     * 双端队列是一个可以在队列任意一端插入元素的队列。在广度优先搜索遍历当前层节点拓展下一层节点的时候我们仍然从左往右按顺序拓展，但是对当前层节点的存储我们维护一个变量 \textit{isOrderLeft}isOrderLeft 记录是从左至右还是从右至左的：
     * 如果从左至右，我们每次将被遍历到的元素插入至双端队列的末尾。
     * 如果从右至左，我们每次将被遍历到的元素插入至双端队列的头部。
     */
    public List<List<Integer>> zigzagLevelOrder(TreeNode root) {
        List<List<Integer>> result = new ArrayList<>();
        if(root == null) {
            return result;
        }
        boolean isOrderLeft = true;
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        while(!queue.isEmpty()) {
            Deque<Integer> levelList = new LinkedList<>();
            int size = queue.size();
            for(int i = 0;i<size;i++) {
                TreeNode curNode = queue.poll();
                if(isOrderLeft) {
                    levelList.addLast(curNode.val);
                } else {
                    levelList.addFirst(curNode.val);
                }
                if(curNode.left != null) {
                    queue.add(curNode.left);
                }
                if(curNode.right != null) {
                    queue.add(curNode.right);
                }
            }
            isOrderLeft = !isOrderLeft;
            result.add(new LinkedList(levelList));
        }
        return result;
    }

    /**
     * 二叉树的公共祖先
     * 给定一个二叉树, 找到该树中两个指定节点的最近公共祖先。
     *
     * 百度百科中最近公共祖先的定义为：“对于有根树 T 的两个节点 p、q，最近公共祖先表示为一个节点 x，满足 x 是 p、q 的祖先且 x 的深度尽可能大（一个节点也可以是它自己的祖先）。”
     *
     * (递归) O(n)O(n)
     *
     * 当我们用递归去做这个题时不要被题目误导，应该要明确一点
     * 这个函数的功能有三个：给定两个节点 pp 和 qq
     *
     * 如果 pp 和 qq 都存在，则返回它们的公共祖先；
     * 如果只存在一个，则返回存在的一个；
     * 如果 pp 和 qq 都不存在，则返回NULL
     * 本题说给定的两个节点都存在，那自然还是能用上面的函数来解决
     *
     * 具体思路：
     * （1） 如果当前结点 rootroot 等于 NULL，则直接返回 NULL
     * （2） 如果 rootroot 等于 pp 或者 qq ，那这棵树一定返回 pp 或者 qq
     * （3） 然后递归左右子树，因为是递归，使用函数后可认为左右子树已经算出结果，用 leftleft 和 rightright 表示
     * （4） 此时若leftleft为空，那最终结果只要看 rightright；若 rightright 为空，那最终结果只要看 leftleft
     * （5） 如果 leftleft 和 rightright 都非空，因为只给了 pp 和 qq 两个结点，都非空，说明一边一个，因此 rootroot 是他们的最近公共祖先
     * （6） 如果 leftleft 和 rightright 都为空，则返回空（其实已经包含在前面的情况中了）
     *
     */
    public TreeNode lowestCommonAncestor(TreeNode root,TreeNode p,TreeNode q) {
        if(root == null || p == root || q == root) {
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
}
