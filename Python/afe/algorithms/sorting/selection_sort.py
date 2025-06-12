# -*- coding: utf-8 -*-

from typing import List, TypeVar, Optional, Callable
from .sort_interface import SortInterface


T = TypeVar('T')


class SelectionSort(SortInterface):
    
    """
    A class that implements the Selection Sort algorithm. \n
    Selection Sort is a simple sorting algorithm that divides the input list into two parts: a sorted and an unsorted part.
    It repeatedly selects the smallest (or largest) element from the unsorted part and moves it to the end of the sorted part. \n
    It is not a stable sort, meaning that it does not maintain the relative order of records with equal keys.
    """

    @staticmethod
    def sort(data: List[T], comparator: Optional[Callable[[T, T], int]] = None) -> None:
        
        """
        Sorts a list in-place using Selection Sort. Not stable.
        - Time Complexity: O(n^2)
        - Space Complexity: O(1)
        """

        if comparator is None: comparator = SelectionSort.DEFAULT_COMPARATOR

        n = len(data)
        for i in range(n):
            min_idx = i
            for j in range(i + 1, n):
                if comparator(data[j], data[min_idx]) < 0:
                    min_idx = j
            data[i], data[min_idx] = data[min_idx], data[i]

