# -*- coding: utf-8 -*-

from typing import Generic, TypeVar, Optional
from afe.structs.nodes import LinkedNode
from afe.exceptions import BaseStackException
import uuid


T = TypeVar('T')


class Stack(Generic[T]):
    
    """
    A Last-In-First-Out (LIFO) stack model, based on a linked list structure. \n
    This stack allows elements to be pushed onto the top and popped from the top.
    It supports generic types and provides methods for pushing, popping, and peeking at the top element. \n
    Provides O(1) time complexity for push, pop, and peek operations.
    """

    def __init__(self) -> None:
        
        """
        Initializes an empty stack.
        """

        self._top: Optional[LinkedNode[T]] = None
        self._size: int = 0
        self._uuid: str = str(uuid.uuid4())

    @property
    def uuid(self) -> str:

        """Returns the unique identifier of the stack."""

        return self._uuid

    @property
    def top(self) -> Optional[LinkedNode[T]]:

        """Returns the top node of the stack, or None if empty."""

        return self._top

    @property
    def size(self) -> int:

        """Returns the number of elements in the stack."""

        return self._size
    
    @top.setter
    def top(self, node: Optional[LinkedNode[T]]) -> None:

        """Sets the top node of the stack."""

        if node is not None and not isinstance(node, LinkedNode):
            raise TypeError("Top must be a LinkedNode or None")
        
        self._top = node

    @size.setter
    def size(self, value: int) -> None:

        """Sets the size of the stack."""
        
        if not isinstance(value, int) or value < 0:
            raise BaseStackException("Size must be a non-negative integer")
        
        self._size = value

    def isEmpty(self) -> bool:

        """Checks if the stack is empty."""

        return self._size == 0

    def push(self, value: T) -> None:

        """Pushes a new value onto the top of the stack."""

        if value is None:
            raise BaseStackException("Value cannot be None")
        
        n: LinkedNode = LinkedNode(value)

        # If the stack is empty, set the new node as the top
        if self.isEmpty():
            self.top = n

        # Otherwise, link the new node to the current top
        else:
            n.next = self.top
            self.top = n

        self.size += 1

    def pop(self) -> Optional[T]:

        """
        Removes and returns the value from the top of the stack.
        
        Raises:
            IndexError: If the queue is empty.
        """
        
        if self.isEmpty():
            raise BaseStackException("Pop from an empty stack")
        
        # Store the value to return
        value: T = self.top.value
        
        # Move the top pointer to the next node
        self.top = self.top.next
        
        # Decrease the size of the stack
        self.size -= 1
        
        return value
    
    def peek(self) -> Optional[T]:

        """
        Returns the value at the top of the stack without removing it.
        
        Raises:
            IndexError: If the queue is empty.
        """
        
        if self.isEmpty():
            raise BaseStackException("Peek from an empty stack")
        
        return self.top.value
    
    def __len__(self) -> int:

        """Returns the number of elements in the stack."""
        
        return self.size
    
    def __eq__(self, other: object) -> bool:
        
        """Checks equality based on the unique identifier."""
        
        if not isinstance(other, Stack):
            return NotImplemented
        return self.uuid == other.uuid
    
    def __hash__(self) -> int:

        """Returns a hash of the stack based on its UUID."""
        
        return hash(self.uuid)

    def __contains__(self, item: T) -> bool:
        
        """Checks if an item is in the stack."""

        # Check if the value is None or if the stack is empty
        if item is None: raise BaseStackException("Value cannot be None")
        if self.isEmpty(): return False
        
        # Traverse the stack to check for the item
        current: Optional[LinkedNode[T]] = self.top
        while current is not None:
            if current.value == item:
                return True
            current = current.next

        # If we reach here, the item was not found
        return False

