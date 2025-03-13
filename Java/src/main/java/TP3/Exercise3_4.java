package TP3;

import DataStructs.Lists.LinkedList;

public class Exercise3_4 {
    public static LinkedList mergeThreeSortedArrays(LinkedList a, LinkedList b, LinkedList c) {
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
        return new LinkedList(d);
    }

    public static void main(String[] args) {
        LinkedList a = new LinkedList(new int[]{1, 3, 5});
        LinkedList b = new LinkedList(new int[]{2, 4, 6});
        LinkedList c = new LinkedList(new int[]{0, 7, 8});
        LinkedList d = mergeThreeSortedArrays(a, b, c);
        d.print(); // 0 1 2 3 4 5 6 7 8
    }
}
