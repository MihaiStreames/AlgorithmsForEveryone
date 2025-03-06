from Blueprints.Data_Structures import ListNode


class Stack:
    """
    A class representing a simple stack data structure.
    """

    def __init__(self):
        """
        Initialize an empty stack.
        """
        self.items = []

    def __len__(self) -> int:
        """
        Return the number of items in the stack.
        :return: The number of items in the stack.
        """
        return len(self.items)

    def size(self) -> int:
        """
        Return the size of the stack.
        :return: The number of items in the stack.
        """
        return self.__len__()

    def is_empty(self) -> bool:
        """
        Check if the stack is empty.
        :return: True if the stack is empty, False otherwise.
        """
        return self.size() == 0

    def push(self, item):
        """
        Push an item onto the stack.
        :param item: The item to be pushed onto the stack.
        """
        self.items.append(item)

    def pop(self):
        """
        Remove and return the top item from the stack.
        :return: The item removed from the top of the stack.
        """
        return self.items.pop()

    def peek(self):
        """
        Return the top item of the stack without removing it.
        :return: The top item of the stack.
        """
        return self.items[len(self.items) - 1]


class NodeStack:
    """
    A class representing a simple stack data structure using ListNode.
    """

    def __init__(self):
        """
        Initialize an empty stack.
        """
        self.head = None
        self._size = 0

    def __len__(self) -> int:
        """
        Return the number of items in the stack.
        :return: The number of items in the stack.
        """
        return self._size

    def size(self) -> int:
        """
        Return the size of the stack.
        :return: The number of items in the stack.
        """
        return self.__len__()

    def is_empty(self) -> bool:
        """
        Check if the stack is empty.
        :return: True if the stack is empty, False otherwise.
        """
        return self.size() == 0

    def push(self, item):
        """
        Push an item onto the stack.
        :param item: The item to be pushed onto the stack.
        """
        new_node = ListNode(item, previous=self.head)

        if self.head is not None:
            self.head.next = new_node

        self.head = new_node
        self._size += 1

    def pop(self):
        """
        Remove and return the top item from the stack.
        :return: The item removed from the top of the stack.
        """
        if self.is_empty():
            raise IndexError("pop from empty stack")

        item = self.head.data
        self.head = self.head.previous

        if self.head is not None:
            self.head.next = None

        self._size -= 1
        return item

    def peek(self):
        """
        Return the top item of the stack without removing it.
        :return: The top item of the stack.
        """
        if self.is_empty():
            raise IndexError("peek from empty stack")

        return self.head.data


class NodeStackNext:
    """
    A class representing a simple stack data structure using ListNode and only the .next pointer.
    """

    def __init__(self):
        """
        Initialize an empty stack.
        """
        self.head = None
        self._size = 0

    def __len__(self) -> int:
        """
        Return the number of items in the stack.
        :return: The number of items in the stack.
        """
        return self._size

    def size(self) -> int:
        """
        Return the size of the stack.
        :return: The number of items in the stack.
        """
        return self.__len__()

    def is_empty(self) -> bool:
        """
        Check if the stack is empty.
        :return: True if the stack is empty, False otherwise.
        """
        return self.head is None

    def push(self, item):
        """
        Push an item onto the stack.
        :param item: The item to be pushed onto the stack.
        """
        new_node = ListNode(data=item, next=self.head)
        self.head = new_node
        self._size += 1

    def pop(self):
        """
        Remove and return the top item from the stack.
        :return: The item removed from the top of the stack.
        """
        if self.is_empty():
            raise IndexError("pop from empty stack")

        data = self.head.data
        self.head = self.head.next
        self._size -= 1
        return data

    def peek(self):
        """
        Return the top item of the stack without removing it.
        :return: The top item of the stack.
        """
        if self.is_empty():
            raise IndexError("peek from empty stack")

        return self.head.data
