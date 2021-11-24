package com.ishan.daa;

import java.util.Scanner;

public class OBST {
    static int optCost(int freq[], int i, int j) {
        if (j < i)
            return 0;
        if (j == i)
            return freq[i];
        int fsum = sum(freq, i, j);
        int min = Integer.MAX_VALUE;
        for (int r = i; r <= j; ++r) {
            int cost = optCost(freq, i, r - 1) +
                    optCost(freq, r + 1, j);
            if (cost < min)
                min = cost;
        }
        return min + fsum;
    }

    static int optimalSearchTree(int keys[], int freq[], int n) {
        return optCost(freq, 0, n - 1);
    }

    static int sum(int freq[], int i, int j) {
        int s = 0;
        for (int k = i; k <= j; k++)
            s += freq[k];
        return s;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter the number of keys");
        int n = sc.nextInt();
        int[] keys = new int[n];
        int[] freq = new int[n];
        System.out.println("Enter the keys");
        for (int k = 0; k < n; k++) {
            keys[k] = sc.nextInt();
        }
        System.out.println("Enter the freq");
        for (int k = 0; k < n; k++) {
            freq[k] = sc.nextInt();
        }
        System.out.println("Cost of Optimal BST is " + optimalSearchTree(keys, freq, n));
        System.out.println("IshanGupta-19BCE7467");
    }
}
