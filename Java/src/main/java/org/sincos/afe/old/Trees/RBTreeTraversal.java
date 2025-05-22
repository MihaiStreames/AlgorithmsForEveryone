package org.sincos.afe.old.Trees;

import org.sincos.afe.old.structs.Data.RBTreeNode;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.function.Consumer;

/**
 * A utility class that provides various traversal algorithms for Red-Black Trees.
 */
public class RBTreeTraversal {

    /**
     * Private constructor to prevent instantiation.
     */
    private RBTreeTraversal() {
        // Utility class should not be instantiated
    }

    /**
     * Performs an in-order traversal on the given tree and returns the keys in order.
     *
     * @param <T>  the type of elements in the tree
     * @param root the root of the tree
     * @return a list of keys in in-order
     */
    public static <T extends Comparable<T>> List<T> inOrder(RBTreeNode<T> root) {
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
    public static <T extends Comparable<T>> void inOrderHelper(RBTreeNode<T> node, Consumer<T> consumer) {
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
    public static <T extends Comparable<T>> List<T> preOrder(RBTreeNode<T> root) {
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
    public static <T extends Comparable<T>> void preOrderHelper(RBTreeNode<T> node, Consumer<T> consumer) {
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
    public static <T extends Comparable<T>> List<T> postOrder(RBTreeNode<T> root) {
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
    public static <T extends Comparable<T>> void postOrderHelper(RBTreeNode<T> node, Consumer<T> consumer) {
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
    public static <T extends Comparable<T>> List<T> levelOrder(RBTreeNode<T> root) {
        List<T> result = new ArrayList<>();

        if (root == null) {
            return result;
        }

        Queue<RBTreeNode<T>> queue = new LinkedList<>();
        queue.offer(root);

        while (!queue.isEmpty()) {
            RBTreeNode<T> node = queue.poll();
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
    public static <T extends Comparable<T>> List<List<T>> levelOrderByLevel(RBTreeNode<T> root) {
        List<List<T>> result = new ArrayList<>();

        if (root == null) {
            return result;
        }

        Queue<RBTreeNode<T>> queue = new LinkedList<>();
        queue.offer(root);

        while (!queue.isEmpty()) {
            int levelSize = queue.size();
            List<T> currentLevel = new ArrayList<>();

            for (int i = 0; i < levelSize; i++) {
                RBTreeNode<T> node = queue.poll();
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

    /**
     * Returns a visual representation of a Red-Black Tree as a list of strings.
     * Each string represents a level of the tree.
     *
     * @param <T>  the type of elements in the tree
     * @param root the root of the tree
     * @return a list of strings representing the tree structure
     */
    public static <T extends Comparable<T>> List<String> treeVisualization(RBTreeNode<T> root) {
        List<String> result = new ArrayList<>();

        if (root == null) {
            result.add("(empty tree)");
            return result;
        }

        // Calculate height of the tree
        int height = getHeight(root) + 1;
        int maxWidth = (int) Math.pow(2, height) * 2;

        // Build levels of the tree
        List<List<RBTreeNode<T>>> levels = new ArrayList<>();
        List<RBTreeNode<T>> currentLevel = new ArrayList<>();
        currentLevel.add(root);
        levels.add(currentLevel);

        for (int i = 0; i < height - 1; i++) {
            List<RBTreeNode<T>> nextLevel = new ArrayList<>();

            for (RBTreeNode<T> node : currentLevel) {
                if (node != null) {
                    nextLevel.add(node.getLeft());
                    nextLevel.add(node.getRight());
                } else {
                    nextLevel.add(null);
                    nextLevel.add(null);
                }
            }

            levels.add(nextLevel);
            currentLevel = nextLevel;
        }

        // Generate string representation for each level
        for (int i = 0; i < levels.size(); i++) {
            List<RBTreeNode<T>> level = levels.get(i);
            int levelWidth = (int) Math.pow(2, i);
            int spacing = maxWidth / (levelWidth + 1);

            StringBuilder line = new StringBuilder();

            for (RBTreeNode<T> node : level) {
                String nodeStr = (node == null) ? " " :
                        node.getKey() + "(" + (node.isRed() ? "R" : "B") + ")";
                line.append(String.format("%" + spacing + "s", nodeStr));
            }

            result.add(line.toString());
        }

        return result;
    }

    /**
     * Helper method to calculate the height of a Red-Black Tree.
     *
     * @param <T>  the type of elements in the tree
     * @param node the root of the tree
     * @return the height of the tree
     */
    private static <T extends Comparable<T>> int getHeight(RBTreeNode<T> node) {
        if (node == null) {
            return -1;
        }

        return 1 + Math.max(getHeight(node.getLeft()), getHeight(node.getRight()));
    }
}