package io.github.mihaistreames.afe.algorithms.sorting;

import org.jetbrains.annotations.NotNull;

import java.util.*;

/**
 * Implementation of the Selection Sort algorithm.
 * <p>
 * Selection Sort is a simple sorting algorithm that divides the input list into two parts:
 * a sorted portion at the left end and an unsorted portion at the right end. It repeatedly
 * selects the smallest (or largest) element from the unsorted portion and moves it to the
 * sorted portion.
 * </p>
 * <p>
 * <strong>Time Complexity:</strong> O(n²) in all cases<br>
 * <strong>Space Complexity:</strong> O(1) additional space<br>
 * <strong>Stability:</strong> Not stable - may change relative order of equal elements<br>
 * <strong>In-place:</strong> Yes - sorts in-place with constant extra space
 * </p>
 *
 * @author Sincos Team
 * @version 1.0.0
 * @since 1.0.0
 */
public final class SelectionSort {

    private SelectionSort() {
        throw new UnsupportedOperationException("Utility class cannot be instantiated");
    }

    // ========== PUBLIC API - List Operations ==========

    /**
     * Sorts the list in ascending natural order using selection sort.
     * <p>
     * The list elements must implement {@link Comparable}. The sort is not stable
     * and performs O(n²) comparisons but only O(n) swaps.
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
     * Sorts the list using the provided comparator and selection sort.
     * <p>
     * The sort is not stable and performs O(n²) comparisons but only O(n) swaps.
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
            // Find the minimum element in the remaining unsorted array
            int minIndex = i;
            for (int j = i + 1; j < n; j++) {
                if (comparator.compare(list.get(j), list.get(minIndex)) < 0) {
                    minIndex = j;
                }
            }

            // Swap the found minimum element with the first element
            if (minIndex != i) {
                Collections.swap(list, i, minIndex);
            }
        }
    }

    // ========== PUBLIC API - Array Operations ==========

    /**
     * Sorts the array in ascending natural order using selection sort.
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
     * Sorts the array using the provided comparator and selection sort.
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
}