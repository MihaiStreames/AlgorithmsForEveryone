package io.github.mihaistreames.afe.algorithms.sorting;

import org.jetbrains.annotations.NotNull;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Objects;

/**
 * Implementation of the Insertion Sort algorithm.
 * <p>
 * Insertion Sort is a simple sorting algorithm that builds the final sorted array one item
 * at a time. It is efficient for small datasets and nearly sorted arrays, and is often used
 * as a subroutine in more complex algorithms like QuickSort for small partitions.
 * </p>
 * <p>
 * <strong>Time Complexity:</strong> O(n²) worst case, O(n) best case (nearly sorted)<br>
 * <strong>Space Complexity:</strong> O(1) additional space<br>
 * <strong>Stability:</strong> Stable - maintains relative order of equal elements<br>
 * <strong>In-place:</strong> Yes - sorts in-place with constant extra space
 * </p>
 */
public final class InsertionSort {

    private InsertionSort() {
        throw new UnsupportedOperationException("Utility class cannot be instantiated");
    }

    // ========== PUBLIC API - List Operations ==========

    /**
     * Sorts the list in ascending natural order using insertion sort.
     * <p>
     * The list elements must implement {@link Comparable}. The sort is stable
     * and performs very well on small or nearly sorted datasets.
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
     * Sorts the list using the provided comparator and insertion sort.
     * <p>
     * The sort is stable and performs very well on small or nearly sorted datasets.
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

        for (int i = 1; i < n; i++) {
            final T key = list.get(i);
            int j = i - 1;

            // Move elements greater than key one position ahead
            while (j >= 0 && comparator.compare(list.get(j), key) > 0) {
                list.set(j + 1, list.get(j));
                j--;
            }

            // Insert key at its correct position
            list.set(j + 1, key);
        }
    }

    // ========== PUBLIC API - Array Operations ==========

    /**
     * Sorts the array in ascending natural order using insertion sort.
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
     * Sorts the array using the provided comparator and insertion sort.
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