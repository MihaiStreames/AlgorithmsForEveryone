package io.github.mihaistreames.afe.algorithms.sorting;

import org.jetbrains.annotations.NotNull;

import java.util.Comparator;
import java.util.List;

/**
 * An in-place comparison sort. It can be seen as either a generalization of
 * sorting by exchange (bubble sort) or sorting by insertion (insertion sort).
 * <p>
 * This implementation is not stable. <br>
 * Time Complexity: O(n log^2 n) is common, depends on gap sequence. <br>
 * Space Complexity: O(1)
 */
public final class ShellSort {

    private ShellSort() {
        throw new UnsupportedOperationException("Utility class cannot be instantiated");
    }

    /**
     * Sorts a list using the shell sort algorithm and natural ordering.
     *
     * @param <T>  the type of elements, must extend {@link Comparable}
     * @param list the list to be sorted
     */
    public static <T extends Comparable<T>> void shellSort(@NotNull final List<T> list) {
        sort(list, Comparator.naturalOrder());
    }

    /**
     * Sorts a list using the shell sort algorithm and a custom comparator.
     *
     * @param <T>        the type of elements
     * @param list       the list to be sorted
     * @param comparator the comparator to determine the order
     */
    public static <T> void shellSort(@NotNull final List<T> list,
                                     @NotNull final Comparator<T> comparator) {
        sort(list, comparator);
    }

    private static <T> void sort(@NotNull final List<T> list,
                                 @NotNull final Comparator<T> comparator) {
        final int n = list.size();
        for (int gap = n / 2; gap > 0; gap /= 2) {
            for (int i = gap; i < n; i++) {
                final T temp = list.get(i);
                int j;
                for (j = i; j >= gap && comparator.compare(list.get(j - gap), temp) > 0; j -= gap) {
                    list.set(j, list.get(j - gap));
                }
                list.set(j, temp);
            }
        }
    }
}