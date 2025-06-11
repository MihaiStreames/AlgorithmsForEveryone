from typing import List


class UnionFind:
    """
    Implementation of the Union-Find (Disjoint Set Union) data structure.
    Manages a collection of disjoint sets and supports efficient union
    and find operations. This version uses weighted quick union with path
    compression for optimal performance.

    - Time Complexity: Nearly constant time on average for union and find.
    - Space Complexity: O(n).
    """

    def __init__(self, size: int):
        """
        Initializes a Union-Find structure with 'size' elements.
        Args:
            size: The number of elements (from 0 to size-1).
        """
        if size <= 0:
            raise ValueError("Size must be a positive integer")
        self._ids: List[int] = list(range(size))
        self._heights: List[int] = [1] * size  # For weighted union
        self._count: int = size  # Number of disjoint sets

    def find(self, p: int) -> int:
        """
        Finds the root of the component containing element p, with path compression.
        """
        if not (0 <= p < len(self._ids)):
            raise IndexError("Index out of bounds")

        root = p
        while root != self._ids[root]:
            root = self._ids[root]

        # Path compression
        while p != root:
            next_p = self._ids[p]
            self._ids[p] = root
            p = next_p

        return root

    def count(self) -> int:
        """Returns the number of disjoint sets."""
        return self._count

    def connected(self, p: int, q: int) -> bool:
        """Checks if two elements are in the same set."""
        return self.find(p) == self.find(q)

    def union(self, p: int, q: int):
        """
        Merges the sets containing elements p and q using weighted quick union.
        """
        root_p = self.find(p)
        root_q = self.find(q)

        if root_p == root_q:
            return

        # Union by rank (height)
        if self._heights[root_p] < self._heights[root_q]:
            self._ids[root_p] = root_q
        elif self._heights[root_p] > self._heights[root_q]:
            self._ids[root_q] = root_p
        else:
            self._ids[root_q] = root_p
            self._heights[root_p] += 1

        self._count -= 1
