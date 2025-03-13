package DataStructs.Trees;

import DataStructs.Data.TreeNode;

public class BST {
    private TreeNode root;

    public void insert(int key) {
        root = insert(root, key);
    }

    private TreeNode insert(TreeNode node, int key) {
        if (node == null) {
            return new TreeNode(key);
        }
        if (key < node.key) {
            node.left = insert(node.left, key);
        } else if (key > node.key) {
            node.right = insert(node.right, key);
        }
        return node;
    }

    public boolean search(int key) {
        return search(root, key);
    }

    private boolean search(TreeNode node, int key) {
        if (node == null) {
            return false;
        }
        if (key < node.key) {
            return search(node.left, key);
        } else if (key > node.key) {
            return search(node.right, key);
        } else {
            return true;
        }
    }
}
