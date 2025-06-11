from typing import List, TypeVar, Optional, Callable

T = TypeVar('T')


def _merge(data: List[T], left: List[T], right: List[T], comparator: Callable[[T, T], int]):
    i = j = k = 0
    while i < len(left) and j < len(right):
        if comparator(left[i], right[j]) <= 0:
            data[k] = left[i]
            i += 1
        else:
            data[k] = right[j]
            j += 1
        k += 1

    while i < len(left):
        data[k] = left[i]
        i += 1
        k += 1

    while j < len(right):
        data[k] = right[j]
        j += 1
        k += 1


def sort(
        data: List[T],
        comparator: Optional[Callable[[T, T], int]] = None
):
    """
    Sorts a list using Merge Sort. Stable.
    - Time Complexity: O(n log n)
    - Space Complexity: O(n)
    """
    if len(data) > 1:
        if comparator is None:
            comparator = lambda a, b: (a > b) - (a < b)

        mid = len(data) // 2
        left_half = data[:mid]
        right_half = data[mid:]

        sort(left_half, comparator)
        sort(right_half, comparator)

        _merge(data, left_half, right_half, comparator)
