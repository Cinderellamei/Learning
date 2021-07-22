package Algorithm.leecode.tree;

import Algorithm.leecode.bytedance.DNode;

import java.util.*;

public class TreeNodeTest {

    /**
     * 求二叉树的深度
     * 输入一棵二叉树的根节点，求该树的深度。从根节点到叶节点依次经过的节点（含根、叶节点）形成树的一条路径，最长路径的长度为树的深度
     * 关键点：此树的深度和其左（右）子树的深度之间的关系。显然，此树的深度 等于 左子树的深度 与 右子树的深度 中的 最大值 +1+1
     * 方法：后序遍历
     */
    public int maxDepth(TreeNode root) {
        if (root == null) {
            return 0;
        }
        return Math.max(maxDepth(root.left), maxDepth(root.right)) + 1;
    }

    /**
     * 二叉树中和为某一值的路径
     * 输入一棵二叉树和一个整数，打印出二叉树中节点值的和为输入整数的所有路径。从树的根节点开始往下一直到叶节点所经过的节点形成一条路径
     */
    public ArrayList<ArrayList<Integer>> pathSum(TreeNode root, int target) {
        ArrayList<ArrayList<Integer>> array = new ArrayList<ArrayList<Integer>>();
        if (root == null) {
            return array;
        }
        ArrayList<Integer> tempArray = new ArrayList<>();
        int sum = 0;
        find(root, target, array, tempArray, sum);
        return array;
    }

    public void find(TreeNode root, int target, ArrayList<ArrayList<Integer>> array, ArrayList<Integer> tempArray, int sum) {
        if (root == null) {
            return;
        }
        sum += root.val;
        if (root.left == null && root.right == null) {
            if (sum == target) {
                tempArray.add(root.val);
                array.add(new ArrayList<Integer>(tempArray));
                tempArray.remove(tempArray.size() - 1);
            }
            return;
        }
        tempArray.add(root.val);
        find(root.left, target, array, tempArray, sum);
        find(root.right, target, array, tempArray, sum);
        tempArray.remove(tempArray.size() - 1);
    }

    /**
     * 翻转一颗二叉树
     */
    public TreeNode invertTree(TreeNode root) {
        if (root == null) {
            return root;
        }
        TreeNode left = invertTree(root.left);
        TreeNode right = invertTree(root.right);
        root.left = right;
        root.right = left;
        return root;
    }

    /**
     * 给定一棵二叉树，你需要计算它的直径长度。一棵二叉树的直径长度是任意两个结点路径长度中的最大值。这条路径可能穿过也可能不穿过根结点。
     */
    int ans;

    public int diameterOfBinaryTree(TreeNode root) {
        ans = 1;
        depth(root);
        return ans - 1;
    }

    public int depth(TreeNode node) {
        if (node == null) {
            return 0;
        }
        int L = depth(node.left);
        int R = depth(node.right);
        ans = Math.max(ans, L + R + 1);
        return Math.max(L, R) + 1;
    }


    /**
     * 给定一个二叉树，检查它是否是镜像对称的
     */
    public boolean isSymmetric(TreeNode root) {
        if (root == null) {
            return true;
        } else {
            return recur(root.left, root.right);
        }
    }

    public boolean recur(TreeNode left, TreeNode right) {
        if (left == null && right == null) {
            return true;
        }
        if (left == null || right == null || left.val != right.val) {
            return false;
        }
        return recur(left.left, right.right) && recur(right.left, left.right);
    }

    /**
     * 二叉树的最大宽度
     * 给定一个二叉树，编写一个函数来获取这个树的最大宽度。树的宽度是所有层中的最大宽度。这个二叉树与满二叉树（full binary tree）结构相同，但一些节点为空。
     * <p>
     * 每一层的宽度被定义为两个端点（该层最左和最右的非空节点，两端点间的null节点也计入长度）之间的长度
     * 方法一：取巧
     * 题目中定义的宽度，刚好对应完全二叉树的特性，每一层的层宽，等于完全二叉树中对应节点的编号差
     * 很明显 层宽 = 每一层的最右侧编号 - 最左侧编号 + 1
     * 下面的代码，投机取巧的地方在于，直接原地修改节点的 val 用来存储满二叉树中的编号
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
     * 翻转二叉树以匹配先序遍历
     */

