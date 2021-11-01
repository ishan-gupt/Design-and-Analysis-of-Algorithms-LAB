package com.company;

public class InsertionSort {
    public static void main(String args[]) {
        int[] arr = {10, 35, -15, 7, 55, 1, -22};
        for (int i=1;i< arr.length;i++){
            int a=arr[i];
            int j;
            for(j=i;j>0 && arr[j-1]>a;j--){
                arr[j]=arr[j-1];
            }
            arr[j]=a;
        }
        print(arr);
    }
    public static void print(int[] arr){
        for(int i=0;i<arr.length;i++){
            System.out.print(arr[i]+" ");
        }
    }
}
