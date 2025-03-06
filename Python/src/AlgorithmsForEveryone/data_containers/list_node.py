from typing import TypeVar, Optional

from .node import Node

T = TypeVar('T')


class ListNode(Node[T]):
    """
    Node for linked lists with next and previous pointers.

    Inherits from Node and adds `_next` and `_prev` attributes for
    doubly linked list functionality.

    Args:
        data: The data stored in this node.
        next_node: Optional pointer to the next ListNode.
        prev_node: Optional pointer to the previous ListNode.

    Attributes:
        _next (Optional[ListNode[T]]): The next node in the list.
        _prev (Optional[ListNode[T]]): The previous node in the list.
    """

    def __init__(self, data: T, next_node: Optional['ListNode[T]'] = None, prev_node: Optional['ListNode[T]'] = None):
        super().__init__(data)
        self._next = next_node
        self._prev = prev_node

    @property
    def next(self) -> Optional['ListNode[T]']:
        """
        Get the next node in the list.

        Returns:
            The next ListNode or None if there is no next node.
        """
        return self._next

    @next.setter
    def next(self, new_next: Optional['ListNode[T]']) -> None:
        """
        Set the next node in the list.

        Args:
            new_next: The new next node or None.
        """
        self._next = new_next

    @property
    def prev(self) -> Optional['ListNode[T]']:
        """
        Get the previous node in the list.

        Returns:
            The previous ListNode or None if there is no previous node.
        """
        return self._prev

    @prev.setter
    def prev(self, new_prev: Optional['ListNode[T]']) -> None:
        """
        Set the previous node in the list.

        Args:
            new_prev: The new previous node or None.
        """
        self._prev = new_prev

    def delete(self) -> None:
        """
        Disconnect this node from the list by removing references
        to next and previous nodes.
        """
        self._next = None
        self._prev = None

    def __str__(self) -> str:
        """
        Returns:
            A string representation of the node, including its data.
        """
        return f"ListNode({self.data})"
