package io.github.mihaistreames.afe.algorithms.sorting;

import org.jetbrains.annotations.NotNull;

import java.util.Comparator;
import java.util.List;

/**
 * An in-place comparison sorting algorithm. It has an O(n^2) time complexity,
 * which makes it inefficient on large lists.
 * <p>
 * This implementation is not stable. <br>
 * Time Complexity: O(n^2) <br>
 * Space Complexity: O(1)
 */
public final class SelectionSort {

    private SelectionSort() {
        throw new UnsupportedOperationException("Utility class cannot be instantiated");
    }

    /**
     * Sorts a list using the selection sort algorithm and natural ordering.
     *
     * @param <T>  the type of elements, must extend {@link Comparable}
     * @param list the list to be sorted
     */
    public static <T extends Comparable<T>> void selectionSort(@NotNull final List<T> list) {
        sort(list, Comparator.naturalOrder());
    }

    /**
     * Sorts a list using the selection sort algorithm and a custom comparator.
     *
     * @param <T>        the type of elements
     * @param list       the list to be sorted
     * @param comparator the comparator to determine the order
     */
    public static <T> void selectionSort(@NotNull final List<T> list,
                                         @NotNull final Comparator<T> comparator) {
        sort(list, comparator);
    }

    private static <T> void sort(@NotNull final List<T> list,
                                 @NotNull final Comparator<T> comparator) {
        final int size = list.size();
        for (int i = 0; i < size - 1; i++) {
            int minIdx = i;
            for (int j = i + 1; j < size; j++) {
                if (comparator.compare(list.get(j), list.get(minIdx)) < 0) {
                    minIdx = j;
                }
            }
            final T temp = list.get(minIdx);
            list.set(minIdx, list.get(i));
            list.set(i, temp);
        }
    }
}