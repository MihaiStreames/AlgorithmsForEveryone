package DataStructs.Data;

public class TreeNode<T extends Comparable<T>> {
    public final T key;
    public TreeNode<T> left;
    public TreeNode<T> right;

    public TreeNode(T key) {
        this.key = key;
    }
}
