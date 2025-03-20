package DataStructs.Data;

/**
 * A generic node for a Red-Black Tree.
 *
 * @param <T> the type of data stored in the node, must be comparable
 */
public class RBTreeNode<T extends Comparable<T>> {
    private static final boolean RED = true;
    private static final boolean BLACK = false;

    private final T key;
    private RBTreeNode<T> left;
    private RBTreeNode<T> right;
    private boolean color; // true for RED, false for BLACK

    /**
     * Constructs a new Red-Black Tree node with the given key.
     * New nodes are RED by default.
     *
     * @param key the data to store in this node
     */
    public RBTreeNode(T key) {
        this.key = key;
        this.left = null;
        this.right = null;
        this.color = RED;
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
    public RBTreeNode<T> getLeft() {
        return left;
    }

    /**
     * Sets the left child of this node.
     *
     * @param left the node to set as the left child
     */
    public void setLeft(RBTreeNode<T> left) {
        this.left = left;
    }

    /**
     * Gets the right child of this node.
     *
     * @return the right child, or null if there is none
     */
    public RBTreeNode<T> getRight() {
        return right;
    }

    /**
     * Sets the right child of this node.
     *
     * @param right the node to set as the right child
     */
    public void setRight(RBTreeNode<T> right) {
        this.right = right;
    }

    /**
     * Checks if this node is RED.
     *
     * @return true if this node is RED, false if BLACK
     */
    public boolean isRed() {
        return color == RED;
    }

    /**
     * Gets the color of this node.
     *
     * @return true if RED, false if BLACK
     */
    public boolean getColor() {
        return color;
    }

    /**
     * Sets the color of this node.
     *
     * @param color true for RED, false for BLACK
     */
    public void setColor(boolean color) {
        this.color = color;
    }

    /**
     * Helper method to get the RED constant.
     *
     * @return the RED color constant
     */
    public static boolean RED() {
        return RED;
    }

    /**
     * Helper method to get the BLACK constant.
     *
     * @return the BLACK color constant
     */
    public static boolean BLACK() {
        return BLACK;
    }

    @Override
    public String toString() {
        return key.toString() + "(" + (color == RED ? "R" : "B") + ")";
    }
}