package com.company;
import java.util.Scanner;
public class MergeSort
{
    public static void main(String args[])
    {
        Scanner sc=new Scanner(System.in);
        System.out.println("Enter the length of the array");
        int n=sc.nextInt();
        int arr[]=new int[n];
        System.out.println("Press 1 for sorted and 2 for unsorted and 3 for entering values");
            int z=sc.nextInt();
            switch(z){
                case 1: sorted(n,arr,z);break;
                case 2: unsorted(n,arr,z);break;
                case 3: System.out.println("Enter the values of the array");
                    for(int i=0;i<arr.length;i++){
                        arr[i]=sc.nextInt();
                    }
                    break;
                default:System.out.println("You pressed invalid option");
            }

        System.out.println("Array length is "+n);
        System.out.print("Array entered is ");
        print(arr);
        System.out.print("Array after sorting is ");
        long startTime = System.nanoTime();
        sort(arr,0,arr.length-1);
        long endTime = System.nanoTime();
        print(arr);
        long elapsedTime = endTime - startTime;
        System.out.println("Running time in nanoSecond is "+elapsedTime);
    }

    private static int[] unsorted(int n, int[] arr, int z) {
        int k=0;
        for(int j=arr.length-1;j>0;j--){
            arr[k]=j;
            k++;
        }
        return arr;
    }

    private static int[] sorted(int n, int[] arr, int z) {
        for(int i=0;i< arr.length;i++){
            arr[i]=i;
        }
        return arr;
    }

    public static void print(int[] arr)
    {
        for(int i=0;i<arr.length;i++){
            System.out.print(arr[i]+" ");
        }
        System.out.println("");
    }
    static void merge(int arr[], int l, int m, int r)
    {
        int n1 = m - l + 1;
        int n2 = r - m;
        int L[] = new int[n1];
        int R[] = new int[n2];
        for (int i = 0; i < n1; ++i)
            L[i] = arr[l + i];
        for (int j = 0; j < n2; ++j)
            R[j] = arr[m + 1 + j];
        int i = 0, j = 0;
        int k = l;
        while (i < n1 && j < n2) {
            if (L[i] <= R[j]) {
                arr[k] = L[i];
                i++;
            }
            else {
                arr[k] = R[j];
                 j++;
            }
            k++;
        }
        while (i < n1) {
            arr[k] = L[i];
            i++;
            k++;
        }
        while (j < n2) {
            arr[k] = R[j];
            j++;
            k++;
        }

    }
   public static void sort(int arr[], int l, int r)
    {
        if (l < r) {
            int m =l+ (r-l)/2;
            sort(arr, l, m);
            sort(arr, m + 1, r);
            merge(arr, l, m, r);
        }
    }
}
