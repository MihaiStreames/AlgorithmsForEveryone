package DataStructs.Trees;

import DataStructs.Data.RBTreeNode;

public class RBT {
    private static final boolean RED = true;
    private static final boolean BLACK = false;

    private RBTreeNode root;

    private boolean isRed(RBTreeNode node) {
        return node != null && node.color == RED;
    }

    public RBTreeNode getRoot() {
        return root;
    }

    public void setRoot(RBTreeNode root) {
        this.root = root;
    }

    private RBTreeNode rotateLeft(RBTreeNode h) {
        RBTreeNode x = h.right;
        h.right = x.left;
        x.left = h;
        x.color = h.color;
        h.color = RED;
        return x;
    }

    private RBTreeNode rotateRight(RBTreeNode h) {
        RBTreeNode x = h.left;
        h.left = x.right;
        x.right = h;
        x.color = h.color;
        h.color = RED;
        return x;
    }

    private void flipColors(RBTreeNode h) {
        h.color = RED;
        if (h.left != null) h.left.color = BLACK;
        if (h.right != null) h.right.color = BLACK;
    }

    public void insert(int key) {
        root = insert(root, key);
        if (root != null) {
            root.color = BLACK;
        }
    }

    private RBTreeNode insert(RBTreeNode h, int key) {
        if (h == null) return new RBTreeNode(key);

        if (key < h.key)
            h.left = insert(h.left, key);
        else if (key > h.key)
            h.right = insert(h.right, key);
        // Duplicate keys are not inserted

        // Fix-up any right-leaning links
        if (isRed(h.right) && !isRed(h.left))
            h = rotateLeft(h);
        if (isRed(h.left) && isRed(h.left != null ? h.left.left : null))
            h = rotateRight(h);
        if (isRed(h.left) && isRed(h.right))
            flipColors(h);

        return h;
    }

    public boolean search(int key) {
        RBTreeNode current = root;
        while (current != null) {
            if (key < current.key)
                current = current.left;
            else if (key > current.key)
                current = current.right;
            else
                return true;
        }
        return false;
    }
}
