package com.company;

public class ShellSort {
    public static void main(String args[]) {
        int[] arr = {10, 35, -15, 7, 55, 1, -22};
        for (int gap= arr.length/2;gap>0;gap/=2){
            for (int i = gap; i < arr.length; i++) {
                int a=arr[i];
                int j=i;
                while(j>=gap && arr[j-gap]>a){
                    arr[j]=arr[j-gap];
                    j=j-gap;
                }
                arr[j]=a;

            }
        }
        print(arr);
    }
    public static void print(int[] arr){
        for(int i=0;i<arr.length;i++){
            System.out.print(arr[i]+" ");
        }
    }
}
