# -*- coding: utf-8 -*-

from typing import Generic, TypeVar, Optional
import uuid


T = TypeVar('T')


class Node(Generic[T]):
    
    """
    A generic node for use in linked data structures. \n
    This class can be used to create nodes for linked lists, trees, or any other data structure that
    requires a node with a value and optional children.
    """

    def __init__(self, value: T, label: Optional[str]) -> None:
        
        """
        Initializes a Node with a value and an optional reference.

        Args:
            value: The value stored in the node.
            label: An optional label string for the node, useful for debugging or identification.

        """

        self._value: T = value
        self._label: Optional[str] = label
        self._uuid: str = str(uuid.uuid4())

    @property
    def value(self) -> T:
        """Returns the value stored in the node."""
        return self._value
    
    @property
    def label(self) -> Optional[str]:
        """Returns the label of the node, if any."""
        return self._label
    
    @property
    def uuid(self) -> str:
        """Returns the unique identifier of the node."""
        return self._uuid
    
    def __repr__(self) -> str:
        return f"Node(value={self.value}, label={self.label}, uuid={self.uuid})"
    
    def __str__(self) -> str:
        return f"Node(value={self.value}, label={self.label})"
    
    def __eq__(self, other: object) -> bool:
        if not isinstance(other, Node):
            return NotImplemented
        return self.uuid == other.uuid
    
    def __hash__(self) -> int:
        return hash(self.uuid)

