package Algorithms.Lists;

import org.jetbrains.annotations.NotNull;

/**
 * Implementation of the Merge Sort algorithm.
 * Merge Sort is a stable, divide-and-conquer algorithm with O(n log n) time complexity.
 */
public class MergeSort {

    /**
     * Public method to sort an array of comparable elements.
     *
     * @param <T> type of elements that can be compared to one another
     * @param a the array to be sorted
     */
    public static <T extends Comparable<T>> void sort(T @NotNull [] a) {
        @SuppressWarnings("unchecked")
        T[] aux = (T[]) new Comparable[a.length];
        sort(a, aux, 0, a.length - 1);
    }

    /**
     * Iterative (bottom-up) merge sort for arrays of Comparable elements.
     *
     * @param <T> type of elements that can be compared to one another
     * @param a the array to be sorted
     */
    public static <T extends Comparable<T>> void sortIterative(T @NotNull [] a) {
        int n = a.length;
        @SuppressWarnings("unchecked")
        T[] aux = (T[]) new Comparable[n];

        for (int sz = 1; sz < n; sz = sz + sz) {
            for (int lo = 0; lo < n - sz; lo += sz + sz) {
                merge(a, aux, lo, lo + sz - 1, Math.min(lo + sz + sz - 1, n - 1));
            }
        }
    }


    /**
     * Recursively sorts a subarray by dividing it into halves.
     *
     * @param <T> type of elements that can be compared to one another
     * @param a the array to be sorted
     * @param aux auxiliary array for merging
     * @param lo the lowest index of the subarray
     * @param hi the highest index of the subarray
     */
    private static <T extends Comparable<T>> void sort(T[] a, T[] aux, int lo, int hi) {
        if (hi <= lo) return;
        int mid = lo + (hi - lo) / 2;
        sort(a, aux, lo, mid);
        sort(a, aux, mid + 1, hi);
        merge(a, aux, lo, mid, hi);
    }

    /**
     * Merges two sorted subarrays into a single sorted subarray.
     *
     * @param <T> type of elements that can be compared to one another
     * @param a the array containing the subarrays to be merged
     * @param aux auxiliary array used in the merge process
     * @param lo the starting index of the first subarray
     * @param mid the ending index of the first subarray
     * @param hi the ending index of the second subarray
     */
    private static <T extends Comparable<T>> void merge(T[] a, T[] aux, int lo, int mid, int hi) {
        if (hi + 1 - lo >= 0) System.arraycopy(a, lo, aux, lo, hi + 1 - lo);

        int i = lo;
        int j = mid + 1;

        for (int k = lo; k <= hi; k++) {
            if (i > mid) a[k] = aux[j++];
            else if (j > hi) a[k] = aux[i++];
            else if (aux[j].compareTo(aux[i]) < 0) a[k] = aux[j++];
            else a[k] = aux[i++];
        }
    }
}