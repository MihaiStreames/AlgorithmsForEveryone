# -*- coding: utf-8 -*-

from typing import List, TypeVar, Optional, Callable
from .sort_interface import SortInterface


T = TypeVar('T')


class MergeSort(SortInterface):

    """
    A class implementing the Merge Sort algorithm. \n
    Merge Sort is a divide-and-conquer algorithm that splits the list into halves, sorts each half, and then merges them back together. \n
    It is a stable sort, meaning that it maintains the relative order of records with equal keys.
    """

    @staticmethod
    def _merge(data: List[T], left: List[T], right: List[T], comparator: Callable[[T, T], int]) -> None:
        
        i = j = k = 0
        while i < len(left) and j < len(right):
            if comparator(left[i], right[j]) <= 0:
                data[k] = left[i]
                i += 1
            else:
                data[k] = right[j]
                j += 1
            k += 1

        while i < len(left):
            data[k] = left[i]
            i += 1
            k += 1

        while j < len(right):
            data[k] = right[j]
            j += 1
            k += 1

    @staticmethod
    def sort(data: List[T], comparator: Optional[Callable[[T, T], int]] = None) -> None:
        
        """
        Sorts a list using Merge Sort. Stable.
        - Time Complexity: O(n log n)
        - Space Complexity: O(n)
        """

        if len(data) == 0:
            return

        if comparator is None: comparator = MergeSort.DEFAULT_COMPARATOR

        mid = len(data) // 2
        left_half = data[:mid]
        right_half = data[mid:]

        MergeSort.sort(left_half, comparator)
        MergeSort.sort(right_half, comparator)
        MergeSort._merge(data, left_half, right_half, comparator)

