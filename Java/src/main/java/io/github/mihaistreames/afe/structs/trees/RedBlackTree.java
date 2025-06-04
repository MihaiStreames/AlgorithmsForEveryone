package io.github.mihaistreames.afe.structs.trees;

import io.github.mihaistreames.afe.structs.nodes.RedBlackNode;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Comparator;
import java.util.Objects;

/**
 * Implementation of a (Left-Leaning) Red-Black Tree.
 * <p>
 * This is a simplified variant of Red-Black trees that maintains the following properties:
 * <ol>
 * <li>Red links lean left (no right-leaning red links allowed)</li>
 * <li>No node has two red links connected to it</li>
 * <li>The tree has perfect black balance</li>
 * </ol>
 * </p>
 * <p>
 * <strong>Time Complexity:</strong> O(log n) for search, insert, and delete<br>
 * <strong>Space Complexity:</strong> O(n)<br>
 * <strong>Use Case:</strong> When you need guaranteed logarithmic time operations
 * </p>
 *
 * @param <T> The type of data stored in the tree
 * @author Sincos Team
 * @version 1.0.0
 * @since 0.0.2
 */
public class RedBlackTree<T> {

    private RedBlackNode<T> root;
    private final Comparator<T> comparator; // To compare elements

    /**
     * Constructs a (Left-Leaning) Red-Black Tree using natural ordering.
     * <p>
     * The type T must implement {@link Comparable}.
     * </p>
     */
    @SuppressWarnings("unchecked")
    public RedBlackTree() {
        this.root = null;
        this.comparator = (o1, o2) -> ((Comparable<T>) o1).compareTo(o2);
    }

    /**
     * Constructs a (Left-Leaning) Red-Black Tree using the provided comparator.
     * <p>
     * This constructor allows for custom ordering of elements.
     * </p>
     *
     * @param comparator The comparator to use for ordering elements
     * @throws NullPointerException if the comparator is null
     */
    public RedBlackTree(@NotNull final Comparator<T> comparator) {
        this.root = null;
        this.comparator = Objects.requireNonNull(comparator, "Comparator cannot be null");
    }

    // ========== PUBLIC API ==========

    /**
     * Inserts the specified element into this tree.
     * <p>
     * If the element already exists, this method does nothing.
     * The insertion maintains the Left-Leaning Red-Black tree properties.
     * </p>
     *
     * @param data the element to insert
     * @throws NullPointerException if the data is null
     */
    public void insert(@NotNull final T data) {
        Objects.requireNonNull(data, "Data cannot be null");
        root = put(root, data);
        root.color = RedBlackNode.BLACK; // Root is always black
    }

    // ========== PRIVATE HELPER METHODS ==========

    /**
     * Recursive helper method for insertion.
     * <p>
     * This follows the Left-Leaning Red-Black tree insertion algorithm:
     * <ol>
     * <li>Standard BST insertion (new nodes are red)</li>
     * <li>Rotate left if right child is red and left child is black</li>
     * <li>Rotate right if left child and left-left grandchild are both red</li>
     * <li>Flip colors if both children are red</li>
     * </ol>
     * </p>
     *
     * @param node the current node (subtree root)
     * @param data the data to insert
     * @return the new subtree root after insertion and rebalancing
     */
    private @NotNull RedBlackNode<T> put(@Nullable RedBlackNode<T> node, @NotNull final T data) {
        // Base case: create new red node
        if (node == null) return new RedBlackNode<>(data);

        // Standard BST insertion
        final int comparison = comparator.compare(data, node.data);
        if (comparison < 0) {
            node.left = put(node.left, data);
        } else if (comparison > 0) {
            node.right = put(node.right, data);
        } else {
            // Key already exists - do nothing
            return node;
        }

        // LLRB tree balancing operations
        // 1. Rotate left if right child is red and left child is black
        if (isRed(node.right) && !isRed(node.left)) {
            node = rotateLeft(node);
        }

        // 2. Rotate right if left child and left-left grandchild are both red
        if (isRed(node.left) && isRed(node.left.left)) {
            node = rotateRight(node);
        }

        // 3. Flip colors if both children are red
        if (isRed(node.left) && isRed(node.right)) {
            flipColors(node);
        }

        return node;
    }

    /**
     * Rotates the given node to the left.
     *
     * @param node the node to rotate
     * @return the new root of this subtree (previously the right child)
     */
    @NotNull
    private RedBlackNode<T> rotateLeft(@NotNull final RedBlackNode<T> node) {
        final RedBlackNode<T> rightChild = node.right;
        node.right = rightChild.left;
        rightChild.left = node;
        rightChild.color = rightChild.left.color;
        rightChild.left.color = RedBlackNode.RED;
        return rightChild;
    }

    /**
     * Rotates the given node to the right.
     *
     * @param node the node to rotate
     * @return the new root of this subtree (previously the left child)
     */
    @NotNull
    private RedBlackNode<T> rotateRight(@NotNull final RedBlackNode<T> node) {
        final RedBlackNode<T> leftChild = node.left;
        node.left = leftChild.right;
        leftChild.right = node;
        leftChild.color = leftChild.right.color;
        leftChild.right.color = RedBlackNode.RED;
        return leftChild;
    }

    /**
     * Flips the colors of a node and its two children.
     *
     * @param node the node whose colors (and children's colors) to flip
     */
    private void flipColors(@NotNull final RedBlackNode<T> node) {
        node.color = RedBlackNode.RED;
        if (node.left != null) node.left.color = RedBlackNode.BLACK;
        if (node.right != null) node.right.color = RedBlackNode.BLACK;
    }

    /**
     * Checks if a node is red.
     * <p>
     * Null nodes are considered black by convention.
     * </p>
     *
     * @param node the node to check
     * @return true if the node is red, false if black or null
     */
    private boolean isRed(@Nullable final RedBlackNode<T> node) {
        if (node == null) return false; // Null nodes are black
        return node.isRed();
    }
}