package Algorithm.leecode.bytedance;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * 全O(1)的数据结构
 */
public class AllOne {
    //map1保存（key，value）映射
    private Map<String,Integer> map1;
    //map2保存（value，keys）映射
    //map2的作用是为了O（1）时间拿到统计次数对应的链表节点
    //链表中的所有操作只会涉及前一个或后一个节点，时间复杂度是O（1）
    private Map<Integer,DLinkedNode> map2;
    //双向链表的头，双向链表从head到tail的value值是递减的
    private DLinkedNode head;
    //双向链表的尾节点
    private DLinkedNode tail;

    public AllOne(){
        map1 = new HashMap<>();
        map2 = new HashMap<>();
        head = new DLinkedNode(0);
        tail = new DLinkedNode(0);
        head.next = tail;
        tail.pre = head;
    }

    public void inc(String key) {
        //如果map1中包含这个key
        if(map1.containsKey(key)) {
            int value = map1.get(key);
            map1.put(key,value+1);
            //根据value拿到次数更新前的node
            DLinkedNode node = map2.get(value);
            //value加一后，从原node的set中删除
            node.keys.remove(key);
            DLinkedNode preNode = node.pre;

            /**
             * 当前一个node为head，或前一个node的值大于value加一时，表示目前没有统计次数为value+1的key，此时应该新建一个DLinkedNode
             * 节点，将newNode节点插入到preNode和node之间，并把key加入newNode保存key的set中，同时将新的统计次数value+1和新的DLinkedNode
             * 节点newNode放入map2中
             */
            if(preNode == head || preNode.value>value+1) {
                DLinkedNode newNode = new DLinkedNode(value+1);
                newNode.keys.add(key);
                newNode.next = node;
                newNode.pre = preNode;
                preNode.next = newNode;
                node.pre = newNode;
                map2.put(value+1,newNode);
                preNode = newNode;
                //如果当前已经有统计次数为value+1的节点，只需要把key加入到set中即可
            } else {
                preNode.keys.add(key);
            }
            //如果原节点在移除key后size为0，则删除该节点，并在map2中删除value，node的映射
            if(node.keys.size() == 0) {
                preNode.next = node.next;
                node.next.pre = preNode;
                map2.remove(value);
            }
            //如果map1不包含key
        } else {
            map1.put(key,1);
            DLinkedNode node = map2.get(1);
            //如果map2中没有统计次数为1的节点，则新建节点并插入到双向链表的尾部，因为统计次数最小为1，并将1，newNode的映射加入到map2中
            if(node == null) {
                DLinkedNode newNode = new DLinkedNode(1);
                newNode.keys.add(key);
                newNode.next = tail;
                newNode.pre = tail.pre;
                tail.pre.next = newNode;
                tail.pre = newNode;
                map2.put(1,newNode);
            } else {
                node.keys.add(key);
            }
        }
    }

    public void dec(String key) {
        //如果map1中包含key，进行处理，否则不做任何处理
        if(map1.containsKey(key)) {
            //获取当前统计次数
            int value = map1.get(key);
            //当前统计次数对应的节点
            DLinkedNode node = map2.get(value);
            //从节点的keys set移除当前key
            node.keys.remove(key);
            // 如果原统计次数为1，从map1中删除当前key
            if(value == 1) {
                map1.remove(key);
            } else {
                //更新map1中的统计次数
                map1.put(key,value-1);
                //拿到当前节点的下一个节点
                DLinkedNode nextNode = node.next;
                //如果下一个节点为链表尾节点或下一个节点的统计次数小于value-1
                //则新建一个节点，统计次数为value1-1，将当前key加入到key set中
                //并将新节点插入到当前节点的后面，同时更新map2
                if(nextNode == tail || nextNode.value<value-1) {
                    DLinkedNode newNode = new DLinkedNode(value-1);
                    newNode.keys.add(key);
                    newNode.pre = node;
                    newNode.next = nextNode;
                    node.next = newNode;
                    nextNode.pre = newNode;
                    map2.put(value-1,newNode);
                    //下一个节点的统计次数为value-1，将key加入到下一节点的keys set中，
                } else {
                    nextNode.keys.add(key);
                }
            }
            //如果当前节点只包含这一个key，删除后size为0，则将当前节点删除，并更新map2
            if(node.keys.size() == 0) {
                node.pre.next = node.next;
                node.next.pre = node.pre;
                map2.remove(value);
            }
        }
    }

    public String getMaxKey() {
        //按照双向链表的定义，如果链表中存在节点，则对应最大value的key应该是head的下一个节点
        if(head == null) {
            return " ";
        } else {
            return head.next.keys.iterator().next();
        }
    }

    public String getMinKey() {
        //按照双向链表的定义，如果链表中存在节点，则对应最大value的key应该是tail的前一个节点
        if(tail == null) {
            return " ";
        } else {
            return tail.pre.keys.iterator().next();
        }
    }

    public class DLinkedNode{
        int value;
        Set<String> keys;
        DLinkedNode pre;
        DLinkedNode next;
        public DLinkedNode(int value) {
            this.value = value;
            this.keys = new HashSet<>();
        }
    }
}
