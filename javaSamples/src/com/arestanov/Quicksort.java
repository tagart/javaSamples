package com.arestanov;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;
import java.util.concurrent.RecursiveAction;
import java.util.stream.Stream;

public class Quicksort extends RecursiveAction {

    private static final int N = 100_000_000;
    private static final int[] a = new int[N];
    private static final Random random = new Random();

    private int[] a_;
    private int left, right;

    public static void main(String[] args) {
        for (int i = 0; i < N; i++) {
            a[i] = random.nextInt();
        }
        int[] b = a.clone();
        long time = System.nanoTime();
        Arrays.sort(b);
        System.out.println((System.nanoTime() - time) / 1_000_000_000);
        time = System.nanoTime();
        invokeAll(new Quicksort(a, 0, N - 1));
        System.out.println((System.nanoTime() - time) / 1_000_000_000);
        boolean sorted = true;
        for (int i = 0; i < N - 1; i++) {
            if (a[i] > a[i + 1]) {
                sorted = false;
            }
        }
        System.out.println(b[0]);
        System.out.println(a[0]);
        System.out.println(sorted);
    }

    Quicksort(int[] a, int left, int right) {
        this.a_ = a;
        this.left = left;
        this.right = right;
    }

    @Override
    protected void compute() {
        if (left < right) {
            int pivotIndex = (left + right) / 2;
            int pivotNewIndex = partition(a, left, right, pivotIndex);
            invokeAll(new Quicksort(a_, left, pivotNewIndex - 1),
                    new Quicksort(a_, pivotNewIndex + 1, right));
        }
    }

    private static int partition(int[] a, int left, int right, int pivotIndex) {
        int pivotValue = a[pivotIndex];
        swap(a, pivotIndex, right);
        int storeIndex = left;
        for (int i = left; i < right; i++) {
            if (a[i] < pivotValue) {
                swap(a, i, storeIndex);
                storeIndex++;
            }
        }
        swap(a, storeIndex, right);
        return storeIndex;
    }

    private static void swap(int[] a, int left, int right) {
        int q = a[left];
        a[left] = a[right];
        a[right] = q;
    }

}
