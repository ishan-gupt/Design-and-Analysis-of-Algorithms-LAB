package com.ishan.daa;

import java.util.Scanner;

public class KnapSack {
    static int max(int a, int b) {
        return (a > b) ? a : b;
    }
    static int knapSack(int W, int wt[], int val[], int n) {
        if (n == 0 || W == 0)
            return 0;
        if (wt[n - 1] > W)
            return knapSack(W, wt, val, n - 1);
        else
            return max(val[n - 1] + knapSack(W - wt[n - 1], wt, val, n - 1), knapSack(W, wt, val, n - 1));
    }
    public static void main(String args[]) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Number of items : ");
        int n = sc.nextInt();
        int val[] = new int[n];
        System.out.println("Input the values : ");
        for(int i=0;i<n;i++){
            val[i] = sc.nextInt();
        }
        System.out.println("Input the weight : ");
        int wt[] = new int[n];
        for (int i = 0; i < n; i++) {
            wt[i] = sc.nextInt();
        }
        System.out.print("Enter the maximum weight that can be carried : ");
        int W = sc.nextInt();
        System.out.println("The maximum value is : " + knapSack(W, wt, val, n));
        System.out.println("IshanGupta-19BCE7467");
    }
}
