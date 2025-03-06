from abc import ABC, abstractmethod
from typing import List, Optional, TypeVar, Generic

T = TypeVar('T')

class Heap(ABC, Generic[T]):
    """
    Abstract base class for heap data structures.
    """

    @abstractmethod
    def insert(self, value: T) -> None:
        """Insert an element into the heap."""
        pass

    @abstractmethod
    def delete(self) -> Optional[T]:
        """Remove and return the root element from the heap."""
        pass

    @abstractmethod
    def peek(self) -> Optional[T]:
        """Return the root element without removing it."""
        pass

    @abstractmethod
    def is_empty(self) -> bool:
        """Check if the heap is empty."""
        pass

    @abstractmethod
    def size(self) -> int:
        """Return the number of elements in the heap."""
        pass

    @classmethod
    @abstractmethod
    def heapify(cls, array: List[T]) -> 'Heap[T]':
        """Create a heap from an array."""
        pass

    @classmethod
    @abstractmethod
    def heap_sort(cls, array: List[T]) -> List[T]:
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
