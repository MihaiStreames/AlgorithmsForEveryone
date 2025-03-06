from typing import List

from data_structures.impl.heaps import MinHeap, MaxHeap


def heap_sort(array: List[int], reverse: bool = False) -> List[int]:
    if reverse:
        return MaxHeap.heap_sort(array)
    return MinHeap.heap_sort(array)
