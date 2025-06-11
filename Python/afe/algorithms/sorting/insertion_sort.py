from typing import List, TypeVar, Optional, Callable

T = TypeVar('T')


def sort(
        data: List[T],
        comparator: Optional[Callable[[T, T], int]] = None
):
    """
    Sorts a list in-place using Insertion Sort. Stable.
    - Time Complexity: O(n^2) worst-case, O(n) best-case.
    - Space Complexity: O(1)
    """
    if comparator is None:
        comparator = lambda a, b: (a > b) - (a < b)

    for i in range(1, len(data)):
        key = data[i]
        j = i - 1
        while j >= 0 > comparator(key, data[j]):
            data[j + 1] = data[j]
            j -= 1
        data[j + 1] = key
