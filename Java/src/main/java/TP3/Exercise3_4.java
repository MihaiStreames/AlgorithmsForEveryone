package TP3;

import DataStructs.Lists.ArrayList;

public class Exercise3_4 {

    /**
     * Merges three sorted arrays into a single sorted array.
     *
     * @param <T> the type of elements in the arrays
     * @param a   the first sorted array
     * @param b   the second sorted array
     * @param c   the third sorted array
     * @return a new array containing all elements from a, b, and c in sorted order
     */
    public static <T extends Comparable<T>> ArrayList<T> mergeThreeSortedArrays(
            ArrayList<T> a, ArrayList<T> b, ArrayList<T> c) {

        // Create a new ArrayList with enough capacity for all elements
        ArrayList<T> result = new ArrayList<>(a.size() + b.size() + c.size());

        int i = 0, j = 0, k = 0;

        // While there are still elements in any of the arrays
        while (i < a.size() || j < b.size() || k < c.size()) {
            // Get the current element from each array, or use a sentinel value if the array is exhausted
            T minA = (i < a.size()) ? a.get(i) : null;
            T minB = (j < b.size()) ? b.get(j) : null;
            T minC = (k < c.size()) ? c.get(k) : null;

            // Find the minimum of the three current elements
            if (minA != null &&
                    (minB == null || minA.compareTo(minB) <= 0) &&
                    (minC == null || minA.compareTo(minC) <= 0)) {
                result.add(minA);
                i++;
            } else if (minB != null &&
                    (minC == null || minB.compareTo(minC) <= 0)) {
                result.add(minB);
                j++;
            } else if (minC != null) {
                result.add(minC);
                k++;
            }
        }

        return result;
    }

    public static void main(String[] args) {
        // Create three sorted integer arrays
        Integer[] arrayA = {1, 3, 5};
        Integer[] arrayB = {2, 4, 6};
        Integer[] arrayC = {0, 7, 8};

        ArrayList<Integer> a = new ArrayList<>(arrayA);
        ArrayList<Integer> b = new ArrayList<>(arrayB);
        ArrayList<Integer> c = new ArrayList<>(arrayC);

        System.out.println("Array A: " + a);
        System.out.println("Array B: " + b);
        System.out.println("Array C: " + c);

        ArrayList<Integer> merged = mergeThreeSortedArrays(a, b, c);

        System.out.println("Merged array: " + merged); // [0, 1, 2, 3, 4, 5, 6, 7, 8]
    }
}