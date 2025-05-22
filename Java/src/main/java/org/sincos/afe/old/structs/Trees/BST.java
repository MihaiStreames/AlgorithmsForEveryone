package org.sincos.afe.old.structs.Trees;

import org.sincos.afe.old.structs.Data.TreeNode;

/**
 * A generic Binary Search Tree implementation.
 *
 * @param <T> the type of elements stored in this tree, must be comparable
 */
public class BST<T extends Comparable<T>> {
    private TreeNode<T> root;
    private int size;

    /**
     * Constructs a new empty binary search tree.
     */
    public BST() {
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
    public TreeNode<T> getRoot() {
        return root;
    }

    /**
     * Inserts the specified key into this tree.
     *
     * @param key the key to insert
     */
    public void insert(T key) {
        if (key == null) {
            throw new NullPointerException("Cannot insert null key");
        }

        root = insert(root, key);
        size++;
    }

    /**
     * Recursive helper method to insert a key into a subtree.
     *
     * @param node the root of the subtree
     * @param key  the key to insert
     * @return the new root of the subtree
     */
    private TreeNode<T> insert(TreeNode<T> node, T key) {
        if (node == null) {
            return new TreeNode<>(key);
        }

        int cmp = key.compareTo(node.getKey());

        if (cmp < 0) {
            node.setLeft(insert(node.getLeft(), key));
        } else if (cmp > 0) {
            node.setRight(insert(node.getRight(), key));
        } else {
            // Duplicate key, do nothing
            size--; // Ensure size remains accurate
        }

        return node;
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

        return contains(root, key);
    }

    /**
     * Recursive helper method to check if a subtree contains a key.
     *
     * @param node the root of the subtree
     * @param key  the key to search for
     * @return true if the subtree contains the key
     */
    private boolean contains(TreeNode<T> node, T key) {
        if (node == null) {
            return false;
        }

        int cmp = key.compareTo(node.getKey());

        if (cmp < 0) {
            return contains(node.getLeft(), key);
        } else if (cmp > 0) {
            return contains(node.getRight(), key);
        } else {
            return true; // Key found
        }
    }

    /**
     * Removes the specified key from this tree.
     *
     * @param key the key to remove
     * @return true if the key was found and removed
     * @throws NullPointerException if the key is null
     */
    public boolean remove(T key) {
        if (key == null) {
            throw new NullPointerException("Cannot remove null key");
        }

        int initialSize = size;
        root = remove(root, key);

        return size < initialSize;
    }

    /**
     * Recursive helper method to remove a key from a subtree.
     *
     * @param node the root of the subtree
     * @param key  the key to remove
     * @return the new root of the subtree
     */
    private TreeNode<T> remove(TreeNode<T> node, T key) {
        if (node == null) {
            return null;
        }

        int cmp = key.compareTo(node.getKey());

        if (cmp < 0) {
            node.setLeft(remove(node.getLeft(), key));
        } else if (cmp > 0) {
            node.setRight(remove(node.getRight(), key));
        } else {
            // Node with key found
            size--;

            // Case 1: Node with no children or one child
            if (node.getLeft() == null) {
                return node.getRight();
            } else if (node.getRight() == null) {
                return node.getLeft();
            }

            // Case 2: Node with two children
            // Find the smallest node in the right subtree (successor)
            TreeNode<T> successor = findMin(node.getRight());

            // Replace this node's key with the successor's key
            TreeNode<T> newNode = new TreeNode<>(successor.getKey());
            newNode.setLeft(node.getLeft());

            // Remove the successor from the right subtree
            newNode.setRight(removeMin(node.getRight()));

            return newNode;
        }

        return node;
    }

    /**
     * Finds the node with the minimum key in a subtree.
     *
     * @param node the root of the subtree
     * @return the node with the minimum key
     */
    private TreeNode<T> findMin(TreeNode<T> node) {
        if (node == null) {
            return null;
        }

        while (node.getLeft() != null) {
            node = node.getLeft();
        }

        return node;
    }

    /**
     * Removes the node with the minimum key from a subtree.
     *
     * @param node the root of the subtree
     * @return the new root of the subtree
     */
    private TreeNode<T> removeMin(TreeNode<T> node) {
        if (node == null) {
            return null;
        }

        if (node.getLeft() == null) {
            return node.getRight();
        }

        node.setLeft(removeMin(node.getLeft()));
        return node;
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
    private int height(TreeNode<T> node) {
        if (node == null) {
            return -1;
        }

        return 1 + Math.max(height(node.getLeft()), height(node.getRight()));
    }
}