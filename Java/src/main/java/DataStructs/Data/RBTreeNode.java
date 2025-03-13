package DataStructs.Data;

public class RBTreeNode {
    public final int key;
    public RBTreeNode left;
    public RBTreeNode right;
    public boolean color; // true for RED, false for BLACK

    public RBTreeNode(int key) {
        this.key = key;
        this.color = true; // new nodes are typically RED
    }
}
