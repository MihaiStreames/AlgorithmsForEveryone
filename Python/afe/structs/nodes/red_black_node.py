# -*- coding: utf-8 -*-

from typing import Generic, TypeVar, Optional
from enum import Enum, auto
from .node import Node


T = TypeVar('T')


class NodeColor(Enum):

    """Enumeration for node colors in a Red-Black Tree."""
    
    NONE: int = auto()  # Represents a non-existent node, used for sentinel nodes
    RED: int = auto()
    BLACK: int = auto()

    @staticmethod
    def flip(c: 'NodeColor') -> 'NodeColor':
        """Flips the color of the node."""
        match c:
            case NodeColor.RED: return NodeColor.BLACK
            case NodeColor.BLACK: return NodeColor.RED
            case NodeColor.NONE: return NodeColor.NONE
            case _: return NodeColor.NONE

    def __str__(self) -> str:

        """Returns a string representation of the node color."""

        match self:
            case NodeColor.RED: return "RED"
            case NodeColor.BLACK: return "BLACK"
            case NodeColor.NONE: return "NONE"
            case _: return "UNKNOWN"


class RedBlackNode(Generic[T], Node[T]):

    """
    Represents a node in a Red-Black Tree.
    """

    def __init__(self, value: T, label: Optional[str] = None,
                 left: Optional['RedBlackNode[T]'] = None, 
                 right: Optional['RedBlackNode[T]'] = None, 
                 color: NodeColor = NodeColor.RED) -> None:
        """
        Initializes a RedBlackNode with the given value, lavel, left and right children, and color.

        Args:
            value (T): The data to be stored in the node.
            label (Optional[str]): An optional label for the node, useful for debugging or identification.
            left (Optional[RedBlackNode[T]]): The left child of the node.
            right (Optional[RedBlackNode[T]]): The right child of the node.
            color (NodeColor): The color of the node, defaults to RED.

        """
        super().__init__(value, label)
        self.left: Optional['RedBlackNode[T]'] = left
        self.right: Optional['RedBlackNode[T]'] = right
        self.color: NodeColor = color

    @property
    def color(self) -> NodeColor:
        """Returns the color of the node."""
        return self.color

    def isRed(self) -> bool:
        """Checks if this node is red."""
        return self.color == NodeColor.RED

    def isBlack(self) -> bool:
        """Checks if this node is black."""
        return self.color == NodeColor.BLACK
    
    @property
    def left(self) -> Optional['RedBlackNode[T]']:
        """Returns the left child of the node."""
        return self.left
    
    @property
    def right(self) -> Optional['RedBlackNode[T]']:
        """Returns the right child of the node."""
        return self.right
    
    @left.setter
    def left(self, node: Optional['RedBlackNode[T]']) -> None:
        """Sets the left child of the node."""
        self.left = node

    @right.setter
    def right(self, node: Optional['RedBlackNode[T]']) -> None:
        """Sets the right child of the node."""
        self.right = node

    def __str__(self) -> str:
        return f"RedBlackNode(value={self.value}, label={self.label}, color={self.color})"

