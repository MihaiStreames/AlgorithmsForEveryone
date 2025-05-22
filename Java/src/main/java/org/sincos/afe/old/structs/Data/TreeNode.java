package org.sincos.afe.old.structs.Data;

/**
 * A generic node for a binary tree.
 *
 * @param <T> the type of data stored in the node, must be comparable
 */
public class TreeNode<T extends Comparable<T>> {
    private final T key;
    private TreeNode<T> left;
    private TreeNode<T> right;

    /**
     * Constructs a new tree node with the given key.
     *
     * @param key the data to store in this node
     */
    public TreeNode(T key) {
        this.key = key;
        this.left = null;
        this.right = null;
    }

    /**
     * Gets the key (data) stored in this node.
     *
     * @return the key
     */
    public T getKey() {
        return key;
    }

    /**
     * Gets the left child of this node.
     *
     * @return the left child, or null if there is none
     */
    public TreeNode<T> getLeft() {
        return left;
    }

    /**
     * Sets the left child of this node.
     *
     * @param left the node to set as the left child
     */
    public void setLeft(TreeNode<T> left) {
        this.left = left;
    }

    /**
     * Gets the right child of this node.
     *
     * @return the right child, or null if there is none
     */
    public TreeNode<T> getRight() {
        return right;
    }

    /**
     * Sets the right child of this node.
     *
     * @param right the node to set as the right child
     */
    public void setRight(TreeNode<T> right) {
        this.right = right;
    }

    @Override
    public String toString() {
        return key.toString();
    }
}