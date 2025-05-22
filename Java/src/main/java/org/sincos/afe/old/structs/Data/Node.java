package org.sincos.afe.old.structs.Data;

/**
 * A generic node for a doubly linked list.
 *
 * @param <T> the type of data stored in the node
 */
public class Node<T> {
    private final T key;
    private Node<T> next;
    private Node<T> prev;

    /**
     * Constructs a new node with the given key.
     *
     * @param key the data to store in this node
     */
    public Node(T key) {
        this.key = key;
        this.next = null;
        this.prev = null;
    }

    /**
     * Gets the key (data) stored in this node.
     *
     * @return the key
     */
    public T getKey() {
        return key;
    }

    /**
     * Gets the next node in the list.
     *
     * @return the next node, or null if there is none
     */
    public Node<T> getNext() {
        return next;
    }

    /**
     * Sets the next node in the list.
     *
     * @param next the node to set as next
     */
    public void setNext(Node<T> next) {
        this.next = next;
    }

    /**
     * Gets the previous node in the list.
     *
     * @return the previous node, or null if there is none
     */
    public Node<T> getPrev() {
        return prev;
    }

    /**
     * Sets the previous node in the list.
     *
     * @param prev the node to set as previous
     */
    public void setPrev(Node<T> prev) {
        this.prev = prev;
    }

    @Override
    public String toString() {
        return key.toString();
    }
}