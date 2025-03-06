from collections import deque

class Deque:
    """A simple double-ended queue (deque) implementation."""

    def __init__(self):
        self._items = deque()

    def add_front(self, item):
        """Add an item to the front."""
        self._items.appendleft(item)

    def add_rear(self, item):
        """Add an item to the rear."""
        self._items.append(item)

    def remove_front(self):
        """Remove an item from the front."""
        if not self.is_empty():
            return self._items.popleft()
        raise IndexError("remove_front from empty deque")

    def remove_rear(self):
        """Remove an item from the rear."""
        if not self.is_empty():
            return self._items.pop()
        raise IndexError("remove_rear from empty deque")

    def is_empty(self):
        """Check if the deque is empty."""
        return len(self._items) == 0

    def size(self):
        """Return the number of items in the deque."""
        return len(self._items)