    public List<Integer> flipMatchVoyage(TreeNode root, int[] voyage) {
        List<Integer> result = new ArrayList<>();
        return result;
    }

    /**
     * 二叉树中所有距离为K的节点
     * 给定一个二叉树（具有根结点 root）， 一个目标结点 target ，和一个整数值 K 。
     * 返回到目标结点 target 距离为 K 的所有结点的值的列表。 答案可以以任何顺序返回。
     */
    public List<Integer> distanceK(TreeNode root, int target, int k) {
        List<Integer> result = new ArrayList<>();
        return result;
    }

    /**
     * 设计一个算法，找出二叉搜索树中指定节点的“下一个”节点（也即中序后继）。
     * 如果指定节点没有对应的“下一个”节点，则返回null。
     * 方法一：BST+递归
     * 如果结点 p 的值大于等于 root 的值，说明 p 的后继结点在 root 右子树中，那么就递归到右子树中查找。
     * 如果结点 p 的值小于 root 的值，说明 p 在 root 左子树中，而它的后继结点有两种可能，要么也在左子树中，要么就是 root：
     * 如果左子树中找到了后继结点，那就直接返回答案。
     * 如果左子树中没有找到后继结点，那就说明 p 的右儿子为空，那么 root 就是它的后继结点。
     */
    public TreeNode inorderSuccessor(TreeNode root, TreeNode p) {
        if (root == null || p == null) {
            return null;
        }
        if (p.val >= root.val) {
            return inorderSuccessor(root.right, p);
        } else if (p.val < root.val) {
            TreeNode left = inorderSuccessor(root.left, p);
            return left != null ? left : root;
        } else {
            return null;
        }
    }

    /**
     * 方法二
     * 只有两种情况
     * 一：p没有右子树：在左转的之前，将当前结点记录一下。比如在寻找5，在6结点判断，是要左转的，那么用pre记录一下6这个结点。假如5没有右子树，那么就可以返回pre了，假如路径中发生了新的左转动作，那么pre也需要更新
     * 二：p有右子树：那么只需要直接根据中序遍历的规则，找到p的右子树的最左孩子即可
     */
    public TreeNode inorderSuccessor1(TreeNode root, TreeNode p) {
        TreeNode pre = null;
        while (root.val != p.val) {
            if (p.val > root.val) {
                root = root.right;
            } else {
                pre = root;
                root = root.left;
            }
        }
        if (root.right == null) {
            return pre;
        } else {
            root = root.right;
            while (root.left != null) {
                root = root.left;
            }
            return root;
        }
    }

    /**
     * 树的遍历：
     * 一：前序遍历
     */
    public void preOrder(TreeNode root) {
        if (root == null) {
            return;
        }
        System.out.println(root.val);
        preOrder(root.left);
        preOrder(root.right);
    }

    /**
     * 中序遍历
     */
    public void inOrder(TreeNode root) {
        if (root == null) {
            return;
        }
        inOrder(root.left);
        System.out.println(root.val);
        inOrder(root.right);
    }

    /**
     * 后序遍历
     */
    public void postOrder(TreeNode root) {
        if (root == null) {
            return;
        }
        postOrder(root.left);
        postOrder(root.right);
        System.out.println(root.val);
    }

    /**
     * 深度优先遍历
     */
    public void depthFirstSearch(TreeNode root) {
        if (root == null) {
            return;
        }
        Stack<TreeNode> stack = new Stack<>();
        stack.push(root);
        while (!stack.isEmpty()) {
            TreeNode tempNode = stack.pop();
            if (tempNode.left != null) {
                stack.push(tempNode.left);
            }
            if (tempNode.right != null) {
                stack.push(tempNode.right);
            }
        }
    }

    /**
     * 广度优先遍历
     * 不标记层
     */
    public void broadFirstSearch(TreeNode root) {
        if (root == null) {
            return;
        }
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        while (!queue.isEmpty()) {
            TreeNode tempNode = queue.poll();
            //如果tempNode是我们要找的，则return这个tempNode
            if (tempNode.left != null) {
                queue.add(tempNode.left);
            }
            if (tempNode.right != null) {
                queue.add(tempNode.right);
            }
        }
    }


