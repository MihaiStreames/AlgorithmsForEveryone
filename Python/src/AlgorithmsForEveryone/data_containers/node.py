from typing import TypeVar, Generic

from .inode import INode

T = TypeVar('T')


class Node(Generic[T], INode[T]):
    """
    Base node class implementing the INode interface.
    Provides common functionality for all node types.

    Args:
        data: The data stored in this node.

    Attributes:
        _data (T): Internal storage of the node's data.
    """

    def __init__(self, data: T):
        self._data = data

    def __str__(self) -> str:
        """
        Returns:
            A string representation of the node's data.
        """
        return str(self._data)

    @property
    def data(self) -> T:
        """
        Get the data stored in this node.

        Returns:
            The current data in the node.
        """
        return self._data

    @data.setter
    def data(self, new_data: T) -> None:
        """
        Set the data stored in this node.

        Args:
            new_data: The new data to store.
        """
        self._data = new_data

    def delete(self) -> None:
        """
        Clean up resources used by this node.

        Note:
            For this base class, nothing special is done.
            Subclasses may override to remove pointers.
        """
        pass
