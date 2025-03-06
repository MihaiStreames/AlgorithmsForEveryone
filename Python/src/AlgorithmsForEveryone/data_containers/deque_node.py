from typing import TypeVar, Optional

from .node import Node

T = TypeVar('T')


class DequeNode(Node[T]):
    """
    Node for deque implementations with next and previous pointers.

    Args:
        data: The data stored in this node.
        next_node: Optional pointer to the next DequeNode.
        prev_node: Optional pointer to the previous DequeNode.

    Attributes:
        _next (Optional[DequeNode[T]]): The next node.
        _prev (Optional[DequeNode[T]]): The previous node.
    """

    def __init__(self, data: T, next_node: Optional['DequeNode[T]'] = None, prev_node: Optional['DequeNode[T]'] = None):
        super().__init__(data)
        self._next = next_node
        self._prev = prev_node

    @property
    def next(self) -> Optional['DequeNode[T]']:
        """
        Get the next node in the deque.

        Returns:
            The next DequeNode or None if none exists.
        """
        return self._next

    @next.setter
    def next(self, new_next: Optional['DequeNode[T]']) -> None:
        """
        Set the next node in the deque.

        Args:
            new_next: The new next node or None.
        """
        self._next = new_next

    @property
    def prev(self) -> Optional['DequeNode[T]']:
        """
        Get the previous node in the deque.

        Returns:
            The previous DequeNode or None if none exists.
        """
        return self._prev

    @prev.setter
    def prev(self, new_prev: Optional['DequeNode[T]']) -> None:
        """
        Set the previous node in the deque.

        Args:
            new_prev: The new previous node or None.
        """
        self._prev = new_prev

    def delete(self) -> None:
        """
        Disconnect this node from the deque by removing references
        to the next and previous nodes.
        """
        self._next = None
        self._prev = None

    def __str__(self) -> str:
        """
        Returns:
            A string representation of the node and its data.
        """
        return f"DequeNode({self.data})"
