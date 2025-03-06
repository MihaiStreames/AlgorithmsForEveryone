from typing import Optional, TypeVar

from .node import Node

T = TypeVar('T')


class SimpleNode(Node[T]):
    """
    A simple (singly linked) node implementation for use in Queue and Stack.
    """

    def __init__(self, data: T, next_node: Optional['SimpleNode[T]'] = None):
        super().__init__(data)
        self._next = next_node

    def get_next(self) -> Optional['SimpleNode[T]']:
        return self._next

    def set_next(self, next_node: Optional['SimpleNode[T]']) -> None:
        self._next = next_node

    def delete(self):
        self._next = None
