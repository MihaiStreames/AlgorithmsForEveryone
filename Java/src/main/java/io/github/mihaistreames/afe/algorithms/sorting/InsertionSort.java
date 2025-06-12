package io.github.mihaistreames.afe.algorithms.sorting;

import org.jetbrains.annotations.NotNull;

import java.util.Comparator;
import java.util.List;

/**
 * A simple sorting algorithm that builds the final sorted list one item at a time.
 * It is much less efficient on large lists than more advanced algorithms such as quicksort or mergesort.
 * <p>
 * This implementation is stable. <br>
 * Time Complexity: O(n^2) <br>
 * Space Complexity: O(1)
 */
public final class InsertionSort {

    private InsertionSort() {
        throw new UnsupportedOperationException("Utility class cannot be instantiated");
    }

    /**
     * Sorts a list using the insertion sort algorithm and natural ordering.
     *
     * @param <T>  the type of elements, must extend {@link Comparable}
     * @param list the list to be sorted
     */
    public static <T extends Comparable<T>> void insertionSort(@NotNull final List<T> list) {
        sort(list, Comparator.naturalOrder());
    }

    /**
     * Sorts a list using the insertion sort algorithm and a custom comparator.
     *
     * @param <T>        the type of elements
     * @param list       the list to be sorted
     * @param comparator the comparator to determine the order
     */
    public static <T> void insertionSort(@NotNull final List<T> list,
                                         @NotNull final Comparator<T> comparator) {
        sort(list, comparator);
    }

    private static <T> void sort(@NotNull final List<T> list,
                                 @NotNull final Comparator<T> comparator) {
        final int size = list.size();
        for (int i = 1; i < size; ++i) {
            final T key = list.get(i);
            int j = i - 1;

            while (j >= 0 && comparator.compare(list.get(j), key) > 0) {
                list.set(j + 1, list.get(j));
                j = j - 1;
            }
            list.set(j + 1, key);
        }
    }
}