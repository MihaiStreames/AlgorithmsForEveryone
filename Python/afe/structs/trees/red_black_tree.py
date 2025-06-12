# -*- coding: utf-8 -*-

from typing import Generic, TypeVar, Optional, Callable, List
from afe.structs.nodes import RedBlackNode, NodeColor
from afe.exceptions import BaseRBTException
import uuid


T = TypeVar('T')


class RedBlackTree(Generic[T]):

    """
    A Left-Leaning Red-Black Tree, a type of self-balancing BST. \n
    This tree maintains balance by ensuring that no node has two red links in a row,
    and that the tree remains approximately balanced at all times. \n
    Provides `O(log n)` time complexity for **`search`**, **`insert`** and **`delete`**.
    """

    DEFAULT_COMPARATOR: Callable[[T, T], int] = lambda x, y: (x > y) - (x < y)

    def __init__(self, comparator: Optional[Callable[[T, T], int]] = None) -> None:
        
        """
        Initializes the tree.

        Args:
            comparator: A function that returns `< 0`, `0`, or `> 0` to compare two elements. \
                If **`None`**, standard comparison operators are used.

        """

        self._root: Optional[RedBlackNode[T]] = None
        self._comparator: Callable[[T, T], int] = comparator or self.DEFAULT_COMPARATOR
        self._size: int = 0
        self._uuid: str = str(uuid.uuid4())

    @staticmethod
    def _isRed(node: Optional[RedBlackNode[T]]) -> bool:

        """Checks if the given node is red."""

        return node is not None and node.isRed()

    @property
    def root(self) -> Optional[RedBlackNode[T]]:

        """Returns the root node of the tree."""

        return self._root
    
    @root.setter
    def root(self, node: Optional[RedBlackNode[T]]) -> None:

        """Sets the root node of the tree."""

        if node is not None and not isinstance(node, RedBlackNode):
            raise TypeError("Root must be a RedBlackNode or None")
        
        self._root = node

    def isEmpty(self) -> bool:

        """Checks if the tree is empty."""

        return self.size == 0

    @property
    def size(self) -> int:

        """Returns the number of elements in the tree."""

        return self._size

    @size.setter
    def size(self, value: int) -> None:

        """Sets the size of the tree."""

        if not isinstance(value, int) or value < 0:
            raise BaseRBTException("Size must be a non-negative integer")
        
        self._size = value

    @property
    def uuid(self) -> str:

        """Returns the unique identifier of the tree."""

        return self._uuid

    @staticmethod
    def _rotateLeft(h: RedBlackNode[T]) -> RedBlackNode[T]:

        """Performs a left rotation on the given node."""

        x: RedBlackNode[T] = h.right
        h.right = x.left
        x.left = h
        x.color = h.color
        h.color = NodeColor.RED
        return x

    @staticmethod
    def _rotateRight(h: RedBlackNode[T]) -> RedBlackNode[T]:

        """Performs a right rotation on the given node."""

        x: RedBlackNode[T] = h.left
        h.left = x.right
        x.right = h
        x.color = h.color
        h.color = NodeColor.RED
        return x

    @staticmethod
    def _flipColors(h: RedBlackNode[T]) -> None:

        """Flips the colors of the given node and its children."""

        # Practical, isn't it? >w<
        h.color = NodeColor.flip(h.color)
        h.left.color = NodeColor.flip(h.left.color)
        h.right.color = NodeColor.flip(h.right.color)

    def _put(self, node: Optional[RedBlackNode[T]], data: T) -> RedBlackNode[T]:
        
        """Inserts data into the tree, maintaining balance."""

        # If the node is None (-> empty), create a new RedBlackNode with default color RED.
        if node is None:
            return RedBlackNode(data, NodeColor.RED)
        
        # Compare the data with the current node's data using the comparator function.
        cmp: int = self._comparator(data, node.data)
        if cmp < 0:     node.left  = self._put(node.left,  data)
        elif cmp > 0:   node.right = self._put(node.right, data)

        # LLRB tree balancing operations (thx mihai)

        # 1. Rotate left if right child is red and left child is black
        
        if self._isRed(node.right) and not self._isRed(node.left):
            node = self._rotateLeft(node)

        # 2. Rotate right if left child and left-left grandchild are both red
        if self._isRed(node.left) and self._isRed(node.left.left):
            node = self._rotateRight(node)

        # 3. Flip colors if both children are red
        if self._isRed(node.left) and self._isRed(node.right):
            self._flipColors(node)

        return node
    
    def insert(self, data: T) -> None:

        """Inserts data into the tree, maintaining balance."""

        if data is None:
            raise BaseRBTException("Cannot insert None")
        
        self.root = self._put(self.root, data)
        if self.root is not None: self.root.color = NodeColor.BLACK

    def extend(self, data: List[T]) -> None:
        
        """Inserts multiple elements into the tree."""

        if not isinstance(data, list):
            raise TypeError("Data must be a list")
        
        for item in data: self.insert(item)

    def delete(self, data: T) -> None:

        """Deletes data from the tree. Not implemented yet."""

        # TODO: Implement the delete operation for the Red-Black Tree.
        
        raise NotImplementedError("Delete operation is not implemented yet")
    
    def search(self, data: T) -> Optional[RedBlackNode[T]]:
        
        """Searches for data in the tree and returns the node if found."""

        # Check if data is None before searching
        if data is None:
            raise BaseRBTException("Cannot search for None")
        
        # Start searching from the root node, using the comparator function to navigate the tree.
        current: Optional[RedBlackNode[T]] = self.root
        while current is not None:

            cmp: int = self._comparator(data, current.data)
            
            # Navigate left or right based on the comparison result.
            if cmp < 0: current = current.left
            elif cmp > 0: current = current.right

            # If the data matches, return the current node.
            else: return current
        
        # If the data is not found, return None.
        return None
    
    def __contains__(self, item: T) -> bool:
        
        """Checks if the tree contains the specified item."""

        # Use the search method to check for existence.
        return self.search(item) is not None
    
    def __len__(self) -> int:

        """Returns the number of elements in the tree."""

        return self.size
    
    def __eq__(self, other: object) -> bool:

        """Checks if two trees are equal based on their UUIDs."""

        if not isinstance(other, RedBlackTree):
            return NotImplemented
        
        # Compare UUIDs for equality
        return self.uuid == other.uuid
    
    def __hash__(self) -> int:

        """Returns a hash of the tree based on its UUID."""

        return hash(self.uuid)
    
