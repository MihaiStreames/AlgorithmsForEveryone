from AlgorithmsForEveryone.data_containers.simple_node import SimpleNode


class Stack:
    """
    A LIFO (last-in, first-out) stack built on SimpleNode.

    Attributes:
        top (SimpleNode): The top node of the stack.
        _size (int): The current number of items in the stack.
    """

    def __init__(self):
        """
        Initialize an empty stack.
        """
        self.top = None
        self._size = 0

    def push(self, item):
        """
        Push an item onto the top of the stack.

        Args:
            item: The data to push.
        """
        new_node = SimpleNode(item)
        new_node.set_next(self.top)
        self.top = new_node
        self._size += 1

    def pop(self):
        """
        Remove and return the item at the top of the stack.

        Raises:
            IndexError: If the stack is empty.

        Returns:
            The data of the removed node.
        """
        if self.is_empty():
            raise IndexError("pop from empty stack")
        item = self.top.data
        self.top = self.top.get_next()
        self._size -= 1
        return item

    def peek(self):
        """
        Return the item at the top of the stack without removing it.

        Raises:
            IndexError: If the stack is empty.

        Returns:
            The data of the top node.
        """
        if self.is_empty():
            raise IndexError("peek from empty stack")
        return self.top.data

    def is_empty(self):
        """
        Check if the stack is empty.

        Returns:
            True if the stack has no items, False otherwise.
        """
        return self.top is None

    def size(self):
        """
        Return the number of items in the stack.

        Returns:
            The current size of the stack.
        """
        return self._size

    def __str__(self):
        """
        Returns:
            A string representation of the stack, listing its items from top to bottom.
        """
        items = []
        current = self.top
        while current:
            items.append(str(current.data))
            current = current.get_next()
        return "Stack([" + ", ".join(items) + "])"
