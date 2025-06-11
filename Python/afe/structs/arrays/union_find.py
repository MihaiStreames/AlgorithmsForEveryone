from typing import List


class UnionFind:
    """A Union-Find (or Disjoint Set Union) data structure.

    This class provides multiple implementations for educational purposes,
    from naive to optimized.
    """

    def __init__(self, n: int):
        """Initializes a UnionFind structure with n disjoint sets.

        Args:
            n: The number of elements, indexed from 0 to n-1.

        Raises:
            ValueError: If n is not a positive integer.
        """
        if n <= 0:
            raise ValueError("Number of elements must be positive")

        self._ids: List[int] = list(range(n))
        self._heights: List[int] = [1] * n
        self.size: int = n
        self._count: int = n

    def _validate_index(self, p: int):
        if not (0 <= p < self.size):
            raise IndexError("Index out of bounds")

    def naive_union(self, p: int, q: int):
        """Performs a naive (slow) union of two elements. O(n)."""
        self._validate_index(p)
        self._validate_index(q)
        p_id, q_id = self._ids[p], self._ids[q]
        if p_id == q_id: return
        for i in range(self.size):
            if self._ids[i] == p_id:
                self._ids[i] = q_id
        self._count -= 1

    def quick_union(self, p: int, q: int):
        """Performs a quick union (unweighted). Can lead to tall trees."""
        self._validate_index(p)
        self._validate_index(q)
        p_root, q_root = self.find(p), self.find(q)
        if p_root == q_root: return
        self._ids[p_root] = q_root
        self._count -= 1

    def weighted_quick_union(self, p: int, q: int):
        """Performs a weighted quick union, linking smaller trees to larger ones."""
        self._validate_index(p)
        self._validate_index(q)

        p_root, q_root = self.compressed_find(p), self.compressed_find(q)
        if p_root == q_root: return

        if self._heights[p_root] < self._heights[q_root]:
            self._ids[p_root] = q_root  # connect smaller tree to larger tree
        elif self._heights[p_root] > self._heights[q_root]:
            self._ids[q_root] = p_root  # connect smaller tree to larger tree
        else:
            self._ids[q_root] = p_root  # connect q's root to p's root
            self._heights[p_root] += 1  # increase height of the new root
        self._count -= 1  # decrease the number of components

    def find(self, p: int) -> int:
        """Finds the root of an element without path compression."""
        self._validate_index(p)

        while p != self._ids[p]:
            p = self._ids[p]
        return p

    def compressed_find(self, p: int) -> int:
        """Finds the root of an element with path compression."""
        self._validate_index(p)

        root = p
        while root != self._ids[root]:
            root = self._ids[root]
        while p != root:  # Path compression
            next_p = self._ids[p]
            self._ids[p] = root
            p = next_p
        return root

    def recursive_compressed_find(self, p: int) -> int:
        """Finds the root of an element with recursive path compression."""
        self._validate_index(p)

        if p != self._ids[p]:
            self._ids[p] = self.recursive_compressed_find(self._ids[p])
        return self._ids[p]

    def count(self) -> int:
        """Returns the number of disjoint sets."""
        return self._count
