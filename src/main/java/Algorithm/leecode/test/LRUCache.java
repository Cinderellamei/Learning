package Algorithm.leecode.test;

import java.util.HashMap;

public class LRUCache {
    int size;
    int capacity;
    DLinkedNode head;
    DLinkedNode tail;

    HashMap<Integer,DLinkedNode> cache = new HashMap<>();

    class DLinkedNode {
        int key;
        int value;
        DLinkedNode next;
        DLinkedNode pre;

        public DLinkedNode(){}
        public DLinkedNode(int key,int value) {
            this.key = key;
            this.value = value;
        }
    }

    public LRUCache(int capacity) {
        this.size = 0;
        this.capacity = capacity;
        head = new DLinkedNode();
        tail = new DLinkedNode();
        head.next = tail;
        tail.pre = head;
    }

    public int get(int key) {
        DLinkedNode node = cache.get(key);
        if(node == null) {
            return -1;
        } else {
            moveToHead(node);
            return node.value;
        }
    }

    public void set(int key,int value) {
        DLinkedNode node = cache.get(key);
        if(node == null) {
            DLinkedNode tempNode = new DLinkedNode(key,value);
            cache.put(key,tempNode);
            addToHead(tempNode);
            size++;
            if(size >capacity) {
                DLinkedNode tail = removeTail();
                cache.remove(tail);
                size--;

            }
        } else {
            node.value = value;
            moveToHead(node);
        }
    }

    public void addToHead(DLinkedNode node) {
        node.next = head.next;
        node.pre = head;
        head.next.pre = node;
        head.next = node;
    }

    public void moveToHead(DLinkedNode node) {
        removeNode(node);
        addToHead(node);
    }

    public void removeNode(DLinkedNode node) {
        node.next.pre = node.pre;
        node.pre.next = node.next;
    }

    public DLinkedNode removeTail() {
        DLinkedNode cur = tail.pre;
        removeNode(cur);
        return cur;
    }

}
