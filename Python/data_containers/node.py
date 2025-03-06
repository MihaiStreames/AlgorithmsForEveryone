from typing import TypeVar, Generic

T = TypeVar('T')

class Node(Generic[T]):
    """
    Simple node class used for basic data storage.
    """

    def __init__(self, data: T):
        """
        Initialize a Node with data.
        :param data: The data stored in the node.
        """
        self._data = data

    def __str__(self) -> str:
        """
        Return the string representation of the data stored in the node.
        :return: The string representation of the data stored in the node.
        """
        return str(self._data)

    @property
    def data(self) -> T:
        """
        Get the data stored in the node.
        :return: The data stored in the node.
        """
        return self._data

    @data.setter
    def data(self, new_data: T) -> None:
        """
        Set the data stored in the node.
        :param new_data: The new data to be stored in the node.
        """
        self._data = new_data