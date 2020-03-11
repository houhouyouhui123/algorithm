package com.company;

import jdk.nashorn.internal.codegen.DumpBytecode;

import java.rmi.UnexpectedException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Queue;

public class LRUCache {

    class DuLNode{
        int key;
        int val;
        DuLNode prior;
        DuLNode next;
        public DuLNode(int key,int val){
            this.key=key;
            this.val=val;
            this.prior=null;
            this.next=null;
        }
    }
    private void  moveToTail(DuLNode node){
        if(size<=1||node==tail){
            return ;
        }
        if(node==head){
            head=node.next;
        }else{
            node.prior.next=node.next;
            node.next.prior=node.prior;
        }
        tail.next = node;
        node.prior = tail;
        tail=node;
        tail.next=null;
    }

    private void  addToTail(int key,int val){
        if(head==null){
            head=new DuLNode(key,val);
            tail=head;
        }else{
            tail.next=new DuLNode(key,val);
            tail.next.prior=tail;
            tail=tail.next;
        }
        cacheMap.put(key,tail);
        size++;
    }

    int capacity;
    int size;
    DuLNode head;
    DuLNode tail;
    HashMap<Integer,DuLNode> cacheMap;


    public LRUCache(int capacity) {
        this.capacity=capacity;
        cacheMap=new HashMap<>();
        size=0;
        DuLNode head=null;
        DuLNode tail=null;
    }

    public int get(int key) {
        if(cacheMap.containsKey(key)){
            moveToTail(cacheMap.get(key));
            System.out.println(tail.val);
            return tail.val;
        }else {
            System.out.println(-1);
            return -1;
        }
    }

    public void put(int key, int value) {
        if(cacheMap.containsKey(key)){
            cacheMap.get(key).val=value;
            moveToTail(cacheMap.get(key));
            return;
        }
        if(head==null||size<capacity){
            addToTail(key,value);
            return;
        }
        if(size>=capacity){
            cacheMap.remove(head.key);
            if(head.next==null){
                tail=head=new DuLNode(key,value);
            }else {
                head=head.next;
                head.prior.next=null;
                head.prior=null;
                size--;
                addToTail(key,value);
            }
            cacheMap.put(key,tail);
        }
    }

    public void printCache(){
        DuLNode current=head;
        while (current!=null){
            System.out.print(current.val+"    ");
            current=current.next;
        }
        System.out.println();
    }


    public static void main(String[] args) {


        LRUCache cache = new LRUCache(2);

        cache.put(1,1);
        cache.put(2,2);
        cache.get(1);
        cache.put(3,3);
        cache.get(2);
        cache.put(4,4);
        cache.get(1);
        cache.get(3);
        cache.get(4);


//        cache.printCache();
//        System.out.println(cache.get(3));
//        cache.printCache();
//        System.out.println(cache.get(4));
//        cache.printCache();
//        System.out.println(cache.size);



    }




}
