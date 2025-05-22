package org.sincos.afe.algorithms.sorting;

import org.jetbrains.annotations.NotNull;

import java.util.*;

/**
 * Implementation of the Bubble Sort algorithm.
 * <p>
 * Bubble Sort is a simple sorting algorithm that repeatedly steps through the list,
 * compares adjacent elements and swaps them if they are in the wrong order. The pass
 * through the list is repeated until the list is sorted. Despite its simplicity,
 * it is rarely used in practice due to its poor time complexity.
 * </p>
 * <p>
 * <strong>Time Complexity:</strong> O(n²) worst and average case, O(n) best case<br>
 * <strong>Space Complexity:</strong> O(1) additional space<br>
 * <strong>Stability:</strong> Stable - maintains relative order of equal elements<br>
 * <strong>In-place:</strong> Yes - sorts in-place with constant extra space<br>
 * <strong>Note:</strong> Educational purposes only - inefficient for large datasets
 * </p>
 *
 * @author Sincos Team
 * @version 1.0.0
 * @since 1.0.0
 */
public final class BubbleSort {

    private BubbleSort() {
        throw new UnsupportedOperationException("Utility class cannot be instantiated");
    }

    // ========== PUBLIC API - List Operations ==========

    /**
     * Sorts the list in ascending natural order using bubble sort.
     * <p>
     * The list elements must implement {@link Comparable}. The sort is stable
     * but very inefficient for large datasets. Recommended for educational
     * purposes or very small datasets only.
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
     * Sorts the list using the provided comparator and bubble sort.
     * <p>
     * The sort is stable but very inefficient for large datasets. Recommended
     * for educational purposes or very small datasets only.
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

        for (int i = 0; i < n - 1; i++) {
            boolean swapped = false;

            // Last i elements are already in place
            for (int j = 0; j < n - i - 1; j++) {
                // Swap if the element found is greater than the next element
                if (comparator.compare(list.get(j), list.get(j + 1)) > 0) {
                    Collections.swap(list, j, j + 1);
                    swapped = true;
                }
            }

            // If no two elements were swapped, then the array is sorted
            if (!swapped) {
                break;
            }
        }
    }

    // ========== PUBLIC API - Array Operations ==========

    /**
     * Sorts the array in ascending natural order using bubble sort.
     * <p>
     * Convenience method that converts the array to a list and sorts it.
     * Changes are reflected in the original array. Recommended for educational
     * purposes or very small datasets only.
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
     * Sorts the array using the provided comparator and bubble sort.
     * <p>
     * Convenience method that converts the array to a list and sorts it.
     * Changes are reflected in the original array. Recommended for educational
     * purposes or very small datasets only.
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
}