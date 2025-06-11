from typing import Generic, TypeVar, Optional

T = TypeVar('T')

RED = True
BLACK = False


class RedBlackNode(Generic[T]):
    """Represents a node in a Red-Black Tree.

    New nodes are initialized as RED by default.

    Attributes:
        data (T): The data stored in the node.
        left (Optional['RedBlackNode[T]']): The left child of the node.
        right (Optional['RedBlackNode[T]']): The right child of the node.
        color (bool): The color of the node (RED or BLACK).
    """

    def __init__(self, data: T, color: bool = RED):
        """Initializes a RedBlackNode.

        Args:
            data: The data to be stored in the node.
            color: The color of the node, defaulting to RED.
        """
        self.data: T = data
        self.left: Optional['RedBlackNode[T]'] = None
        self.right: Optional['RedBlackNode[T]'] = None
        self.color: bool = color

    def is_red(self) -> bool:
        """Checks if this node is red."""
        return self.color == RED

    def is_black(self) -> bool:
        """Checks if this node is black."""
        return self.color == BLACK

    def __str__(self) -> str:
        color_str = "RED" if self.is_red() else "BLACK"
        return f"RedBlackNode(data={self.data}, color={color_str})"

    def __eq__(self, other) -> bool:
        if not isinstance(other, RedBlackNode):
            return NotImplemented
        return self.data == other.data and self.color == other.color

    def __hash__(self) -> int:
        return hash((self.data, self.color))
