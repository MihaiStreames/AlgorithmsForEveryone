package DataStructs.Heaps;

import java.util.ArrayList;
import java.util.Comparator;

/**
 * A generic binary heap implementation that can function as either a min-heap or max-heap.
 *
 * @param <T> the type of elements stored in this heap, must be comparable
 */
public class Heap<T> {
    private final ArrayList<T> elements;
    private final Comparator<? super T> comparator;
    private final boolean isMaxHeap;

    /**
     * Constructs a new empty heap with the specified ordering.
     *
     * @param isMaxHeap true for a max-heap, false for a min-heap
     */
    public Heap(boolean isMaxHeap) {
        this.elements = new ArrayList<>();
        this.isMaxHeap = isMaxHeap;

        // Create appropriate comparator based on heap type
        if (isMaxHeap) {
            this.comparator = (a, b) -> {
                if (a instanceof Comparable) {
                    @SuppressWarnings("unchecked")
                    Comparable<T> comp = (Comparable<T>) a;
                    return comp.compareTo(b);
                }
                throw new ClassCastException("Elements must implement Comparable");
            };
        } else {
            this.comparator = (a, b) -> {
                if (a instanceof Comparable) {
                    @SuppressWarnings("unchecked")
                    Comparable<T> comp = (Comparable<T>) a;
                    return -comp.compareTo(b);
                }
                throw new ClassCastException("Elements must implement Comparable");
            };
        }
    }

    /**
     * Constructs a new empty heap with the specified ordering and custom comparator.
     *
     * @param isMaxHeap  true for a max-heap, false for a min-heap
     * @param comparator the comparator to determine element ordering
     */
    public Heap(boolean isMaxHeap, Comparator<? super T> comparator) {
        this.elements = new ArrayList<>();
        this.isMaxHeap = isMaxHeap;

        // If a custom comparator is provided, use it (possibly reversed based on heap type)
        if (isMaxHeap) {
            this.comparator = comparator;
        } else {
            this.comparator = (a, b) -> -comparator.compare(a, b);
        }
    }

    /**
     * Returns the number of elements in this heap.
     *
     * @return the number of elements in this heap
     */
    public int size() {
        return elements.size();
    }

    /**
     * Returns true if this heap contains no elements.
     *
     * @return true if this heap contains no elements
     */
    public boolean isEmpty() {
        return elements.isEmpty();
    }

    /**
     * Returns the root element of this heap without removing it.
     *
     * @return the root element of this heap
     * @throws IllegalStateException if this heap is empty
     */
    public T peek() {
        if (isEmpty()) {
            throw new IllegalStateException("Heap is empty");
        }
        return elements.get(0);
    }

    /**
     * Inserts the specified element into this heap.
     *
     * @param element the element to add
     */
    public void insert(T element) {
        if (element == null) {
            throw new NullPointerException("Cannot insert null element");
        }

        // Add the element to the end of the heap
        elements.add(element);

        // Fix the heap property by sifting up
        siftUp(elements.size() - 1);
    }

    /**
     * Removes and returns the root element from this heap.
     *
     * @return the root element
     * @throws IllegalStateException if this heap is empty
     */
    public T extract() {
        if (isEmpty()) {
            throw new IllegalStateException("Heap is empty");
        }

        // Get the root element
        T root = elements.get(0);

        // Replace the root with the last element
        T lastElement = elements.remove(elements.size() - 1);

        if (!isEmpty()) {
            elements.set(0, lastElement);

            // Fix the heap property by sifting down
            siftDown(0);
        }

        return root;
    }

    /**
     * Restores the heap property by moving an element up the tree.
     *
     * @param index the index of the element to sift up
     */
    private void siftUp(int index) {
        T element = elements.get(index);

        while (index > 0) {
            int parentIndex = (index - 1) / 2;
            T parent = elements.get(parentIndex);

            // If the element is not in the correct order compared to its parent, swap them
            if (comparator.compare(element, parent) <= 0) {
                break;
            }

            // Swap with parent
            elements.set(index, parent);
            elements.set(parentIndex, element);

            // Move up
            index = parentIndex;
        }
    }

    /**
     * Restores the heap property by moving an element down the tree.
     *
     * @param index the index of the element to sift down
     */
    private void siftDown(int index) {
        int size = elements.size();
        T element = elements.get(index);

        while (true) {
            int leftChildIndex = 2 * index + 1;
            int rightChildIndex = 2 * index + 2;

            // Stop if there are no children
            if (leftChildIndex >= size) {
                break;
            }

            // Find the larger/smaller child (depending on heap type)
            int targetChildIndex = leftChildIndex;
            if (rightChildIndex < size) {
                T leftChild = elements.get(leftChildIndex);
                T rightChild = elements.get(rightChildIndex);

                if (comparator.compare(rightChild, leftChild) > 0) {
                    targetChildIndex = rightChildIndex;
                }
            }

            T targetChild = elements.get(targetChildIndex);

            // If the element is in the correct order compared to its target child, stop
            if (comparator.compare(element, targetChild) >= 0) {
                break;
            }

            // Swap with target child
            elements.set(index, targetChild);
            elements.set(targetChildIndex, element);

            // Move down
            index = targetChildIndex;
        }
    }

    /**
     * Returns whether this heap is a max-heap or min-heap.
     *
     * @return true if this is a max-heap, false if this is a min-heap
     */
    public boolean isMaxHeap() {
        return isMaxHeap;
    }

    /**
     * Returns a string representation of this heap.
     *
     * @return a string representation of this heap
     */
    @Override
    public String toString() {
        return elements.toString();
    }
}