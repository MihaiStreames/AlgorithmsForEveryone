# -*- coding: utf-8 -*-

from typing import List, TypeVar, Optional, Callable


T = TypeVar('T')


class Search:

    """
    A class containing static methods for searching algorithms. \n
    It includes linear search and binary search methods, both iterative and recursive.
    Each method can accept a custom comparator function for element comparison.
    """

    DEFAULT_COMPARATOR: Callable[[T, T], int] = lambda x, y: (x > y) - (x < y)

    @staticmethod
    def linearSearch(data: List[T], key: T, comparator: Optional[Callable[[T, T], int]] = None) -> int:
        
        """
        Searches for a key in a list using linear search.

        Achieves O(n) time complexity.

        Args:
            data: The list to search in.
            key: The element to search for.
            comparator: A function to compare elements. If None, standard comparison is used.

        Returns:
            The index of the key if found, otherwise -1.
        """

        if comparator is None: comparator = Search.DEFAULT_COMPARATOR

        for index, item in enumerate(data):
            if comparator(item, key) == 0:
                return index

        return -1

    @staticmethod
    def binarySearch(data: List[T], key: T, comparator: Optional[Callable[[T, T], int]] = None) -> int:
        
        """
        Searches for a key in a sorted list.

        Achieves O(log n) time complexity.

        Args:
            data: The sorted list to search in.
            key: The element to search for.
            comparator: A function to compare elements. If None, standard comparison is used.

        Returns:
            The index of the key if found, otherwise -1.
        """

        if comparator is None: comparator = Search.DEFAULT_COMPARATOR

        low: int = 0
        high: int = len(data) - 1

        while low <= high:

            # Find midpoint, comparison, and adjust search range
            mid: int = low + (high - low) // 2
            comparison: int = comparator(data[mid], key)

            if comparison < 0: low = mid + 1
            elif comparison > 0: high = mid - 1
            
            # Key found!
            else: return mid

        return -1

    @staticmethod
    def binarySearchRecursive(data: List[T], key: T, low: int, high: int, comparator: Optional[Callable[[T, T], int]] = None) -> int:
        
        """
        Searches for a key in a sorted list using recursive binary search.

        Achieves O(log n) time complexity.

        Args:
            data: The sorted list to search in.
            key: The element to search for.
            low: The starting index of the search range.
            high: The ending index of the search range.
            comparator: A function to compare elements. If None, standard comparison is used.

        Returns:
            The index of the key if found, otherwise -1.
        """

        if comparator is None: comparator = Search.DEFAULT_COMPARATOR

        if low > high:
            return -1

        mid: int = low + (high - low) // 2
        comparison: int = comparator(data[mid], key)

        if comparison < 0:
            return Search.binarySearchRecursive(data, key, mid + 1, high, comparator)
        elif comparison > 0:
            return Search.binarySearchRecursive(data, key, low, mid - 1, comparator)
        else:
            return mid

 