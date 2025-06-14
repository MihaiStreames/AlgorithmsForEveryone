package io.github.mihaistreames.afe.structs.nodes;

/**
 * Represents a node in a (Left-Leaning) Red-Black Tree.
 *
 * @param <T> The type of data stored in this node, expected to be comparable.
 * @author Sincos Team
 * @version 1.0.0
 * @since 0.0.2
 */
public class RedBlackNode<T> {
    public static final boolean RED = true;
    public static final boolean BLACK = false;

    public T data;
    public RedBlackNode<T> left;
    public RedBlackNode<T> right;
    public boolean color;

    /**
     * Constructs a Red-Black Node with the specified data.
     *
     * @param data The data to be stored in this node
     */
    public RedBlackNode(T data) {
        this.data = data;
        this.left = null;
        this.right = null;
        this.color = RED; // New nodes are always red by default
    }

    /**
     * Checks if this node is red.
     *
     * @return true if the node is red, false if it is black
     */
    public boolean isRed() {
        return this.color == RED;
    }

    /**
     * Checks if this node is black.
     *
     * @return true if the node is black, false if it is red
     */
    public boolean isBlack() {
        return this.color == BLACK;
    }

    @Override
    public String toString() {
        return "RedBlackNode{" +
                "data=" + data +
                ", color=" + (color == RED ? "RED" : "BLACK") +
                '}';
    }
}