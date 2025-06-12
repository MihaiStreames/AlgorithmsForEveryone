package io.github.mihaistreames.afe.structs.nodes;

/**
 * A generic node class, typically used as a building block for linked data structures.
 *
 * @param <T> The type of the value stored in the node.
 */
public class Node<T> {
    public T value;
    public Node<T> next;

    /**
     * Constructs a node with a given value.
     *
     * @param value The value to be stored in the node.
     */
    public Node(T value) {
        this.value = value;
        this.next = null;
    }
}