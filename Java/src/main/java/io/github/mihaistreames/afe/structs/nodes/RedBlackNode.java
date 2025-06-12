package io.github.mihaistreames.afe.structs.nodes;

/**
 * Represents a node in a Red-Black Tree.
 * Each node contains a key-value pair, references to its children, and a color.
 *
 * @param <Key>   The type of the key, must be comparable.
 * @param <Value> The type of the value.
 */
public class RedBlackNode<Key extends Comparable<Key>, Value> {
    public Key key;
    public Value value;
    public RedBlackNode<Key, Value> left, right;
    public boolean color; // true for Red, false for Black
    public int size;

    /**
     * Constructs a Red-Black Tree node.
     *
     * @param key   the key for the node.
     * @param value the value associated with the key.
     * @param color the color of the node (true for Red, false for Black).
     * @param size  the size of the subtree rooted at this node.
     */
    public RedBlackNode(Key key, Value value, boolean color, int size) {
        this.key = key;
        this.value = value;
        this.color = color;
        this.size = size;
    }
}