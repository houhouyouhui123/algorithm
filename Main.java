package com.company;

import java.util.*;

public class Main {

    public ListNode reverseKGroup(ListNode head, int k) {
        if(head==null||head.next==null){
            return head;
        }
        ListNode currentNode=head;
        ListNode nextNode=head.next;
        ListNode tempNode=null;
        ListNode headNode=null;
        ListNode tail=null;
        head=null;
        while(nextOfK(currentNode,k-1)!=null){
            headNode=nextOfK(currentNode,k);
            currentNode.next=null;
            while(nextNode!=headNode){
                tempNode=nextNode.next;
                nextNode.next=currentNode;
                currentNode=nextNode;
                nextNode=tempNode;
            }
            if(head==null){
                head= currentNode;
                tail=currentNode;
            }else{
                tail.next= currentNode;
            }
            while(tail.next!=null){
                tail=tail.next;
            }
            currentNode=nextNode;
            if(nextNode!=null){
                nextNode=nextNode.next;
            }
        }

        return head;
    }
    private ListNode nextOfK(ListNode l,int k){
        while(k>0){
            if(l==null){
                return null;
            }
            l=l.next;
            k--;
        }
        return l;
    }

    public ListNode mergeKLists(ListNode[] lists) {
        if(lists.length==0){
            return null;
        }
        if(lists.length==1){
            return lists[0];
        }
        int index=1;
        int minIndex=0;
        ListNode tempNode=null;
        for(index=0;index<lists.length;index++){
            if(lists[index]!=null){
                break;
            }
        }
        if(index==lists.length){
            return null;
        }

        if(index!=0){
            tempNode= lists[0];
            lists[0]=lists[index];
            lists[index]=tempNode;
        }


        for(index=1;index<lists.length;index++){
            if(lists[minIndex]!=null&&lists[index]!=null){
                if(lists[minIndex].val>lists[index].val){
                    minIndex=index;
                }
            }
        }
        if(minIndex!=0){
            tempNode= lists[0];
            lists[0]=lists[minIndex];
            lists[minIndex]=tempNode;
        }
        for(index=1;index<lists.length;index++){
            mergeTwoLists(lists[0],lists[index]);
        }
        return lists[0];
    }

    private void mergeTwoLists(ListNode l1 ,ListNode l2){
        ListNode currentNode=null;
        ListNode tempNode=null;
        for(;l2!=null;){
            tempNode=l2.next;
            for(currentNode=l1;currentNode.next!=null;currentNode=currentNode.next){
                if(l2.val>=currentNode.val&&l2.val<currentNode.next.val){
                    l2.next=currentNode.next;
                    currentNode.next=l2;
                    break;
                }
            }
            if(currentNode.next==null){
                currentNode.next=l2;
                l2.next=null;
            }
            l2=tempNode;
        }
    }



    public ListNode rotateRight(ListNode head, int k) {
        if(head==null||head.next==null){
            return head;
        }
        ListNode currentNode=head;
        ListNode tempNode=head;
        while(currentNode.next!=null){
            if(k==0){
                head=currentNode.next;
                currentNode.next=null;
                break;
            }
            k--;
            currentNode=currentNode.next;
        }
        while(currentNode.next!=null){
            currentNode=currentNode.next;
        }

        currentNode.next=tempNode;

        return head;
    }
    public ListNode deleteDuplicates(ListNode head) {
        ListNode currentNode=new ListNode(1);
        currentNode.next=head;
        head=currentNode;
        ListNode nextNode=null;
        boolean status=false;
        while(currentNode.next!=null){
            status=false;
            if(currentNode.next.next!=null&&currentNode.next.val==currentNode.next.next.val){
                nextNode=currentNode.next.next.next;
                if(nextNode==null){
                    currentNode.next=null;
                    status=true;
                    break;
                }
                while(nextNode!=null){
                    if(nextNode.val!=currentNode.next.val){
                        currentNode.next=nextNode;
                        status=true;
                        break;
                    }else{
                        currentNode.next=currentNode.next.next;
                    }
                    nextNode=nextNode.next;
                }
                if(status){
                    continue;
                }else{
                    currentNode.next=null;
                    break;
                }
            }
            currentNode=currentNode.next;
        }
        currentNode.next=null;
        return head.next;
    }

    public boolean isValid(String s) {
        if(s==null){
            return true;
        }
        if(s.length()%2==1){
            return false;
        }
        Stack<Character> stack =new Stack<Character>();
        char tempChar='0';
        for(int index=0;index<s.length();index++){
            if(stack.isEmpty()){
                if(isLeft(s.charAt(index))){
                    stack.push(s.charAt(index));
                }else{
                    return false;
                }

            }else{
                tempChar= stack.pop();
                if(tempChar=='('){
                    if(s.charAt(index)!=')'){
                        if(isLeft(s.charAt(index))){
                            stack.push(tempChar);
                            stack.push(s.charAt(index));
                        }else{
                            return false;
                        }
                    }
                }else{
                    if(s.charAt(index)-tempChar!=2){
                        if(isLeft(s.charAt(index))){
                            stack.push(tempChar);
                            stack.push(s.charAt(index));
                        }else{
                            return false;
                        }
                    }

                }
            }

        }
        if(stack.isEmpty()){
            return true;
        }else{
            return false;
        }
    }

    private Boolean isLeft(char c){
        if(c=='('||c=='{'||c=='['){
            return true;
        }else{
            return false;
        }
    }


    public int lengthOfLastWord(String s) {
        if(s==null||s.length()==0){
            return 0;
        }
        char[] aa=s.toCharArray();
        System.out.print(aa[1]=='0');
        aa.toString();
        int index=s.length()-1;
        for(;index>0;index--){
            if(s.charAt(index)==' '){
                index++;
                break;
            }
        }
        return s.length()-index;
    }


