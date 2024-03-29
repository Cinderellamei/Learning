package Algorithm.leecode.hot100;

import java.util.HashMap;
import java.util.Map;

/**
 * LRU缓存机制
 * 运用你所掌握的数据结构，设计和实现一个  LRU (最近最少使用) 缓存机制 。
 * 实现 LRUCache 类：
 *
 * LRUCache(int capacity) 以正整数作为容量 capacity 初始化 LRU 缓存
 * int get(int key) 如果关键字 key 存在于缓存中，则返回关键字的值，否则返回 -1 。
 * void put(int key, int value) 如果关键字已经存在，则变更其数据值；如果关键字不存在，则插入该组「关键字-值」。当缓存容量达到上限时，它应该在写入新数据之前删除最久未使用的数据值，从而为新的数据值留出空间。
 *
 * 进阶：你是否可以在 O(1) 时间复杂度内完成这两种操作？
 *
 * 方法：哈希表+双向链表
 */


public class LRUCache {
   class DLinkedNode{
       int key;
       int value;
       DLinkedNode pre;
       DLinkedNode next;
       public DLinkedNode(){}
       public DLinkedNode(int key,int value) {
           this.key = key;
           this.value = value;
       }
   }

    private Map<Integer,DLinkedNode> cache = new HashMap<Integer,DLinkedNode>();
    private int size;
    private int capacity;
    private DLinkedNode head;
    private DLinkedNode tail;

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

    public void put(int key,int value) {
        DLinkedNode node = cache.get(key);
        if(node == null) {
            DLinkedNode temp = new DLinkedNode(key,value);
            cache.put(key,temp);
            addToHead(temp);
            ++size;
            if(size>capacity) {
                DLinkedNode tail = removeTail();
                cache.remove(tail.key);
                --size;
            }
        } else {
            node.value = value;
            moveToHead(node);
        }
    }

    private void addToHead(DLinkedNode node) {
        head.next.pre = node;
        node.next = head.next;
        head.next = node;
        node.pre = head;
    }

    private void removeNode(DLinkedNode node) {
        node.pre.next = node.next;
        node.next.pre = node.pre;
    }

    private void moveToHead(DLinkedNode node) {
        removeNode(node);
        addToHead(node);
    }

    private DLinkedNode removeTail() {
        DLinkedNode cur = tail.pre;
        removeNode(cur);
        return cur;
    }
}
