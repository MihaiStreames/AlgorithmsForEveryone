package TP2;

import DataStructs.UF.UnionFind;

public class Exercise2_1 extends UnionFind {
    public Exercise2_1(int n) {
        super(n);
    }

    public static void main(String[] args) {
        Exercise2_1 uf = new Exercise2_1(10);

        uf.union(1, 2);
        uf.union(3, 4);
        uf.union(2, 3);

        System.out.println("Are 1 and 4 connected? " + uf.connected(1, 4)); // Should print true
        System.out.println("Find(3): " + uf.find(3)); // Representative of 3's set
        System.out.println("Total sets: " + uf.count()); // Should decrease after unions
    }

    // Recursive find with path compression
    @Override
    public int find(int p) {
        int[] parent = getParent();
        if (parent[p] != p) {
            parent[p] = find(parent[p]); // Recursively compress path
        }

        return parent[p];
    }
}
