package io.github.mihaistreames.afe.algorithms.sorting;

import org.jetbrains.annotations.NotNull;

import java.util.Comparator;
import java.util.List;

/**
 * An efficient, in-place, comparison-based sorting algorithm.
 * It is a divide and conquer algorithm.
 * <p>
 * This implementation is not stable. <br>
 * Time Complexity: O(n log n) average, O(n^2) worst-case <br>
 * Space Complexity: O(log n)
 */
public final class QuickSort {
    private QuickSort() {
        throw new UnsupportedOperationException("Utility class cannot be instantiated");
    }

    /**
     * Sorts a list using the quicksort algorithm and natural ordering.
     *
     * @param <T>  the type of elements, must extend {@link Comparable}
     * @param list the list to be sorted
     */
    public static <T extends Comparable<T>> void quickSort(@NotNull final List<T> list) {
        sort(list, 0, list.size() - 1, Comparator.naturalOrder());
    }

    /**
     * Sorts a list using the quicksort algorithm and a custom comparator.
     *
     * @param <T>        the type of elements
     * @param list       the list to be sorted
     * @param comparator the comparator to determine the order
     */
    public static <T> void quickSort(@NotNull final List<T> list,
                                     @NotNull final Comparator<T> comparator) {
        sort(list, 0, list.size() - 1, comparator);
    }

    private static <T> void sort(@NotNull final List<T> list,
                                 int low,
                                 int high,
                                 @NotNull final Comparator<T> comparator) {
        if (low < high) {
            int pi = partition(list, low, high, comparator);
            sort(list, low, pi - 1, comparator);
            sort(list, pi + 1, high, comparator);
        }
    }

    private static <T> int partition(@NotNull final List<T> list,
                                     int low,
                                     int high,
                                     @NotNull final Comparator<T> comparator) {
        final T pivot = list.get(high);
        int i = (low - 1);
        for (int j = low; j < high; j++) {
            if (comparator.compare(list.get(j), pivot) < 0) {
                i++;
                final T temp = list.get(i);
                list.set(i, list.get(j));
                list.set(j, temp);
            }
        }
        final T temp = list.get(i + 1);
        list.set(i + 1, list.get(high));
        list.set(high, temp);
        return i + 1;
    }
}