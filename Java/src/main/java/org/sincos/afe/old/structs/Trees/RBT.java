package org.sincos.afe.old.structs.Trees;

import org.sincos.afe.old.structs.Data.RBTreeNode;

/**
 * A generic Red-Black Tree implementation.
 * Red-Black Trees are self-balancing binary search trees with the following properties:
 * 1. Every node is either red or black
 * 2. The root is black
 * 3. Every leaf (null) is black
 * 4. If a node is red, then both its children are black
 * 5. For each node, all paths from the node to descendant leaves contain the same number of black nodes
 *
 * @param <T> the type of elements stored in this tree, must be comparable
 */
public class RBT<T extends Comparable<T>> {
    private RBTreeNode<T> root;
    private int size;

    /**
     * Constructs a new empty Red-Black Tree.
     */
    public RBT() {
        this.root = null;
        this.size = 0;
    }

    /**
     * Returns the number of nodes in this tree.
     *
     * @return the number of nodes in this tree
     */
    public int size() {
        return size;
    }

    /**
     * Returns true if this tree contains no nodes.
     *
     * @return true if this tree contains no nodes
     */
    public boolean isEmpty() {
        return size == 0;
    }

    /**
     * Returns the root of this tree.
     *
     * @return the root of this tree, or null if the tree is empty
     */
    public RBTreeNode<T> getRoot() {
        return root;
    }

    /**
     * Checks if a node is red.
     * Null nodes are considered black.
     *
     * @param node the node to check
     * @return true if the node is red, false otherwise
     */
    private boolean isRed(RBTreeNode<T> node) {
        return node != null && node.isRed();
    }

    /**
     * Performs a left rotation on the specified node.
     *
     * @param h the node to rotate
     * @return the new root of the rotated subtree
     */
    private RBTreeNode<T> rotateLeft(RBTreeNode<T> h) {
        if (h == null || h.getRight() == null) {
            return h; // Cannot rotate
        }

        RBTreeNode<T> x = h.getRight();
        h.setRight(x.getLeft());
        x.setLeft(h);
        x.setColor(h.getColor());
        h.setColor(RBTreeNode.RED());

        return x;
    }

    /**
     * Performs a right rotation on the specified node.
     *
     * @param h the node to rotate
     * @return the new root of the rotated subtree
     */
    private RBTreeNode<T> rotateRight(RBTreeNode<T> h) {
        if (h == null || h.getLeft() == null) {
            return h; // Cannot rotate
        }

        RBTreeNode<T> x = h.getLeft();
        h.setLeft(x.getRight());
        x.setRight(h);
        x.setColor(h.getColor());
        h.setColor(RBTreeNode.RED());

        return x;
    }

    /**
     * Flips the colors of a node and its two children.
     * The node must have two children.
     *
     * @param h the node to flip colors for
     */
    private void flipColors(RBTreeNode<T> h) {
        if (h == null) {
            return;
        }

        h.setColor(RBTreeNode.RED());

        if (h.getLeft() != null) {
            h.getLeft().setColor(RBTreeNode.BLACK());
        }

        if (h.getRight() != null) {
            h.getRight().setColor(RBTreeNode.BLACK());
        }
    }

    /**
     * Inserts the specified key into this tree.
     *
     * @param key the key to insert
     * @throws NullPointerException if the key is null
     */
    public void insert(T key) {
        if (key == null) {
            throw new NullPointerException("Cannot insert null key");
        }

        root = insert(root, key);
        root.setColor(RBTreeNode.BLACK()); // Ensure the root is black (Property 2)
    }

    /**
     * Recursive helper method to insert a key into a subtree.
     *
     * @param h   the root of the subtree
     * @param key the key to insert
     * @return the new root of the subtree
     */
    private RBTreeNode<T> insert(RBTreeNode<T> h, T key) {
        if (h == null) {
            size++;
            return new RBTreeNode<>(key); // New nodes are red by default
        }

        int cmp = key.compareTo(h.getKey());

        if (cmp < 0) {
            h.setLeft(insert(h.getLeft(), key));
        } else if (cmp > 0) {
            h.setRight(insert(h.getRight(), key));
        } else {
            // Duplicate key - we could update the value if there was one
            return h;
        }

        // Fix Red-Black Tree properties

        // Case 1: Right child is red, left child is black (or null)
        if (isRed(h.getRight()) && !isRed(h.getLeft())) {
            h = rotateLeft(h);
        }

        // Case 2: Left child and left-left grandchild are both red
        if (isRed(h.getLeft()) && h.getLeft() != null && isRed(h.getLeft().getLeft())) {
            h = rotateRight(h);
        }

        // Case 3: Both children are red
        if (isRed(h.getLeft()) && isRed(h.getRight())) {
            flipColors(h);
        }

        return h;
    }

    /**
     * Returns true if this tree contains the specified key.
     *
     * @param key the key to search for
     * @return true if this tree contains the specified key
     * @throws NullPointerException if the key is null
     */
    public boolean contains(T key) {
        if (key == null) {
            throw new NullPointerException("Cannot search for null key");
        }

        RBTreeNode<T> current = root;

        while (current != null) {
            int cmp = key.compareTo(current.getKey());

            if (cmp < 0) {
                current = current.getLeft();
            } else if (cmp > 0) {
                current = current.getRight();
            } else {
                return true; // Key found
            }
        }

        return false;
    }

    /**
     * Returns the height of this tree.
     *
     * @return the height of this tree, or -1 if the tree is empty
     */
    public int height() {
        return height(root);
    }

    /**
     * Recursive helper method to calculate the height of a subtree.
     *
     * @param node the root of the subtree
     * @return the height of the subtree, or -1 if the subtree is empty
     */
    private int height(RBTreeNode<T> node) {
        if (node == null) {
            return -1;
        }

        return 1 + Math.max(height(node.getLeft()), height(node.getRight()));
    }
}