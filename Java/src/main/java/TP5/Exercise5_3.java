package TP5;

import Algorithms.Trees.RBTreeTraversal;
import DataStructs.Data.RBTreeNode;
import DataStructs.Trees.RBT;

public class Exercise5_3 {

    /**
     * Builds a Red-Black Tree containing the elements of a sorted array.
     * This implementation simply inserts the elements one by one, which is O(n log n).
     *
     * @param <T>    the type of elements in the array
     * @param sorted the sorted array of elements
     * @return a Red-Black Tree containing all elements from the array
     */
    public static <T extends Comparable<T>> RBT<T> buildRBTreeFromSortedArray(T[] sorted) {
        RBT<T> tree = new RBT<>();

        // Insert each element into the tree
        for (T item : sorted) {
            tree.insert(item);
        }

        return tree;
    }

    /**
     * An improved version that builds the tree in O(n) time by using
     * a recursive approach to create a balanced BST first.
     * This implementation is more efficient but for simplicity,
     * we're not implementing it here.
     *
     * @param <T>    the type of elements in the array
     * @param sorted the sorted array of elements
     * @return a Red-Black Tree containing all elements from the array
     */
    public static <T extends Comparable<T>> RBT<T> buildRBTreeFromSortedArrayOptimized(T[] sorted) {
        // This would involve creating a balanced BST directly and then fixing
        // the red-black properties. Left as a future exercise.
        return buildRBTreeFromSortedArray(sorted);
    }

    /**
     * Main method for testing the Red-Black Tree building algorithm.
     */
    public static void main(String[] args) {
        String[] sorted = {"A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K"};

        System.out.println("Building a Red-Black Tree from sorted array:");
        for (String s : sorted) {
            System.out.print(s + " ");
        }
        System.out.println();

        RBT<String> tree = buildRBTreeFromSortedArray(sorted);

        System.out.println("\nTree visualization:");
        for (String line : RBTreeTraversal.treeVisualization(tree.getRoot())) {
            System.out.println(line);
        }

        System.out.println("\nValidating the Red-Black Tree properties:");
        RBTreeNode<String> root = tree.getRoot();
        boolean valid = Exercise5_2.isRedBlackBST(root);
        System.out.println("Is a valid Red-Black BST: " + valid);

        System.out.println("\nTree traversals:");
        System.out.println("In-order: " + RBTreeTraversal.inOrder(root));
        System.out.println("Level-order: " + RBTreeTraversal.levelOrder(root));
    }
}