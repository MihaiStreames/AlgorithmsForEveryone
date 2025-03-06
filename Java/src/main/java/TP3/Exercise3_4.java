package TP3;

import DataStructs.Arrays.Array;

public class Exercise3_4 {
    public static Array mergeThreeSortedArrays(Array a, Array b, Array c) {
        int n = a.length();
        int[] d = new int[3 * n];
        int i = 0, j = 0, k = 0, idx = 0;

        while (i < n || j < n || k < n) {
            int minA = (i < n) ? a.get(i) : Integer.MAX_VALUE;
            int minB = (j < n) ? b.get(j) : Integer.MAX_VALUE;
            int minC = (k < n) ? c.get(k) : Integer.MAX_VALUE;

            if (minA <= minB && minA <= minC) {
                d[idx++] = a.get(i++);
            } else if (minB <= minA && minB <= minC) {
                d[idx++] = b.get(j++);
            } else {
                d[idx++] = c.get(k++);
            }
        }
        return new Array(d);
    }

    public static void main(String[] args) {
        Array a = new Array(new int[]{1, 3, 5});
        Array b = new Array(new int[]{2, 4, 6});
        Array c = new Array(new int[]{0, 7, 8});
        Array d = mergeThreeSortedArrays(a, b, c);
        d.print(); // 0 1 2 3 4 5 6 7 8
    }
}
