package io.github.mihaistreames.afe.algorithms;

import io.github.mihaistreames.afe.algorithms.searching.BinarySearch;
import io.github.mihaistreames.afe.algorithms.sorting.*;
import org.jetbrains.annotations.NotNull;

import java.util.Comparator;
import java.util.List;

/**
 * Unified facade providing convenient access to all algorithms in the library.
 * <p>
 * This class serves as the main entry point for users who want to access algorithms
 * without needing to import individual algorithm classes.
 * </p>
 * <p>
 * <strong>Usage Example:</strong>
 * <pre>{@code
 * List<Integer> numbers = Arrays.asList(64, 34, 25, 12, 22, 11, 90);
 *
 * // Sort using different algorithms
 * Algorithms.quickSort(numbers);
 * Algorithms.mergeSort(numbers);
 * Algorithms.heapSort(numbers);
 *
 * // Search in sorted data
 * int index = Algorithms.binarySearch(numbers, 25);
 * }</pre>
 * </p>
 *
 * @author Sincos Team
 * @version 1.0.0
 * @since 0.0.1
 */
public final class Algorithms {

    private Algorithms() {
        throw new UnsupportedOperationException("Utility class cannot be instantiated");
    }

    // ========== SORTING ALGORITHMS ==========

    /**
     * Sorts the list using QuickSort with natural ordering.
     * <p>
     * <strong>Time:</strong> O(n log n) average, O(n²) worst case<br>
     * <strong>Space:</strong> O(log n)<br>
     * <strong>Stability:</strong> Not stable
     * </p>
     *
     * @param <T>  the type of elements, must extend {@link Comparable}
     * @param list the list to sort
     * @throws NullPointerException if the list is null
     */
    public static <T extends Comparable<T>> void quickSort(@NotNull final List<T> list) {
        QuickSort.sort(list);
    }

    /**
     * Sorts the list using QuickSort with a custom comparator.
     *
     * @param <T>        the type of elements
     * @param list       the list to sort
     * @param comparator the comparator to determine element order
     * @throws NullPointerException if the list or comparator is null
     */
    public static <T> void quickSort(@NotNull final List<T> list, @NotNull final Comparator<T> comparator) {
        QuickSort.sort(list, comparator);
    }

    /**
     * Sorts the array using QuickSort with natural ordering.
     *
     * @param <T>   the type of elements, must extend {@link Comparable}
     * @param array the array to sort
     * @throws NullPointerException if the array is null
     */
    public static <T extends Comparable<T>> void quickSort(@NotNull final T[] array) {
        QuickSort.sort(array);
    }

    /**
     * Sorts the array using QuickSort with a custom comparator.
     *
     * @param <T>        the type of elements
     * @param array      the array to sort
     * @param comparator the comparator to determine element order
     * @throws NullPointerException if the array or comparator is null
     */
    public static <T> void quickSort(@NotNull final T[] array, @NotNull final Comparator<T> comparator) {
        QuickSort.sort(array, comparator);
    }

    /**
     * Sorts the list using MergeSort with natural ordering.
     * <p>
     * <strong>Time:</strong> O(n log n) guaranteed<br>
     * <strong>Space:</strong> O(n)<br>
     * <strong>Stability:</strong> Stable
     * </p>
     *
     * @param <T>  the type of elements, must extend {@link Comparable}
     * @param list the list to sort
     * @throws NullPointerException if the list is null
     */
    public static <T extends Comparable<T>> void mergeSort(@NotNull final List<T> list) {
        MergeSort.sort(list);
    }

    /**
     * Sorts the list using MergeSort with a custom comparator.
     *
     * @param <T>        the type of elements
     * @param list       the list to sort
     * @param comparator the comparator to determine element order
     * @throws NullPointerException if the list or comparator is null
     */
    public static <T> void mergeSort(@NotNull final List<T> list, @NotNull final Comparator<T> comparator) {
        MergeSort.sort(list, comparator);
    }

    /**
     * Sorts the array using MergeSort with natural ordering.
     *
     * @param <T>   the type of elements, must extend {@link Comparable}
     * @param array the array to sort
     * @throws NullPointerException if the array is null
     */
    public static <T extends Comparable<T>> void mergeSort(@NotNull final T[] array) {
        MergeSort.sort(array);
    }

