package org.sincos.afe.searching;

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
 * <strong>Space Complexity:</strong> O(1) iterative implementation<br>
 * <strong>Prerequisite:</strong> The collection must be sorted in ascending order<br>
 * <strong>Return Value:</strong> Index of found element, or -1 if not found
 * </p>
 *
 * @author Sincos Team
 * @version 1.0.0
 * @since 1.0.0
 */
public final class BinarySearch {

    /**
     * Private constructor to prevent instantiation of utility class.
     */
    private BinarySearch() {
        throw new UnsupportedOperationException("Utility class cannot be instantiated");
    }

    // ========== PUBLIC API - List Operations ==========

    /**
     * Searches for the specified element in the sorted list using binary search.
     * <p>
     * The list elements must implement the {@link Comparable} interface and the list
     * must be sorted in ascending natural order. If the list is not sorted, the
     * result is undefined.
     * </p>
     *
     * @param <T>  the type of elements in the list, must extend {@link Comparable}
     * @param list the sorted list to search; must not be null
     * @param key  the element to search for; may be null if T supports null values
     * @return the index of the element if found, or -1 if not found
     * @throws NullPointerException if the list is null
     * @throws ClassCastException   if elements cannot be compared to the key
     * @see #binarySearch(List, Object, Comparator)
     */
    public static <T extends Comparable<T>> int binarySearch(@NotNull final List<T> list, final T key) {
        Objects.requireNonNull(list, "List cannot be null");
        return search(list, key, Comparable::compareTo);
    }

    /**
     * Searches for the specified element in the sorted list using binary search and a custom comparator.
     * <p>
     * The list must be sorted according to the specified comparator. If the list is not sorted
     * according to the comparator, the result is undefined.
     * </p>
     *
     * @param <T>        the type of elements in the list
     * @param list       the sorted list to search; must not be null
     * @param key        the element to search for; may be null if comparator supports null values
     * @param comparator the comparator that was used to sort the list; must not be null
     * @return the index of the element if found, or -1 if not found
     * @throws NullPointerException if the list or comparator is null
     * @see #binarySearch(List, Object)
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
     * Searches for the specified element in the sorted array using binary search.
     * <p>
     * This is a convenience method that converts the array to a list and performs the search.
     * The array elements must implement the {@link Comparable} interface and the array
     * must be sorted in ascending natural order.
     * </p>
     *
     * @param <T>   the type of elements in the array, must extend {@link Comparable}
     * @param array the sorted array to search; must not be null
     * @param key   the element to search for; may be null if T supports null values
     * @return the index of the element if found, or -1 if not found
     * @throws NullPointerException if the array is null
     * @throws ClassCastException   if elements cannot be compared to the key
     * @see #binarySearch(Object[], Object, Comparator)
     * @see #binarySearch(List, Object)
     */
    public static <T extends Comparable<T>> int binarySearch(@NotNull final T[] array, final T key) {
        Objects.requireNonNull(array, "Array cannot be null");
        final List<T> list = Arrays.asList(array);
        return binarySearch(list, key);
    }

    /**
     * Searches for the specified element in the sorted array using binary search and a custom comparator.
     * <p>
     * This is a convenience method that converts the array to a list and performs the search.
     * The array must be sorted according to the specified comparator.
     * </p>
     *
     * @param <T>        the type of elements in the array
     * @param array      the sorted array to search; must not be null
     * @param key        the element to search for; may be null if comparator supports null values
     * @param comparator the comparator that was used to sort the array; must not be null
     * @return the index of the element if found, or -1 if not found
     * @throws NullPointerException if the array or comparator is null
     * @see #binarySearch(Object[], Object)
     * @see #binarySearch(List, Object, Comparator)
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
     * <p>
     * This method performs the actual binary search algorithm using the provided comparator.
     * </p>
     *
     * @param <T>        the type of elements in the list
     * @param list       the sorted list to search
     * @param key        the element to search for
     * @param comparator the comparator for element comparison
     * @return the index of the element if found, or -1 if not found
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