    /**
     * 广度优先遍历
     * 标记层
     */
    public void boradFirstSearch1(TreeNode root, int k) {
        if (root == null) {
            return;
        }
        Queue<TreeNode> queue = new LinkedList<>();
        int step = 0;
        int size = 0;
        ArrayList<TreeNode> ans = new ArrayList<>();
        while (!queue.isEmpty()) {
            size = queue.size() - 1;
            if (size != 0) {
                TreeNode tempNode = queue.poll();
                if (step == k) {
                    ans.add(tempNode);
                }
                if (tempNode.left != null) {
                    queue.add(tempNode.left);
                }
                if (tempNode.right != null) {
                    queue.add(tempNode.right);
                }
            }
            step += 1;
        }
    }

    /**
     * DFS搜索题套路模版
     */
    public void dfs(TreeNode root) {
        Stack<TreeNode> stack = new Stack<>();
        if (root == null) {
            return;
        }
        if (root.left == null && root.right == null) {
            return;
        }
        stack.push(root);
        dfs(root.left);
        dfs(root.right);
        stack.pop();
        return;
    }

    /**
     * 前序遍历构造二叉搜索树
     * 返回与给定前序遍历 preorder 相匹配的二叉搜索树（binary search tree）的根结点
     */
    public TreeNode bstFromPreorder(int[] preorder) {
        int length = preorder.length;
        return build(preorder, 0, length - 1);
    }

    public TreeNode build(int[] preorder, int start, int end) {
        if (start > end) {
            return null;
        }
        TreeNode root = new TreeNode(preorder[start]);
        int k = start;
        while (k < end) {
            if (preorder[k + 1] > root.val) {
                break;
            }
            k++;
        }
        root.left = build(preorder, start + 1, k);
        root.right = build(preorder, k + 1, end);
        return root;
    }

    /**
     * 最大二叉树
     * 给定一个不含重复元素的整数数组 nums 。一个以此数组直接递归构建的 最大二叉树 定义如下：
     * <p>
     * 二叉树的根是数组 nums 中的最大元素。
     * 左子树是通过数组中 最大值左边部分 递归构造出的最大二叉树。
     * 右子树是通过数组中 最大值右边部分 递归构造出的最大二叉树。
     * 返回有给定数组 nums 构建的 最大二叉树
     */
    public TreeNode constructMaximumBinaryTree(int[] nums) {
        return construct(nums, 0, nums.length);
    }

    public TreeNode construct(int[] nums, int left, int right) {
        if (left == right) {
            return null;
        }
        int max = max(nums, left, right);
        TreeNode root = new TreeNode(nums[max]);
        root.left = construct(nums, left, max);
        root.right = construct(nums, max + 1, right);
        return root;
    }

    public int max(int[] nums, int left, int right) {
        int max = left;
        for (int i = left; i < right; i++) {
            if (nums[max] < nums[i]) {
                max = i;
            }
        }
        return max;
    }

    /**
     * 序列化二叉树
     */

