# -*- coding: utf-8 -*-

from typing import List, TypeVar, Optional, Callable
from .sort_interface import SortInterface


T = TypeVar('T')


class HeapSort(SortInterface):

    """
    A class that implements Heap Sort algorithm. \n
    Heap Sort is an in-place, non-stable sorting algorithm that uses a binary heap data structure.
    It first builds a max heap from the input data, then repeatedly extracts the maximum element from the heap
    and rebuilds the heap until all elements are sorted. \n
    It is not a stable sort, meaning that it does not maintain the relative order of records with equal keys.
    """

    @staticmethod
    def _heapify(data: List[T], n: int, i: int, comparator: Callable[[T, T], int]):

        largest = i
        left = 2 * i + 1
        right = 2 * i + 2

        if left < n and comparator(data[i], data[left]) < 0:
            largest = left

        if right < n and comparator(data[largest], data[right]) < 0:
            largest = right

        if largest != i:
            data[i], data[largest] = data[largest], data[i]
            HeapSort._heapify(data, n, largest, comparator)

    @staticmethod
    def sort(data: List[T], comparator: Optional[Callable[[T, T], int]] = None) -> None:
        
        """
        Sorts a list in-place using Heap Sort. Not stable.
        - Time Complexity: O(n log n)
        - Space Complexity: O(1)
        """

        if comparator is None: comparator = HeapSort.DEFAULT_COMPARATOR

        n = len(data)

        # Build a maxheap
        for i in range(n // 2 - 1, -1, -1):
            HeapSort._heapify(data, n, i, comparator)

        # Extract elements one by one
        for i in range(n - 1, 0, -1):
            data[i], data[0] = data[0], data[i]
            HeapSort._heapify(data, i, 0, comparator)

