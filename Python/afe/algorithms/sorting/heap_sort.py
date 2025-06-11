from typing import List, TypeVar, Optional, Callable

T = TypeVar('T')


def _heapify(data: List[T], n: int, i: int, comparator: Callable[[T, T], int]):
    largest = i
    left = 2 * i + 1
    right = 2 * i + 2

    if left < n and comparator(data[i], data[left]) < 0:
        largest = left

    if right < n and comparator(data[largest], data[right]) < 0:
        largest = right

    if largest != i:
        data[i], data[largest] = data[largest], data[i]
        _heapify(data, n, largest, comparator)


def sort(
        data: List[T],
        comparator: Optional[Callable[[T, T], int]] = None
):
    """
    Sorts a list in-place using Heap Sort. Not stable.
    - Time Complexity: O(n log n)
    - Space Complexity: O(1)
    """
    if comparator is None:
        comparator = lambda a, b: (a > b) - (a < b)

    n = len(data)

    # Build a maxheap
    for i in range(n // 2 - 1, -1, -1):
        _heapify(data, n, i, comparator)

    # Extract elements one by one
    for i in range(n - 1, 0, -1):
        data[i], data[0] = data[0], data[i]
        _heapify(data, i, 0, comparator)
