from abc import ABC, abstractmethod
from typing import Generic, TypeVar

T = TypeVar('T')

class Queue(ABC, Generic[T]):
    """
    Abstract base class for queue data structures.
    """

    @abstractmethod
    def enqueue(self, item: T) -> None:
        """Add an item to the queue."""
        pass

    @abstractmethod
    def dequeue(self) -> T:
        """Remove and return the front item from the queue."""
        pass

    @abstractmethod
    def peek(self) -> T:
        """Return the front item without removing it."""
        pass

    @abstractmethod
    def is_empty(self) -> bool:
        """Check if the queue is empty."""
        pass

    @abstractmethod
    def size(self) -> int:
        """Return the number of items in the queue."""
        pass