package DataStructs.Heaps;

public class Heap {
    private final int[] a;
    private final boolean isMaxHeap;
    private int n;

    public Heap(boolean isMaxHeap) {
        a = new int[100];
        n = 0;
        this.isMaxHeap = isMaxHeap;
    }

    public int size() {
        return n;
    }

    public int peek() {
        return a[0];
    }

    public void insert(int x) {
        a[n++] = x;
        int i = n - 1;
        while (i > 0) {
            int p = (i - 1) / 2;
            if (isMaxHeap && a[p] >= a[i]) {
                break;
            }
            if (!isMaxHeap && a[p] <= a[i]) {
                break;
            }
            int temp = a[i];
            a[i] = a[p];
            a[p] = temp;
            i = p;
        }
    }

    public int extract() {
        int x = a[0];
        a[0] = a[--n];
        int i = 0;
        while (true) {
            int left = 2 * i + 1;
            int right = 2 * i + 2;
            if (left >= n) {
                break;
            }
            int j = left;
            if (right < n && ((isMaxHeap && a[right] > a[left]) || (!isMaxHeap && a[right] < a[left]))) {
                j = right;
            }
            if ((isMaxHeap && a[i] >= a[j]) || (!isMaxHeap && a[i] <= a[j])) {
                break;
            }
            int temp = a[i];
            a[i] = a[j];
            a[j] = temp;
            i = j;
        }
        return x;
    }
}
