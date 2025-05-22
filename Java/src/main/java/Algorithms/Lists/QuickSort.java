package Algorithms.Lists;

import org.jetbrains.annotations.NotNull;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class QuickSort {

    public static <T extends Comparable<T>> void sort(T[] a, int lo, int hi) {
        if (hi <= lo) return;
        int j = partition(a, lo, hi);
        sort(a, lo, j - 1);
        sort(a, j + 1, hi);
    }

    public static <T extends Comparable<T>> void sortRandomized(T[] a) {
        List<T> list = Arrays.asList(a);
        Collections.shuffle(list);
        sort(a, 0, a.length - 1);
    }

    private static <T extends Comparable<T>> void swap(T @NotNull [] a, int i, int j) {
        T tmp = a[i];
        a[i] = a[j];
        a[j] = tmp;
    }

    private static <T extends Comparable<T>> int partition(T @NotNull [] a, int lo, int hi) {
        int i = lo;
        int j = hi + 1;

        while (true) {
            while (a[++i].compareTo(a[lo]) < 0) {
                if (i == hi)
                    break;
            }
            while (a[lo].compareTo(a[--j]) < 0) {
                if (j == lo)
                    break;
            }
            if (i >= j)
                break;
            swap(a, i, j);
        }
        swap(a, lo, j);
        return j;
    }
}
