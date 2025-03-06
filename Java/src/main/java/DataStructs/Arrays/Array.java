package DataStructs.Arrays;

public class Array {
    private final int[] a;
    private final int n;

    public Array(int[] a) {
        this.a = a;
        n = a.length;
    }

    public int get(int i) {
        return a[i];
    }

    public int length() {
        return n;
    }

    public void set(int i, int x) {
        a[i] = x;
    }

    public void print() {
        for (int i = 0; i < n; i++) {
            System.out.print(a[i] + " ");
        }
        System.out.println();
    }
}
