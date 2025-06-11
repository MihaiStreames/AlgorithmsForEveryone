from typing import List, TypeVar, Optional, Callable

T = TypeVar('T')


def binary_search(
        data: List[T],
        key: T,
        comparator: Optional[Callable[[T, T], int]] = None
) -> int:
    """
    Searches for a key in a sorted list.

    - Time Complexity: O(log n)
    - Space Complexity: O(1)
    - Prerequisite: The list must be sorted.

    Args:
        data: The sorted list to search in.
        key: The element to search for.
        comparator: A function to compare elements. If None, standard
                    comparison is used.

    Returns:
        The index of the key if found, otherwise -1.
    """
    if comparator is None:
        comparator = lambda a, b: (a > b) - (a < b)

    low, high = 0, len(data) - 1

    while low <= high:
        mid = low + (high - low) // 2
        comparison = comparator(data[mid], key)

        if comparison < 0:
            low = mid + 1
        elif comparison > 0:
            high = mid - 1
        else:
            return mid

    return -1  # Key not found
