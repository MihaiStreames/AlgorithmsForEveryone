package DataStructs.Lists;

import DataStructs.Data.Node;

/**
 * A generic doubly linked list implementation.
 *
 * @param <T> the type of elements stored in the list
 */
public class LinkedList<T> {
    private Node<T> head;
    private Node<T> tail;
    private int size;

    /**
     * Constructs a new empty linked list.
     */
    public LinkedList() {
        this.head = null;
        this.tail = null;
        this.size = 0;
    }

    /**
     * Constructs a new linked list containing the elements of the specified array.
     *
     * @param elements the array whose elements are to be placed into this list
     */
    public LinkedList(T[] elements) {
        this();
        if (elements == null) {
            throw new IllegalArgumentException("Array cannot be null");
        }

        for (T element : elements) {
            addLast(element);
        }
    }

    /**
     * Returns the number of elements in this list.
     *
     * @return the number of elements in this list
     */
    public int size() {
        return size;
    }

    /**
     * Returns true if this list contains no elements.
     *
     * @return true if this list contains no elements
     */
    public boolean isEmpty() {
        return size == 0;
    }

    /**
     * Appends the specified element to the end of this list.
     *
     * @param element element to be appended to this list
     */
    public void addLast(T element) {
        Node<T> newNode = new Node<>(element);

        if (isEmpty()) {
            head = newNode;
            tail = newNode;
        } else {
            newNode.setPrev(tail);
            tail.setNext(newNode);
            tail = newNode;
        }

        size++;
    }

    /**
     * Inserts the specified element at the beginning of this list.
     *
     * @param element element to be inserted at the beginning of this list
     */
    public void addFirst(T element) {
        Node<T> newNode = new Node<>(element);

        if (isEmpty()) {
            head = newNode;
            tail = newNode;
        } else {
            newNode.setNext(head);
            head.setPrev(newNode);
            head = newNode;
        }

        size++;
    }

    /**
     * Returns the element at the specified position in this list.
     *
     * @param index index of the element to return
     * @return the element at the specified position in this list
     * @throws IndexOutOfBoundsException if the index is out of range
     */
    public T get(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + size);
        }

        Node<T> current;

        // Optimize traversal by starting from the closest end
        if (index < size / 2) {
            current = head;
            for (int i = 0; i < index; i++) {
                current = current.getNext();
            }
        } else {
            current = tail;
            for (int i = size - 1; i > index; i--) {
                current = current.getPrev();
            }
        }

        return current.getKey();
    }

    /**
     * Replaces the element at the specified position in this list with the specified element.
     *
     * @param index   index of the element to replace
     * @param element element to be stored at the specified position
     * @throws IndexOutOfBoundsException if the index is out of range
     */
    public void set(int index, T element) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + size);
        }

        // Remove the old node and insert a new one at the specified position
        Node<T> current;

        // Optimize traversal by starting from the closest end
        if (index < size / 2) {
            current = head;
            for (int i = 0; i < index; i++) {
                current = current.getNext();
            }
        } else {
            current = tail;
            for (int i = size - 1; i > index; i--) {
                current = current.getPrev();
            }
        }

        // Create new node with the element
        Node<T> newNode = new Node<>(element);

        // Connect new node to its neighbors
        Node<T> prev = current.getPrev();
        Node<T> next = current.getNext();

        if (prev != null) {
            prev.setNext(newNode);
            newNode.setPrev(prev);
        } else {
            head = newNode;
        }

        if (next != null) {
            next.setPrev(newNode);
            newNode.setNext(next);
        } else {
            tail = newNode;
        }
    }

    /**
     * Removes and returns the first element from this list.
     *
     * @return the first element from this list
     * @throws IllegalStateException if this list is empty
     */
    public T removeFirst() {
        if (isEmpty()) {
            throw new IllegalStateException("List is empty");
        }

        T element = head.getKey();

        if (head == tail) {
            head = null;
            tail = null;
        } else {
            head = head.getNext();
            head.setPrev(null);
        }

        size--;
        return element;
    }

    /**
     * Removes and returns the last element from this list.
     *
     * @return the last element from this list
     * @throws IllegalStateException if this list is empty
     */
    public T removeLast() {
        if (isEmpty()) {
            throw new IllegalStateException("List is empty");
        }

        T element = tail.getKey();

        if (head == tail) {
            head = null;
            tail = null;
        } else {
            tail = tail.getPrev();
            tail.setNext(null);
        }

        size--;
        return element;
    }

    /**
     * Removes the element at the specified position in this list.
     *
     * @param index the index of the element to be removed
     * @return the element previously at the specified position
     * @throws IndexOutOfBoundsException if the index is out of range
     */
    public T remove(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + size);
        }

        if (index == 0) {
            return removeFirst();
        } else if (index == size - 1) {
            return removeLast();
        }

        Node<T> current;

        // Optimize traversal by starting from the closest end
        if (index < size / 2) {
            current = head;
            for (int i = 0; i < index; i++) {
                current = current.getNext();
            }
        } else {
            current = tail;
            for (int i = size - 1; i > index; i--) {
                current = current.getPrev();
            }
        }

        T element = current.getKey();

        current.getPrev().setNext(current.getNext());
        current.getNext().setPrev(current.getPrev());

        size--;
        return element;
    }

    /**
     * Prints the elements of this list to the standard output.
     */
    public void print() {
        Node<T> current = head;
        while (current != null) {
            System.out.print(current.getKey() + " ");
            current = current.getNext();
        }
        System.out.println();
    }

    @Override
    public String toString() {
        if (isEmpty()) {
            return "[]";
        }

        StringBuilder sb = new StringBuilder("[");
        Node<T> current = head;

        while (current != null) {
            sb.append(current.getKey());
            current = current.getNext();

            if (current != null) {
                sb.append(", ");
            }
        }

        sb.append("]");
        return sb.toString();
    }
}