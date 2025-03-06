from typing import TypeVar, List, Optional

from .abstract_heap import AbstractHeap

T = TypeVar('T')


class MaxHeap(AbstractHeap[T]):
    """
    A max-heap that stores plain values in a list (no node objects).
    """

    def __init__(self, data: Optional[List[T]] = None):
        super().__init__(cmp=lambda a, b: a > b)
        if data:
            self._build_heap(data)

    def _build_heap(self, data: List[T]) -> None:
        """
        Build a max-heap from a list of values in O(n) time
        via bottom-up heap construction.
        """
        self._data = data[:]  # Make a copy
        # Call _swim_down from the last non-leaf down to the root
        for i in range((self.size // 2) - 1, -1, -1):
            self._swim_down(i)

    def _swim_up(self, idx: int) -> None:
        """
        Move the element at idx upward while it's larger than its parent.
        """
        while idx > 0:
            parent = self._parent(idx)
            if self._cmp(self._data[idx], self._data[parent]):
                self._swap(idx, parent)
                idx = parent
            else:
                break

    def _swim_down(self, idx: int) -> None:
        """
        Move the element at idx downward while it's smaller than its largest child.
        """
        size = self.size
        while True:
            left = self._left(idx)
            right = self._right(idx)
            largest = idx

            if left < size and self._cmp(self._data[left], self._data[largest]):
                largest = left
            if right < size and self._cmp(self._data[right], self._data[largest]):
                largest = right

            if largest == idx:
                break

            self._swap(idx, largest)
            idx = largest
