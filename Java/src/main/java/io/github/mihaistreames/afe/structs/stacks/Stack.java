package io.github.mihaistreames.afe.structs.stacks;

import io.github.mihaistreames.afe.structs.nodes.Node;
import org.jetbrains.annotations.NotNull;

import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Objects;

/**
 * Implementation of a generic, iterable stack using a singly-linked list.
 * <p>
 * A stack is a linear data structure that follows the Last-In-First-Out (LIFO) principle.
 * Elements are added and removed from the top of the stack.
 * This implementation is iterable, allowing for-each loop iteration from top to bottom.
 * </p>
 * <p>
 * <strong>Time Complexity:</strong> O(1) for push, pop, peek, and size<br>
 * <strong>Space Complexity:</strong> O(n) where n is the number of elements<br>
 * </p>
 *
 * @param <T> the type of elements in this stack
 */
public class Stack<T> implements Iterable<T> {

    private Node<T> top;
    private int size;

    /**
     * Constructs an empty stack.
     */
    public Stack() {
        this.top = null;
        this.size = 0;
    }

    // ========== PUBLIC API ==========

    /**
     * Pushes an element onto the top of this stack.
     *
     * @param value the element to push
     * @throws NullPointerException if the specified element is null
     */
    public void push(@NotNull final T value) {
        Objects.requireNonNull(value, "Value cannot be null");
        Node<T> newNode = new Node<>(value);
        newNode.next = top;
        top = newNode;
        size++;
    }

    /**
     * Removes and returns the element at the top of this stack.
     *
     * @return the element at the top of the stack
     * @throws NoSuchElementException if the stack is empty
     */
    @NotNull
    public T pop() {
        if (isEmpty()) {
            throw new NoSuchElementException("Cannot pop from an empty stack");
        }
        T value = top.value;
        top = top.next;
        size--;
        return value;
    }

    /**
     * Returns the element at the top of this stack without removing it.
     *
     * @return the element at the top of the stack
     * @throws NoSuchElementException if the stack is empty
     */
    @NotNull
    public T peek() {
        if (isEmpty()) {
            throw new NoSuchElementException("Cannot peek into an empty stack");
        }
        return top.value;
    }

    /**
     * Returns true if this stack contains no elements.
     *
     * @return true if this stack is empty, false otherwise
     */
    public boolean isEmpty() {
        return top == null;
    }

    /**
     * Returns the number of elements in this stack.
     *
     * @return the number of elements in this stack
     */
    public int size() {
        return size;
    }

    /**
     * Returns an iterator over the elements in this stack in LIFO order.
     *
     * @return an iterator that traverses the stack from top to bottom
     */
    @NotNull
    @Override
    public Iterator<T> iterator() {
        return new ListIterator(top);
    }

    // Private iterator class
    private class ListIterator implements Iterator<T> {
        private Node<T> current;

        public ListIterator(Node<T> first) {
            current = first;
        }

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