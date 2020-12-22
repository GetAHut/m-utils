package com.xyt.utils.linked;

/**
 * 双向链表
 *      --> 链表的遍历即： 取出存入数组
 *
 * @Author: abby
 * @Date: 2020/12/11 10:22
 */
public class DoubleLinked {

    private Node head;
    private Node tail;
    private int size = 0;

    public DoubleLinked(){
        head = new Node();
        tail = new Node();
        head.next = null;
        tail.pre = null;
    }

    /**
     * 判断链表是否为空
     * @return
     */
    public boolean empty(){
        if (head.next == null){
            return true;
        }
        return false;
    }

    /**
     * 获取当前下标前节点
     * @param index
     * @return
     */
    public Node getPre(int index){
        Node pre = head;
        int dex = -1;
        while (pre.next != null){
            //找到上一个节点
            if (dex == index - 1){
                return pre;
            }
            //遍历
            pre = pre.next;
            dex++;
        }
        return null;
    }

    /**
     * 获取当前下标节点
     * @param index
     * @return
     */
    public Node getNow(int index){

        if (index < 0 || index >= size){
            throw new ArrayIndexOutOfBoundsException();
        }

        Node hNode = head;
        int dex = -1;
        while (hNode.next != null){
            if (dex == index){
                return hNode;
            }
            hNode = hNode.next;
            dex++;
        }
        //给最后一个节点做处理
        if (dex == size - 1){
            return hNode;
        }
        return null;
    }

    /**
     * 向链表结束插入节点
     * @param e
     */
    public void add(Object e){

        Node node = new Node(e);
        Node rnode = head;
        //链表如果为空 则没有上一个节点
        if (this.empty()){
            rnode.next = node;
            rnode.next.pre = null;
            tail.pre = node;
            size++;
        }else {
            //遍历至末节点
            while (rnode.next != null){
                rnode = rnode.next;
            }
            rnode.next = node;
            node.pre = rnode;
            tail.pre = node;
            size++;
        }
    }

    /**
     * 根据下标插入数据
     * @param index
     * @param e
     */
    public boolean add(int index, Object e) {

        if (index < 0 || index >= size){
            return false;
        }

        Node node = new Node(e);
        //获取前一个节点
        Node preNode = this.getPre(index);

        //画图
        node.next = preNode.next;
        preNode.next.pre = node;
        node.pre = preNode;
        preNode.next = node;
        size++;
        return true;
    }

    /**
     * 在指定下标处加linked -- 插入链表
     * @param index
     * @param linked
     * @return
     */
    public boolean add(int index, DoubleLinked linked){

        if (index < 0 || index >= size){
            return false;
        }
        //获取当前下标的前节点
        Node preNode = this.getPre(index);
        //tail.pre
        linked.tail.pre.next = preNode.next;
        preNode.next.pre = linked.tail.pre.pre;
        //head.next
        linked.head.next.pre = preNode;
        preNode.next = linked.head.next;

        linked.tail = null;
        linked.head = null;
        size += linked.size;

        return true;
    }

    /**
     *  通过下标获取数据
     * @param index
     * @return
     */
    public Object get(int index){

        if (index < 0 || index >= size){
            throw new ArrayIndexOutOfBoundsException();
        }
        //
        return getNow(index).data;
    }

    /**
     * 获取链表size
     * @return
     */
    public int size(){
        return size;
    }

    /**
     * 根据下标移除节点 并返回节点值
     * @param index
     * @return
     */
    public Object remove(int index){
        Object result = this.get(index);

        if (index < 0 || index >= size){
            //越界
            throw new ArrayIndexOutOfBoundsException();
        }

        //移除最后一个节点
        if (index == size - 1){
            //this  当前类对象
            Node node = this.getNow(index);
            this.tail.pre = this.tail.pre.pre;
            this.tail.pre.next.pre = null; //断开指针
            this.tail.pre.next = null;
            size--;
            return result;
        } else {
            //size >= 2
            Node preNode = this.getPre(index);
            preNode.next = preNode.next.next;
            preNode.next.pre.next = null;  //断开被删节点next指针
            preNode.next.pre = preNode.next.pre.pre;
            size--;
            return result;
        }

    }


    /**
     * 双向链表 节点类
     */
    private class Node{
        public Object data;
        public Node next;
        public Node pre;

        public Node() {

        }

        public Node(Object e){
            this.data = e;
            next = null;
            pre = null;
        }
    }
}
