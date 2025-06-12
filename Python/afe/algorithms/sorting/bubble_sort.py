# -*- coding: utf-8 -*-

from typing import List, TypeVar, Optional, Callable
from .sort_interface import SortInterface


T = TypeVar('T')


class BubbleSort(SortInterface):

    """
    A class that implements the Bubble Sort algorithm. \n
    Bubble Sort is a simple sorting algorithm that repeatedly steps through the list, compares adjacent elements,
    and swaps them if they are in the wrong order. The pass through the list is repeated until no swaps are needed,
    which means the list is sorted. \n
    It is a stable sort, meaning that it maintains the relative order of records with equal keys.
    """

    @staticmethod
    def sort(data: List[T], comparator: Optional[Callable[[T, T], int]] = None) -> None:
        
        """
        Sorts a list in-place using Bubble Sort. Stable.
        - Time Complexity: O(n^2)
        - Space Complexity: O(1)
        """
        
        if comparator is None: comparator = BubbleSort.DEFAULT_COMPARATOR

        n = len(data)
        for i in range(n):
            swapped = False
            for j in range(0, n - i - 1):
                if comparator(data[j], data[j + 1]) > 0:
                    data[j], data[j + 1] = data[j + 1], data[j]
                    swapped = True
            if not swapped:
                break

