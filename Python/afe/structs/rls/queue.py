# -*- coding: utf-8 -*-

from typing import Generic, TypeVar, Optional
from afe.structs.nodes import LinkedNode
from afe.exceptions import BaseQueueException
import uuid


T = TypeVar('T')


class Queue(Generic[T]):

    """
    A First-In-First-Out (FIFO) queue model, based on a linked list structure. \n
    This queue allows elements to be added to the rear and removed from the front.
    It supports generic types and provides methods for enqueueing, dequeueing, and peeking at the front element. \n
    Provides O(1) time complexity for enqueue, dequeue, and peek operations.
    """

    def __init__(self) -> None:

        """
        Initializes an empty queue.
        """

        self._front: Optional[LinkedNode[T]] = None
        self._rear: Optional[LinkedNode[T]] = None
        self._size: int = 0
        self._uuid: str = str(uuid.uuid4())

    @property
    def uuid(self) -> str:

        """Returns the unique identifier of the queue."""

        return self._uuid

    @property
    def front(self) -> Optional[LinkedNode[T]]:

        """Returns the front node of the queue, or None if empty."""

        return self._front
    
    @property
    def rear(self) -> Optional[LinkedNode[T]]:

        """Returns the rear node of the queue, or None if empty."""

        return self._rear
    
    @property
    def size(self) -> int:

        """Returns the number of elements in the queue."""

        return self._size
    
    @front.setter
    def front(self, value: Optional[LinkedNode[T]]) -> None:
        
        """Sets the front node of the queue."""

        # Ensure the front is either a LinkedNode or None
        if value is not None and not isinstance(value, LinkedNode):
            raise TypeError("Front must be a Node or None")
        
        self._front = value

    @rear.setter
    def rear(self, value: Optional[LinkedNode[T]]) -> None:

        """Sets the rear node of the queue."""

        # Ensure the rear is either a LinkedNode or None
        if value is not None and not isinstance(value, LinkedNode):
            raise TypeError("Rear must be a Node or None")
        
        self._rear = value

    @size.setter
    def size(self, value: int) -> None:

        """Sets the size of the queue."""

        # Ensure the size is a non-negative integer
        if not isinstance(value, int) or value < 0:
            raise BaseQueueException("Size must be a non-negative integer")
        
        self._size = value

    def isEmpty(self) -> bool:

        """Returns True if the queue contains no elements."""

        return self.size == 0

    def enqueue(self, value: T) -> None:
        
        """Adds an element to the rear of the queue."""

        # Check if the value is None before enqueuing
        if value is None:
            raise BaseQueueException("Value cannot be None")

        # Create a new LinkedNode with the given value
        n: LinkedNode = LinkedNode(value, None)

        # If queue is empty, set both front and rear to the new node
        if self.isEmpty():
            self.front = n
            self.rear = n
    
        # If queue is not empty, add to the rear
        else:
            self.rear.next = n
            self.rear = n
        
        # Increment the size of the queue
        self.size += 1

    def dequeue(self) -> T:
        
        """
        Removes and returns the element at the front of the queue.

        Raises:
            IndexError: If the queue is empty.
        """

        # Check if the queue is empty before dequeuing
        if self.isEmpty():
            raise BaseQueueException("Cannot dequeue from empty queue")

        # Store the value to return, then update front
        value: T = self.front.value
        self.front = self.front.next
        self.size -= 1

        # If queue becomes empty, update rear reference
        if self.isEmpty(): self.rear = None

        # Return the value of the dequeued node
        return value

    def peek(self) -> T:

        """
        Returns the element at the front without removing it.

        Raises:
            IndexError: If the queue is empty.
        """

        # Check if the queue is empty before peeking
        if self.isEmpty():
            raise BaseQueueException("Cannot peek into an empty queue")
        
        return self.front.value

    def __len__(self) -> int:

        """Returns the number of elements in the queue."""

        return self.size

    def __hash__(self) -> int:

        """Returns a hash of the queue based on its UUID."""

        return hash(self.uuid)

    def __eq__(self, other: object) -> bool:

        """Checks if two queues are equal based on their UUIDs."""

        if not isinstance(other, Queue):
            return NotImplemented
        
        # Compare UUIDs for equality
        return self.uuid == other.uuid

    def __contains__(self, item: T) -> bool:

        """Checks if the queue contains the specified element."""
        
        # Check if the value is None or if the queue is empty
        if item is None: raise BaseQueueException("Value cannot be None")
        if self.isEmpty(): return False

        # Start from the front and traverse the queue
        current: Optional[LinkedNode[T]] = self.front

        # Traverse the linked nodes until we find the value or reach the end
        while current is not None:
            if current.value == item:
                return True
            current = current.next

        # If we reach here, the value was not found
        return False