    /**
     * Sorts the array using MergeSort with a custom comparator.
     *
     * @param <T>        the type of elements
     * @param array      the array to sort
     * @param comparator the comparator to determine element order
     * @throws NullPointerException if the array or comparator is null
     */
    public static <T> void mergeSort(@NotNull final T[] array, @NotNull final Comparator<T> comparator) {
        MergeSort.sort(array, comparator);
    }

    /**
     * Sorts the list using iterative MergeSort with natural ordering.
     * <p>
     * This bottom-up approach avoids recursion and prevents stack overflow
     * issues for very large datasets.
     * </p>
     *
     * @param <T>  the type of elements, must extend {@link Comparable}
     * @param list the list to sort
     * @throws NullPointerException if the list is null
     */
    public static <T extends Comparable<T>> void mergeSortIterative(@NotNull final List<T> list) {
        MergeSort.sortIterative(list);
    }

    /**
     * Sorts the list using iterative MergeSort with a custom comparator.
     * <p>
     * This bottom-up approach avoids recursion and prevents stack overflow
     * issues for very large datasets.
     * </p>
     *
     * @param <T>        the type of elements
     * @param list       the list to sort
     * @param comparator the comparator to determine element order
     * @throws NullPointerException if the list or comparator is null
     */
    public static <T> void mergeSortIterative(@NotNull final List<T> list, @NotNull final Comparator<T> comparator) {
        MergeSort.sortIterative(list, comparator);
    }

    /**
     * Sorts the list using HeapSort with natural ordering.
     * <p>
     * <strong>Time:</strong> O(n log n) guaranteed<br>
     * <strong>Space:</strong> O(1)<br>
     * <strong>Stability:</strong> Not stable
     * </p>
     *
     * @param <T>  the type of elements, must extend {@link Comparable}
     * @param list the list to sort
     * @throws NullPointerException if the list is null
     */
    public static <T extends Comparable<T>> void heapSort(@NotNull final List<T> list) {
        HeapSort.sort(list);
    }

    /**
     * Sorts the list using HeapSort with a custom comparator.
     *
     * @param <T>        the type of elements
     * @param list       the list to sort
     * @param comparator the comparator to determine element order
     * @throws NullPointerException if the list or comparator is null
     */
    public static <T> void heapSort(@NotNull final List<T> list, @NotNull final Comparator<T> comparator) {
        HeapSort.sort(list, comparator);
    }

    /**
     * Sorts the array using HeapSort with natural ordering.
     *
     * @param <T>   the type of elements, must extend {@link Comparable}
     * @param array the array to sort
     * @throws NullPointerException if the array is null
     */
    public static <T extends Comparable<T>> void heapSort(@NotNull final T[] array) {
        HeapSort.sort(array);
    }

    /**
     * Sorts the array using HeapSort with a custom comparator.
     *
     * @param <T>        the type of elements
     * @param array      the array to sort
     * @param comparator the comparator to determine element order
     * @throws NullPointerException if the array or comparator is null
     */
    public static <T> void heapSort(@NotNull final T[] array, @NotNull final Comparator<T> comparator) {
        HeapSort.sort(array, comparator);
    }

    /**
     * Sorts the list using InsertionSort with natural ordering.
     * <p>
     * <strong>Time:</strong> O(n²) worst case, O(n) best case<br>
     * <strong>Space:</strong> O(1)<br>
     * <strong>Stability:</strong> Stable<br>
     * <strong>Note:</strong> Excellent for small datasets or nearly sorted data
     * </p>
     *
     * @param <T>  the type of elements, must extend {@link Comparable}
     * @param list the list to sort
     * @throws NullPointerException if the list is null
     */
    public static <T extends Comparable<T>> void insertionSort(@NotNull final List<T> list) {
        InsertionSort.sort(list);
    }

    /**
     * Sorts the list using InsertionSort with a custom comparator.
     *
     * @param <T>        the type of elements
     * @param list       the list to sort
     * @param comparator the comparator to determine element order
     * @throws NullPointerException if the list or comparator is null
     */
    public static <T> void insertionSort(@NotNull final List<T> list, @NotNull final Comparator<T> comparator) {
        InsertionSort.sort(list, comparator);
    }

