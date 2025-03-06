from typing import TypeVar, Optional

from .node import Node

T = TypeVar('T')


class ListNode(Node[T]):
    """
    Node for linked lists with next and previous pointers.
    """

    def __init__(self, data: T, next_node: Optional['ListNode[T]'] = None, prev_node: Optional['ListNode[T]'] = None):
        super().__init__(data)
        self._next = next_node
        self._prev = prev_node

    @property
    def next(self) -> Optional['ListNode[T]']:
        """Get the next node."""
        return self._next

    @next.setter
    def next(self, new_next: Optional['ListNode[T]']) -> None:
        """Set the next node."""
        self._next = new_next

    @property
    def prev(self) -> Optional['ListNode[T]']:
        """Get the previous node."""
        return self._prev

    @prev.setter
    def prev(self, new_prev: Optional['ListNode[T]']) -> None:
        """Set the previous node."""
        self._prev = new_prev

    def delete(self) -> None:
        """Disconnect this node from the list."""
        self._next = None
        self._prev = None

    def __str__(self) -> str:
        return f"ListNode({self.data})"
