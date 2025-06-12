package io.github.mihaistreames.afe.algorithms.sorting;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

/**
 * An efficient, general-purpose, comparison-based sorting algorithm.
 * It is a divide and conquer algorithm.
 * <p>
 * This implementation is stable. <br>
 * Time Complexity: O(n log n) <br>
 * Space Complexity: O(n)
 */
public final class MergeSort {

    private MergeSort() {
        throw new UnsupportedOperationException("Utility class cannot be instantiated");
    }

    /**
     * Sorts a list using the merge sort algorithm and natural ordering.
     *
     * @param <T>  the type of elements, must extend {@link Comparable}
     * @param list the list to be sorted
     */
    public static <T extends Comparable<T>> void mergeSort(@NotNull final List<T> list) {
        sort(list, Comparator.naturalOrder());
    }

    /**
     * Sorts a list using the merge sort algorithm and a custom comparator.
     *
     * @param <T>        the type of elements
     * @param list       the list to be sorted
     * @param comparator the comparator to determine the order
     */
    public static <T> void mergeSort(@NotNull final List<T> list,
                                     @NotNull final Comparator<T> comparator) {
        sort(list, comparator);
    }

    private static <T> void sort(@NotNull final List<T> list,
                                 @NotNull final Comparator<T> comparator) {
        if (list.size() <= 1) return;

        final int mid = list.size() / 2;
        final List<T> left = new ArrayList<>(list.subList(0, mid));
        final List<T> right = new ArrayList<>(list.subList(mid, list.size()));

        sort(left, comparator);
        sort(right, comparator);
        merge(list, left, right, comparator);
    }

    private static <T> void merge(@NotNull final List<T> list,
                                  @NotNull final List<T> left,
                                  @NotNull final List<T> right,
                                  @NotNull final Comparator<T> comparator) {
        int i = 0, j = 0, k = 0;
        while (i < left.size() && j < right.size()) {
            if (comparator.compare(left.get(i), right.get(j)) <= 0) {
                list.set(k++, left.get(i++));
            } else {
                list.set(k++, right.get(j++));
            }
        }
        while (i < left.size()) {
            list.set(k++, left.get(i++));
        }
        while (j < right.size()) {
            list.set(k++, right.get(j++));
        }
    }
}