    /**
     * Sorts the array using InsertionSort with natural ordering.
     *
     * @param <T>   the type of elements, must extend {@link Comparable}
     * @param array the array to sort
     * @throws NullPointerException if the array is null
     */
    public static <T extends Comparable<T>> void insertionSort(@NotNull final T[] array) {
        InsertionSort.sort(array);
    }

    /**
     * Sorts the array using InsertionSort with a custom comparator.
     *
     * @param <T>        the type of elements
     * @param array      the array to sort
     * @param comparator the comparator to determine element order
     * @throws NullPointerException if the array or comparator is null
     */
    public static <T> void insertionSort(@NotNull final T[] array, @NotNull final Comparator<T> comparator) {
        InsertionSort.sort(array, comparator);
    }

    /**
     * Sorts the list using SelectionSort with natural ordering.
     * <p>
     * <strong>Time:</strong> O(n²) in all cases<br>
     * <strong>Space:</strong> O(1)<br>
     * <strong>Stability:</strong> Not stable<br>
     * <strong>Note:</strong> Educational purposes - performs O(n) swaps
     * </p>
     *
     * @param <T>  the type of elements, must extend {@link Comparable}
     * @param list the list to sort
     * @throws NullPointerException if the list is null
     */
    public static <T extends Comparable<T>> void selectionSort(@NotNull final List<T> list) {
        SelectionSort.sort(list);
    }

    /**
     * Sorts the list using SelectionSort with a custom comparator.
     *
     * @param <T>        the type of elements
     * @param list       the list to sort
     * @param comparator the comparator to determine element order
     * @throws NullPointerException if the list or comparator is null
     */
    public static <T> void selectionSort(@NotNull final List<T> list, @NotNull final Comparator<T> comparator) {
        SelectionSort.sort(list, comparator);
    }

    /**
     * Sorts the array using SelectionSort with natural ordering.
     *
     * @param <T>   the type of elements, must extend {@link Comparable}
     * @param array the array to sort
     * @throws NullPointerException if the array is null
     */
    public static <T extends Comparable<T>> void selectionSort(@NotNull final T[] array) {
        SelectionSort.sort(array);
    }

    /**
     * Sorts the array using SelectionSort with a custom comparator.
     *
     * @param <T>        the type of elements
     * @param array      the array to sort
     * @param comparator the comparator to determine element order
     * @throws NullPointerException if the array or comparator is null
     */
    public static <T> void selectionSort(@NotNull final T[] array, @NotNull final Comparator<T> comparator) {
        SelectionSort.sort(array, comparator);
    }

    /**
     * Sorts the list using ShellSort with natural ordering.
     * <p>
     * <strong>Time:</strong> O(n log²n) to O(n^1.5) depending on gap sequence<br>
     * <strong>Space:</strong> O(1)<br>
     * <strong>Stability:</strong> Not stable<br>
     * <strong>Note:</strong> Good performance for medium-sized datasets
     * </p>
     *
     * @param <T>  the type of elements, must extend {@link Comparable}
     * @param list the list to sort
     * @throws NullPointerException if the list is null
     */
    public static <T extends Comparable<T>> void shellSort(@NotNull final List<T> list) {
        ShellSort.sort(list);
    }

    /**
     * Sorts the list using ShellSort with a custom comparator.
     *
     * @param <T>        the type of elements
     * @param list       the list to sort
     * @param comparator the comparator to determine element order
     * @throws NullPointerException if the list or comparator is null
     */
    public static <T> void shellSort(@NotNull final List<T> list, @NotNull final Comparator<T> comparator) {
        ShellSort.sort(list, comparator);
    }

    /**
     * Sorts the array using ShellSort with natural ordering.
     *
     * @param <T>   the type of elements, must extend {@link Comparable}
     * @param array the array to sort
     * @throws NullPointerException if the array is null
     */
    public static <T extends Comparable<T>> void shellSort(@NotNull final T[] array) {
        ShellSort.sort(array);
    }

