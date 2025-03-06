from node import Node
from typing import TypeVar, Optional

T = TypeVar('T')

class ListNode(Node[T]):
    """
    Node used for linked lists with optional previous and next pointers.
    """

    def __init__(self, data: T, next_node: Optional['ListNode[T]'] = None, prev_node: Optional['ListNode[T]'] = None):
        """
        Initialize a ListNode with data, and optional next and previous pointers.
        :param data: The data stored in the node.
        :param next_node: The next node in the linked list.
        :param prev_node: The previous node in the linked list.
        """
        super().__init__(data)
        self._next = next_node
        self._prev = prev_node

    @property
    def next(self) -> Optional['ListNode[T]']:
        """
        Get the next node in the linked list.
        :return: The next node.
        """
        return self._next

    @property
    def prev(self) -> Optional['ListNode[T]']:
        """
        Get the previous node in the linked list.
        :return: The previous node.
        """
        return self._prev

    @next.setter
    def next(self, new_next: Optional['ListNode[T]']) -> None:
        """
        Set the next node in the linked list.
        :param new_next: The new next node.
        """
        self._next = new_next

    @prev.setter
    def prev(self, new_prev: Optional['ListNode[T]']) -> None:
        """
        Set the previous node in the linked list.
        :param new_prev: The new previous node.
        """
        self._prev = new_prev