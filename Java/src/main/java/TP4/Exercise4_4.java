package TP4;

import DataStructs.Heaps.Heap;

public class Exercise4_4 {
    public static void main(String[] args) {
        MedianStructure ms = new MedianStructure();
        ms.insert(1);
        ms.insert(2);
        ms.insert(3);
        ms.insert(4);
        ms.insert(5);
        ms.insert(6);
        ms.insert(7);
        ms.insert(8);
        ms.insert(9);
        System.out.println(ms.getMedian()); // 5
        ms.deleteMedian();
        System.out.println(ms.getMedian()); // 6
    }

    public static class MedianStructure {
        private final Heap maxHeap;
        private final Heap minHeap;

        public MedianStructure() {
            maxHeap = new Heap(true);
            minHeap = new Heap(false);
        }

        public void insert(int x) {
            if (maxHeap.size() == 0 || x < maxHeap.peek()) {
                maxHeap.insert(x);
            } else {
                minHeap.insert(x);
            }
            if (maxHeap.size() > minHeap.size() + 1) {
                minHeap.insert(maxHeap.extract());
            } else if (minHeap.size() > maxHeap.size() + 1) {
                maxHeap.insert(minHeap.extract());
            }
        }

        public void deleteMedian() {
            if (maxHeap.size() > minHeap.size()) {
                maxHeap.extract();
            } else {
                minHeap.extract();
            }
        }

        public int getMedian() {
            if (maxHeap.size() > minHeap.size()) {
                return maxHeap.peek();
            } else {
                return minHeap.peek();
            }
        }
    }
}