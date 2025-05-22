package org.sincos.afe.old.structs.UF;

/**
 * An Union-Find (Disjoint-Set) data structure implementation with path compression and union by size.
 * This data structure efficiently tracks a collection of disjoint sets and supports
 * two operations:
 * - Find: Determine which set an element belongs to
 * - Union: Merge two sets
 */
public class UnionFind {
    private final int[] parent;  // parent[i] = parent of element i
    private final int[] size;    // size[i] = size of set with root i
    private int count;           // number of disjoint sets

    /**
     * Initializes an Union-Find data structure with n elements,
     * initially each element is in its own set.
     *
     * @param n the number of elements
     * @throws IllegalArgumentException if n is negative
     */
    public UnionFind(int n) {
        if (n < 0) {
            throw new IllegalArgumentException("Number of elements cannot be negative");
        }

        parent = new int[n];
        size = new int[n];
        count = n;

        for (int i = 0; i < n; i++) {
            parent[i] = i;    // Each element is its own parent
            size[i] = 1;      // Each set initially has size 1
        }
    }

    /**
     * Returns the canonical element (root) of the set containing element p.
     *
     * @param p the element to find
     * @return the canonical element of the set containing p
     * @throws IllegalArgumentException if p is out of bounds
     */
    public int find(int p) {
        // Check that p is within bounds
        validate(p);

        // Path compression: Make each examined node point directly to the root
        int root = p;
        while (root != parent[root]) {
            root = parent[root];
        }

        // Make each node on the path from p to root point directly to the root
        while (p != root) {
            int newParent = parent[p];
            parent[p] = root;
            p = newParent;
        }

        return root;
    }

    public int count() {
        return count;
    }

    /**
     * Returns true if the elements p and q are in the same set.
     *
     * @param p the first element
     * @param q the second element
     * @return true if p and q are in the same set, false otherwise
     * @throws IllegalArgumentException if p or q is out of bounds
     */
    public boolean connected(int p, int q) {
        return find(p) == find(q);
    }

    /**
     * Merges the set containing element p with the set containing element q.
     *
     * @param p the first element
     * @param q the second element
     * @throws IllegalArgumentException if p or q is out of bounds
     */
    public void union(int p, int q) {
        int rootP = find(p);
        int rootQ = find(q);

        if (rootP == rootQ) {
            return; // Already in the same set
        }

        // Make smaller root point to larger one (union by size)
        if (size[rootP] < size[rootQ]) {
            parent[rootP] = rootQ;
            size[rootQ] += size[rootP];
        } else {
            parent[rootQ] = rootP;
            size[rootP] += size[rootQ];
        }

        count--; // Reduce the number of disjoint sets
    }

    public int sizeOf(int p) {
        return size[find(p)];
    }

    private void validate(int p) {
        int n = parent.length;
        if (p < 0 || p >= n) {
            throw new IllegalArgumentException("Index " + p + " is not between 0 and " + (n - 1));
        }
    }

    public int[] getParent() {
        int[] copy = new int[parent.length];
        System.arraycopy(parent, 0, copy, 0, parent.length);
        return copy;
    }

    public int[] getSize() {
        int[] copy = new int[size.length];
        System.arraycopy(size, 0, copy, 0, size.length);
        return copy;
    }
}