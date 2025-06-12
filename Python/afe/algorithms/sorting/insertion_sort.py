# -*- coding: utf-8 -*-

from typing import List, TypeVar, Optional, Callable
from .sort_interface import SortInterface


T = TypeVar('T')


class InsertionSort(SortInterface):

    """
    A class implementing the Insertion Sort algorithm. \n
    Insertion Sort is a simple and intuitive sorting algorithm that builds a sorted array one element at a time.
    It is much less efficient on large lists than more advanced algorithms such as quicksort, heapsort, or merge sort. \n
    It is a stable sort, meaning that it maintains the relative order of records with equal keys.
    """

    @staticmethod
    def sort(data: List[T], comparator: Optional[Callable[[T, T], int]] = None) -> None:
        
        """
        Sorts a list in-place using Insertion Sort. Stable.
        - Time Complexity: O(n^2) worst-case, O(n) best-case.
        - Space Complexity: O(1)
        """

        if comparator is None:
            comparator = lambda a, b: (a > b) - (a < b)

        for i in range(1, len(data)):
            key = data[i]
            j = i - 1
            while j >= 0 and comparator(key, data[j]) < 0:
                data[j + 1] = data[j]
                j -= 1
            data[j + 1] = key

