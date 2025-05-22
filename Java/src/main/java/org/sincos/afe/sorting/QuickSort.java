package org.sincos.afe.sorting;

import org.jetbrains.annotations.NotNull;

import java.util.*;

/**
 * Implementation of the QuickSort algorithm.
 * <p>
 * QuickSort is an efficient, divide-and-conquer sorting algorithm that works by selecting
 * a 'pivot' element and partitioning the array around it, then recursively sorting the
 * sub-arrays on either side of the pivot.
 * </p>
 * <p>
 * <strong>Time Complexity:</strong> O(n log n) average case, O(n²) worst case<br>
 * <strong>Space Complexity:</strong> O(log n) average case (recursion stack)<br>
 * <strong>Stability:</strong> Not stable - may change relative order of equal elements<br>
 * <strong>In-place:</strong> Yes - sorts in-place with minimal extra space
 * </p>
 *
 * @author Sincos Team
 * @version 1.0.0
 * @since 1.0.0
 */
public final class QuickSort {

    /**
     * Private constructor to prevent instantiation of utility class.
     */
    private QuickSort() {
        throw new UnsupportedOperationException("Utility class cannot be instantiated");
    }

    // ========== PUBLIC API - List Operations ==========

    /**
     * Sorts the specified list in ascending natural order using the quicksort algorithm.
     * <p>
     * The list elements must implement the {@link Comparable} interface.
     * The list is shuffled before sorting to avoid worst-case O(n²) performance
     * on already sorted or reverse-sorted inputs.
     * </p>
     *
     * @param <T>  the type of elements in the list, must extend {@link Comparable}
     * @param list the list to sort; must not be null
     * @throws NullPointerException if the list is null
     * @throws ClassCastException   if elements cannot be compared to one another
     * @see #sort(List, Comparator)
     */
    public static <T extends Comparable<T>> void sort(@NotNull final List<T> list) {
        Objects.requireNonNull(list, "List cannot be null");
        sort(list, Comparable::compareTo);
    }

    /**
     * Sorts the specified list using the provided comparator and the quicksort algorithm.
     * <p>
     * The list is shuffled before sorting to avoid worst-case O(n²) performance
     * on already sorted or reverse-sorted inputs.
     * </p>
     *
     * @param <T>        the type of elements in the list
     * @param list       the list to sort; must not be null
     * @param comparator the comparator to determine element order; must not be null
     * @throws NullPointerException if the list or comparator is null
     * @see #sort(List)
     */
    public static <T> void sort(@NotNull final List<T> list, @NotNull final Comparator<T> comparator) {
        Objects.requireNonNull(list, "List cannot be null");
        Objects.requireNonNull(comparator, "Comparator cannot be null");

        if (list.size() <= 1) {
            return;
        }

        // Shuffle to avoid worst-case performance on sorted inputs
        Collections.shuffle(list);
        quickSortRecursive(list, 0, list.size() - 1, comparator);
    }

    // ========== PUBLIC API - Array Operations ==========

    /**
     * Sorts the specified array in ascending natural order using the quicksort algorithm.
     * <p>
     * This is a convenience method that converts the array to a list, sorts it, and
     * the changes are reflected in the original array.
     * </p>
     *
     * @param <T>   the type of elements in the array, must extend {@link Comparable}
     * @param array the array to sort; must not be null
     * @throws NullPointerException if the array is null
     * @throws ClassCastException   if elements cannot be compared to one another
     * @see #sort(Object[], Comparator)
     * @see #sort(List)
     */
    public static <T extends Comparable<T>> void sort(@NotNull final T[] array) {
        Objects.requireNonNull(array, "Array cannot be null");
        final List<T> list = Arrays.asList(array);
        sort(list);
    }

    /**
     * Sorts the specified array using the provided comparator and the quicksort algorithm.
     * <p>
     * This is a convenience method that converts the array to a list, sorts it, and
     * the changes are reflected in the original array.
     * </p>
     *
     * @param <T>        the type of elements in the array
     * @param array      the array to sort; must not be null
     * @param comparator the comparator to determine element order; must not be null
     * @throws NullPointerException if the array or comparator is null
     * @see #sort(Object[])
     * @see #sort(List, Comparator)
     */
    public static <T> void sort(@NotNull final T[] array, @NotNull final Comparator<T> comparator) {
        Objects.requireNonNull(array, "Array cannot be null");
        Objects.requireNonNull(comparator, "Comparator cannot be null");
        final List<T> list = Arrays.asList(array);
        sort(list, comparator);
    }

    // ========== PRIVATE IMPLEMENTATION ==========

    /**
     * Recursively sorts the specified portion of the list using quicksort.
     *
     * @param <T>        the type of elements in the list
     * @param list       the list to sort
     * @param low        the starting index (inclusive)
     * @param high       the ending index (inclusive)
     * @param comparator the comparator for element comparison
     */
    private static <T> void quickSortRecursive(@NotNull final List<T> list,
                                               final int low,
                                               final int high,
                                               @NotNull final Comparator<T> comparator) {
        if (high <= low) {
            return;
        }

        final int pivotIndex = partition(list, low, high, comparator);
        quickSortRecursive(list, low, pivotIndex - 1, comparator);
        quickSortRecursive(list, pivotIndex + 1, high, comparator);
    }

    /**
     * Partitions the list around a pivot element using the Hoare partition scheme.
     * <p>
     * After partitioning, all elements smaller than the pivot are to its left,
     * and all elements greater than or equal to the pivot are to its right.
     * </p>
     *
     * @param <T>        the type of elements in the list
     * @param list       the list to partition
     * @param low        the starting index of the partition range
     * @param high       the ending index of the partition range
     * @param comparator the comparator for element comparison
     * @return the final position of the pivot element
     */
    private static <T> int partition(@NotNull final List<T> list,
                                     final int low,
                                     final int high,
                                     @NotNull final Comparator<T> comparator) {
        final T pivot = list.get(low);  // Use first element as pivot
        int i = low;
        int j = high + 1;

        while (true) {
            // Find element on left side greater than or equal to pivot
            while (comparator.compare(list.get(++i), pivot) < 0) {
                if (i == high) {
                    break;
                }
            }

            // Find element on right side less than or equal to pivot
            while (comparator.compare(pivot, list.get(--j)) < 0) {
                if (j == low) {
                    break;
                }
            }

            // Check if pointers cross
            if (i >= j) {
                break;
            }

            // Swap elements at i and j
            Collections.swap(list, i, j);
        }

        // Put pivot in its final position
        Collections.swap(list, low, j);
        return j;
    }
}