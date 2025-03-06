from typing import Optional

from AlgorithmsForEveryone.data_containers.deque_node import DequeNode


class Deque:
    """
    Deque implemented using a node-based doubly linked list.

    Attributes:
        front (Optional[DequeNode]): The front node of the deque.
        rear (Optional[DequeNode]): The rear node of the deque.
        _size (int): The current size of the deque.
    """

    def __init__(self):
        """
        Initialize an empty deque.
        """
        self.front: Optional[DequeNode] = None
        self.rear: Optional[DequeNode] = None
        self._size: int = 0

    def add_front(self, item):
        """
        Add an item to the front of the deque.

        Args:
            item: The value to insert.
        """
        new_node = DequeNode(item)
        if self.front is None:
            self.front = self.rear = new_node
        else:
            new_node.next = self.front
            self.front.prev = new_node
            self.front = new_node
        self._size += 1

    def add_rear(self, item):
        """
        Add an item to the rear of the deque.

        Args:
            item: The value to insert.
        """
        new_node = DequeNode(item)
        if self.rear is None:
            self.front = self.rear = new_node
        else:
            new_node.prev = self.rear
            self.rear.next = new_node
            self.rear = new_node
        self._size += 1

    def remove_front(self):
        """
        Remove and return the front item of the deque.

        Raises:
            IndexError: If the deque is empty.

        Returns:
            The data of the removed front node.
        """
        if self.is_empty():
            raise IndexError("remove_front from empty deque")
        removed_data = self.front.data
        self.front = self.front.next
        if self.front:
            self.front.prev = None
        else:
            self.rear = None
        self._size -= 1
        return removed_data

    def remove_rear(self):
        """
        Remove and return the rear item of the deque.

        Raises:
            IndexError: If the deque is empty.

        Returns:
            The data of the removed rear node.
        """
        if self.is_empty():
            raise IndexError("remove_rear from empty deque")
        removed_data = self.rear.data
        self.rear = self.rear.prev
        if self.rear:
            self.rear.next = None
        else:
            self.front = None
        self._size -= 1
        return removed_data

    def peek_front(self):
        """
        Return the front item of the deque without removing it.

        Raises:
            IndexError: If the deque is empty.

        Returns:
            The data of the front node.
        """
        if self.is_empty():
            raise IndexError("peek_front from empty deque")
        return self.front.data

    def peek_rear(self):
        """
        Return the rear item of the deque without removing it.

        Raises:
            IndexError: If the deque is empty.

        Returns:
            The data of the rear node.
        """
        if self.is_empty():
            raise IndexError("peek_rear from empty deque")
        return self.rear.data

    def is_empty(self):
        """
        Check if the deque is empty.

        Returns:
            True if empty, False otherwise.
        """
        return self._size == 0

    def size(self):
        """
        Return the number of items in the deque.

        Returns:
            The size of the deque.
        """
        return self._size

    def __str__(self):
        """
        Returns:
            A string representation of the deque, listing its items from front to rear.
        """
        result = []
        current = self.front
        while current:
            result.append(str(current.data))
            current = current.next
        return "Deque([" + ", ".join(result) + "])"
