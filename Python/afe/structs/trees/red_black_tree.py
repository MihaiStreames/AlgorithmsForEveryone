from typing import Generic, TypeVar, Optional, Callable

from ..nodes import RedBlackNode

T = TypeVar('T')


class RedBlackTree(Generic[T]):
    """A Left-Leaning Red-Black Tree, a type of self-balancing BST.

    Provides O(log n) time complexity for search, insert, and delete.
    """

    def __init__(self, comparator: Optional[Callable[[T, T], int]] = None):
        """Initializes the tree.

        Args:
            comparator: A function that returns <0, 0, or >0 to compare two
                elements. If None, standard comparison operators are used.
        """
        self._root: Optional[RedBlackNode[T]] = None
        self._comparator = comparator or (lambda a, b: (a > b) - (a < b))

    @staticmethod
    def _is_red(node: Optional[RedBlackNode[T]]) -> bool:
        return node is not None and node.is_red()

    @staticmethod
    def _rotate_left(h: RedBlackNode[T]) -> RedBlackNode[T]:
        x = h.right
        h.right = x.left
        x.left = h
        x.color = h.color
        h.color = RED
        return x

    @staticmethod
    def _rotate_right(h: RedBlackNode[T]) -> RedBlackNode[T]:
        x = h.left
        h.left = x.right
        x.right = h
        x.color = h.color
        h.color = RED
        return x

    @staticmethod
    def _flip_colors(h: RedBlackNode[T]):
        h.color = not h.color
        h.left.color = not h.left.color
        h.right.color = not h.right.color

    def insert(self, data: T):
        """Inserts data into the tree, maintaining balance."""
        if data is None: raise ValueError("Cannot insert None")
        self._root = self._put(self._root, data)
        if self._root is not None: self._root.color = BLACK

    def _put(self, node: Optional[RedBlackNode[T]], data: T) -> RedBlackNode[T]:
        if node is None: return RedBlackNode(data, RED)

        cmp = self._comparator(data, node.data)
        if cmp < 0:
            node.left = self._put(node.left, data)
        elif cmp > 0:
            node.right = self._put(node.right, data)

        # LLRB tree balancing operations
        # 1. Rotate left if right child is red and left child is black
        if self._is_red(node.right) and not self._is_red(node.left):
            node = self._rotate_left(node)

        # 2. Rotate right if left child and left-left grandchild are both red
        if self._is_red(node.left) and self._is_red(node.left.left):
            node = self._rotate_right(node)

        # 3. Flip colors if both children are red
        if self._is_red(node.left) and self._is_red(node.right):
            self._flip_colors(node)

        return node
