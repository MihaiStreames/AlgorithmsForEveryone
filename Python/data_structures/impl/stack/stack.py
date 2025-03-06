class NodeStack:
    def __init__(self):
        self.top = None
        self._size = 0

    def push(self, item):
        self.top = Node(item, self.top)
        self._size += 1

    def pop(self):
        if self.is_empty():
            raise IndexError("pop from empty stack")
        item = self.top.data
        self.top = self.top.next
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