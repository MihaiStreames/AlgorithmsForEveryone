package TP5;

import DataStructs.Data.RBTreeNode;
import DataStructs.Trees.RBT;

public class Exercise5_3 {
    /**
     * Recursive function to build a Red-Black Tree from a sorted array
     **/
    private static RBTreeNode buildFromSortedArray(int[] arr, int start, int end) {
        if (start > end) return null;
        int mid = start + (end - start) / 2;
        RBTreeNode node = new RBTreeNode(arr[mid]);
        node.left = buildFromSortedArray(arr, start, mid - 1);
        node.right = buildFromSortedArray(arr, mid + 1, end);
        node.color = false; // All nodes are initially black
        return node;
    }

    /**
     * Builds a Red-Black Tree containing the elements of a sorted array in O(n) time
     * All the nodes are initially black
     **/
    public static RBT buildRBTreeFromSortedArray(int[] sorted) {
        RBTreeNode root = buildFromSortedArray(sorted, 0, sorted.length - 1);
        if (root != null) root.color = false; // The root is always black
        RBT tree = new RBT();
        tree.setRoot(root);
        return tree;
    }
}
