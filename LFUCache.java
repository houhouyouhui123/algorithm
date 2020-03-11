package com.company;

import java.util.HashMap;

public class LFUCache {
    class DuLNode{
        int key;
        int val;
        int frequency;
        DuLNode prior;
        DuLNode next;
        public DuLNode(int key,int val){
            this.key=key;
            this.val=val;
            this.frequency=1;
            this.prior=null;
            this.next=null;
        }
        public void increaseFrequency(){
            frequency++;
        }
    }

    class DuLNodeList{
        DuLNode head;
        DuLNode tail;
        void add(DuLNode node){
            if(head==null){
                tail=head=node;
            }else {
                node.prior=tail;
                tail.next=node;
                tail=node;
            }
        }


        DuLNode poll(){
            DuLNode first=head;
            head=first.next;
            head.prior=null;
            first.next=null;
            return first;
        }

        void remove(DuLNode node){
            if(head==tail){
                head=null;
                tail=null;
                return ;
            }
            if(node==head){
                head=head.next;
                head.prior=null;
                node.next=null;
                return ;
            }
            if(node==tail){
                tail=tail.prior;
                tail.next.prior=null;
                tail.next=null;
                return ;
            }
            node.prior.next=node.next;
            node.next.prior=node.prior;
            node.prior=null;
            node.next=null;
        }

        Boolean isEmpty(){
            return head==null;
        }


    }

    int capacity;
    int size;
    int minFrequency;
    HashMap<Integer, DuLNode> cacheMap;
    HashMap<Integer, DuLNodeList> frequencyMap;

    private void addItem(DuLNode node){
        if(frequencyMap.containsKey(node.frequency)){
            frequencyMap.get(node.frequency).add(node);
        }else{
            DuLNodeList duLNodeList=new DuLNodeList();
            duLNodeList.add(node);
            frequencyMap.put(node.frequency,duLNodeList);
        }
    }

    private void removeItem(DuLNode node){
        frequencyMap.get(node.frequency).remove(node);
        if(frequencyMap.get(node.frequency).isEmpty()){
            frequencyMap.remove(node.frequency);
        }
    }

    public LFUCache(int capacity) {
        this.capacity=capacity;
        cacheMap=new HashMap<>();
        frequencyMap=new HashMap<>();
        this.size=0;
        minFrequency=1;
    }

    public int get(int key) {
        int result=-1;
        if(cacheMap.containsKey(key)){
            DuLNode node=cacheMap.get(key);
            removeItem(node);
            if(!frequencyMap.containsKey(minFrequency)) {
                minFrequency++;
            }
            node.increaseFrequency();
            addItem(node);
            result= node.val;
        }
        System.out.println(result);
        return result;
    }





    public void put(int key, int value) {
        if(capacity==0){
            return;
        }
        if(cacheMap.containsKey(key)){
            DuLNode node=cacheMap.get(key);
            node.val=value;
            removeItem(node);
            if(!frequencyMap.containsKey(minFrequency)) {
                minFrequency++;
            }
            node.increaseFrequency();
            addItem(node);
            return;
        }

        DuLNode node=new DuLNode(key,value);
        if(size<capacity){
            size++;
        }else {
            DuLNode head=frequencyMap.get(minFrequency).head;
            removeItem(head);
            cacheMap.remove(head.key);
        }
        addItem(node);
        cacheMap.put(key,node);
        minFrequency=1;
    }


    public static void main(String[] args) {
        LFUCache cache = new LFUCache(0);
        cache.put(0,0);
        cache.get(2);       // returns -1 (not found)
    }

}
