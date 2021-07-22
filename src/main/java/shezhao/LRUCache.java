package shezhao;

import java.util.HashMap;

public class LRUCache {

    private HashMap<Integer,DLinkedNode> cache = new HashMap<>();
    private int count;
    private int capacity;
    private DLinkedNode head,tail;


    private static class DLinkedNode {
        int key;
        int value;
        DLinkedNode pre;
        DLinkedNode post;
    }

    //在头节点后插入节点
    private void addNode(DLinkedNode node) {
        node.pre = head;
        node.post = head.post;
        head.post.pre = node;
        head.post = node;
    }

    //摘除一个节点
    private void removeNode(DLinkedNode node) {
        node.pre.post = node.post;
        node.post.pre = node.pre;
    }

    //将一个节点移动到头节点后
    private void moveToHead(DLinkedNode node) {
        this.removeNode(node);
        this.moveToHead(node);
    }

    //弹出尾巴节点
    private DLinkedNode popTail() {
        DLinkedNode node = tail.pre;
        this.removeNode(node);
        return node;
    }

    public LRUCache(int capacity) {
        this.count = 0;
        this.capacity = capacity;

        head = new DLinkedNode();
        head.pre = null;

        tail = new DLinkedNode();
        tail.post = null;

        head.post = tail;
        tail.pre = head;
    }

    public int get(int key) {
        int result = 0;
        return result;
    }


}
