package io.github.mihaistreames.afe.structs.queues;

import io.github.mihaistreames.afe.structs.nodes.Node;
import org.jetbrains.annotations.NotNull;

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * A generic First-In-First-Out (FIFO) queue, implemented with a linked list.
 *
 * @param <T> The type of elements in the queue.
 */
public class Queue<T> implements Iterable<T> {
    private Node<T> first;
    private Node<T> last;
    private int size;

    /**
     * Constructs an empty queue.
     */
    public Queue() {
        first = null;
        last = null;
        size = 0;
    }

    /**
     * Checks if the queue is empty.
     *
     * @return true if the queue is empty, false otherwise.
     */
    public boolean isEmpty() {
        return first == null;
    }

    /**
     * Returns the number of elements in the queue.
     *
     * @return the number of elements.
     */
    public int size() {
        return size;
    }

    /**
     * Adds an element to the end of the queue.
     *
     * @param value the element to add.
     */
    public void enqueue(T value) {
        Node<T> oldLast = last;
        last = new Node<>(value);
        if (isEmpty()) {
            first = last;
        } else {
            oldLast.next = last;
        }
        size++;
    }

    /**
     * Removes and returns the element at the beginning of the queue.
     *
     * @return the removed element.
     * @throws NoSuchElementException if the queue is empty.
     */
    public T dequeue() {
        if (isEmpty()) throw new NoSuchElementException("Queue underflow");
        T value = first.value;
        first = first.next;
        size--;
        if (isEmpty()) {
            last = null;
        }
        return value;
    }

    /**
     * Returns the element at the beginning of the queue without removing it.
     *
     * @return the first element.
     * @throws NoSuchElementException if the queue is empty.
     */
    public T peek() {
        if (isEmpty()) throw new NoSuchElementException("Queue underflow");
        return first.value;
    }

    @Override
    public @NotNull Iterator<T> iterator() {
        return new QueueIterator();
    }

    private class QueueIterator implements Iterator<T> {
        private Node<T> current = first;

        @Override
        public boolean hasNext() {
            return current != null;
        }

        @Override
        public T next() {
            if (!hasNext()) throw new NoSuchElementException();
            T value = current.value;
            current = current.next;
            return value;
        }
    }
}