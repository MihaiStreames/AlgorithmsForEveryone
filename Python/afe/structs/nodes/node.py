from typing import Generic, TypeVar, Optional

T = TypeVar('T')


class Node(Generic[T]):
    """
    Represents a generic node in a linked data structure.
    Each node contains a value and a reference to the next node.
    """

    def __init__(self, value: T, next_node: Optional['Node[T]'] = None):
        """
        Constructs a new node.
        Args:
            value: The value to store in this node.
            next_node: The next node in the sequence, or None.
        """
        if value is None:
            raise ValueError("Value cannot be None")
        self.value: T = value
        self.next: Optional['Node[T]'] = next_node

    def has_next(self) -> bool:
        """Checks if this node has a next node."""
        return self.next is not None

    def __str__(self) -> str:
        return f"Node(value={self.value}, has_next={self.has_next()})"

    def __eq__(self, other) -> bool:
        if not isinstance(other, Node):
            return NotImplemented
        return self.value == other.value

    def __hash__(self) -> int:
        return hash(self.value)
