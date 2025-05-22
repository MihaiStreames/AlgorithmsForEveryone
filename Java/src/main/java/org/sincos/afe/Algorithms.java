package org.sincos.afe;

import org.jetbrains.annotations.NotNull;
import org.sincos.afe.searching.BinarySearch;
import org.sincos.afe.sorting.MergeSort;
import org.sincos.afe.sorting.QuickSort;

import java.util.Comparator;
import java.util.List;

/**
 * Unified facade providing convenient access to all algorithms in the library.
 * <p>
 * This class serves as the main entry point for users who want to access algorithms
 * without needing to import individual algorithm classes. It provides a clean,
 * consistent API that delegates to the underlying algorithm implementations.
 * </p>
 * <p>
 * <strong>Usage Example:</strong>
 * <pre>{@code
 * List<Integer> numbers = Arrays.asList(64, 34, 25, 12, 22, 11, 90);
 *
 * // Sort using different algorithms
 * Algorithms.quickSort(numbers);
 * Algorithms.mergeSort(numbers);
 *
 * // Search in sorted data
 * int index = Algorithms.binarySearch(numbers, 25);
 * }</pre>
 * </p>
 *
 * @author SinCos Algorithms Team
 * @version 1.0.0
 * @since 1.0.0
 */
public final class Algorithms {

    /**
     * Private constructor to prevent instantiation of utility class.
     */
    private Algorithms() {
        throw new UnsupportedOperationException("Utility class cannot be instantiated");
    }

    // ========== SORTING ALGORITHMS ==========

    /**
     * Sorts the specified list using the QuickSort algorithm with natural ordering.
     * <p>
     * Delegates to {@link QuickSort#sort(List)}.
     * </p>
     *
     * @param <T>  the type of elements in the list, must extend {@link Comparable}
     * @param list the list to sort; must not be null
     * @throws NullPointerException if the list is null
     * @throws ClassCastException   if elements cannot be compared to one another
     * @see QuickSort#sort(List)
     */
    public static <T extends Comparable<T>> void quickSort(@NotNull final List<T> list) {
        QuickSort.sort(list);
    }

    /**
     * Sorts the specified list using the QuickSort algorithm with a custom comparator.
     * <p>
     * Delegates to {@link QuickSort#sort(List, Comparator)}.
     * </p>
     *
     * @param <T>        the type of elements in the list
     * @param list       the list to sort; must not be null
     * @param comparator the comparator to determine element order; must not be null
     * @throws NullPointerException if the list or comparator is null
     * @see QuickSort#sort(List, Comparator)
     */
    public static <T> void quickSort(@NotNull final List<T> list, @NotNull final Comparator<T> comparator) {
        QuickSort.sort(list, comparator);
    }

    /**
     * Sorts the specified array using the QuickSort algorithm with natural ordering.
     * <p>
     * Delegates to {@link QuickSort#sort(Object[])}.
     * </p>
     *
     * @param <T>   the type of elements in the array, must extend {@link Comparable}
     * @param array the array to sort; must not be null
     * @throws NullPointerException if the array is null
     * @throws ClassCastException   if elements cannot be compared to one another
     * @see QuickSort#sort(Object[])
     */
    public static <T extends Comparable<T>> void quickSort(@NotNull final T[] array) {
        QuickSort.sort(array);
    }

    /**
     * Sorts the specified array using the QuickSort algorithm with a custom comparator.
     * <p>
     * Delegates to {@link QuickSort#sort(Object[], Comparator)}.
     * </p>
     *
     * @param <T>        the type of elements in the array
     * @param array      the array to sort; must not be null
     * @param comparator the comparator to determine element order; must not be null
     * @throws NullPointerException if the array or comparator is null
     * @see QuickSort#sort(Object[], Comparator)
     */
    public static <T> void quickSort(@NotNull final T[] array, @NotNull final Comparator<T> comparator) {
        QuickSort.sort(array, comparator);
    }

    /**
     * Sorts the specified list using the MergeSort algorithm with natural ordering.
     * <p>
     * Delegates to {@link MergeSort#sort(List)}.
     * </p>
     *
     * @param <T>  the type of elements in the list, must extend {@link Comparable}
     * @param list the list to sort; must not be null
     * @throws NullPointerException if the list is null
     * @throws ClassCastException   if elements cannot be compared to one another
     * @see MergeSort#sort(List)
     */
    public static <T extends Comparable<T>> void mergeSort(@NotNull final List<T> list) {
        MergeSort.sort(list);
    }

    /**
     * Sorts the specified list using the MergeSort algorithm with a custom comparator.
     * <p>
     * Delegates to {@link MergeSort#sort(List, Comparator)}.
     * </p>
     *
     * @param <T>        the type of elements in the list
     * @param list       the list to sort; must not be null
     * @param comparator the comparator to determine element order; must not be null
     * @throws NullPointerException if the list or comparator is null
     * @see MergeSort#sort(List, Comparator)
     */
    public static <T> void mergeSort(@NotNull final List<T> list, @NotNull final Comparator<T> comparator) {
        MergeSort.sort(list, comparator);
    }

    /**
     * Sorts the specified array using the MergeSort algorithm with natural ordering.
     * <p>
     * Delegates to {@link MergeSort#sort(Object[])}.
     * </p>
     *
     * @param <T>   the type of elements in the array, must extend {@link Comparable}
     * @param array the array to sort; must not be null
     * @throws NullPointerException if the array is null
     * @throws ClassCastException   if elements cannot be compared to one another
     * @see MergeSort#sort(Object[])
     */
    public static <T extends Comparable<T>> void mergeSort(@NotNull final T[] array) {
        MergeSort.sort(array);
    }

