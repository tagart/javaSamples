package com.arestanov;

import java.util.Random;
import java.util.Vector;

public class VectorTest {
    private static final int N = 10_000_000;
    public static void main(String[] args) {
        Vector<Integer> v = new Vector<>(N);
        int[] a = new int[N];
        Random r = new Random();
        for(int i = 0; i < N; i++) {
            v.add(r.nextInt());
            //a[i] = r.nextInt();
        }
        System.out.println(v.get(0));
        long time = System.nanoTime();
        System.out.println(v.indexOf(v.get(N-1)));
        System.out.println((System.nanoTime() - time));
    }
}
