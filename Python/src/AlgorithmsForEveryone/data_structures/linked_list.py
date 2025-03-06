from typing import Iterable, Optional

from AlgorithmsForEveryone.data_containers.list_node import ListNode


class UnorderedLinkedList:
    """
    Unordered singly linked list built on ListNode.

    This data structure allows insertion at the head,
    as well as searching and removing elements by value.

    Args:
        data: An optional iterable of items to initialize the list.

    Attributes:
        head (Optional[ListNode]): The first node of the list.
        count (int): The current number of nodes in the list.
    """

    def __init__(self, data: Optional[Iterable] = None):
        self.head: Optional[ListNode] = None
        self.count = 0
        if data:
            for item in data:
                self.insert(item)

    def __iter__(self):
        """
        Iterate over the items in the list from head to tail.

        Yields:
            The data of each node in sequence.
        """
        current = self.head
        while current:
            yield current.data
            current = current.next

    def __str__(self):
        """
        Returns:
            A string representation of the list, showing its items in order.
        """
        return "UnorderedLinkedList([" + ", ".join(str(item) for item in self) + "])"

    @property
    def isEmpty(self) -> bool:
        """
        Check if the list is empty.

        Returns:
            True if the list has no nodes, False otherwise.
        """
        return self.head is None

    def insert(self, item):
        """
        Insert an item at the head of the list.

        Args:
            item: The data to store in the new node.
        """
        new_node = ListNode(item)
        new_node.next = self.head
        self.head = new_node
        self.count += 1

    def insertAfter(self, base, item):
        """
        Insert a new item after the node containing 'base'.

        Args:
            base: The value to find in the list.
            item: The new item to insert after the base node.
        """
        base_node = self.search(base)
        if not base_node:
            return
        new_node = ListNode(item)
        new_node.next = base_node.next
        base_node.next = new_node
        self.count += 1

    def size(self) -> int:
        """
        Return the number of nodes in the list.

        Returns:
            The count of nodes.
        """
        return self.count

    def remove(self, item):
        """
        Remove the first occurrence of 'item' from the list.

        Args:
            item: The value to remove.
        """
        current = self.head
        prev = None
        while current:
            if current.data == item:
                if prev is None:
                    self.head = current.next
                else:
                    prev.next = current.next
                self.count -= 1
                return
            prev = current
            current = current.next

    def search(self, item) -> Optional[ListNode]:
        """
        Find the first node containing 'item'.

        Args:
            item: The value to find.

        Returns:
            The node containing 'item', or None if not found.
        """
        current = self.head
        while current:
            if current.data == item:
                return current
            current = current.next
        return None
