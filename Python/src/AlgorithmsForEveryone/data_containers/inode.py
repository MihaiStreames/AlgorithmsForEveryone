from typing import TypeVar, Generic, Protocol, runtime_checkable

T = TypeVar('T')


@runtime_checkable
class INode(Protocol, Generic[T]):
    """
    Node interface that defines the basic operations all nodes must implement.

    Attributes:
        data (T): The data stored in this node.
    """

    @property
    def data(self) -> T:
        """Get the data stored in this node."""
        ...

    @data.setter
    def data(self, new_data: T) -> None:
        """Set the data stored in this node."""
        ...

    def delete(self) -> None:
        """Clean up resources used by this node."""
        ...
