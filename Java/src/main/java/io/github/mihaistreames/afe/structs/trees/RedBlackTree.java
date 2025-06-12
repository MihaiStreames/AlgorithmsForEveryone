package io.github.mihaistreames.afe.structs.trees;

import io.github.mihaistreames.afe.structs.nodes.RedBlackNode;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;

/**
 * A generic, self-balancing binary search tree.
 * This implementation stores key-value pairs and guarantees O(log n) time for search, insert, and delete operations.
 *
 * @param <Key>   The type of the key, must be comparable.
 * @param <Value> The type of the value.
 */
public class RedBlackTree<Key extends Comparable<Key>, Value> {

    private static final boolean RED = true;
    private static final boolean BLACK = false;

    private RedBlackNode<Key, Value> root;

    /**
     * Checks if the given node is red.
     *
     * @param x The node to check.
     * @return true if the node is red, false otherwise.
     */
    private boolean isRed(RedBlackNode<Key, Value> x) {
        if (x == null) return false;
        return x.color == RED;
    }

    /**
     * Returns the size of the subtree rooted at the given node.
     *
     * @param x The root of the subtree.
     * @return The number of nodes in the subtree.
     */
    private int size(RedBlackNode<Key, Value> x) {
        if (x == null) return 0;
        return x.size;
    }

    /**
     * Returns the total number of key-value pairs in the tree.
     *
     * @return The size of the tree.
     */
    public int size() {
        return size(root);
    }

    /**
     * Checks if the tree is empty.
     *
     * @return true if the tree is empty, false otherwise.
     */
    public boolean isEmpty() {
        return root == null;
    }

    /**
     * Retrieves the value associated with the given key.
     *
     * @param key The key to search for.
     * @return The value associated with the key, or null if the key is not found.
     */
    public Value get(Key key) {
        if (key == null) throw new IllegalArgumentException("Key cannot be null");
        return get(root, key);
    }

    private Value get(RedBlackNode<Key, Value> x, Key key) {
        while (x != null) {
            int cmp = key.compareTo(x.key);
            if (cmp < 0) x = x.left;
            else if (cmp > 0) x = x.right;
            else return x.value;
        }
        return null;
    }

    /**
     * Checks if the tree contains the given key.
     *
     * @param key The key to search for.
     * @return true if the key exists, false otherwise.
     */
    public boolean contains(Key key) {
        return get(key) != null;
    }

    /**
     * Inserts a key-value pair into the tree.
     *
     * @param key   The key.
     * @param value The value.
     */
    public void put(Key key, Value value) {
        if (key == null) throw new IllegalArgumentException("Key cannot be null");
        if (value == null) {
            // delete(key); // Optional: handle null value as deletion
            return;
        }
        root = put(root, key, value);
        root.color = BLACK;
    }

    @Contract("null, _, _ -> new")
    private @NotNull RedBlackNode<Key, Value> put(RedBlackNode<Key, Value> h, Key key, Value val) {
        if (h == null) return new RedBlackNode<>(key, val, RED, 1);

        int cmp = key.compareTo(h.key);
        if (cmp < 0) h.left = put(h.left, key, val);
        else if (cmp > 0) h.right = put(h.right, key, val);
        else h.value = val;

        // Balance the tree
        if (isRed(h.right) && !isRed(h.left)) h = rotateLeft(h);
        if (isRed(h.left) && isRed(h.left.left)) h = rotateRight(h);
        if (isRed(h.left) && isRed(h.right)) flipColors(h);
        h.size = size(h.left) + size(h.right) + 1;

        return h;
    }


    // --- Helper methods for balancing ---

    @NotNull
    private RedBlackNode<Key, Value> rotateRight(@NotNull RedBlackNode<Key, Value> h) {
        RedBlackNode<Key, Value> x = h.left;
        h.left = x.right;
        x.right = h;
        x.color = h.color;
        h.color = RED;
        x.size = h.size;
        h.size = size(h.left) + size(h.right) + 1;
        return x;
    }

    @NotNull
    private RedBlackNode<Key, Value> rotateLeft(@NotNull RedBlackNode<Key, Value> h) {
        RedBlackNode<Key, Value> x = h.right;
        h.right = x.left;
        x.left = h;
        x.color = h.color;
        h.color = RED;
        x.size = h.size;
        h.size = size(h.left) + size(h.right) + 1;
        return x;
    }

    private void flipColors(@NotNull RedBlackNode<Key, Value> h) {
        h.color = !h.color;
        h.left.color = !h.left.color;
        h.right.color = !h.right.color;
    }
}