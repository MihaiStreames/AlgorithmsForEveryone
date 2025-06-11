from typing import List, TypeVar, Optional, Callable

T = TypeVar('T')


def sort(
        data: List[T],
        comparator: Optional[Callable[[T, T], int]] = None
):
    """
    Sorts a list in-place using Shell Sort. Not stable.
    Uses Knuth's gap sequence.
    - Time Complexity: O(n log^2 n) to O(n^1.5)
    - Space Complexity: O(1)
    """
    if comparator is None:
        comparator = lambda a, b: (a > b) - (a < b)

    n = len(data)
    gap = 1
    while gap < n / 3:
        gap = 3 * gap + 1

    while gap >= 1:
        for i in range(gap, n):
            temp = data[i]
            j = i
            while j >= gap and comparator(data[j - gap], temp) > 0:
                data[j] = data[j - gap]
                j -= gap
            data[j] = temp
        gap = gap // 3
