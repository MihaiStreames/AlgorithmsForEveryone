import random
from typing import List, TypeVar, Optional, Callable

T = TypeVar('T')


def _partition(data: List[T], low: int, high: int, comparator: Callable[[T, T], int]) -> int:
    # Use random pivot to avoid worst-case
    pivot_index = random.randint(low, high)
    data[pivot_index], data[high] = data[high], data[pivot_index]
    pivot = data[high]

    i = low - 1
    for j in range(low, high):
        if comparator(data[j], pivot) <= 0:
            i += 1
            data[i], data[j] = data[j], data[i]

    data[i + 1], data[high] = data[high], data[i + 1]
    return i + 1


def _quick_sort_recursive(data: List[T], low: int, high: int, comparator: Callable[[T, T], int]):
    if low < high:
        pi = _partition(data, low, high, comparator)
        _quick_sort_recursive(data, low, pi - 1, comparator)
        _quick_sort_recursive(data, pi + 1, high, comparator)


def sort(
        data: List[T],
        comparator: Optional[Callable[[T, T], int]] = None
):
    """Sorts a list in-place using the Quick Sort algorithm.

    This implementation is not stable. It uses a randomized pivot to achieve
    O(n log n) average time complexity.

    Args:
        data: The list of elements to sort.
        comparator: A function for comparing two elements. If None, default
            comparison operators are used.
    """
    if not data:
        return
    if comparator is None:
        comparator = lambda a, b: (a > b) - (a < b)

    _quick_sort_recursive(data, 0, len(data) - 1, comparator)
