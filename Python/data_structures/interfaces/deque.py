from abc import ABC, abstractmethod
from typing import Generic, TypeVar

T = TypeVar('T')

class Deque(ABC, Generic[T]):
    """
    Abstract base class for double-ended queue (deque) data structures.
    """

    @abstractmethod
    def add_front(self, item: T) -> None:
        """Add an item to the front of the deque."""
        pass

    @abstractmethod
    def add_rear(self, item: T) -> None:
        """Add an item to the rear of the deque."""
        pass

    @abstractmethod
    def remove_front(self) -> T:
        """Remove and return the item from the front."""
        pass

    @abstractmethod
    def remove_rear(self) -> T:
        """Remove and return the item from the rear."""
        pass

    @abstractmethod
    def peek_front(self) -> T:
        """Return the front item without removing it."""
        pass

    @abstractmethod
    def peek_rear(self) -> T:
        """Return the rear item without removing it."""
        pass

    @abstractmethod
    def is_empty(self) -> bool:
        """Check if the deque is empty."""
        pass

    @abstractmethod
    def size(self) -> int:
        """Return the number of items in the deque."""
        pass
