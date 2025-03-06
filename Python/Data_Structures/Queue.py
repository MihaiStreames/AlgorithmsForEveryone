from Blueprints.Data_Structures import ListNode


class Queue:
    """
    A class representing a simple queue data structure.
    """

    def __init__(self):
        """
        Initialize an empty queue.
        """
        self.items = []

    def __len__(self) -> int:
        """
        Return the number of items in the queue.
        :return: The number of items in the queue.
        """
        return len(self.items)

    def size(self) -> int:
        """
        Return the size of the queue.
        :return: The number of items in the queue.
        """
        return self.__len__()

    def is_empty(self) -> bool:
        """
        Check if the queue is empty.
        :return: True if the queue is empty, False otherwise.
        """
        return self.size() == 0

    def head(self):
        """
        Return the front item from the queue without removing it.
        :return: The item at the front of the queue.
        """
        if self.is_empty():
            raise IndexError("head from empty queue")

        return self.items[-1]

    def enqueue(self, item):
        """
        Add an item to the end of the queue.
        :param item: The item to be added to the queue.
        """
        self.items.insert(0, item)

    def dequeue(self):
        """
        Remove and return the front item from the queue.
        :return: The item removed from the front of the queue.
        """
        return self.items.pop()


class NodeQueue:
    """
    A class representing a simple queue data structure using ListNode.
    """

    def __init__(self):
        """
        Initialize an empty queue.
        """
        self.front = None
        self.rear = None
        self._size = 0

    def __len__(self) -> int:
        """
        Return the number of items in the queue.
        :return: The number of items in the queue.
        """
        return self._size

    def size(self) -> int:
        """
        Return the size of the queue.
        :return: The number of items in the queue.
        """
        return self.__len__()

    def is_empty(self) -> bool:
        """
        Check if the queue is empty.
        :return: True if the queue is empty, False otherwise.
        """
        return self.front is None

    def head(self):
        """
        Return the front item from the queue without removing it.
        :return: The item at the front of the queue.
        """
        if self.is_empty():
            raise IndexError("head from empty queue")

        return self.front.data

    def enqueue(self, item):
        """
        Add an item to the end of the queue.
        :param item: The item to be added to the queue.
        """
        new_node = ListNode(item)

        if self.rear is not None:
            self.rear.next = new_node
            new_node.previous = self.rear

        self.rear = new_node

        if self.front is None:
            self.front = new_node

        self._size += 1

    def dequeue(self):
        """
        Remove and return the front item from the queue.
        :return: The item removed from the front of the queue.
        """
        if self.is_empty():
            raise IndexError("dequeue from empty queue")

        item = self.head()
        self.front = self.front.next

        if self.front is not None:
            self.front.previous = None
        else:
            self.rear = None

        self._size -= 1
        return item


class NodeQueueNext:
    """
    A class representing a simple queue data structure using ListNode and only the .next pointer.
    """

    def __init__(self):
        """
        Initialize an empty queue.
        """
        self.front = None
        self.rear = None
        self._size = 0

    def __len__(self) -> int:
        """
        Return the number of items in the queue.
        :return: The number of items in the queue.
        """
        return self._size

    def size(self) -> int:
        """
        Return the size of the queue.
        :return: The number of items in the queue.
        """
        return self.__len__()

    def is_empty(self) -> bool:
        """
        Check if the queue is empty.
        :return: True if the queue is empty, False otherwise.
        """
        return self.front is None

    def head(self):
        """
        Return the front item from the queue without removing it.
        :return: The item at the front of the queue.
        """
        if self.is_empty():
            raise IndexError("head from empty queue")

        return self.front.data

    def enqueue(self, item):
        """
        Add an item to the end of the queue.
        :param item: The item to be added to the queue.
        """
        new_node = ListNode(item)

        if self.is_empty():
            self.front = new_node
            self.rear = new_node
        else:
            self.rear.next = new_node
            self.rear = new_node

        self._size += 1

    def dequeue(self):
        """
        Remove and return the front item from the queue.
        :return: The item removed from the front of the queue.
        """
        if self.is_empty():
            raise IndexError("dequeue from empty queue")

        item = self.head()
        self.front = self.front.next

        if self.front is None:
            self.rear = None

        self._size -= 1
        return item
