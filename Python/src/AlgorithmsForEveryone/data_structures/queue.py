from AlgorithmsForEveryone.data_containers.simple_node import SimpleNode


class Queue:
    """
    Queue built on SimpleNode.
    """

    def __init__(self):
        self.head = None
        self.tail = None
        self._size = 0

    def enqueue(self, item):
        new_node = SimpleNode(item)
        new_node.set_next(None)
        if self.tail:
            self.tail.set_next(new_node)
        self.tail = new_node
        if self.head is None:
            self.head = new_node
        self._size += 1

    def dequeue(self):
        if self.is_empty():
            raise IndexError("dequeue from empty queue")
        item = self.head.data
        self.head = self.head.get_next()
        if self.head is None:
            self.tail = None
        self._size -= 1
        return item

    def peek(self):
        if self.is_empty():
            raise IndexError("peek from empty queue")
        return self.head.data

    def is_empty(self):
        return self._size == 0

    def size(self):
        return self._size

    def __str__(self):
        items = []
        current = self.head
        while current:
            items.append(str(current.data))
            current = current.get_next()
        return "Queue([" + ", ".join(items) + "])"
