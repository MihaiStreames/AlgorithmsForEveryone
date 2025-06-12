# -*- coding: utf-8 -*-

from typing import List, TypeVar, Optional, Callable
from .sort_interface import SortInterface
import random


T = TypeVar('T')


class QuickSort(SortInterface):

    """
    A class implementing the Quick Sort algorithm. \n
    Quick Sort is a divide-and-conquer algorithm that selects a 'pivot' element and partitions the other elements
    into two sub-arrays according to whether they are less than or greater than the pivot. \n
    It is not a stable sort, meaning that it does not maintain the relative order of records with equal keys.
    """

    @staticmethod
    def _partition(data: List[T], low: int, high: int, comparator: Callable[[T, T], int]) -> int:
        # Use random pivot to avoid worst-case
        pivot_index = random.randint(low, high)
        data[pivot_index], data[high] = data[high], data[pivot_index]
        pivot = data[high]

        i = low - 1
        for j in range(low, high):
            if comparator(data[j], pivot) <= 0:
                i += 1
                data[i], data[j] = data[j], data[i]

        data[i + 1], data[high] = data[high], data[i + 1]
        return i + 1

    @staticmethod
    def _quick_sort_recursive(data: List[T], low: int, high: int, comparator: Callable[[T, T], int]):
        if low < high:
            pi = QuickSort._partition(data, low, high, comparator)
            QuickSort._quick_sort_recursive(data, low, pi - 1, comparator)
            QuickSort._quick_sort_recursive(data, pi + 1, high, comparator)

    @staticmethod
    def sort(data: List[T], comparator: Optional[Callable[[T, T], int]] = None) -> None:
        
        """Sorts a list in-place using the Quick Sort algorithm.

        This implementation is not stable. It uses a randomized pivot to achieve
        O(n log n) average time complexity.

        Args:
            data: The list of elements to sort.
            comparator: A function for comparing two elements. If None, default
                comparison operators are used.
        """

        if not data: return
        if comparator is None: comparator = QuickSort.DEFAULT_COMPARATOR

        QuickSort._quick_sort_recursive(data, 0, len(data) - 1, comparator)

