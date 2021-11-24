package com.ishan.daa;

import java.util.Arrays;
import java.util.Comparator;
import java.util.Scanner;

class FractionalKnapSack {
    private static double getMaxValue(int[] wt, int[] val, int capacity) {
        ItemValue[] iVal = new ItemValue[wt.length];

        for (int i = 0; i < wt.length; i++) {
            iVal[i] = new ItemValue(wt[i], val[i], i);
        }
        Arrays.sort(iVal, new Comparator<ItemValue>() {
            @Override
            public int compare(ItemValue o1, ItemValue o2) {
                return o2.cost.compareTo(o1.cost);
            }
        });

        double totalValue = 0d;

        for (ItemValue i : iVal) {

            int curWt = (int) i.wt;
            int curVal = (int) i.val;

            if (capacity - curWt >= 0) {
                capacity = capacity - curWt;
                totalValue += curVal;
            } else {
                double fraction = ((double) capacity / (double) curWt);
                totalValue += (curVal * fraction);
                capacity = (int) (capacity - (curWt * fraction));
                break;
            }
        }

        return totalValue;
    }

    static class ItemValue {
        Double cost;
        double wt, val, ind;

        public ItemValue(int wt, int val, int ind) {
            this.wt = wt;
            this.val = val;
            this.ind = ind;
            cost = new Double((double) val / (double) wt);
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter the number of objects");
        int n = sc.nextInt();
        System.out.println("Enter the weights");
        int[] wt = new int[n];
        for (int i = 0; i < n; i++) {
            wt[i] = sc.nextInt();
        }
        System.out.println("Enter the weights");
        int[] val = new int[n];
        for (int i = 0; i < n; i++) {
            val[i] = sc.nextInt();
        }
        System.out.println("Enter the capacity");
        int capacity = sc.nextInt();
        double maxValue = getMaxValue(wt, val, capacity);
        System.out.println("Maximum value we can obtain = " + maxValue);
        System.out.println("Ishan Gupta - 19BCE7467");
    }
}
