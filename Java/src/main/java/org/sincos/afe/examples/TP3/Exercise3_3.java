package org.sincos.afe.examples.TP3;

import org.sincos.afe.old.structs.Heaps.Heap;

public class Exercise3_3 {

    /**
     * Sorts a k-sorted array in O((n-k) log k) time using a min-heap.
     *
     * @param arr the k-sorted array to sort
     * @param k   the maximum displacement of any element
     */
    public static void sortKSortedArray(int[] arr, int k) {
        // Create a min-heap
        Heap<Integer> heap = new Heap<>(false);

        // Add the first k+1 elements to the heap
        int initialElements = Math.min(k + 1, arr.length);
        for (int i = 0; i < initialElements; i++) {
            heap.insert(arr[i]);
        }

        // Extract the minimum element and add the next element from the array
        int index = 0;
        for (int i = k + 1; i < arr.length; i++) {
            arr[index++] = heap.extract();
            heap.insert(arr[i]);
        }

        // Extract any remaining elements from the heap
        while (!heap.isEmpty()) {
            arr[index++] = heap.extract();
        }
    }

    public static void main(String[] args) {
        int[] arr = {6, 5, 3, 2, 8, 10, 9}; // k = 3
        int k = 3;

        System.out.println("Original array: " + java.util.Arrays.toString(arr));

        sortKSortedArray(arr, k);

        System.out.println("Sorted array: " + java.util.Arrays.toString(arr)); // [2, 3, 5, 6, 8, 9, 10]
    }
}