package io.github.mihaistreames.afe.algorithms.sorting;

import org.jetbrains.annotations.NotNull;

import java.util.*;

/**
 * Implementation of the Merge Sort algorithm.
 * <p>
 * Merge Sort is a stable, divide-and-conquer sorting algorithm that divides the input array
 * into two halves, recursively sorts both halves, and then merges the sorted halves.
 * </p>
 * <p>
 * <strong>Time Complexity:</strong> O(n log n) in all cases<br>
 * <strong>Space Complexity:</strong> O(n) auxiliary space<br>
 * <strong>Stability:</strong> Stable - maintains relative order of equal elements<br>
 * <strong>In-place:</strong> No - requires additional space
 * </p>
 *
 * @author Sincos Team
 * @version 1.0.0
 * @since 1.0.0
 */
public final class MergeSort {

    private MergeSort() {
        throw new UnsupportedOperationException("Utility class cannot be instantiated");
    }

    // ========== PUBLIC API - List Operations ==========

    /**
     * Sorts the list in ascending natural order using merge sort.
     * <p>
     * The list elements must implement {@link Comparable}. The sort is stable
     * and guaranteed to run in O(n log n) time.
     * </p>
     *
     * @param <T>  the type of elements, must extend {@link Comparable}
     * @param list the list to sort
     * @throws NullPointerException if the list is null
     */
    public static <T extends Comparable<T>> void sort(@NotNull final List<T> list) {
        Objects.requireNonNull(list, "List cannot be null");
        sort(list, Comparable::compareTo);
    }

    /**
     * Sorts the list using the provided comparator and merge sort.
     * <p>
     * The sort is stable and guaranteed to run in O(n log n) time.
     * </p>
     *
     * @param <T>        the type of elements
     * @param list       the list to sort
     * @param comparator the comparator to determine element order
     * @throws NullPointerException if the list or comparator is null
     */
    public static <T> void sort(@NotNull final List<T> list, @NotNull final Comparator<T> comparator) {
        Objects.requireNonNull(list, "List cannot be null");
        Objects.requireNonNull(comparator, "Comparator cannot be null");

        if (list.size() <= 1) {
            return;
        }

        final List<T> auxiliary = new ArrayList<>(Collections.nCopies(list.size(), null));
        mergeSortRecursive(list, auxiliary, 0, list.size() - 1, comparator);
    }

    /**
     * Sorts the list using iterative merge sort with natural ordering.
     * <p>
     * This bottom-up approach eliminates recursion and prevents stack overflow
     * issues for very large datasets.
     * </p>
     *
     * @param <T>  the type of elements, must extend {@link Comparable}
     * @param list the list to sort
     * @throws NullPointerException if the list is null
     */
    public static <T extends Comparable<T>> void sortIterative(@NotNull final List<T> list) {
        Objects.requireNonNull(list, "List cannot be null");
        sortIterative(list, Comparable::compareTo);
    }

    /**
     * Sorts the list using iterative merge sort with a custom comparator.
     * <p>
     * This bottom-up approach eliminates recursion and prevents stack overflow
     * issues for very large datasets.
     * </p>
     *
     * @param <T>        the type of elements
     * @param list       the list to sort
     * @param comparator the comparator to determine element order
     * @throws NullPointerException if the list or comparator is null
     */
    public static <T> void sortIterative(@NotNull final List<T> list, @NotNull final Comparator<T> comparator) {
        Objects.requireNonNull(list, "List cannot be null");
        Objects.requireNonNull(comparator, "Comparator cannot be null");

        final int n = list.size();
        if (n <= 1) {
            return;
        }

        final List<T> auxiliary = new ArrayList<>(Collections.nCopies(n, null));

        for (int size = 1; size < n; size = size + size) {
            for (int low = 0; low < n - size; low += size + size) {
                final int mid = low + size - 1;
                final int high = Math.min(low + size + size - 1, n - 1);
                merge(list, auxiliary, low, mid, high, comparator);
            }
        }
    }

    // ========== PUBLIC API - Array Operations ==========

    /**
     * Sorts the array in ascending natural order using merge sort.
     * <p>
     * Convenience method that converts the array to a list and sorts it.
     * Changes are reflected in the original array.
     * </p>
     *
     * @param <T>   the type of elements, must extend {@link Comparable}
     * @param array the array to sort
     * @throws NullPointerException if the array is null
     */
    public static <T extends Comparable<T>> void sort(@NotNull final T[] array) {
        Objects.requireNonNull(array, "Array cannot be null");
        final List<T> list = Arrays.asList(array);
        sort(list);
    }

    /**
     * Sorts the array using the provided comparator and merge sort.
     * <p>
     * Convenience method that converts the array to a list and sorts it.
     * Changes are reflected in the original array.
     * </p>
     *
     * @param <T>        the type of elements
     * @param array      the array to sort
     * @param comparator the comparator to determine element order
     * @throws NullPointerException if the array or comparator is null
     */
    public static <T> void sort(@NotNull final T[] array, @NotNull final Comparator<T> comparator) {
        Objects.requireNonNull(array, "Array cannot be null");
        Objects.requireNonNull(comparator, "Comparator cannot be null");
        final List<T> list = Arrays.asList(array);
        sort(list, comparator);
    }

    // ========== PRIVATE IMPLEMENTATION ==========

    /**
     * Recursively sorts the specified portion of the list using merge sort.
     */
    private static <T> void mergeSortRecursive(@NotNull final List<T> list,
                                               @NotNull final List<T> auxiliary,
                                               final int low,
                                               final int high,
                                               @NotNull final Comparator<T> comparator) {
        if (high <= low) {
            return;
        }

        final int mid = low + (high - low) / 2;
        mergeSortRecursive(list, auxiliary, low, mid, comparator);
        mergeSortRecursive(list, auxiliary, mid + 1, high, comparator);
        merge(list, auxiliary, low, mid, high, comparator);
    }

    /**
     * Merges two sorted sublists into a single sorted list.
     * <p>
     * Merges list[low..mid] with list[mid+1..high] using the auxiliary array.
     * </p>
     */
    private static <T> void merge(@NotNull final List<T> list,
                                  @NotNull final List<T> auxiliary,
                                  final int low,
                                  final int mid,
                                  final int high,
                                  @NotNull final Comparator<T> comparator) {
        // Copy current state to auxiliary list
        for (int k = low; k <= high; k++) {
            auxiliary.set(k, list.get(k));
        }

        int i = low;        // Index for left sublist
        int j = mid + 1;    // Index for right sublist

        // Merge back to original list
        for (int k = low; k <= high; k++) {
            if (i > mid) {
                // Left sublist exhausted, take from right
                list.set(k, auxiliary.get(j++));
            } else if (j > high) {
                // Right sublist exhausted, take from left
                list.set(k, auxiliary.get(i++));
            } else if (comparator.compare(auxiliary.get(j), auxiliary.get(i)) < 0) {
                // Right element is smaller, take from right
                list.set(k, auxiliary.get(j++));
            } else {
                // Left element is smaller or equal, take from left (maintains stability)
                list.set(k, auxiliary.get(i++));
            }
        }
    }
}