    /**
     * 填充每个节点的下一个右侧节点指针
     * 给定一个 完美二叉树 ，其所有叶子节点都在同一层，每个父节点都有两个子节点
     * 填充它的每个 next 指针，让这个指针指向其下一个右侧节点。如果找不到下一个右侧节点，则将 next 指针设置为 NULL。
     * 初始状态下，所有 next 指针都被设置为 NULL
     */
    public Node connect(Node root) {
        if (root == null) {
            return null;
        }
        Queue<Node> queue = new LinkedList<>();
        queue.add(root);
        while (!queue.isEmpty()) {
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                Node node = queue.poll();
                if (i == size - 1) {
                    node.next = null;
                } else {
                    node.next = queue.peek();
                }
                if (node.left != null) {
                    queue.add(node.left);
                }
                if (node.right != null) {
                    queue.add(node.right);
                }
            }
        }
        return root;
    }

    /**
     * 删除二叉搜索树中的节点
     * 给定一个二叉搜索树的根节点 root 和一个值 key，删除二叉搜索树中的 key 对应的节点，并保证二叉搜索树的性质不变。返回二叉搜索树（有可能被更新）的根节点的引用。
     * 根据二叉搜索树的性质
     *
     * 如果目标节点大于当前节点值，则去右子树中删除；
     * 如果目标节点小于当前节点值，则去左子树中删除；
     * 如果目标节点就是当前节点，分为以下三种情况：
     * 其无左子：其右子顶替其位置，删除了该节点；
     * 其无右子：其左子顶替其位置，删除了该节点；
     * 其左右子节点都有：其左子树转移到其右子树的最左节点的左子树上，然后右子树顶替其位置，由此删除了该节点。
     */
    public TreeNode deleteNode(TreeNode root, int key) {
        if (root == null) {
            return null;
        }
        if (root.val < key) {
            root.right = deleteNode(root.right, key);
        }
        if (root.val > key) {
            root.left = deleteNode(root.left, key);
        } else if (root.val == key) {
            if (root.left == null) {
                return root.right;
            }
            if (root.right == null) {
                return root.left;
            }
            TreeNode node = root.right;
            while (node.left != null) {
                node = node.left;
            }
            node.left = root.left;
            root = root.right;
        }
        return root;
    }

    /**
     * 验证二叉搜索树
     * 给定一个二叉树，判断其是否是一个有效的二叉搜索树
     * 方法一：二叉树的中序遍历是有序的
     */
    public boolean isValidBST(TreeNode root) {
        Stack<TreeNode> stack = new Stack<>();
        TreeNode pre = null;
        while(root != null || !stack.isEmpty()) {
            while(root != null) {
                stack.push(root);
                root = root.left;
            }
            root = stack.pop();
            if(pre != null&&root.val <= pre.val) {
                return false;
            }
            pre = root;
            root = root.right;
        }
        return true;
    }

    /**
     * 验证二叉搜索树
     * 方法二：递归
     */
    public boolean isValidBST1(TreeNode root) {
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
     * 二叉树转链表
     * 给定一个二叉树，原地将它展开为链表
     * 方法一：关键的是递归的时候先转换右子树，再转换左子树，所以需要记录一下右子树转换完后链表的头结点在哪里，注意没有新定义一个next指针，
     * 而是直接将right当做next指针，那么left指针赋值为null就可以了
     */
    private TreeNode pre = null;
    public void flatten(TreeNode root) {
        if(root == null) {
            return;
        }
        flatten(root.right);
        flatten(root.left);
        root.right = pre;
        root.left = null;
        pre = root;
    }

    /**
     * 方法二：
     * 1.将左子树插入到右子树的地方
     * 2.将原来的右子树接到左子树的最右边节点
     * 3.考虑新的右子树的根节点，一直重复上边的过程，直到新的右子树为 null
     */
    public void flatten1(TreeNode root) {
        while(root != null) {
            if(root.left == null) {
                root = root.right;
            } else {
                //找到左子树最右边的节点
                TreeNode pre = root.left;
                while(pre.right != null) {
                    pre = pre.right;
                }
                pre.right = root.right;
                root.right = root.left;
                root.left = null;
                root = root.right;
            }
        }
    }


    /**
     * 方法三：使用前序遍历，用栈保存右孩子，pre保存上次遍历的节点
     */
    public void flatten2(TreeNode root) {
        if(root == null) {
            return;
        }
        Stack<TreeNode> stack = new Stack<>();
        stack.push(root);
        TreeNode pre = null;
        while(!stack.isEmpty()) {
            TreeNode currentNode = stack.pop();
            if(pre != null) {
                pre.right = currentNode;
                pre.left = null;
            }
            if(currentNode.right != null) {
                stack.push(currentNode.right);
            }
            if(currentNode.left != null) {
                stack.push(currentNode.left);
            }
            pre = currentNode;
        }
    }

    /**
     * 二叉搜索树转换为双链表
     * 输入一棵二叉搜索树，将该二叉搜索树转换成一个排序的循环双向链表。要求不能创建任何新的节点，只能调整树中节点指针的指向。
     */
    public DNode treeToDoublyList(DNode root) {
        if(root == null) {
            return root;
        }
        Stack<DNode> stack = new Stack<>();
        stack.push(root);
        DNode pre = null;
        while(!stack.isEmpty()) {
            DNode currentNode = stack.pop();
            if(pre != null) {
                pre.right = currentNode;
                currentNode .left = pre;
            }
            if(currentNode.right != null) {
                stack.push(currentNode.right);
            }
            if(currentNode.left != null) {
                stack.push(currentNode.left);
            }
            pre = currentNode;
        }
        return root;
    }

}
