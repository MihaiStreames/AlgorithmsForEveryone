package io.github.mihaistreames.afe.algorithms.sorting;

import org.jetbrains.annotations.NotNull;

import java.util.Comparator;
import java.util.List;

/**
 * A comparison-based sorting algorithm that uses a binary heap data structure.
 * <p>
 * This implementation is not stable. <br>
 * Time Complexity: O(n log n) <br>
 * Space Complexity: O(1)
 */
public final class HeapSort {

    private HeapSort() {
        throw new UnsupportedOperationException("Utility class cannot be instantiated");
    }

    /**
     * Sorts a list using the heapsort algorithm and natural ordering.
     *
     * @param <T>  the type of elements, must extend {@link Comparable}
     * @param list the list to be sorted
     */
    public static <T extends Comparable<T>> void heapSort(@NotNull final List<T> list) {
        sort(list, Comparator.naturalOrder());
    }

    /**
     * Sorts a list using the heapsort algorithm and a custom comparator.
     *
     * @param <T>        the type of elements
     * @param list       the list to be sorted
     * @param comparator the comparator to determine the order
     */
    public static <T> void heapSort(@NotNull final List<T> list,
                                    @NotNull final Comparator<T> comparator) {
        sort(list, comparator);
    }

    private static <T> void sort(@NotNull final List<T> list,
                                 @NotNull final Comparator<T> comparator) {
        final int size = list.size();
        // Build heap (rearrange array)
        for (int i = size / 2 - 1; i >= 0; i--) {
            heapify(list, size, i, comparator);
        }
        // One by one extract an element from heap
        for (int i = size - 1; i >= 0; i--) {
            // Move current root to end
            final T temp = list.getFirst();
            list.set(0, list.get(i));
            list.set(i, temp);
            // call max heapify on the reduced heap
            heapify(list, i, 0, comparator);
        }
    }

    private static <T> void heapify(@NotNull final List<T> list,
                                    final int size,
                                    final int i,
                                    @NotNull final Comparator<T> comparator) {
        int largest = i; // Initialize largest as root
        final int left = 2 * i + 1;
        final int right = 2 * i + 2;

        // If left child is larger than root
        if (left < size && comparator.compare(list.get(left), list.get(largest)) > 0) {
            largest = left;
        }
        // If right child is larger than largest so far
        if (right < size && comparator.compare(list.get(right), list.get(largest)) > 0) {
            largest = right;
        }
        // If largest is not root
        if (largest != i) {
            final T swap = list.get(i);
            list.set(i, list.get(largest));
            list.set(largest, swap);
            // Recursively heapify the affected sub-tree
            heapify(list, size, largest, comparator);
        }
    }
}