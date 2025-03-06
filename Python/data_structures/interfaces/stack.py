from abc import ABC, abstractmethod
from typing import Generic, TypeVar

T = TypeVar('T')

class Stack(ABC, Generic[T]):
    """
    Abstract base class for stack data structures.
    """

    @abstractmethod
    def push(self, item: T) -> None:
        """Push an item onto the stack."""
        pass

    @abstractmethod
    def pop(self) -> T:
        """Remove and return the top item from the stack."""
        pass

    @abstractmethod
    def peek(self) -> T:
        """Return the top item without removing it."""
        pass

    @abstractmethod
    def is_empty(self) -> bool:
        """Check if the stack is empty."""
        pass

    @abstractmethod
    def size(self) -> int:
        """Return the number of items in the stack."""
        pass

