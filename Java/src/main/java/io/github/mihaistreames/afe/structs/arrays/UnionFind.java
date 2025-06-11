package io.github.mihaistreames.afe.structs.arrays;

/**
 * Implementation of a UnionFind structure.
 * <p>
 * This structure is used to efficiently manage and query connected components in a set.
 * It supports three union operations:
 * <ol>
 *     <li>Naive Union</li>
 *     <li>Quick Union</li>
 *     <li>Weighted Quick Union</li>
 * </ol>
 * The structure also supports path compression to optimize the find operation.<br>
 * <ul>
 *     <li><strong>Time Complexity:</strong> O(log n) for union and find operations with path compression</li>
 *     <li><strong>Time Complexity:</strong> O(n) for naive union</li>
 *     <li><strong>Space Complexity:</strong> O(n)</li>
 * </ul>
 * </p>
 */
public class UnionFind {
    private final int[] ids;
    private final int[] heights; // for weighted quick union
    private final int size;
    private int count;

    /**
     * Constructs an UnionFind structure with n elements.
     *
     * @param n the number of elements in the UnionFind structure
     * @throws IllegalArgumentException if n is less than or equal to 0
     */
    public UnionFind(int n) {
        ids = new int[n];
        heights = new int[n]; // initialize heights for weighted quick union
        count = size = n;

        for (int i = 0; i < n; i++) {
            ids[i] = i;
            heights[i] = 1; // initialize heights to 1
        }
    }

    /**
     * Does a naive union of two elements p and q.
     *
     * @param p the first element
     * @param q the second element
     * @throws IllegalArgumentException if p or q is out of bounds
     */
    public void naiveUnion(int p, int q) {
        validateIndex(p);
        validateIndex(q);

        int pID = ids[p];
        int qID = ids[q];

        if (pID == qID) {
            return; // already connected
        }
        for (int i = 0; i < size; i++) {
            if (ids[i] == pID) {
                ids[i] = qID; // connect p's component to q's component
                count--;
                break;
            }
        }
    }

    /**
     * Does a quick union of two elements p and q.
     *
     * @param p the first element
     * @param q the second element
     * @throws IllegalArgumentException if p or q is out of bounds
     */
    public void quickUnion(int p, int q) {
        validateIndex(p);
        validateIndex(q);

        int pRoot = find(p);
        int qRoot = find(q);
        if (pRoot == qRoot) {
            return; // already connected
        }
        ids[pRoot] = qRoot; // connect p's root to q's root
    }

    /**
     * Does a weighted quick union of two elements p and q.
     *
     * @param p the first element
     * @param q the second element
     * @throws IllegalArgumentException if p or q is out of bounds
     */
    public void weightedQuickUnion(int p, int q) {
        validateIndex(p);
        validateIndex(q);

        int pRoot = compressedFind(p);
        int qRoot = compressedFind(q);
        if (pRoot == qRoot) {
            return; // already connected
        }

        if (heights[pRoot] < heights[qRoot]) {
            ids[pRoot] = qRoot; // connect smaller tree to larger tree
        } else if (heights[pRoot] > heights[qRoot]) {
            ids[qRoot] = pRoot; // connect smaller tree to larger tree
        } else {
            ids[qRoot] = pRoot; // connect q's root to p's root
            heights[pRoot]++; // increase height of the new root
        }
        count--; // decrease the number of components
    }

    /**
     * Finds the root of the component containing element p.
     *
     * @param p the first element
     * @return the root of the component containing element p
     * @throws IllegalArgumentException if p or q is out of bounds
     */
    public int find(int p) {
        validateIndex(p);

        while (p != ids[p]) {
            p = ids[p]; // follow the chain to find the root
        }
        return p;
    }

    /**
     * Compressed find operation that uses path compression to flatten the structure.
     *
     * @param p the element to find
     * @return the root of the component containing element p
     * @throws IllegalArgumentException if p is out of bounds
     */
    public int compressedFind(int p) {
        validateIndex(p);

        int root = p;
        while (root != ids[root]) {
            root = ids[root]; // find the root
        }
        // path compression
        while (p != root) {
            int next = ids[p];
            ids[p] = root; // point directly to the root
            p = next;
        }
        return root;
    }

    /**
     * Recursive compressed find operation that uses path compression to flatten the structure.
     *
     * @param p the element to find
     * @return the root of the component containing element p
     * @throws IllegalArgumentException if p is out of bounds
     */
    public int recursiveCompressedFind(int p) {
        validateIndex(p);

        if (p != ids[p]) {
            ids[p] = recursiveCompressedFind(ids[p]); // path compression
        }
        return ids[p]; // return the root
    }

    /**
     * @return the number of sets in the UnionFind structure
     */
    public int count() {
        return count;
    }

    // ========== PRIVATE HELPER METHODS ==========

    /**
     * Checks if an index is within the valid range.
     *
     * @param index the index to validate
     */
    private void validateIndex(int index) {
        if (index < 0 || index >= size) {
            throw new IllegalArgumentException("Index out of bounds");
        }
    }
}
