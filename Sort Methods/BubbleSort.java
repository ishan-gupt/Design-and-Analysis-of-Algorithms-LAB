package com.company;

public class BubbleSort {
    public static void main(String args[]){
        int arr[]={10,35,-15,7,55,1,-22};
        for(int i= arr.length-1;i>0;i--){
            for(int j=0;j<i;j++){
                if(arr[j]>arr[j+1]){
                    swap(arr,j,j+1);
                }
            }
        }
        print(arr);
    }
    public static void swap(int[] arr,int i,int j){
        if(i==j){
            return;
        }
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }
    public static void print(int[] arr){
        for(int i=0;i<arr.length;i++){
            System.out.print(arr[i]+" ");
        }
    }
}
