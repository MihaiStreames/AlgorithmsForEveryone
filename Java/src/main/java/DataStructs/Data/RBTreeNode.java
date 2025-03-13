package DataStructs.Data;

public class RBTreeNode<T extends Comparable<T>> {
    public final T key;
    public RBTreeNode<T> left;
    public RBTreeNode<T> right;
    public boolean color; // true for RED, false for BLACK

    public RBTreeNode(T key) {
        this.key = key;
        this.color = true; // new nodes are typically RED
    }
}
