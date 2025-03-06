from abc import ABC, abstractmethod
from typing import List, Optional


class Heap(ABC):
    """
    Abstract base class for heap data structures.
    """

    @abstractmethod
    def insert(self, value: int) -> None:
        """Insert an element into the heap."""
        pass

    @abstractmethod
    def delete(self) -> Optional[int]:
        """Remove and return the root element from the heap."""
        pass

    @abstractmethod
    def heapify(self, idx: int) -> None:
        """Restore the heap property starting from the given index downward."""
        pass

    @abstractmethod
    def swim_up(self, idx: int) -> None:
        """Restore the heap property by moving the element at index `idx` upward."""
        pass

    @abstractmethod
    def swim_down(self, idx: int) -> None:
        """Restore the heap property by moving the element at index `idx` downward."""
        pass

    @classmethod
    @abstractmethod
    def heap_sort(cls, array: List[int]) -> List[int]:
        """Sort an array using heap sort algorithm."""
        pass

    @property
    @abstractmethod
    def array(self) -> List[int]:
        """Return the underlying array representing the heap."""
        pass

    @property
    @abstractmethod
    def depth(self) -> int:
        """Return the depth of the heap."""
        pass

    @abstractmethod
    def __str__(self) -> str:
        """Return a string representation of the heap."""
        pass
