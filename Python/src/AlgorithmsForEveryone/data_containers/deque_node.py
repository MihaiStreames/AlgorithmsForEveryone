from typing import TypeVar, Optional

from .node import Node

T = TypeVar('T')


class DequeNode(Node[T]):
    """
    Node for deque implementations with next and previous pointers.
    """

    def __init__(self, data: T, next_node: Optional['DequeNode[T]'] = None, prev_node: Optional['DequeNode[T]'] = None):
        super().__init__(data)
        self._next = next_node
        self._prev = prev_node

    @property
    def next(self) -> Optional['DequeNode[T]']:
        """Get the next node."""
        return self._next

    @next.setter
    def next(self, new_next: Optional['DequeNode[T]']) -> None:
        """Set the next node."""
        self._next = new_next

    @property
    def prev(self) -> Optional['DequeNode[T]']:
        """Get the previous node."""
        return self._prev

    @prev.setter
    def prev(self, new_prev: Optional['DequeNode[T]']) -> None:
        """Set the previous node."""
        self._prev = new_prev

    def delete(self) -> None:
        """Disconnect this node from the deque."""
        self._next = None
        self._prev = None

    def __str__(self) -> str:
        return f"DequeNode({self.data})"
