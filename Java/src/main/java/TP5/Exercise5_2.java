package TP5;

import DataStructs.Data.RBTreeNode;

public class Exercise5_2 {
    /**
     * Checks that no node is connected to two successive red links
     * and that there is no right-leaning red link.
     */
    public static boolean is23(RBTreeNode x) {
        if (x == null) return true;
        if (x.right != null && x.right.color) return false;
        if (x.color && x.left != null && x.left.color) return false;
        return is23(x.left) && is23(x.right);
    }

    /**
     * Calculates the black height of a path from the root to a leaf.
     * Returns -1 if the heights are different in between the subtrees.
     */
    private static int blackHeight(RBTreeNode x) {
        if (x == null) return 0;
        int leftBlack = blackHeight(x.left);
        int rightBlack = blackHeight(x.right);
        if (leftBlack == -1 || rightBlack == -1) return -1;
        if (leftBlack != rightBlack) return -1;
        return leftBlack + (!x.color ? 1 : 0);
    }

    /**
     * Verifies that all paths from the root to a leaf have the same black height.
     */
    public static boolean isBalanced(RBTreeNode root) {
        return blackHeight(root) != -1;
    }

    /**
     * Verifies that the tree is a binary search tree by checking
     * that for each node, all the nodes of the left subtree are less
     * than the node and all the nodes of the right subtree are greater.
     */
    public static boolean isBST(RBTreeNode x, int min, int max) {
        if (x == null) return true;
        if (x.key <= min || x.key >= max) return false;
        return isBST(x.left, min, x.key) && isBST(x.right, x.key, max);
    }

    /**
     * Verifies that the tree is a BST, then that it satisfies the
     * red-black properties: no red link to the right,
     * no two successive red links, and black height balance.
     */
    public static boolean isRedBlackBST(RBTreeNode root) {
        return isBST(root, Integer.MIN_VALUE, Integer.MAX_VALUE) && is23(root) && isBalanced(root);
    }
}
