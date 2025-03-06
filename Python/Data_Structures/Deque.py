from Blueprints.Data_Structures import ListNode


class Deque:
    """
    A class representing a double-ended queue (deque).
    """

    def __init__(self):
        """
        Initialize an empty deque.
        """
        self.items = []

    def __len__(self) -> int:
        """
        Return the number of items in the deque.
        :return: The number of items in the deque.
        """
        return len(self.items)

    def size(self) -> int:
        """
        Return the size of the deque.
        :return: The number of items in the deque.
        """
        return self.__len__()

    def is_empty(self) -> bool:
        """
        Check if the deque is empty.
        :return: True if the deque is empty, False otherwise.
        """
        return self.size() == 0

    def add_front(self, item):
        """
        Add an item to the front of the deque.
        :param item: The item to be added to the front of the deque.
        """
        self.items.append(item)

    def remove_front(self):
        """
        Remove and return the item from the front of the deque.
        :return: The item removed from the front of the deque if not empty, None otherwise.
        """
        if not self.is_empty():
            return self.items.pop()
        return None

    def add_rear(self, item):
        """
        Add an item to the rear (beginning) of the deque.
        :param item: The item to be added to the rear (beginning) of the deque.
        """
        self.items.insert(0, item)

    def remove_rear(self):
        """
        Remove and return the item from the rear (beginning) of the deque.
        :return: The item removed from the rear (beginning) of the deque if not empty, None otherwise.
        """
        if not self.is_empty():
            return self.items.pop(0)
        return None


class NodeDeque:
    """
    A class representing a double-ended queue (deque) using ListNode.
    """

    def __init__(self):
        """
        Initialize an empty deque.
        """
        self.front = None
        self.rear = None
        self._size = 0

    def __len__(self) -> int:
        """
        Return the number of items in the deque.
        :return: The number of items in the deque.
        """
        return self._size

    def size(self) -> int:
        """
        Return the size of the deque.
        :return: The number of items in the deque.
        """
        return self.__len__()

    def is_empty(self) -> bool:
        """
        Check if the deque is empty.
        :return: True if the deque is empty, False otherwise.
        """
        return self.front is None

    def head(self):
        """
        Return the front item from the deque without removing it.
        :return: The item at the front of the deque.
        """
        if self.is_empty():
            raise IndexError("head from empty deque")

        return self.front.data

    def tail(self):
        """
        Return the rear item from the deque without removing it.
        :return: The item at the rear of the deque.
        """
        if self.is_empty():
            raise IndexError("tail from empty deque")

        return self.rear.data

    def add_front(self, item):
        """
        Add an item to the front of the deque.
        :param item: The item to be added to the front of the deque.
        """
        new_node = ListNode(item)

        if self.is_empty():
            self.front = new_node
            self.rear = new_node
        else:
            new_node.next = self.front
            self.front.previous = new_node
            self.front = new_node

        self._size += 1

    def remove_front(self):
        """
        Remove and return the item from the front of the deque.
        :return: The item removed from the front of the deque if not empty, None otherwise.
        """
        if self.is_empty():
            raise IndexError("remove_front from empty deque")

        item = self.head()
        self.front = self.front.next

        if self.front is not None:
            self.front.previous = None
        else:
            self.rear = None

        self._size -= 1
        return item

    def add_rear(self, item):
        """
        Add an item to the rear of the deque.
        :param item: The item to be added to the rear of the deque.
        """
        new_node = ListNode(item)

        if self.is_empty():
            self.front = new_node
            self.rear = new_node
        else:
            self.rear.next = new_node
            new_node.previous = self.rear
            self.rear = new_node

        self._size += 1

    def remove_rear(self):
        """
        Remove and return the item from the rear of the deque.
        :return: The item removed from the rear of the deque if not empty, None otherwise.
        """
        if self.is_empty():
            raise IndexError("remove_rear from empty deque")

        item = self.tail()
        self.rear = self.rear.previous

        if self.rear is not None:
            self.rear.next = None
        else:
            self.front = None

        self._size -= 1
        return item


class NodeDequeNext:
    """
    A class representing a double-ended queue (deque) using ListNode with only .next.
    """

    def __init__(self):
        """
        Initialize an empty deque.
        """
        self.front = None
        self.rear = None
        self._size = 0

    def __len__(self) -> int:
        """
        Return the number of items in the deque.
        :return: The number of items in the deque.
        """
        return self._size

    def size(self) -> int:
        """
        Return the size of the deque.
        :return: The number of items in the deque.
        """
        return self.__len__()

    def is_empty(self) -> bool:
        """
        Check if the deque is empty.
        :return: True if the deque is empty, False otherwise.
        """
        return self.front is None

    def head(self):
        """
        Return the front item from the deque without removing it.
        :return: The item at the front of the deque.
        """
        if self.is_empty():
            raise IndexError("head from empty deque")

        return self.front.data

    def tail(self):
        """
        Return the rear item from the deque without removing it.
        :return: The item at the rear of the deque.
        """
        if self.is_empty():
            raise IndexError("tail from empty deque")

        return self.rear.data

    def add_front(self, item):
        """
        Add an item to the front of the deque.
        :param item: The item to be added to the front of the deque.
        """
        new_node = ListNode(item)

        if self.is_empty():
            self.front = new_node
            self.rear = new_node
        else:
            new_node.next = self.front
            self.front = new_node

        self._size += 1

    def remove_front(self):
        """
        Remove and return the item from the front of the deque.
        :return: The item removed from the front of the deque if not empty, None otherwise.
        """
        if self.is_empty():
            raise IndexError("remove_front from empty deque")

        item = self.head()
        self.front = self.front.next

        if self.front is None:
            self.rear = None

        self._size -= 1
        return item

    def add_rear(self, item):
        """
        Add an item to the rear of the deque.
        :param item: The item to be added to the rear of the deque.
        """
        new_node = ListNode(item)

        if self.is_empty():
            self.front = new_node
            self.rear = new_node
        else:
            self.rear.next = new_node
            self.rear = new_node

        self._size += 1

    def remove_rear(self):
        """
        Remove and return the item from the rear of the deque.
        :return: The item removed from the rear of the deque if not empty, None otherwise.
        """
        if self.is_empty():
            raise IndexError("remove_rear from empty deque")

        if self.front == self.rear:
            item = self.tail()
            self.front = None
            self.rear = None
        else:
            current = self.front

            while current.next != self.rear:
                current = current.next

            item = self.tail()
            self.rear = current
            self.rear.next = None

        self._size -= 1
        return item