    public String addBinary(String a, String b) {
        if(a.length()<b.length()){
            String tempStr=a;
            a=b;
            b=tempStr;
        }
        char[] arra= a.toCharArray();
        char[] arrb= b.toCharArray();
        int indexA= arra.length-1;
        int indexB= arrb.length-1;
        Boolean status=false;
        for(;indexB>=0;indexA--,indexB--){
            if(!status&&arra[indexA]=='0'){
                arra[indexA]=arrb[indexB];
                continue;
            }

            if(!status&&arrb[indexB]=='1'){
                arra[indexA]='0';
                status=true;
                continue;
            }
            if(status){
                if(arra[indexA]=='0'&&arrb[indexB]=='0'){
                    arra[indexA]='1';
                    status=false;
                    continue;
                }
                if(arra[indexA]=='1'&&arrb[indexB]=='1'){
                    arra[indexA]='1';
                    continue;
                }else{
                    arra[indexA]='0';
                    continue;
                }
            }
        }
        if(!status){
            return String.valueOf(arra);
        }
        for(;indexA>=0;indexA--){
            if(arra[indexA]=='0'){
                arra[indexA]='1';
                status=false;
                break;
            }else{
                arra[indexA]='0';
            }
        }
        if(status){
            return "1"+String.valueOf(arra);
        }
        return String.valueOf(arra);
    }

    public int mySqrt(int x) {
        if(x==0||x==1){
            return x;
        }
        long low =1;
        long high=1;
        while(high*high<x){
            high*=2;
        }
        if(high*high==x){
            return (int) high;
        }
        long result =0;
        while(high-low>1){
            result=(high+low)/2;
            if(result*result>x){
                high=result;
            }else{
                low=result;
            }
        }
        return (int) high;
    }

    public int[] twoSum(int[] numbers, int target) {
        if(numbers.length<2){
            return null;
        }
        int start=0;
        int end= numbers.length-1;
        while(numbers[start]+numbers[end]<target){
            start++;
        }
        while(numbers[0]+numbers[end]>target){
            end--;
        }
        int index1=start;
        int index2=end;
        for(;2*numbers[index1]<=target;index1++){
            for(index2=index1+1;index2<=end&&numbers[index1]+numbers[index2]<target;index2++){
            }
            if(numbers[index1]+numbers[index2]==target){
                return new int[]{index1+1,index2+1};
            }
        }
        return null;
    }

    public String convertToTitle(int n) {
        String result="";
        char ch='A'-1;
        String s;

        while(n!=0){
            result=(char)(ch+n%26)+result;
            n/=26;
        }
        return result;
    }
    public boolean isPerfectSquare(int num) {
        long high=1;
        while(high*high<num){
            high*=2;
        }
        if(high>num){
            high=num;
        }
        long low=0;
        long mid=0;
        while(low+1<high){
            mid=(low+high)/2;
            if(mid*mid<num){
                low=mid;
            }
            if(mid*mid>num){
                high=mid;
            }else{
                return true;
            }
        }
        if(high*high==num){
            return true;
        }
        return false;
    }
    public boolean judgeSquareSum(int c) {
        int end1=(int)Math.sqrt(c/2)+1;
        int end2=(int)Math.sqrt(c)+1;
        int index2;
        for(int index1=0;index1<end1;index1++){
            for(index2=index1;index2<end2;index2++){
                if(index1*index1+index2*index2==c){
                    return true;
                }
            }
        }
        return false;
    }
    public int arrangeCoins(int n) {
        long row=(int)Math.sqrt(2.0*n);
        while((1+row)*row/2>n){
            row--;
        }
        return (int) row;
    }

    public String largestTimeFromDigits(int[] A) {
        int h=-1;
        int m=-1;
        for(int h1=0;h1<4;h1++){
            for(int h2=0;h2<4;h2++){
                if(h1==h2){
                    continue;
                }
                for(int m1=0;m1<4;m1++){
                    if(m1==h1||m1==h2){
                        continue;
                    }
                    for(int m2=0;m2<4;m2++){
                        if(m2==m1&&m2==h1&&m2==h2){
                            continue;
                        }
                        if((A[h1]*10+A[h2])>24||(A[m1]*10+A[m2])>60){
                            continue;
                        }
                        if((A[h1]*10+A[h2])*60+(A[m1]*10+A[m2])>h*60+m){
                            h=A[h1]*10+A[h2];
                            m=A[m1]*10+A[m2];
                        }
                    }
                }
            }
        }
        if(h==-1){
            return "";
        }
        return h+":"+m;
    }

    public int bitwiseComplement(int N) {
        String s="";
        while(N>0){
            s=N%2+s;
            N/=2;
        }
        int index=0;
        for(;index<s.length();index++){
            if(s.charAt(index)=='0'){
                break;
            }
        }
        int result=0;
        for(;index<s.length();index++){
            result=result*2;
            if(s.charAt(index)=='0'){
                result+=1;
            }
        }
        return result;
    }

    public int[] distributeCandies(int candies, int num_people) {
        int last = (int)Math.sqrt(candies*2);
        int[] result=new int[num_people];
        for(int num:result){
            num=0;
        }
        if(last*(last+1)<=candies*2){
            last++;
        }
        int index=0;
        for(int num=1;num<last;num++,index++){
            result[index%num_people]+=num;
        }
        result[index%num_people]+=candies-last*(last-1)/2;
        return result;
    }

    public static void main(String[] args) {
	// write your code here
        Main main=new Main();

        int[] result=main.distributeCandies(10,3);;
        for(int num:result){
            System.out.print(num+" ");

        }

    }


}


