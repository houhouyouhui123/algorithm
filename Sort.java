package com.company;

import java.util.Arrays;
import java.util.Random;

public class Sort {
    static int count=0;
    private static void swap(int [] nums,int pos1,int pos2){
        int temp=nums[pos1];
        nums[pos1]=nums[pos2];
        nums[pos2]=temp;
    }
    
    public static void printNums(int [] nums){
        for( int num:nums){
            System.out.print(num+"   ");
        }
        System.out.println();
    }

    public static int[] getRandomNums(int low,int hight,int length){
        int[] nums=new int[length];
        for(int pos=0;pos<length;pos++){
            nums[pos]=new Random().nextInt(hight)+low;
        }
        return nums;
    }

    public static Boolean check(int[]nums,int[]sortedNums){
        nums=nums.clone();
        Arrays.sort(nums);
        printNums(nums);
        for(int pos=0;pos<nums.length;pos++){
            if(nums[pos]!=sortedNums[pos]){
                System.out.print("Check not correct!");
                return false;
            }
        }
        System.out.print("Check correct!");
        return true;
    }


    public static void selectionSort(int[] nums){
        int minPos=0;
        for(int pos1=0;pos1<nums.length-1;pos1++){
            minPos=pos1;
            for(int pos2=pos1+1;pos2<nums.length;pos2++){
                if(nums[pos2]<nums[minPos]){
                    minPos=pos2;
                }
            }
            if(minPos!=pos1){
                swap(nums,pos1,minPos);
            }
        }
    }


    public static void bubbleSort(int[] nums){
        if(nums==null){
            throw new NullPointerException("nums is null");
        }
        for(int pos1=0;pos1<nums.length-1;pos1++){
            for(int pos2=pos1+1;pos2<nums.length;pos2++){
                if(nums[pos2]<nums[pos1]){
                    swap(nums,pos1,pos2);
                }
            }
        }
    }


    public static void InsertionSort(int[] nums){
        int temp=0;
        int pos1=0;
        int pos2=0;
        for(pos1=1;pos1<nums.length;pos1++){
            temp=nums[pos1];
            for(pos2=pos1-1;pos2>=0&&nums[pos2]>temp;pos2--){
                nums[pos2+1]=nums[pos2];
            }
            if(pos2!=pos1-1){
                nums[pos2+1]=temp;
            }
        }
    }

    public static void HeapSort(int[] nums){
        int end=nums.length-1;
        buildHeap( nums,end);
        swap(nums,0,end);
        end--;
        for(;end>0;end--){
            heapify( nums,end,0);
            swap(nums,0,end);
        }
    }

    private static void heapify(int[] nums,int end,int parentPos){
        int leftPos=2*parentPos+1;
        int rightPos=2*parentPos+2;
        while(leftPos<=end){
            int maxPos=parentPos;
            if(leftPos<=end&&nums[leftPos]>nums[maxPos]){
                maxPos=leftPos;
            }
            if(rightPos<=end&&nums[rightPos]>nums[maxPos]){
                maxPos=rightPos;
            }
            if(maxPos!=parentPos){
                swap(nums,parentPos,maxPos);
                parentPos=maxPos;
                leftPos=2*parentPos+1;
                rightPos=2*parentPos+2;
            }else {
                return ;
            }
        }
    }




    private static void buildHeap(int[] nums,int end){

    }



    public static int[] shellSort(int[] nums){
        return nums;
    }

    public static void mergeSort(int[] nums){
        mergeSort(nums,0,nums.length-1);
    }

    private static void mergeSort(int[] nums,int left,int right){
        if(left<right){
            int mid=(left+right)/2;
            mergeSort(nums,left,mid);
            mergeSort(nums,mid+1,right);
            merge(nums,left,mid,right);
        }
    }

