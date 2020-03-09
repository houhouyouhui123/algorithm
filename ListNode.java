package com.company;

public class ListNode {
    int val;
    ListNode next;
    ListNode(int x) {
        val = x;
        next=null;
    }
    static ListNode getList(int[] arr){
        ListNode head=new ListNode(1);
        ListNode currentNode=head;

        for(int num:arr){
            currentNode.next=new ListNode(num);
            currentNode=currentNode.next;
        }
        return head.next;
    }
}
