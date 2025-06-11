from typing import List, TypeVar, Optional, Callable

T = TypeVar('T')


def sort(
        data: List[T],
        comparator: Optional[Callable[[T, T], int]] = None
):
    """
    Sorts a list in-place using Bubble Sort. Stable.
    - Time Complexity: O(n^2)
    - Space Complexity: O(1)
    """
    if comparator is None:
        comparator = lambda a, b: (a > b) - (a < b)

    n = len(data)
    for i in range(n):
        swapped = False
        for j in range(0, n - i - 1):
            if comparator(data[j], data[j + 1]) > 0:
                data[j], data[j + 1] = data[j + 1], data[j]
                swapped = True
        if not swapped:
            break
