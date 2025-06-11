package io.github.mihaistreames.afe.algorithms.sorting;

import org.jetbrains.annotations.NotNull;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Objects;

/**
 * Implementation of the Shell Sort algorithm.
 * <p>
 * Shell Sort is an in-place comparison sort that generalizes insertion sort by allowing
 * the exchange of items that are far apart. It starts by sorting pairs of elements far
 * apart from each other, then progressively reducing the gap between elements to be compared.
 * </p>
 * <p>
 * <strong>Time Complexity:</strong> O(n log²n) to O(n^1.5) depending on gap sequence<br>
 * <strong>Space Complexity:</strong> O(1) additional space<br>
 * <strong>Stability:</strong> Not stable - may change relative order of equal elements<br>
 * <strong>In-place:</strong> Yes - sorts in-place with constant extra space
 * </p>
 */
public final class ShellSort {

    private ShellSort() {
        throw new UnsupportedOperationException("Utility class cannot be instantiated");
    }

    // ========== PUBLIC API - List Operations ==========

    /**
     * Sorts the list in ascending natural order using shell sort.
     * <p>
     * The list elements must implement {@link Comparable}. Uses Knuth's gap sequence
     * (3k + 1) for optimal performance characteristics.
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
     * Sorts the list using the provided comparator and shell sort.
     * <p>
     * Uses Knuth's gap sequence (3k + 1) for optimal performance characteristics.
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

        // Start with a big gap, then reduce the gap using Knuth's sequence
        int gap = 1;
        while (gap < n / 3) {
            gap = 3 * gap + 1; // Knuth's gap sequence: 1, 4, 13, 40, 121, ...
        }

        while (gap >= 1) {
            // Do a gapped insertion sort for this gap size
            for (int i = gap; i < n; i++) {
                final T key = list.get(i);
                int j = i;

                // Shift earlier gap-sorted elements up until the correct location for key is found
                while (j >= gap && comparator.compare(list.get(j - gap), key) > 0) {
                    list.set(j, list.get(j - gap));
                    j -= gap;
                }

                // Put key in its correct location
                list.set(j, key);
            }

            // Reduce gap for next iteration
            gap = gap / 3;
        }
    }

    // ========== PUBLIC API - Array Operations ==========

    /**
     * Sorts the array in ascending natural order using shell sort.
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
     * Sorts the array using the provided comparator and shell sort.
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