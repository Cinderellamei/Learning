package Algorithm.leecode.tree;

public class Node {
    int val;
    Node left;
    Node right;
    Node next;
    public Node(){};
    public Node(int val) {
        this.val = val;
    }
    public Node(int val,Node left,Node right,Node next) {
        this.val = val;
        this.left = left;
        this.right = right;
        this.next = next;
    }
}
