from Blueprints.Data_Structures import Stack

class MinStack:
    def __init__(self):
        self.stack = Stack()
        self.min_stack = Stack()

    def push(self, item):
        self.stack.push(item)

        if self.min_stack.is_empty() or item <= self.min_stack.peek():
            self.min_stack.push(item)

    def pop(self):
        elem = self.stack.pop()

        if elem == self.min_stack.peek():
            self.min_stack.pop()

        return elem

    @property
    def peek(self):
        return self.stack.peek() if not self.stack.is_empty() else None

    @property
    def get_min(self):
        return self.min_stack.peek() if not self.min_stack.is_empty() else None