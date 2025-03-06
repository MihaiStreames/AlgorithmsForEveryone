package DataStructs.UF;

public class UnionFind {
    private final int[] parent;
    private final int[] size;

    public UnionFind(int n) {
        parent = new int[n];
        size = new int[n];

        for (int i = 0; i < n; i++) {
            parent[i] = i;
            size[i] = 1;  // Chaque ensemble commence avec une taille de 1
        }
    }

    public int find(int p) {
        while (p != parent[p])
            p = parent[p];
        return p;
    }

    public void union(int p, int q) {
        int rootP = find(p);
        int rootQ = find(q);

        if (rootP == rootQ) return;

        // Attacher l'arbre le plus petit à l'arbre le plus grand
        if (size[rootP] < size[rootQ]) {
            parent[rootP] = rootQ;
            size[rootQ] += size[rootP];
        } else {
            parent[rootQ] = rootP;
            size[rootP] += size[rootQ];
        }
    }

    public boolean connected(int p, int q) {
        return find(p) == find(q);
    }

    public int count() {
        int count = 0;
        for (int i = 0; i < parent.length; i++) {
            if (parent[i] == i) count++; // Count root elements
        }
        return count;
    }

    public int[] getParent() {
        return parent;
    }
}
