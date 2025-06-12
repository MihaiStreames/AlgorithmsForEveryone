package io.github.mihaistreames.afe.algorithms.sorting;

import org.jetbrains.annotations.NotNull;

import java.util.Comparator;
import java.util.List;

/**
 * A simple sorting algorithm that repeatedly steps through the list,
 * compares adjacent elements and swaps them if they are in the wrong order.
 * <p>
 * This implementation is not stable. <br>
 * Time Complexity: O(n^2) <br>
 * Space Complexity: O(1)
 */
public final class BubbleSort {

    private BubbleSort() {
        throw new UnsupportedOperationException("Utility class cannot be instantiated");
    }

    /**
     * Sorts a list using the bubble sort algorithm and natural ordering.
     *
     * @param <T>  the type of elements, must extend {@link Comparable}
     * @param list the list to be sorted
     */
    public static <T extends Comparable<T>> void bubbleSort(@NotNull final List<T> list) {
        sort(list, Comparator.naturalOrder());
    }

    /**
     * Sorts a list using the bubble sort algorithm and a custom comparator.
     *
     * @param <T>        the type of elements
     * @param list       the list to be sorted
     * @param comparator the comparator to determine the order
     */
    public static <T> void bubbleSort(@NotNull final List<T> list,
                                      @NotNull final Comparator<T> comparator) {
        sort(list, comparator);
    }

    private static <T> void sort(@NotNull final List<T> list,
                                 @NotNull final Comparator<T> comparator) {
        final int size = list.size();
        for (int i = 0; i < size - 1; i++) {
            for (int j = 0; j < size - i - 1; j++) {
                if (comparator.compare(list.get(j), list.get(j + 1)) > 0) {
                    final T temp = list.get(j);
                    list.set(j, list.get(j + 1));
                    list.set(j + 1, temp);
                }
            }
        }
    }
}