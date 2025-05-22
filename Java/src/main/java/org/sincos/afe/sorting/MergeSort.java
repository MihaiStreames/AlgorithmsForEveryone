package org.sincos.afe.sorting;

import org.jetbrains.annotations.NotNull;

import java.util.*;

/**
 * Implementation of the Merge Sort algorithm.
 * <p>
 * Merge Sort is a stable, divide-and-conquer sorting algorithm that divides the input array
 * into two halves, recursively sorts both halves, and then merges the sorted halves.
 * </p>
 * <p>
 * <strong>Time Complexity:</strong> O(n log n) in all cases (best, average, worst)<br>
 * <strong>Space Complexity:</strong> O(n) auxiliary space<br>
 * <strong>Stability:</strong> Stable - maintains relative order of equal elements<br>
 * <strong>In-place:</strong> No - requires additional space
 * </p>
 *
 * @author Sincos Team
 * @version 1.0.0
 * @since 1.0.0
 */
public final class MergeSort {

    /**
     * Private constructor to prevent instantiation of utility class.
     */
    private MergeSort() {
        throw new UnsupportedOperationException("Utility class cannot be instantiated");
    }

    // ========== PUBLIC API - List Operations ==========

    /**
     * Sorts the specified list in ascending natural order using the merge sort algorithm.
     * <p>
     * The list elements must implement the {@link Comparable} interface.
     * The sort is stable and guaranteed to run in O(n log n) time.
     * </p>
     *
     * @param <T>  the type of elements in the list, must extend {@link Comparable}
     * @param list the list to sort; must not be null
     * @throws NullPointerException if the list is null
     * @throws ClassCastException   if elements cannot be compared to one another
     * @see #sort(List, Comparator)
     * @see #sortIterative(List)
     */
    public static <T extends Comparable<T>> void sort(@NotNull final List<T> list) {
        Objects.requireNonNull(list, "List cannot be null");
        sort(list, Comparable::compareTo);
    }

    /**
     * Sorts the specified list using the provided comparator and the merge sort algorithm.
     * <p>
     * The sort is stable and guaranteed to run in O(n log n) time.
     * </p>
     *
     * @param <T>        the type of elements in the list
     * @param list       the list to sort; must not be null
     * @param comparator the comparator to determine element order; must not be null
     * @throws NullPointerException if the list or comparator is null
     * @see #sort(List)
     * @see #sortIterative(List, Comparator)
     */
    public static <T> void sort(@NotNull final List<T> list, @NotNull final Comparator<T> comparator) {
        Objects.requireNonNull(list, "List cannot be null");
        Objects.requireNonNull(comparator, "Comparator cannot be null");

        if (list.size() <= 1) {
            return;
        }

        final List<T> auxiliary = new ArrayList<>(Collections.nCopies(list.size(), null));
        mergeSortRecursive(list, auxiliary, 0, list.size() - 1, comparator);
    }

    /**
     * Sorts the specified list in ascending natural order using the iterative merge sort algorithm.
     * <p>
     * This bottom-up approach eliminates recursion and may be preferable for very large datasets
     * to avoid potential stack overflow issues.
     * </p>
     *
     * @param <T>  the type of elements in the list, must extend {@link Comparable}
     * @param list the list to sort; must not be null
     * @throws NullPointerException if the list is null
     * @throws ClassCastException   if elements cannot be compared to one another
     * @see #sort(List)
     * @see #sortIterative(List, Comparator)
     */
    public static <T extends Comparable<T>> void sortIterative(@NotNull final List<T> list) {
        Objects.requireNonNull(list, "List cannot be null");
        sortIterative(list, Comparable::compareTo);
    }

    /**
     * Sorts the specified list using the provided comparator and the iterative merge sort algorithm.
     * <p>
     * This bottom-up approach eliminates recursion and may be preferable for very large datasets
     * to avoid potential stack overflow issues.
     * </p>
     *
     * @param <T>        the type of elements in the list
     * @param list       the list to sort; must not be null
     * @param comparator the comparator to determine element order; must not be null
     * @throws NullPointerException if the list or comparator is null
     * @see #sortIterative(List)
     * @see #sort(List, Comparator)
     */
    public static <T> void sortIterative(@NotNull final List<T> list, @NotNull final Comparator<T> comparator) {
        Objects.requireNonNull(list, "List cannot be null");
        Objects.requireNonNull(comparator, "Comparator cannot be null");

        final int n = list.size();
        if (n <= 1) {
            return;
        }

        final List<T> auxiliary = new ArrayList<>(Collections.nCopies(n, null));

        for (int size = 1; size < n; size = size + size) {
            for (int low = 0; low < n - size; low += size + size) {
                final int mid = low + size - 1;
                final int high = Math.min(low + size + size - 1, n - 1);
                merge(list, auxiliary, low, mid, high, comparator);
            }
        }
    }

    // ========== PUBLIC API - Array Operations ==========

    /**
     * Sorts the specified array in ascending natural order using the merge sort algorithm.
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
     * Sorts the specified array using the provided comparator and the merge sort algorithm.
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
     * Recursively sorts the specified portion of the list using merge sort.
     *
     * @param <T>        the type of elements in the list
     * @param list       the list to sort
     * @param auxiliary  the auxiliary list used for merging
     * @param low        the starting index (inclusive)
     * @param high       the ending index (inclusive)
     * @param comparator the comparator for element comparison
     */
    private static <T> void mergeSortRecursive(@NotNull final List<T> list,
                                               @NotNull final List<T> auxiliary,
                                               final int low,
                                               final int high,
                                               @NotNull final Comparator<T> comparator) {
        if (high <= low) {
            return;
        }

        final int mid = low + (high - low) / 2;
        mergeSortRecursive(list, auxiliary, low, mid, comparator);
        mergeSortRecursive(list, auxiliary, mid + 1, high, comparator);
        merge(list, auxiliary, low, mid, high, comparator);
    }

    /**
     * Merges two sorted sublists into a single sorted list.
     * <p>
     * Merges list[low..mid] with list[mid+1..high] using the auxiliary array.
     * </p>
     *
     * @param <T>        the type of elements in the list
     * @param list       the list containing the sublists to merge
     * @param auxiliary  the auxiliary list used for temporary storage
     * @param low        the starting index of the first sublist
     * @param mid        the ending index of the first sublist
     * @param high       the ending index of the second sublist
     * @param comparator the comparator for element comparison
     */
    private static <T> void merge(@NotNull final List<T> list,
                                  @NotNull final List<T> auxiliary,
                                  final int low,
                                  final int mid,
                                  final int high,
                                  @NotNull final Comparator<T> comparator) {
        // Copy current state to auxiliary list
        for (int k = low; k <= high; k++) {
            auxiliary.set(k, list.get(k));
        }

        int i = low;        // Index for left sublist
        int j = mid + 1;    // Index for right sublist

        // Merge back to original list
        for (int k = low; k <= high; k++) {
            if (i > mid) {
                // Left sublist exhausted, take from right
                list.set(k, auxiliary.get(j++));
            } else if (j > high) {
                // Right sublist exhausted, take from left
                list.set(k, auxiliary.get(i++));
            } else if (comparator.compare(auxiliary.get(j), auxiliary.get(i)) < 0) {
                // Right element is smaller, take from right
                list.set(k, auxiliary.get(j++));
            } else {
                // Left element is smaller or equal, take from left (maintains stability)
                list.set(k, auxiliary.get(i++));
            }
        }
    }
}