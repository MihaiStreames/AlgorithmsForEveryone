package TP5;

import DataStructs.Data.RBTreeNode;

public class Exercise5_2 {

    /**
     * Checks that no node is connected to two successive red links
     * and that there is no right-leaning red link.
     *
     * @param <T> the type of keys in the tree
     * @param x   the root of the tree to check
     * @return true if the tree satisfies the property, false otherwise
     */
    public static <T extends Comparable<T>> boolean is23(RBTreeNode<T> x) {
        if (x == null) {
            return true;
        }

        // Check for right-leaning red link
        if (x.getRight() != null && x.getRight().isRed()) {
            return false;
        }

        // Check for two consecutive red links
        if (x.isRed() && x.getLeft() != null && x.getLeft().isRed()) {
            return false;
        }

        // Check subtrees recursively
        return is23(x.getLeft()) && is23(x.getRight());
    }

    /**
     * Calculates the black height of a path from the root to a leaf.
     * Returns -1 if the heights are different between the subtrees.
     *
     * @param <T> the type of keys in the tree
     * @param x   the root of the tree
     * @return the black height, or -1 if the tree is not balanced
     */
    private static <T extends Comparable<T>> int blackHeight(RBTreeNode<T> x) {
        if (x == null) {
            return 0; // Null nodes are considered black
        }

        int leftBlack = blackHeight(x.getLeft());
        int rightBlack = blackHeight(x.getRight());

        // If either subtree is not balanced, the whole tree is not balanced
        if (leftBlack == -1 || rightBlack == -1) {
            return -1;
        }

        // If the subtrees have different black heights, the tree is not balanced
        if (leftBlack != rightBlack) {
            return -1;
        }

        // Add 1 to the black height if this node is black
        return leftBlack + (x.isRed() ? 0 : 1);
    }

    /**
     * Verifies that all paths from the root to a leaf have the same black height.
     *
     * @param <T>  the type of keys in the tree
     * @param root the root of the tree
     * @return true if the tree is balanced, false otherwise
     */
    public static <T extends Comparable<T>> boolean isBalanced(RBTreeNode<T> root) {
        return blackHeight(root) != -1;
    }

    /**
     * Verifies that the tree is a binary search tree by checking
     * that for each node, all the nodes of the left subtree are less
     * than the node and all the nodes of the right subtree are greater.
     *
     * @param <T> the type of keys in the tree
     * @param x   the root of the tree
     * @param min the minimum allowed key value
     * @param max the maximum allowed key value
     * @return true if the tree is a BST, false otherwise
     */
    public static <T extends Comparable<T>> boolean isBST(RBTreeNode<T> x, T min, T max) {
        if (x == null) {
            return true;
        }

        // Check that the key is within the allowed range
        if (min != null && x.getKey().compareTo(min) <= 0) {
            return false;
        }

        if (max != null && x.getKey().compareTo(max) >= 0) {
            return false;
        }

        // Check subtrees recursively, updating the allowed range
        return isBST(x.getLeft(), min, x.getKey()) && isBST(x.getRight(), x.getKey(), max);
    }

    /**
     * Verifies that the tree is a Red-Black BST by checking all required properties:
     * 1. It is a valid BST
     * 2. It has no red right links and no two consecutive red links (2-3 property)
     * 3. All paths from root to leaf have the same black height (balanced)
     *
     * @param <T>  the type of keys in the tree
     * @param root the root of the tree
     * @return true if the tree is a valid Red-Black BST, false otherwise
     */
    public static <T extends Comparable<T>> boolean isRedBlackBST(RBTreeNode<T> root) {
        return isBST(root, null, null) && is23(root) && isBalanced(root);
    }
}