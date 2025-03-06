package TP3;

import java.util.Arrays;
import java.util.Random;

public class Exercise3_2 {
    private static final Random random = new Random();

    public static void matchNutsBolts(int[] nuts, int[] bolts) {
        if (nuts.length != bolts.length) throw new IllegalArgumentException("Mismatched sizes");
        quickSort(nuts, bolts, 0, nuts.length - 1);
    }

    public static void quickSort(int[] nuts, int[] bolts, int low, int high) {
        if (low >= high) return;
        int pivotIdx = low + random.nextInt((high - low) + 1);
        swap(nuts, pivotIdx, high);
        pivotIdx = partition(nuts, low, high, bolts[high]);
        partition(bolts, low, high, nuts[pivotIdx]);
        quickSort(nuts, bolts, low, pivotIdx - 1);
        quickSort(nuts, bolts, pivotIdx + 1, high);
    }

    private static int partition(int[] arr, int low, int high, int pivot) {
        int i = low;
        for (int j = low; j < high; j++) {
            if (arr[j] < pivot) swap(arr, i++, j);
            else if (arr[j] == pivot) swap(arr, j--, high);
        }
        swap(arr, i, high);
        return i;
    }

    private static void swap(int[] arr, int i, int j) {
        int tmp = arr[i];
        arr[i] = arr[j];
        arr[j] = tmp;
    }

    public static void main(String[] args) {
        int[] nuts = {4, 2, 3, 6, 1, 5};
        int[] bolts = {3, 1, 4, 6, 2, 5};
        matchNutsBolts(nuts, bolts);
        System.out.println("Matched nuts: " + Arrays.toString(nuts));
        System.out.println("Matched bolts: " + Arrays.toString(bolts));
    }
}
