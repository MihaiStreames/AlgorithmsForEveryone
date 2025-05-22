package org.sincos.afe.examples.TP3;

import java.util.Arrays;

public class Exercise3_1 {
    public static void partition(int[] arr) {
        if (arr.length == 0) return;
        int v = arr[0];
        int low = 0, mid = 0, high = arr.length - 1;

        while (mid <= high) {
            int cmp = Integer.compare(arr[mid], v);
            if (cmp < 0) {
                swap(arr, low++, mid++);
            } else if (cmp > 0) {
                swap(arr, mid, high--);
            } else {
                mid++;
            }
        }
    }

    private static void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

    public static void main(String[] args) {
        int[] arr = {4, 2, 3, 4, 1, 4, 5, 4, 6};
        partition(arr);
        System.out.println(Arrays.toString(arr));
    }
}
