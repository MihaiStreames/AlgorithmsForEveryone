package io.github.mihaistreames.afe.structs.queues;

import io.github.mihaistreames.afe.structs.nodes.Node;
import org.jetbrains.annotations.NotNull;

import java.util.NoSuchElementException;
import java.util.Objects;

/**
 * Implementation of a generic queue using a singly-linked list.
 * <p>
 * A queue is a linear data structure that follows the First-In-First-Out (FIFO) principle.
 * Elements are added at the rear (enqueue) and removed from the front (dequeue).
 * </p>
 * <p>
 * <strong>Time Complexity:</strong> O(1) for enqueue, dequeue, peek, and size<br>
 * <strong>Space Complexity:</strong> O(n) where n is the number of elements<br>
 * </p>
 *
 * @param <T> the type of elements stored in this queue
 * @author Sincos Team
 * @version 1.0.0
 * @since 0.0.3
 */
public class Queue<T> {

    private Node<T> front;
    private Node<T> rear;
    private int size;

    /**
     * Constructs an empty queue.
     */
    public Queue() {
        this.front = null;
        this.rear = null;
        this.size = 0;
    }

    // ========== PUBLIC API ==========

    /**
     * Adds the specified element to the rear of this queue.
     *
     * @param value the element to add to the queue
     * @throws NullPointerException if the specified element is null
     */
    public void enqueue(@NotNull final T value) {
        Objects.requireNonNull(value, "Value cannot be null");

        final Node<T> newNode = new Node<>(value);

        if (isEmpty()) {
            // First element in the queue
            front = rear = newNode;
        } else {
            // Add to the rear
            rear.next = newNode;
            rear = newNode;
        }

        size++;
    }

    /**
     * Removes and returns the element at the front of this queue.
     *
     * @return the element at the front of the queue
     * @throws NoSuchElementException if the queue is empty
     */
    @NotNull
    public T dequeue() {
        if (isEmpty()) throw new NoSuchElementException("Cannot dequeue from empty queue");

        final T value = front.value;
        front = front.next;
        size--;

        // If queue becomes empty, update rear reference
        if (front == null) rear = null;
        return value;
    }

    /**
     * Returns the element at the front of this queue without removing it.
     *
     * @return the element at the front of the queue
     * @throws NoSuchElementException if the queue is empty
     */
    @NotNull
    public T peek() {
        if (isEmpty()) throw new NoSuchElementException("Cannot peek into empty queue");
        return front.value;
    }

    /**
     * Returns true if this queue contains no elements.
     *
     * @return true if this queue is empty, false otherwise
     */
    public boolean isEmpty() {
        return front == null;
    }

    /**
     * Returns the number of elements in this queue.
     *
     * @return the number of elements in this queue
     */
    public int size() {
        return size;
    }

    /**
     * Checks if this queue contains the specified element.
     *
     * @param value the element to search for
     * @return true if the queue contains the specified element, false otherwise
     * @throws NullPointerException if the specified element is null
     */
    public boolean contains(@NotNull final T value) {
        Objects.requireNonNull(value, "Value cannot be null");

        Node<T> current = front;
        while (current != null) {
            if (value.equals(current.value)) return true;
            current = current.next;
        }

        return false;
    }
}