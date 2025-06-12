package io.github.mihaistreames.afe.structs.stacks;

import io.github.mihaistreames.afe.structs.nodes.Node;
import org.jetbrains.annotations.NotNull;

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * A generic Last-In-First-Out (LIFO) stack, implemented with a linked list.
 *
 * @param <T> The type of elements in the stack.
 */
public class Stack<T> implements Iterable<T> {
    private Node<T> top;
    private int size;

    /**
     * Constructs an empty stack.
     */
    public Stack() {
        top = null;
        size = 0;
    }

    /**
     * Checks if the stack is empty.
     *
     * @return true if the stack is empty, false otherwise.
     */
    public boolean isEmpty() {
        return top == null;
    }

    /**
     * Returns the number of elements in the stack.
     *
     * @return the number of elements.
     */
    public int size() {
        return size;
    }

    /**
     * Adds an element to the top of the stack.
     *
     * @param value the element to add.
     */
    public void push(T value) {
        Node<T> newTop = new Node<>(value);
        newTop.next = top;
        top = newTop;
        size++;
    }

    /**
     * Removes and returns the element at the top of the stack.
     *
     * @return the removed element.
     * @throws NoSuchElementException if the stack is empty.
     */
    public T pop() {
        if (isEmpty()) throw new NoSuchElementException("Stack underflow");
        T value = top.value;
        top = top.next;
        size--;
        return value;
    }

    /**
     * Returns the element at the top of the stack without removing it.
     *
     * @return the top element.
     * @throws NoSuchElementException if the stack is empty.
     */
    public T peek() {
        if (isEmpty()) throw new NoSuchElementException("Stack underflow");
        return top.value;
    }

    @Override
    public @NotNull Iterator<T> iterator() {
        return new StackIterator();
    }

    private class StackIterator implements Iterator<T> {
        private Node<T> current = top;

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