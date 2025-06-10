package io.github.mihaistreames.afe.structs.arrays;

public class UnionFind {
    private final int[] ids;
    private final int[] heights; // for weighted quick union
    private final int size;
    private int count;

    public UnionFind(int n) {
        ids = new int[n];
        heights = new int[n]; // initialize heights for weighted quick union
        count = size = n;

        for (int i = 0; i < n; i++) {
            ids[i] = i;
            heights[i] = 1; // initialize heights to 1
        }
    }

    public void naiveUnion (int p, int q) {
        if (p < 0 || p >= size || q < 0 || q >= size) {
            throw new IllegalArgumentException("Index out of bounds");
        }
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

    public void quickUnion (int p, int q) {
        if (p < 0 || p >= size || q < 0 || q >= size) {
            throw new IllegalArgumentException("Index out of bounds");
        }
        int pRoot = find(p);
        int qRoot = find(q);
        if (pRoot == qRoot) {
            return; // already connected
        }
        ids[pRoot] = qRoot; // connect p's root to q's root
    }

    public void weightedQuickUnion (int p, int q) {
        if (p < 0 || p >= size || q < 0 || q >= size) {
            throw new IllegalArgumentException("Index out of bounds");
        }
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

    public int find (int p) {
        if (p < 0 || p >= size) {
            throw new IllegalArgumentException("Index out of bounds");
        }
        while (p != ids[p]) {
            p = ids[p]; // follow the chain to find the root
        }
        return p;
    }

    public int compressedFind(int p) {
        if (p < 0 || p >= size) {
            throw new IllegalArgumentException("Index out of bounds");
        }
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

    public int recursiveCompressedFind(int p) {
        if (p < 0 || p >= size) {
            throw new IllegalArgumentException("Index out of bounds");
        }
        if (p != ids[p]) {
            ids[p] = recursiveCompressedFind(ids[p]); // path compression
        }

        return ids[p]; // return the root
    }

    public int count (){
        return count;
    }
}
