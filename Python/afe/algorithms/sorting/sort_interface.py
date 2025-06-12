# -*- coding: utf-8 -*-

from typing import List, TypeVar, Optional, Callable
from abc import ABC, abstractmethod


T = TypeVar('T')


class SortInterface(ABC):

    """
    An abstract base class for sorting algorithms. \n
    It defines the interface for sorting methods, which must be implemented by any concrete sorting algorithm class.
    """

    DEFAULT_COMPARATOR: Callable[[T, T], int] = lambda x, y: (x > y) - (x < y)

    @abstractmethod
    def sort(self, data: List[T], comparator: Optional[Callable[[T, T], int]] = None) -> None:
        
        """
        Sorts a list in-place using the specified sorting algorithm.

        Args:
            data: The list to be sorted.
            comparator: A function that compares two elements. If None, standard comparison is used.
        """

        raise NotImplementedError("Subclasses must implement this method.")

