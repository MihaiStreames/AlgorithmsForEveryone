from typing import Generic, TypeVar, Optional

from ..nodes import Node

T = TypeVar('T')


class Queue(Generic[T]):
    """A First-In-First-Out (FIFO) queue implemented with a linked list.

    Provides O(1) time complexity for enqueue, dequeue, and peek operations.
    """

    def __init__(self):
        """Initializes an empty queue."""
        self._front: Optional[Node[T]] = None
        self._rear: Optional[Node[T]] = None
        self._size: int = 0

    def enqueue(self, value: T):
        """Adds an element to the rear of the queue."""
        if value is None: raise ValueError("Value cannot be None")

        new_node = Node(value)

        if self.is_empty():
            # First element in the queue
            self._front = self._rear = new_node
        else:
            # Add to the rear
            self._rear.next = new_node
            self._rear = new_node
        self._size += 1

    def dequeue(self) -> T:
        """Removes and returns the element at the front of the queue.

        Raises:
            IndexError: If the queue is empty.
        """
        if self.is_empty(): raise IndexError("Cannot dequeue from empty queue")

        value = self._front.value
        self._front = self._front.next
        self._size -= 1

        # If queue becomes empty, update rear reference
        if self._front is None: self._rear = None
        return value

    def peek(self) -> T:
        """Returns the element at the front without removing it.

        Raises:
            IndexError: If the queue is empty.
        """
        if self.is_empty(): raise IndexError("Cannot peek into an empty queue")
        return self._front.value

    def is_empty(self) -> bool:
        """Returns True if the queue contains no elements."""
        return self._front is None

    def __len__(self) -> int:
        """Returns the number of elements in the queue."""
        return self._size

    def __contains__(self, value: T) -> bool:
        """Checks if the queue contains the specified element."""
        if value is None: raise ValueError("Value cannot be None")

        current = self._front
        while current:
            if current.value == value:
                return True
            current = current.next
        return False
