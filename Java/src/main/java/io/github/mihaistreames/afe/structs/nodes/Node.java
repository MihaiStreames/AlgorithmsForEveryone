package io.github.mihaistreames.afe.structs.nodes;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Objects;

/**
 * Represents a generic node in a linked data structure.
 * <p>
 * Each node contains a value and a reference to the next node in the sequence.
 * </p>
 *
 * @param <T> the type of value stored in this node
 * @author Sincos Team
 * @version 1.0.0
 * @since 0.0.3
 */
public class Node<T> {

    public T value;
    public Node<T> next;

    /**
     * Constructs a new node with the specified value.
     *
     * @param value the value to store in this node
     * @throws NullPointerException if the value is null
     */
    public Node(@NotNull final T value) {
        this.value = Objects.requireNonNull(value, "Value cannot be null");
        this.next = null;
    }

    /**
     * Constructs a new node with the specified value and next reference.
     *
     * @param value the value to store in this node
     * @param next  the next node in the sequence, or null if this is the last node
     * @throws NullPointerException if the value is null
     */
    public Node(@NotNull final T value, @Nullable final Node<T> next) {
        this.value = Objects.requireNonNull(value, "Value cannot be null");
        this.next = next;
    }

    /**
     * Checks if this node has a next node.
     *
     * @return true if there is a next node, false if this is the last node
     */
    public boolean hasNext() {
        return next != null;
    }

    @Override
    public String toString() {
        return "Node{" +
                "value=" + value +
                ", hasNext=" + hasNext() +
                '}';
    }

    @Override
    public boolean equals(final Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        final Node<?> that = (Node<?>) obj;
        return Objects.equals(value, that.value);
    }

    @Override
    public int hashCode() {
        return Objects.hash(value);
    }
}