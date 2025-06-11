from typing import Generic, TypeVar, Optional

T = TypeVar('T')


class Node(Generic[T]):
    """A generic node for use in linked data structures.

    Attributes:
        value (T): The data stored in the node.
        next (Optional['Node[T]']): Reference to the next node in the sequence.
    """

    def __init__(self, value: T, next_node: Optional['Node[T]'] = None):
        """Initializes a Node.

        Args:
            value: The data to store in the node. Cannot be None.
            next_node: The next node in the sequence. Defaults to None.

        Raises:
            ValueError: If the provided value is None.
        """
        if value is None:
            raise ValueError("Value cannot be null")
        self.value: T = value
        self.next: Optional['Node[T]'] = next_node

    def has_next(self) -> bool:
        """Checks if this node has a subsequent node."""
        return self.next is not None

    def __str__(self) -> str:
        return f"Node(value={self.value}, has_next={self.has_next()})"

    def __eq__(self, other) -> bool:
        if not isinstance(other, Node):
            return NotImplemented
        return self.value == other.value

    def __hash__(self) -> int:
        return hash(self.value)
