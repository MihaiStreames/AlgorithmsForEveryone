from typing import Optional

from AlgorithmsForEveryone.data_containers.deque_node import DequeNode


class Deque:
    """
    Deque implemented using a node-based doubly linked list.
    """

    def __init__(self):
        self.front: Optional[DequeNode] = None
        self.rear: Optional[DequeNode] = None
        self._size: int = 0

    def add_front(self, item):
        new_node = DequeNode(item)
        if self.front is None:
            self.front = self.rear = new_node
        else:
            new_node.next = self.front
            self.front.prev = new_node
            self.front = new_node
        self._size += 1

    def add_rear(self, item):
        new_node = DequeNode(item)
        if self.rear is None:
            self.front = self.rear = new_node
        else:
            new_node.prev = self.rear
            self.rear.next = new_node
            self.rear = new_node
        self._size += 1

    def remove_front(self):
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
        if self.is_empty():
            raise IndexError("peek_front from empty deque")
        return self.front.data

    def peek_rear(self):
        if self.is_empty():
            raise IndexError("peek_rear from empty deque")
        return self.rear.data

    def is_empty(self):
        return self._size == 0

    def size(self):
        return self._size

    def __str__(self):
        result = []
        current = self.front
        while current:
            result.append(str(current.data))
            current = current.next
        return "Deque([" + ", ".join(result) + "])"