    /**
     * Sorts the specified array using the MergeSort algorithm with a custom comparator.
     * <p>
     * Delegates to {@link MergeSort#sort(Object[], Comparator)}.
     * </p>
     *
     * @param <T>        the type of elements in the array
     * @param array      the array to sort; must not be null
     * @param comparator the comparator to determine element order; must not be null
     * @throws NullPointerException if the array or comparator is null
     * @see MergeSort#sort(Object[], Comparator)
     */
    public static <T> void mergeSort(@NotNull final T[] array, @NotNull final Comparator<T> comparator) {
        MergeSort.sort(array, comparator);
    }

    /**
     * Sorts the specified list using the iterative MergeSort algorithm with natural ordering.
     * <p>
     * This bottom-up approach may be preferable for very large datasets to avoid
     * potential stack overflow issues. Delegates to {@link MergeSort#sortIterative(List)}.
     * </p>
     *
     * @param <T>  the type of elements in the list, must extend {@link Comparable}
     * @param list the list to sort; must not be null
     * @throws NullPointerException if the list is null
     * @throws ClassCastException   if elements cannot be compared to one another
     * @see MergeSort#sortIterative(List)
     */
    public static <T extends Comparable<T>> void mergeSortIterative(@NotNull final List<T> list) {
        MergeSort.sortIterative(list);
    }

    /**
     * Sorts the specified list using the iterative MergeSort algorithm with a custom comparator.
     * <p>
     * This bottom-up approach may be preferable for very large datasets to avoid
     * potential stack overflow issues. Delegates to {@link MergeSort#sortIterative(List, Comparator)}.
     * </p>
     *
     * @param <T>        the type of elements in the list
     * @param list       the list to sort; must not be null
     * @param comparator the comparator to determine element order; must not be null
     * @throws NullPointerException if the list or comparator is null
     * @see MergeSort#sortIterative(List, Comparator)
     */
    public static <T> void mergeSortIterative(@NotNull final List<T> list, @NotNull final Comparator<T> comparator) {
        MergeSort.sortIterative(list, comparator);
    }

    // ========== SEARCHING ALGORITHMS ==========

    /**
     * Searches for the specified element in the sorted list using binary search with natural ordering.
     * <p>
     * Delegates to {@link BinarySearch#binarySearch(List, Object)}.
     * </p>
     *
     * @param <T>  the type of elements in the list, must extend {@link Comparable}
     * @param list the sorted list to search; must not be null
     * @param key  the element to search for; may be null if T supports null values
     * @return the index of the element if found, or -1 if not found
     * @throws NullPointerException if the list is null
     * @throws ClassCastException   if elements cannot be compared to the key
     * @see BinarySearch#binarySearch(List, Object)
     */
    public static <T extends Comparable<T>> int binarySearch(@NotNull final List<T> list, final T key) {
        return BinarySearch.binarySearch(list, key);
    }

    /**
     * Searches for the specified element in the sorted list using binary search with a custom comparator.
     * <p>
     * Delegates to {@link BinarySearch#binarySearch(List, Object, Comparator)}.
     * </p>
     *
     * @param <T>        the type of elements in the list
     * @param list       the sorted list to search; must not be null
     * @param key        the element to search for; may be null if comparator supports null values
     * @param comparator the comparator that was used to sort the list; must not be null
     * @return the index of the element if found, or -1 if not found
     * @throws NullPointerException if the list or comparator is null
     * @see BinarySearch#binarySearch(List, Object, Comparator)
     */
    public static <T> int binarySearch(@NotNull final List<T> list,
                                       final T key,
                                       @NotNull final Comparator<T> comparator) {
        return BinarySearch.binarySearch(list, key, comparator);
    }

    /**
     * Searches for the specified element in the sorted array using binary search with natural ordering.
     * <p>
     * Delegates to {@link BinarySearch#binarySearch(Object[], Object)}.
     * </p>
     *
     * @param <T>   the type of elements in the array, must extend {@link Comparable}
     * @param array the sorted array to search; must not be null
     * @param key   the element to search for; may be null if T supports null values
     * @return the index of the element if found, or -1 if not found
     * @throws NullPointerException if the array is null
     * @throws ClassCastException   if elements cannot be compared to the key
     * @see BinarySearch#binarySearch(Object[], Object)
     */
    public static <T extends Comparable<T>> int binarySearch(@NotNull final T[] array, final T key) {
        return BinarySearch.binarySearch(array, key);
    }

    /**
     * Searches for the specified element in the sorted array using binary search with a custom comparator.
     * <p>
     * Delegates to {@link BinarySearch#binarySearch(Object[], Object, Comparator)}.
     * </p>
     *
     * @param <T>        the type of elements in the array
     * @param array      the sorted array to search; must not be null
     * @param key        the element to search for; may be null if comparator supports null values
     * @param comparator the comparator that was used to sort the array; must not be null
     * @return the index of the element if found, or -1 if not found
     * @throws NullPointerException if the array or comparator is null
     * @see BinarySearch#binarySearch(Object[], Object, Comparator)
     */
    public static <T> int binarySearch(@NotNull final T[] array,
                                       final T key,
                                       @NotNull final Comparator<T> comparator) {
        return BinarySearch.binarySearch(array, key, comparator);
    }
}