    /**
     * Sorts the array using ShellSort with a custom comparator.
     *
     * @param <T>        the type of elements
     * @param array      the array to sort
     * @param comparator the comparator to determine element order
     * @throws NullPointerException if the array or comparator is null
     */
    public static <T> void shellSort(@NotNull final T[] array, @NotNull final Comparator<T> comparator) {
        ShellSort.sort(array, comparator);
    }

    /**
     * Sorts the list using BubbleSort with natural ordering.
     * <p>
     * <strong>Time:</strong> O(n²) worst case, O(n) best case<br>
     * <strong>Space:</strong> O(1)<br>
     * <strong>Stability:</strong> Stable<br>
     * <strong>Warning:</strong> Educational purposes only - very inefficient for large datasets
     * </p>
     *
     * @param <T>  the type of elements, must extend {@link Comparable}
     * @param list the list to sort
     * @throws NullPointerException if the list is null
     */
    public static <T extends Comparable<T>> void bubbleSort(@NotNull final List<T> list) {
        BubbleSort.sort(list);
    }

    /**
     * Sorts the list using BubbleSort with a custom comparator.
     *
     * @param <T>        the type of elements
     * @param list       the list to sort
     * @param comparator the comparator to determine element order
     * @throws NullPointerException if the list or comparator is null
     */
    public static <T> void bubbleSort(@NotNull final List<T> list, @NotNull final Comparator<T> comparator) {
        BubbleSort.sort(list, comparator);
    }

    /**
     * Sorts the array using BubbleSort with natural ordering.
     *
     * @param <T>   the type of elements, must extend {@link Comparable}
     * @param array the array to sort
     * @throws NullPointerException if the array is null
     */
    public static <T extends Comparable<T>> void bubbleSort(@NotNull final T[] array) {
        BubbleSort.sort(array);
    }

    /**
     * Sorts the array using BubbleSort with a custom comparator.
     *
     * @param <T>        the type of elements
     * @param array      the array to sort
     * @param comparator the comparator to determine element order
     * @throws NullPointerException if the array or comparator is null
     */
    public static <T> void bubbleSort(@NotNull final T[] array, @NotNull final Comparator<T> comparator) {
        BubbleSort.sort(array, comparator);
    }

    // ========== SEARCHING ALGORITHMS ==========

    /**
     * Searches for an element in the sorted list using binary search.
     * <p>
     * <strong>Time:</strong> O(log n)<br>
     * <strong>Space:</strong> O(1)<br>
     * <strong>Prerequisite:</strong> List must be sorted in ascending order
     * </p>
     *
     * @param <T>  the type of elements, must extend {@link Comparable}
     * @param list the sorted list to search
     * @param key  the element to search for
     * @return the index of the element if found, or -1 if not found
     * @throws NullPointerException if the list is null
     */
    public static <T extends Comparable<T>> int binarySearch(@NotNull final List<T> list, final T key) {
        return BinarySearch.binarySearch(list, key);
    }

    /**
     * Searches for an element in the sorted list using binary search with a custom comparator.
     * <p>
     * <strong>Prerequisite:</strong> List must be sorted according to the comparator
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
        return BinarySearch.binarySearch(list, key, comparator);
    }

    /**
     * Searches for an element in the sorted array using binary search.
     * <p>
     * <strong>Prerequisite:</strong> Array must be sorted in ascending order
     * </p>
     *
     * @param <T>   the type of elements, must extend {@link Comparable}
     * @param array the sorted array to search
     * @param key   the element to search for
     * @return the index of the element if found, or -1 if not found
     * @throws NullPointerException if the array is null
     */
    public static <T extends Comparable<T>> int binarySearch(@NotNull final T[] array, final T key) {
        return BinarySearch.binarySearch(array, key);
    }

    /**
     * Searches for an element in the sorted array using binary search with a custom comparator.
     * <p>
     * <strong>Prerequisite:</strong> Array must be sorted according to the comparator
     * </p>
     *
     * @param <T>        the type of elements
     * @param array      the sorted array to search
     * @param key        the element to search for
     * @param comparator the comparator that was used to sort the array
     * @return the index of the element if found, or -1 if not found
     * @throws NullPointerException if the array or comparator is null
     */
    public static <T> int binarySearch(@NotNull final T[] array, final T key, @NotNull final Comparator<T> comparator) {
        return BinarySearch.binarySearch(array, key, comparator);
    }
}