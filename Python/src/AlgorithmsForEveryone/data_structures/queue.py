from AlgorithmsForEveryone.data_containers.simple_node import SimpleNode


class Queue:
    """
    A FIFO (first-in, first-out) queue built on SimpleNode.

    Attributes:
        head (SimpleNode): The front node of the queue.
        tail (SimpleNode): The rear node of the queue.
        _size (int): The current number of items in the queue.
    """

    def __init__(self):
        """
        Initialize an empty queue.
        """
        self.head = None
        self.tail = None
        self._size = 0

    def enqueue(self, item):
        """
        Add an item to the rear of the queue.

        Args:
            item: The data to enqueue.
        """
        new_node = SimpleNode(item)
        new_node.set_next(None)
        if self.tail:
            self.tail.set_next(new_node)
        self.tail = new_node
        if self.head is None:
            self.head = new_node
        self._size += 1

    def dequeue(self):
        """
        Remove and return the item at the front of the queue.

        Raises:
            IndexError: If the queue is empty.

        Returns:
            The data of the removed node.
        """
        if self.is_empty():
            raise IndexError("dequeue from empty queue")
        item = self.head.data
        self.head = self.head.get_next()
        if self.head is None:
            self.tail = None
        self._size -= 1
        return item

    def peek(self):
        """
        Return the item at the front of the queue without removing it.

        Raises:
            IndexError: If the queue is empty.

        Returns:
            The data of the front node.
        """
        if self.is_empty():
            raise IndexError("peek from empty queue")
        return self.head.data

    def is_empty(self):
        """
        Check if the queue is empty.

        Returns:
            True if the queue has no items, False otherwise.
        """
        return self._size == 0

    def size(self):
        """
        Return the number of items in the queue.

        Returns:
            The current size of the queue.
        """
        return self._size

    def __str__(self):
        """
        Returns:
            A string representation of the queue, listing its items from front to rear.
        """
        items = []
        current = self.head
        while current:
            items.append(str(current.data))
            current = current.get_next()
        return "Queue([" + ", ".join(items) + "])"
