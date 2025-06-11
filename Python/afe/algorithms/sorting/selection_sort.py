from typing import List, TypeVar, Optional, Callable

T = TypeVar('T')


def sort(
        data: List[T],
        comparator: Optional[Callable[[T, T], int]] = None
):
    """
    Sorts a list in-place using Selection Sort. Not stable.
    - Time Complexity: O(n^2)
    - Space Complexity: O(1)
    """
    if comparator is None:
        comparator = lambda a, b: (a > b) - (a < b)

    n = len(data)
    for i in range(n):
        min_idx = i
        for j in range(i + 1, n):
            if comparator(data[j], data[min_idx]) < 0:
                min_idx = j
        data[i], data[min_idx] = data[min_idx], data[i]
