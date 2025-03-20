package Algorithms.Trees;

import DataStructs.Data.TreeNode;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.function.Consumer;

/**
 * A utility class that provides various tree traversal algorithms.
 */
public class TreeTraversal {

    /**
     * Private constructor to prevent instantiation.
     */
    private TreeTraversal() {
        // Utility class should not be instantiated
    }

    /**
     * Performs an in-order traversal on the given tree and returns the keys in order.
     *
     * @param <T>  the type of elements in the tree
     * @param root the root of the tree
     * @return a list of keys in in-order
     */
    public static <T extends Comparable<T>> List<T> inOrder(TreeNode<T> root) {
        List<T> result = new ArrayList<>();
        inOrderHelper(root, result::add);
        return result;
    }

    /**
     * Helper method for in-order traversal.
     *
     * @param <T>      the type of elements in the tree
     * @param node     the current node
     * @param consumer consumer function to process each key
     */
    public static <T extends Comparable<T>> void inOrderHelper(TreeNode<T> node, Consumer<T> consumer) {
        if (node != null) {
            inOrderHelper(node.getLeft(), consumer);
            consumer.accept(node.getKey());
            inOrderHelper(node.getRight(), consumer);
        }
    }

    /**
     * Performs a pre-order traversal on the given tree and returns the keys in order.
     *
     * @param <T>  the type of elements in the tree
     * @param root the root of the tree
     * @return a list of keys in pre-order
     */
    public static <T extends Comparable<T>> List<T> preOrder(TreeNode<T> root) {
        List<T> result = new ArrayList<>();
        preOrderHelper(root, result::add);
        return result;
    }

    /**
     * Helper method for pre-order traversal.
     *
     * @param <T>      the type of elements in the tree
     * @param node     the current node
     * @param consumer consumer function to process each key
     */
    public static <T extends Comparable<T>> void preOrderHelper(TreeNode<T> node, Consumer<T> consumer) {
        if (node != null) {
            consumer.accept(node.getKey());
            preOrderHelper(node.getLeft(), consumer);
            preOrderHelper(node.getRight(), consumer);
        }
    }

    /**
     * Performs a post-order traversal on the given tree and returns the keys in order.
     *
     * @param <T>  the type of elements in the tree
     * @param root the root of the tree
     * @return a list of keys in post-order
     */
    public static <T extends Comparable<T>> List<T> postOrder(TreeNode<T> root) {
        List<T> result = new ArrayList<>();
        postOrderHelper(root, result::add);
        return result;
    }

    /**
     * Helper method for post-order traversal.
     *
     * @param <T>      the type of elements in the tree
     * @param node     the current node
     * @param consumer consumer function to process each key
     */
    public static <T extends Comparable<T>> void postOrderHelper(TreeNode<T> node, Consumer<T> consumer) {
        if (node != null) {
            postOrderHelper(node.getLeft(), consumer);
            postOrderHelper(node.getRight(), consumer);
            consumer.accept(node.getKey());
        }
    }

    /**
     * Performs a level-order (breadth-first) traversal on the given tree and returns the keys in order.
     *
     * @param <T>  the type of elements in the tree
     * @param root the root of the tree
     * @return a list of keys in level-order
     */
    public static <T extends Comparable<T>> List<T> levelOrder(TreeNode<T> root) {
        List<T> result = new ArrayList<>();

        if (root == null) {
            return result;
        }

        Queue<TreeNode<T>> queue = new LinkedList<>();
        queue.offer(root);

        while (!queue.isEmpty()) {
            TreeNode<T> node = queue.poll();
            result.add(node.getKey());

            if (node.getLeft() != null) {
                queue.offer(node.getLeft());
            }

            if (node.getRight() != null) {
                queue.offer(node.getRight());
            }
        }

        return result;
    }

    /**
     * Performs a level-order (breadth-first) traversal on the given tree and processes each level separately.
     *
     * @param <T>  the type of elements in the tree
     * @param root the root of the tree
     * @return a list of lists, where each inner list contains the keys at a single level
     */
    public static <T extends Comparable<T>> List<List<T>> levelOrderByLevel(TreeNode<T> root) {
        List<List<T>> result = new ArrayList<>();

        if (root == null) {
            return result;
        }

        Queue<TreeNode<T>> queue = new LinkedList<>();
        queue.offer(root);

        while (!queue.isEmpty()) {
            int levelSize = queue.size();
            List<T> currentLevel = new ArrayList<>();

            for (int i = 0; i < levelSize; i++) {
                TreeNode<T> node = queue.poll();
                currentLevel.add(node.getKey());

                if (node.getLeft() != null) {
                    queue.offer(node.getLeft());
                }

                if (node.getRight() != null) {
                    queue.offer(node.getRight());
                }
            }

            result.add(currentLevel);
        }

        return result;
    }
}