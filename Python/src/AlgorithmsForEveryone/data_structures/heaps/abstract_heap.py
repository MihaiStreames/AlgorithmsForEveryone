import math
from abc import ABC, abstractmethod
from typing import Generic, TypeVar, Callable, List, Optional

T = TypeVar('T')


class AbstractHeap(ABC, Generic[T]):
    """
    Abstract base class for an array-based heap.

    Stores elements in a Python list, using index arithmetic to
    identify parents and children (no node objects).
    """

    def __init__(self, cmp: Callable[[T, T], bool]):
        """
        Args:
            cmp: Comparator returning True if the first argument
                 should be higher in the heap ordering than the second.
        """
        self._cmp = cmp
        self._data: List[T] = []  # Underlying array for heap

    @property
    def size(self) -> int:
        """Return the number of elements in the heap."""
        return len(self._data)

    @property
    def height(self) -> int:
        """Return the height of the heap."""
        return math.floor(math.log2(self.size)) + 1 if self.size > 0 else 0

    def is_empty(self) -> bool:
        """Check if the heap is empty."""
        return self.size == 0

    def clear(self) -> None:
        """Remove all elements from the heap."""
        self._data.clear()

    def insert(self, value: T) -> None:
        """
        Insert a value into the heap.
        Appends to the end of the array, then 'swim up' to restore ordering.
        """
        self._data.append(value)
        self._swim_up(self.size - 1)

    def delete(self) -> Optional[T]:
        """
        Remove and return the root (top) element of the heap, or None if empty.
        """
        if self.is_empty():
            return None

        root_val = self._data[0]
        last_idx = self.size - 1

        if last_idx == 0:
            # Only one element in the heap
            self._data.pop()
            return root_val

        # Swap the root with the last element
        self._swap(0, last_idx)
        # Remove the last element
        self._data.pop()

        # Restore heap property by swimming the new root down
        self._swim_down(0)

        return root_val

    def _swap(self, i: int, j: int) -> None:
        """
        Swap two elements in the array.
        """
        self._data[i], self._data[j] = self._data[j], self._data[i]

    @staticmethod
    def _parent(idx: int) -> int:
        return (idx - 1) // 2

    @staticmethod
    def _left(idx: int) -> int:
        return 2 * idx + 1

    @staticmethod
    def _right(idx: int) -> int:
        return 2 * idx + 2

    @abstractmethod
    def _swim_up(self, idx: int) -> None:
        """
        Move the element at idx upward until heap property is satisfied.
        """
        pass

    @abstractmethod
    def _swim_down(self, idx: int) -> None:
        """
        Move the element at idx downward until heap property is satisfied.
        """
        pass
