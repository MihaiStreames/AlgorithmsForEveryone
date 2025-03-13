package DataStructs.Trees;

import DataStructs.Data.RBTreeNode;

import java.util.ArrayList;
import java.util.List;

public class RBT<T extends Comparable<T>> {
    private static final boolean RED = true;
    private static final boolean BLACK = false;

    private RBTreeNode<T> root;

    private boolean isRed(RBTreeNode<T> node) {
        return node != null && node.color == RED;
    }

    public RBTreeNode<T> getRoot() {
        return root;
    }

    public void setRoot(RBTreeNode<T> root) {
        this.root = root;
    }

    private int getHeight(RBTreeNode<T> node) {
        if (node == null) return 0;
        return Math.max(getHeight(node.left), getHeight(node.right)) + 1;
    }

    private RBTreeNode<T> rotateLeft(RBTreeNode<T> h) {
        RBTreeNode<T> x = h.right;
        h.right = x.left;
        x.left = h;
        x.color = h.color;
        h.color = RED;
        return x;
    }

    private RBTreeNode<T> rotateRight(RBTreeNode<T> h) {
        RBTreeNode<T> x = h.left;
        h.left = x.right;
        x.right = h;
        x.color = h.color;
        h.color = RED;
        return x;
    }

    private void flipColors(RBTreeNode<T> h) {
        h.color = RED;
        if (h.left != null) h.left.color = BLACK;
        if (h.right != null) h.right.color = BLACK;
    }

    public void insert(T key) {
        root = insert(root, key);
        if (root != null) {
            root.color = BLACK;
        }
    }

    private RBTreeNode<T> insert(RBTreeNode<T> h, T key) {
        if (h == null) return new RBTreeNode<>(key);

        int cmp = key.compareTo(h.key);
        if (cmp < 0) {
            h.left = insert(h.left, key);
        } else if (cmp > 0) {
            h.right = insert(h.right, key);
        }
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

    public boolean search(T key) {
        RBTreeNode<T> current = root;
        while (current != null) {
            int cmp = key.compareTo(current.key);
            if (cmp < 0)
                current = current.left;
            else if (cmp > 0)
                current = current.right;
            else
                return true;
        }
        return false;
    }

    public void printTree() {
        int height = getHeight(root);
        if (height == 0) {
            System.out.println("(empty tree)");
            return;
        }
        int maxWidth = (int) Math.pow(2, height) * 2;
        List<RBTreeNode<T>> currentLevel = new ArrayList<>();
        currentLevel.add(root);
        for (int level = 0; level < height; level++) {
            StringBuilder line = new StringBuilder();
            int gap = maxWidth / ((int) Math.pow(2, level) + 1);
            for (RBTreeNode<T> node : currentLevel) {
                String nodeStr = (node == null)
                        ? " "
                        : node.key + "(" + (node.color ? "R" : "B") + ")";
                line.append(String.format("%" + gap + "s", nodeStr));
            }
            System.out.println(line.toString());

            List<RBTreeNode<T>> nextLevel = new ArrayList<>();
            for (RBTreeNode<T> node : currentLevel) {
                if (node != null) {
                    nextLevel.add(node.left);
                    nextLevel.add(node.right);
                } else {
                    nextLevel.add(null);
                    nextLevel.add(null);
                }
            }
            currentLevel = nextLevel;
        }
    }
}
