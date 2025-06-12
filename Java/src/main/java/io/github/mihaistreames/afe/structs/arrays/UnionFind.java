package io.github.mihaistreames.afe.structs.arrays;

import org.jetbrains.annotations.NotNull;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

/**
 * A generic Union-Find (Disjoint Set) data structure.
 * This structure manages a collection of disjoint sets and supports operations
 * to merge them and find which set an element belongs to.
 *
 * @param <T> The type of elements in the sets.
 */
public class UnionFind<T> {
    private final Map<T, T> parent;       // parent[i] = parent of i
    private final Map<T, Integer> height; // height[i] = height of subtree rooted at i
    private int count;                    // number of components

    /**
     * Initializes a UnionFind structure with elements, each in its own set.
     *
     * @param elements The initial collection of elements.
     */
    public UnionFind(@NotNull Collection<T> elements) {
        parent = new HashMap<>();
        height = new HashMap<>();
        for (T element : elements) {
            parent.put(element, element);
            height.put(element, 1);
        }
        this.count = elements.size();
    }

    /**
     * @return the number of disjoint sets.
     */
    public int count() {
        return count;
    }

    /**
     * Validates that the element exists in the structure.
     *
     * @param p the element to validate.
     */
    private void validate(T p) {
        if (!parent.containsKey(p)) {
            throw new IllegalArgumentException("Element " + p + " is not in the set.");
        }
    }

    /**
     * Finds the representative of the set containing p (without path compression).
     *
     * @param p the element.
     * @return the representative of the set containing p.
     */
    public T find(T p) {
        validate(p);
        T current = p;
        while (!current.equals(parent.get(current))) {
            current = parent.get(current);
        }
        return current;
    }

    /**
     * Finds the representative of the set containing p using iterative path compression.
     *
     * @param p the element.
     * @return the representative of the set containing p.
     */
    public T compressedFind(T p) {
        validate(p);
        T root = find(p); // Find the root first
        // Path compression
        T current = p;
        while (!current.equals(root)) {
            T next = parent.get(current);
            parent.put(current, root);
            current = next;
        }
        return root;
    }

    /**
     * Finds the representative of the set containing p using recursive path compression.
     *
     * @param p the element.
     * @return the representative of the set containing p.
     */
    public T recursiveCompressedFind(T p) {
        validate(p);
        if (p.equals(parent.get(p))) {
            return p;
        }
        T root = recursiveCompressedFind(parent.get(p));
        parent.put(p, root); // Path compression
        return root;
    }

    /**
     * Merges the sets containing p and q using a naive, inefficient approach.
     *
     * @param p an element in the first set.
     * @param q an element in the second set.
     */
    public void naiveUnion(T p, T q) {
        validate(p);
        validate(q);
        T pID = find(p);
        T qID = find(q);

        if (pID.equals(qID)) return;

        // Inefficiently connect p's component to q's component
        for (T key : parent.keySet()) {
            if (parent.get(key).equals(pID)) {
                parent.put(key, qID);
            }
        }
        count--;
    }

    /**
     * Merges the sets containing p and q (quick union).
     *
     * @param p an element in the first set.
     * @param q an element in the second set.
     */
    public void quickUnion(T p, T q) {
        validate(p);
        validate(q);
        T pRoot = find(p);
        T qRoot = find(q);

        if (pRoot.equals(qRoot)) return;

        parent.put(pRoot, qRoot);
        count--;
    }

    /**
     * Merges the sets containing p and q using a weighted strategy (union by height/rank).
     *
     * @param p an element in the first set.
     * @param q an element in the second set.
     */
    public void weightedQuickUnion(T p, T q) {
        validate(p);
        validate(q);
        // Use a find that does path compression for best performance
        T rootP = compressedFind(p);
        T rootQ = compressedFind(q);

        if (rootP.equals(rootQ)) return;

        int heightP = height.get(rootP);
        int heightQ = height.get(rootQ);

        // Attach smaller tree under root of taller tree
        if (heightP < heightQ) {
            parent.put(rootP, rootQ);
        } else if (heightP > heightQ) {
            parent.put(rootQ, rootP);
        } else {
            parent.put(rootQ, rootP);
            height.put(rootP, heightP + 1);
        }
        count--;
    }
}