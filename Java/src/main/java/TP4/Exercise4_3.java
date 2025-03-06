package TP4;

public class Exercise4_3 {
    public static boolean isHeap(int[] a) {
        int n = a.length;
        for (int i = 0; i < n; i++) {
            int left = 2 * i + 1;
            int right = 2 * i + 2;
            if (left < n && a[i] > a[left]) {
                return false;
            }
            if (right < n && a[i] > a[right]) {
                return false;
            }
        }
        return true;
    }

    public static void main(String[] args) {
        int[] a = {1, 2, 3, 4, 5, 6, 7, 8, 9};
        System.out.println(isHeap(a)); // true
        int[] b = {1, 2, 3, 4, 5, 6, 9, 8, 7};
        System.out.println(isHeap(b)); // false
    }
}