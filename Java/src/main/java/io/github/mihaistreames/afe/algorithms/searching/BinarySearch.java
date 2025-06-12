package io.github.mihaistreames.afe.algorithms.searching;

import org.jetbrains.annotations.NotNull;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Objects;

/**
 * An efficient algorithm for finding an element in a sorted collection.
 * <p>
 * Time Complexity: O(log n) <br>
 * Space Complexity: O(1)
 */
public final class BinarySearch {

    private BinarySearch() {
        throw new UnsupportedOperationException("Utility class cannot be instantiated");
    }

    /**
     * Searches for an element in a sorted list using natural ordering.
     *
     * @param <T>  the type of elements, must extend {@link Comparable}
     * @param list the sorted list to search
     * @param key  the element to search for
     * @return the index of the element if found, or -1 otherwise.
     */
    public static <T extends Comparable<T>> int binarySearch(@NotNull final List<T> list, final T key) {
        Objects.requireNonNull(list, "List cannot be null");
        return search(list, key, Comparable::compareTo);
    }

    /**
     * Searches for an element in a sorted list using a custom comparator.
     *
     * @param <T>        the type of elements
     * @param list       the sorted list to search
     * @param key        the element to search for
     * @param comparator the comparator used to sort the list
     * @return the index of the element if found, or -1 otherwise.
     */
    public static <T> int binarySearch(@NotNull final List<T> list,
                                       final T key,
                                       @NotNull final Comparator<T> comparator) {
        Objects.requireNonNull(list, "List cannot be null");
        Objects.requireNonNull(comparator, "Comparator cannot be null");
        return search(list, key, comparator);
    }

    /**
     * Searches for an element in a sorted array using natural ordering.
     *
     * @param <T>   the type of elements, must extend {@link Comparable}
     * @param array the sorted array to search
     * @param key   the element to search for
     * @return the index of the element if found, or -1 otherwise.
     */
    public static <T extends Comparable<T>> int binarySearch(@NotNull final T[] array, final T key) {
        Objects.requireNonNull(array, "Array cannot be null");
        final List<T> list = Arrays.asList(array);
        return binarySearch(list, key);
    }

    /**
     * Searches for an element in a sorted array using a custom comparator.
     *
     * @param <T>        the type of elements
     * @param array      the sorted array to search
     * @param key        the element to search for
     * @param comparator the comparator used to sort the array
     * @return the index of the element if found, or -1 otherwise.
     */
    public static <T> int binarySearch(@NotNull final T[] array,
                                       final T key,
                                       @NotNull final Comparator<T> comparator) {
        Objects.requireNonNull(array, "Array cannot be null");
        Objects.requireNonNull(comparator, "Comparator cannot be null");
        final List<T> list = Arrays.asList(array);
        return binarySearch(list, key, comparator);
    }

    private static <T> int search(@NotNull final List<T> list,
                                  final T key,
                                  @NotNull final Comparator<T> comparator) {
        int low = 0;
        int high = list.size() - 1;

        while (low <= high) {
            final int mid = low + (high - low) / 2;
            final int comparison = comparator.compare(list.get(mid), key);

            if (comparison < 0) {
                low = mid + 1;
            } else if (comparison > 0) {
                high = mid - 1;
            } else {
                return mid;
            }
        }
        return -1; // Element not found
    }
}