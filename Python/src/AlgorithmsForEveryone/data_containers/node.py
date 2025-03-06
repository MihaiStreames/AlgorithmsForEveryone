from typing import TypeVar, Generic

from .inode import INode

T = TypeVar('T')


class Node(Generic[T], INode[T]):
    """
    Base node class implementing the INode interface.
    Provides common functionality for all node types.
    """

    def __init__(self, data: T):
        self._data = data

    def __str__(self) -> str:
        return str(self._data)

    @property
    def data(self) -> T:
        """Get the data stored in this node."""
        return self._data

    @data.setter
    def data(self, new_data: T) -> None:
        """Set the data stored in this node."""
        self._data = new_data

    def delete(self) -> None:
        """Clean up resources used by this node."""
        pass