    public static void merge(int[]nums,int left,int mid,int right){
        System.out.println("merge----");
        int temp=0;
        int pos1=left;
        int pos2=mid+1;
        int [] tempArray=new int[right-left+1];
        int tempPos=0;
        while(pos1<=mid&&pos2<=right){
            if(nums[pos1]<nums[pos2]){
                tempArray[tempPos++]=nums[pos1++];
            }else{
                tempArray[tempPos++]=nums[pos2++];
            }
        }
        pos2--;
        if(pos1<=mid){
            while(mid>=pos1){
                nums[pos2--]=nums[mid--];
            }
        }
        for(int pos=0;pos<=pos2-left;pos++){
            nums[left+pos]=tempArray[pos];
        }
    }

    public static void quickSort(int[] nums){
        quickSort(nums,0,nums.length-1);
    }


    private static void quickSort(int[] nums,int left,int right){
        System.out.println( "count"+ ++count);
        if(left<right){
            int pivot=nums[right];
            int pos1=left;
            int pos2=right-1;
            while(pos1<pos2){
                while(pos1<pos2&&nums[pos1]<=pivot){
                    pos1++;
                }
                while(pos1<pos2&&nums[pos2]>=pivot){
                    pos2--;
                }
                if(nums[pos1]>pivot&&nums[pos2]<pivot){
                    swap(nums,pos1,pos2);
                    pos1++;
                    pos2--;
                }
            }
            if(nums[pos1]<pivot){
                pos1++;
            }
            for(pos2=right;pos2>pos1;pos2--){
                nums[pos2]=nums[pos2-1];
            }
            nums[pos2]=pivot;
            quickSort(nums,left,pos2-1);
            quickSort(nums,pos2+1,right);
        }
    }


    public static void countingSort(int[] nums){
        int min=nums[0];
        int max=nums[0];
        for(int pos=1;pos<nums.length;pos++){
            if(min>nums[pos]){
                min=nums[pos];
            }
            if(max<nums[pos]){
                max=nums[pos];
            }
        }
        int[] countArray=new int[max-min+1];
        for(int pos=0;pos<countArray.length;pos++){
            countArray[pos]=0;
        }
        for(int pos=0;pos<nums.length;pos++){
            countArray[nums[pos]-min]++;
        }
        int numsPos=0;
        for(int pos=0;pos<countArray.length;){
            if(countArray[pos]>0){
                nums[numsPos++]=pos+min;
                countArray[pos]--;
            }else {
                pos++;
            }
        }
    }

    public static void radixSort(int[] nums) {
        int maxDigit=0;
        int max=nums[0];
        for(int pos=1;pos<nums.length;pos++){
            if(max<nums[pos]){
                max=nums[pos];
            }
        }
        while (max>0){
            max/=10;
            maxDigit++;
        }
        int[] tempArray=new int[nums.length];
        int tempPos=0;
        int divisor=1;
        for(int digit=0;digit<maxDigit;digit++,divisor*=10){
            tempPos=0;
            for(int num=0;num<10;num++){
                for(int pos=0;pos<nums.length;pos++){
                    if(nums[pos]/divisor%10==num){
                        tempArray[tempPos++]=nums[pos];
                    }
                }
            }
            int[] temp=tempArray;
            tempArray=nums;
            nums=temp;
        }
    }













    public static void main(String[] args) {
        int[] nums=getRandomNums(0,100,1000);
        int[] sortedNums=nums.clone();

//        selectionSort(sortedNums);
//        bubbleSort(sortedNums);
//        InsertionSort(sortedNums);
//        HeapSort(sortedNums);
//        shellSort(sortedNums);
//        mergeSort(sortedNums);
//        quickSort(sortedNums);
//        countingSort(sortedNums);
//        radixSort(sortedNums);








        if(nums==null||sortedNums==null){
            System.out.print("Nums is null or sortedNums is null!");
            return;
        }
        System.out.print("The  original  random  nums is:  ");
        printNums(nums);
        System.out.print("The nums sorted  by  custom is:  ");
        printNums(sortedNums);
        System.out.print("The nums sorted by javaUtil is:  ");
        check(nums,sortedNums);
        System.out.print("\n\nThe  original  random  nums is:  ");
        printNums(nums);

    }
}
