package io.github.mihaistreames.afe.algorithms.sorting;

import org.jetbrains.annotations.NotNull;

import java.util.*;

/**
 * Implementation of the Heap Sort algorithm.
 * <p>
 * Heap Sort is a comparison-based sorting algorithm that uses a binary heap data structure.
 * It divides the input into a sorted and an unsorted region, and iteratively shrinks the
 * unsorted region by extracting the largest element and moving it to the sorted region.
 * </p>
 * <p>
 * <strong>Time Complexity:</strong> O(n log n) in all cases<br>
 * <strong>Space Complexity:</strong> O(1) additional space<br>
 * <strong>Stability:</strong> Not stable - may change relative order of equal elements<br>
 * <strong>In-place:</strong> Yes - sorts in-place with constant extra space
 * </p>
 *
 * @author Sincos Team
 * @version 1.0.0
 * @since 0.0.1
 */
public final class HeapSort {

    private HeapSort() {
        throw new UnsupportedOperationException("Utility class cannot be instantiated");
    }

    // ========== PUBLIC API - List Operations ==========

    /**
     * Sorts the list in ascending natural order using heap sort.
     * <p>
     * The list elements must implement {@link Comparable}. The sort is not stable
     * but runs in guaranteed O(n log n) time.
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
     * Sorts the list using the provided comparator and heap sort.
     * <p>
     * The sort is not stable but runs in guaranteed O(n log n) time.
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

        final int n = list.size();
        if (n <= 1) {
            return;
        }

        // Build max heap
        for (int i = n / 2 - 1; i >= 0; i--) {
            heapify(list, n, i, comparator);
        }

        // Extract elements from heap one by one
        for (int i = n - 1; i > 0; i--) {
            // Move current root to end
            Collections.swap(list, 0, i);

            // Call heapify on the reduced heap
            heapify(list, i, 0, comparator);
        }
    }

    // ========== PUBLIC API - Array Operations ==========

    /**
     * Sorts the array in ascending natural order using heap sort.
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
     * Sorts the array using the provided comparator and heap sort.
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
     * Heapifies a subtree rooted with node i which is an index in the list.
     * <p>
     * Assumes that the subtrees rooted at left and right children are already heaps.
     * </p>
     */
    private static <T> void heapify(@NotNull final List<T> list,
                                    final int n,
                                    final int i,
                                    @NotNull final Comparator<T> comparator) {
        int largest = i;        // Initialize largest as root
        int left = 2 * i + 1;   // Left child
        int right = 2 * i + 2;  // Right child

        // If left child is larger than root
        if (left < n && comparator.compare(list.get(left), list.get(largest)) > 0) {
            largest = left;
        }

        // If right child is larger than largest so far
        if (right < n && comparator.compare(list.get(right), list.get(largest)) > 0) {
            largest = right;
        }

        // If largest is not root
        if (largest != i) {
            Collections.swap(list, i, largest);

            // Recursively heapify the affected sub-tree
            heapify(list, n, largest, comparator);
        }
    }
}