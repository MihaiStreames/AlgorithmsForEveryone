from typing import Optional, TypeVar

from .node import Node

T = TypeVar('T')


class SimpleNode(Node[T]):
    """
    A simple (singly linked) node implementation for use in Queue and Stack.

    Args:
        data: The data stored in this node.
        next_node: Optional pointer to the next SimpleNode.

    Attributes:
        _next (Optional[SimpleNode[T]]): The next node in the structure.
    """

    def __init__(self, data: T, next_node: Optional['SimpleNode[T]'] = None):
        super().__init__(data)
        self._next = next_node

    def get_next(self) -> Optional['SimpleNode[T]']:
        """
        Get the next node in the singly linked structure.

        Returns:
            The next SimpleNode or None if none exists.
        """
        return self._next

    def set_next(self, next_node: Optional['SimpleNode[T]']) -> None:
        """
        Set the next node in the singly linked structure.

        Args:
            next_node: The new next node or None.
        """
        self._next = next_node

    def delete(self):
        """
        Disconnect this node from the structure by setting
        its next pointer to None.
        """
        self._next = None
