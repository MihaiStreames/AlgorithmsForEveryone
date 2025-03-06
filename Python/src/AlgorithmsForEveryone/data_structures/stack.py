from AlgorithmsForEveryone.data_containers.simple_node import SimpleNode


class Stack:
    """
    Stack built on SimpleNode.
    """

    def __init__(self):
        self.top = None
        self._size = 0

    def push(self, item):
        new_node = SimpleNode(item)
        new_node.set_next(self.top)
        self.top = new_node
        self._size += 1

    def pop(self):
        if self.is_empty():
            raise IndexError("pop from empty stack")
        item = self.top.data
        self.top = self.top.get_next()
        self._size -= 1
        return item

    def peek(self):
        if self.is_empty():
            raise IndexError("peek from empty stack")
        return self.top.data

    def is_empty(self):
        return self.top is None

    def size(self):
        return self._size

    def __str__(self):
        items = []
        current = self.top
        while current:
            items.append(str(current.data))
            current = current.get_next()
        return "Stack([" + ", ".join(items) + "])"
