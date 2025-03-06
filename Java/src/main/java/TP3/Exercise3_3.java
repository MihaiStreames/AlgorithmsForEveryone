package TP3;

import DataStructs.Heaps.Heap;

public class Exercise3_3 {
    public static void sortKSortedArray(int[] arr, int k) {
        Heap heap = new Heap(false);
        for (int i = 0; i < k + 1; i++) {
            heap.insert(arr[i]);
        }
        int index = 0;
        for (int i = k + 1; i < arr.length; i++) {
            arr[index++] = heap.extract();
            heap.insert(arr[i]);
        }
        while (heap.size() > 0) {
            arr[index++] = heap.extract();
        }
    }

    public static void main(String[] args) {
        int[] arr = {6, 5, 3, 2, 8, 10, 9}; // k = 3
        int k = 3;
        sortKSortedArray(arr, k);
        System.out.println(java.util.Arrays.toString(arr)); // [2, 3, 5, 6, 8, 9, 10]
    }
}
