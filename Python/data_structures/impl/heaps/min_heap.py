from typing import List, Optional

from data_structures.interfaces import Heap


class MinHeap(Heap):
    """Concrete min-heap implementation."""

    def __init__(self, data: Optional[List[int]] = None):
        self._array = data.copy() if data else []
        for i in range(len(self._array) // 2 - 1, -1, -1):
            self.heapify(i)

    def insert(self, value: int) -> None:
        self._array.append(value)
        self.swim_up(len(self._array) - 1)

    def delete(self) -> Optional[int]:
        if not self._array:
            return None
        root = self._array[0]
        self._array[0] = self._array.pop()
        if self._array:
            self.swim_down(0)
        return root

    def heapify(self, idx: int) -> None:
        smallest, left, right = idx, 2 * idx + 1, 2 * idx + 2
        if left < len(self._array) and self._array[left] < self._array[smallest]:
            smallest = left
        if right < len(self._array) and self._array[right] < self._array[smallest]:
            smallest = right
        if smallest != idx:
            self._array[smallest], self._array[idx] = self._array[idx], self._array[smallest]
            self.heapify(smallest)

    def swim_up(self, idx: int) -> None:
        parent = (idx - 1) // 2
        while idx > 0 and self._array[parent] > self._array[idx]:
            self._array[parent], self._array[idx] = self._array[idx], self._array[parent]
            idx, parent = parent, (parent - 1) // 2

    def swim_down(self, idx: int) -> None:
        self.heapify(idx)

    @classmethod
    def heap_sort(cls, array: List[int]) -> List[int]:
        heap = cls(array)
        return [heap.delete() for _ in range(len(array))]

    def __str__(self) -> str:
        return str(self._array)

    @property
    def array(self):
        return self._array

    @property
    def depth(self) -> int:
        depth = 0
        while 2 ** depth - 1 < len(self._array):
            depth += 1
        return depth
