package Algorithm.leecode.bytedance;

public class DNode {
    public int val;
    public DNode left;
    public DNode right;

    public DNode() {}
    public DNode(int val) {
        this.val = val;
    }

    public DNode(int val,DNode left,DNode right) {
        this.val = val;
        this.left = left;
        this.right = right;
    }
}
