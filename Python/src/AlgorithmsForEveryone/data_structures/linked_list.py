from typing import Iterable, Optional

from AlgorithmsForEveryone.data_containers.list_node import ListNode


class UnorderedLinkedList:
    """
    Unordered singly linked list built on ListNode.

    Can be constructed from an iterable.
    """

    def __init__(self, data: Optional[Iterable] = None):
        self.head: Optional[ListNode] = None
        self.count = 0
        if data:
            for item in data:
                self.insert(item)

    def __iter__(self):
        current = self.head
        while current:
            yield current.data
            current = current.next

    def __str__(self):
        return "UnorderedLinkedList([" + ", ".join(str(item) for item in self) + "])"

    @property
    def isEmpty(self) -> bool:
        return self.head is None

    def insert(self, item):
        """
        Inserts at the head.
        """
        new_node = ListNode(item)
        new_node.next = self.head
        self.head = new_node
        self.count += 1

    def insertAfter(self, base, item):
        """
        Inserts after the node containing base.
        """
        base_node = self.search(base)
        if not base_node:
            return
        new_node = ListNode(item)
        new_node.next = base_node.next
        base_node.next = new_node
        self.count += 1

    def size(self) -> int:
        return self.count

    def remove(self, item):
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
        current = self.head
        while current:
            if current.data == item:
                return current
            current = current.next
        return None
