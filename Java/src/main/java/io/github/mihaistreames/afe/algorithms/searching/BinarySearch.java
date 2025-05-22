package io.github.mihaistreames.afe.algorithms.searching;

import org.jetbrains.annotations.NotNull;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Objects;

/**
 * Implementation of the Binary Search algorithm.
 * <p>
 * Binary Search is an efficient algorithm for finding a specific element in a sorted
 * collection. It works by repeatedly dividing the search interval in half and eliminating
 * the half that cannot contain the target element.
 * </p>
 * <p>
 * <strong>Time Complexity:</strong> O(log n)<br>
 * <strong>Space Complexity:</strong> O(1)<br>
 * <strong>Prerequisite:</strong> The collection must be sorted in ascending order
 * </p>
 *
 * @author Sincos Team
 * @version 1.0.0
 * @since 1.0.0
 */
public final class BinarySearch {

    private BinarySearch() {
        throw new UnsupportedOperationException("Utility class cannot be instantiated");
    }

    // ========== PUBLIC API - List Operations ==========

    /**
     * Searches for an element in the sorted list using binary search.
     * <p>
     * The list elements must implement {@link Comparable} and the list
     * must be sorted in ascending natural order.
     * </p>
     *
     * @param <T>  the type of elements, must extend {@link Comparable}
     * @param list the sorted list to search
     * @param key  the element to search for
     * @return the index of the element if found, or -1 if not found
     * @throws NullPointerException if the list is null
     */
    public static <T extends Comparable<T>> int binarySearch(@NotNull final List<T> list, final T key) {
        Objects.requireNonNull(list, "List cannot be null");
        return search(list, key, Comparable::compareTo);
    }

    /**
     * Searches for an element in the sorted list using binary search with a custom comparator.
     * <p>
     * The list must be sorted according to the specified comparator.
     * </p>
     *
     * @param <T>        the type of elements
     * @param list       the sorted list to search
     * @param key        the element to search for
     * @param comparator the comparator that was used to sort the list
     * @return the index of the element if found, or -1 if not found
     * @throws NullPointerException if the list or comparator is null
     */
    public static <T> int binarySearch(@NotNull final List<T> list,
                                       final T key,
                                       @NotNull final Comparator<T> comparator) {
        Objects.requireNonNull(list, "List cannot be null");
        Objects.requireNonNull(comparator, "Comparator cannot be null");
        return search(list, key, comparator);
    }

    // ========== PUBLIC API - Array Operations ==========

    /**
     * Searches for an element in the sorted array using binary search.
     * <p>
     * Convenience method that converts the array to a list and performs the search.
     * The array elements must implement {@link Comparable} and the array
     * must be sorted in ascending natural order.
     * </p>
     *
     * @param <T>   the type of elements, must extend {@link Comparable}
     * @param array the sorted array to search
     * @param key   the element to search for
     * @return the index of the element if found, or -1 if not found
     * @throws NullPointerException if the array is null
     */
    public static <T extends Comparable<T>> int binarySearch(@NotNull final T[] array, final T key) {
        Objects.requireNonNull(array, "Array cannot be null");
        final List<T> list = Arrays.asList(array);
        return binarySearch(list, key);
    }

    /**
     * Searches for an element in the sorted array using binary search with a custom comparator.
     * <p>
     * Convenience method that converts the array to a list and performs the search.
     * The array must be sorted according to the specified comparator.
     * </p>
     *
     * @param <T>        the type of elements
     * @param array      the sorted array to search
     * @param key        the element to search for
     * @param comparator the comparator that was used to sort the array
     * @return the index of the element if found, or -1 if not found
     * @throws NullPointerException if the array or comparator is null
     */
    public static <T> int binarySearch(@NotNull final T[] array,
                                       final T key,
                                       @NotNull final Comparator<T> comparator) {
        Objects.requireNonNull(array, "Array cannot be null");
        Objects.requireNonNull(comparator, "Comparator cannot be null");
        final List<T> list = Arrays.asList(array);
        return binarySearch(list, key, comparator);
    }

    // ========== PRIVATE IMPLEMENTATION ==========

    /**
     * Core binary search implementation using iterative approach.
     */
    private static <T> int search(@NotNull final List<T> list,
                                  final T key,
                                  @NotNull final Comparator<T> comparator) {
        int low = 0;
        int high = list.size() - 1;

        while (low <= high) {
            // Use this formula to prevent integer overflow
            final int mid = low + (high - low) / 2;
            final int comparison = comparator.compare(list.get(mid), key);

            if (comparison < 0) {
                // Middle element is less than key, search right half
                low = mid + 1;
            } else if (comparison > 0) {
                // Middle element is greater than key, search left half
                high = mid - 1;
            } else {
                // Element found
                return mid;
            }
        }

        // Element not found
        return -1;
    }
}