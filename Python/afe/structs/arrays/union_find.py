# -*- coding: utf-8 -*-

from typing import List
import uuid
from afe.exceptions import BaseUnionFindException


class UnionFind:

    """
    A Union-Find (or Disjoint Set Union) data structure. \n
    
    The Union-Find data structure represents a collection of disjoint sets (also known as classes). Each set is represented by
    a representative element, and elements in the same set share the same representative, and are considered equivalent.
    Therefore, we refer to these as "equivalence classes". \n

    The Union-Find structure supports the following basic operations:
    - `find(int p)`: Returns the representative of the set containing element `p`.
    - `union(int p, int q)`: Merges the sets containing elements `p` and `q`.

    The Union-Find structure can be implemented in various ways, including:
    - Naive Union: A simple but inefficient implementation that merges sets by iterating through all elements.
    - Quick Union: A more efficient implementation that uses a tree structure to represent sets, but can lead to tall trees.
    - Weighted Quick Union: An optimized version of Quick Union that uses the size or height of trees to keep them balanced.
    - Path Compression: An optimization that flattens the structure of the tree whenever `find` is called, speeding up future operations.

    The union-find data structure is particularly useful in scenarios where we need to efficiently manage and query connected components,
    such as in network connectivity, image processing, and clustering algorithms. \n
    """

    def __init__(self, n: int) -> None:

        """
        Initializes a UnionFind structure with n disjoint sets.

        Args:
            n: The number of elements, indexed from 0 to n-1.

        Raises:
            ValueError: If n is not a positive integer.

        """

        if n <= 0: raise BaseUnionFindException(f"Number of elements must be positive (got n={n})")

        self._classes: List[int] = [i for i in range(n)]
        self._heights: List[int] = [1 for _ in range(n)]
        self._size: int = n
        self._count: int = n
        self._uuid: str = str(uuid.uuid4())

    @classmethod
    def fromVector(cls, vec: List[int]) -> "UnionFind":
        """
        Initializes a UnionFind structure from a vector of representatives.

        Args:
            vec: A list of integers representing the initial representatives for each element.

        Returns:
            An instance of UnionFind initialized with the given vector.

        Raises:
            ValueError: If vec is empty or contains invalid representatives.
        """

        n: int = len(vec)
        if not vec or not all(isinstance(x, int) for x in vec):
            raise BaseUnionFindException("Input vector must be a non-empty list of integers.")
        instance: "UnionFind" = cls(n)
        for e in vec: instance._validateIndex(e)
        instance._classes = vec
        return instance

    @property
    def classes(self) -> List[int]:
        """List of classes (representatives) for each element."""
        return self._classes
    
    @property
    def heights(self) -> List[int]:
        """List of heights for each tree in the Union-Find structure."""
        return self._heights
    
    @property
    def size(self) -> int:
        """Returns the number of elements in the Union-Find structure."""
        return self._size
    
    @property
    def count(self) -> int:
        """Returns the number of disjoint sets (components) in the Union-Find structure."""
        return self._count

    @property
    def uuid(self) -> str:
        """Returns a unique identifier for the Union-Find structure."""
        return self._uuid

    def _validateIndex(self, p: int) -> None:
        """
        Validates that the index p is within the valid range [0, size-1].

        Args:
            p: The index to validate.

        Raises:
            BaseUnionFindException: If p is out of bounds.
        
        """
        if not (0 <= p < self.size):
            raise BaseUnionFindException(f"Index {p} is out of bounds for size {self._size}.")

    def isUnited(self) -> bool:
        """
        Checks if all elements are in the same set (i.e., if there is only one disjoint set).

        Returns:
            True if all elements are in the same set, False otherwise.
        """
        return self.count == 1

    def Find(self, p: int) -> int:
        
        """
        Finds the representative of the set containing element p.

        Args:
            p: The index of the element to find.

        Returns:
            The representative of the set containing element p.

        Raises:
            BaseUnionFindException: If p is out of bounds.
        """

        self._validateIndex(p)
        return self.classes[p]

    def Union(self, p: int, q: int) -> None:
        
        """
        Merges the sets containing elements p and q.

        Args:
            p: The index of the first element.
            q: The index of the second element.

        Raises:
            BaseUnionFindException: If either p or q is out of bounds.
        """

        p_root: int = self.Find(p)
        q_root: int = self.Find(q)

        # If both elements are already in the same set, terminate early
        if p_root == q_root:
            return
        
        # Iterate through all elements and update their representatives
        for idx in range(self.size):
            if self.Find(idx) == q_root:
                self.classes[idx] = p_root

        # Decrease the number of components
        self._count -= 1
        
    def FastFind(self, p: int) -> int:
        
        """
        Finds the representative of the set containing element p, using tree based logic.

        Args:
            p: The index of the element to find.

        Returns:
            The representative of the set containing element p.

        Raises:
            BaseUnionFindException: If p is out of bounds.
        """

        self._validateIndex(p)

        # We use tree-based logic to find the representative
        while self.classes[p] != p:
            p = self.classes[p]
        return p
    
    def FastUnion(self, p: int, q: int) -> None:
        """
        Merges the sets containing elements p and q, using tree based logic.

        Args:
            p: The index of the first element.
            q: The index of the second element.

        Raises:
            BaseUnionFindException: If either p or q is out of bounds.
        """

        p_root: int = self.FastFind(p)
        q_root: int = self.FastFind(q)

        # Merge the two sets by making one root point to the other
        if p_root != q_root:
            self.classes[q_root] = p_root
            self._count -= 1  # Decrease the number of components
            
    def CompressedFind(self, p: int) -> int:
        
        """
        Finds the representative of the set containing element p, using path compression.

        Args:
            p: The index of the element to find.

        Returns:
            The representative of the set containing element p.

        Raises:
            BaseUnionFindException: If p is out of bounds.
        """

        self._validateIndex(p)

        # For each element, we traverse up the tree to find the root,
        # and we compress the path by making each node point directly to the root.
        if self.classes[p] != p:
            self.classes[p] = self.CompressedFind(self.classes[p])
        return self.classes[p]
    
    def CompressedUnion(self, p: int, q: int) -> None:

        """
        Merges the sets containing elements p and q, using path compression.

        Args:
            p: The index of the first element.
            q: The index of the second element.

        Raises:
            BaseUnionFindException: If either p or q is out of bounds.
        """

        p_root: int = self.CompressedFind(p)
        q_root: int = self.CompressedFind(q)

        # Merge the two sets by making one root point to the other
        if p_root != q_root:
            
            # We can choose to merge the smaller tree under the larger tree
            if self.heights[p_root] > self.heights[q_root]:
                self.classes[q_root] = p_root
            elif self.heights[p_root] < self.heights[q_root]:
                self.classes[p_root] = q_root
            # We arbitrarily choose one root to be the new root if they are of equal height
            else:
                self.classes[q_root] = p_root
                self.heights[p_root] += 1

            self._count -= 1

    def __repr__(self) -> str:
        return f"UnionFind(size={self.size}, count={self.count}, uuid={self.uuid}) \nClasses: {self.classes}\nHeights: {self.heights}"
    
    def __hash__(self) -> int:
        return hash(self.uuid)
    
    def __eq__(self, other: object) -> bool:
        if not isinstance(other, UnionFind):
            return NotImplemented
        return self.uuid == other.uuid
    
    def __len__(self) -> int:
        return self.size
    
    def __getitem__(self, index: int) -> int:
        self._validateIndex(index)
        return self.classes[index]
    
