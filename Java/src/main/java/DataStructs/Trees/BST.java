package DataStructs.Trees;

import DataStructs.Data.TreeNode;

public class BST<T extends Comparable<T>> {
    private TreeNode<T> root;

    public void insert(T key) {
        root = insert(root, key);
    }

    private TreeNode<T> insert(TreeNode<T> node, T key) {
        if (node == null) {
            return new TreeNode<>(key);
        }
        if (key.compareTo(node.key) < 0) {
            node.left = insert(node.left, key);
        } else if (key.compareTo(node.key) > 0) {
            node.right = insert(node.right, key);
        }
        return node;
    }

    public boolean search(T key) {
        return search(root, key);
    }

    private boolean search(TreeNode<T> node, T key) {
        if (node == null) {
            return false;
        }
        if (key.compareTo(node.key) < 0) {
            return search(node.left, key);
        } else if (key.compareTo(node.key) > 0) {
            return search(node.right, key);
        } else {
            return true;
        }
    }
}

