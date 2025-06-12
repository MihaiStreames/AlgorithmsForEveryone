# -*- coding: utf-8 -*-

from typing import Generic, TypeVar, Optional
from .node import Node


T = TypeVar('T')


class LinkedNode(Node[T], Generic[T]):

    """
    A node for linked data structures that can reference other nodes. \n
    This class extends the basic Node class to include references to previous and next nodes,
    allowing it to be used in linked lists, doubly linked lists, or other structures that require
    bidirectional traversal.
    """

    def __init__(self, value: T, label: Optional[str] = None,
                 prev: Optional['LinkedNode[T]'] = None,
                 next: Optional['LinkedNode[T]'] = None
        ) -> None:
        
        """
        Initializes a LinkedNode with a value, optional reference, and optional previous and next nodes.

        Args:
            value: The value stored in the node.
            label: An optional label for the node, useful for debugging or identification.
            prev: An optional reference to the previous LinkedNode.
            next: An optional reference to the next LinkedNode.
        """

        super().__init__(value, label)
        self._prev: Optional['LinkedNode[T]'] = prev
        self._next: Optional['LinkedNode[T]'] = next

    @property
    def prev(self) -> Optional['LinkedNode[T]']:
        """Returns the previous LinkedNode, if any."""
        return self._prev
    
    @property
    def next(self) -> Optional['LinkedNode[T]']:
        """Returns the next LinkedNode, if any."""
        return self._next
    
    @prev.setter
    def prev(self, node: Optional['LinkedNode[T]']) -> None:
        """Sets the previous LinkedNode."""
        self._prev = node

    @next.setter
    def next(self, node: Optional['LinkedNode[T]']) -> None:
        """Sets the next LinkedNode."""
        self._next = node

