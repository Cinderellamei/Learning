package Algorithm.leecode.bytedance;

import java.util.HashMap;
import java.util.Map;

public class LRUCache {
    int size;
    int capacity;
    DLinkedNode head;
    DLinkedNode tail;
    
    public class DLinkedNode{
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

    private Map<Integer,DLinkedNode> cache = new HashMap<>();

    public LRUCache(int capacity) {
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
    		return node.value;
    	}
    }

    public void put(int key,int value) {
    	DLinkedNode node = cache.get(key);
    		if(node == null) {
    			DLinkedNode tempNode = new DLinkedNode(key,value);
    			cache.put(key,tempNode);
    			addToHead(tempNode);
    			size++;
    			if(size > capacity) {
    				DLinkedNode temp = removeTail();
    				cache.remove(temp.key);
    				size--;
    			}
    		} else {
    			node.value = value;
    			moveToHead(node);
    		}
    	}

    private void addToHead(DLinkedNode node) {
    	node.next = head.next;
    	node.pre = head;
    	head.next.pre = node;
    	head.next = node;
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
