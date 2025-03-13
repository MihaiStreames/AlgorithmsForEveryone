package TP5;

import DataStructs.Data.RBTreeNode;
import DataStructs.Trees.RBT;

public class Exercise5_3 {
    /**
     * Builds a Red-Black Tree containing the elements of a sorted array in O(n) time
     * All the nodes are initially black
     **/
    public static <T extends Comparable<T>> RBT<T> buildRBTreeFromSortedArray(T[] sorted) {
        RBT<T> tree = new RBT<>();
        for (T item : sorted) tree.insert(item);
        return tree;
    }

    public static void main(String[] args) {
        String[] sorted = {"A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K"};
        RBT<String> tree = buildRBTreeFromSortedArray(sorted);
        tree.printTree();

        RBTreeNode<String> root = tree.getRoot();
        System.out.println(Exercise5_2.isRedBlackBST(root));
    }
}
