from typing import Generic, TypeVar, Optional, Callable

from afe.structs.nodes.red_black_node import RedBlackNode, RED, BLACK

T = TypeVar('T')


class RedBlackTree(Generic[T]):
    """
    Implementation of a Left-Leaning Red-Black Tree.
    This is a self-balancing binary search tree.

    - Time Complexity: O(log n) for search, insert, and delete.
    - Space Complexity: O(n).
    """

    def __init__(self, comparator: Optional[Callable[[T, T], int]] = None):
        """
        Constructs a Red-Black Tree.
        Args:
            comparator: A function that compares two elements, returning
                        <0 if a < b, 0 if a == b, >0 if a > b.
                        If None, elements must support standard comparison.
        """
        self._root: Optional[RedBlackNode[T]] = None
        self._comparator = comparator or (lambda a, b: (a > b) - (a < b))

    @staticmethod
    def _is_red(node: Optional[RedBlackNode[T]]) -> bool:
        """Checks if a node is red (null nodes are black)."""
        if node is None:
            return False
        return node.is_red()

    @staticmethod
    def _rotate_left(h: RedBlackNode[T]) -> RedBlackNode[T]:
        """Rotates a right-leaning red link to lean left."""
        x = h.right
        h.right = x.left
        x.left = h
        x.color = h.color
        h.color = RED
        return x

    @staticmethod
    def _rotate_right(h: RedBlackNode[T]) -> RedBlackNode[T]:
        """Rotates a left-leaning red link to lean right."""
        x = h.left
        h.left = x.right
        x.right = h
        x.color = h.color
        h.color = RED
        return x

    @staticmethod
    def _flip_colors(h: RedBlackNode[T]):
        """Flips the colors of a node and its two red children."""
        h.color = RED
        h.left.color = BLACK
        h.right.color = BLACK

    def insert(self, data: T):
        """Inserts data into the tree."""
        if data is None:
            raise ValueError("Cannot insert None")
        self._root = self._put(self._root, data)
        self._root.color = BLACK

    def _put(self, node: Optional[RedBlackNode[T]], data: T) -> RedBlackNode[T]:
        """Recursive helper to insert a node."""
        if node is None:
            return RedBlackNode(data)

        cmp = self._comparator(data, node.data)
        if cmp < 0:
            node.left = self._put(node.left, data)
        elif cmp > 0:
            node.right = self._put(node.right, data)
        else:
            # Key already exists, no-op
            return node

        # Balancing operations
        if self._is_red(node.right) and not self._is_red(node.left):
            node = self._rotate_left(node)
        if self._is_red(node.left) and self._is_red(node.left.left):
            node = self._rotate_right(node)
        if self._is_red(node.left) and self._is_red(node.right):
            self._flip_colors(node)

        return node
