package org.sincos.afe.examples.TP4;

import org.sincos.afe.old.structs.Heaps.Heap;

public class Exercise4_4 {
    public static void main(String[] args) {
        MedianStructure ms = new MedianStructure();

        // Insert values from 1 to 9
        for (int i = 1; i <= 9; i++) {
            ms.insert(i);
            System.out.println("Inserted: " + i + ", Current median: " + ms.getMedian());
        }

        System.out.println("\nFinal median: " + ms.getMedian()); // 5
        ms.deleteMedian();
        System.out.println("After deleting median, new median: " + ms.getMedian()); // 6
    }

    /**
     * A data structure that maintains the median of a set of integers efficiently.
     * Uses two heaps: a max-heap for elements less than median and a min-heap for elements greater than median.
     */
    public static class MedianStructure {
        private final Heap<Integer> maxHeap; // Stores elements less than median
        private final Heap<Integer> minHeap; // Stores elements greater than median

        /**
         * Constructs a new median-finding structure.
         */
        public MedianStructure() {
            maxHeap = new Heap<>(true);  // Max heap
            minHeap = new Heap<>(false); // Min heap
        }

        /**
         * Inserts a new value into the structure.
         *
         * @param x the value to insert
         */
        public void insert(int x) {
            // Decide which heap to insert into
            if (maxHeap.isEmpty() || x <= maxHeap.peek()) {
                maxHeap.insert(x);
            } else {
                minHeap.insert(x);
            }

            // Rebalance heaps if necessary
            // The size difference between the heaps should be at most 1
            if (maxHeap.size() > minHeap.size() + 1) {
                minHeap.insert(maxHeap.extract());
            } else if (minHeap.size() > maxHeap.size()) {
                maxHeap.insert(minHeap.extract());
            }
        }

        /**
         * Deletes the median element from the structure.
         */
        public void deleteMedian() {
            if (maxHeap.size() >= minHeap.size()) {
                maxHeap.extract(); // The median is in the max heap
            } else {
                minHeap.extract(); // The median is in the min heap
            }
        }

        /**
         * Returns the current median.
         *
         * @return the median value
         * @throws IllegalStateException if the structure is empty
         */
        public int getMedian() {
            if (maxHeap.isEmpty() && minHeap.isEmpty()) {
                throw new IllegalStateException("No elements in the structure");
            }

            if (maxHeap.size() >= minHeap.size()) {
                return maxHeap.peek(); // The median is the maximum of the smaller half
            } else {
                return minHeap.peek(); // The median is the minimum of the larger half
            }
        }
    }
}