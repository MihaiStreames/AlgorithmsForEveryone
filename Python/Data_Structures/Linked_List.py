from Blueprints.Data_Structures import ListNode, Edge


class UnorderedLinkedList:
    """
    Represents an unordered singly linked list.
    """

    def __init__(self):
        """
        Initialize an empty unordered singly linked list.
        """
        self.head = None
        self.count = 0

    def __iter__(self):
        """
        Iterate over the items in the linked list.
        :return: An iterator over the items in the linked list.
        """
        curr = self.head
        while curr:
            yield curr
            curr = curr.next

    @property
    def is_empty(self) -> bool:
        """
        Check if the linked list is empty.
        :return: True if the linked list is empty, False otherwise.
        """
        return self.head is None

    def insert(self, item, *args):
        """
        Insert an item at the end of the linked list.
        :param item: The item to be inserted.
        """
        new_node = ListNode(item)
        new_node.next = self.head
        self.head = new_node
        self.count += 1

    def insert_after(self, base, item):
        """
        Insert an item after a given base item.
        :param base: The item after which the new item is to be inserted.
        :param item: The item to be inserted.
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
        Return the number of items in the linked list.
        :return: The number of items in the linked list.
        """
        return self.count

    def remove(self, item):
        """
        Remove the first occurrence of an item from the linked list.
        :param item: The item to be removed.
        """
        curr = self.search(item)
        if not curr:
            return

        if self.head == curr:
            self.head = curr.next
        else:
            prev = self.head

            while prev.next != curr:
                prev = prev.next

            prev.next = curr.next
        self.count -= 1

    def search(self, item):
        """
        Search for an item in the linked list.
        :param item: The item to be searched.
        :return: The node containing the item if found, None otherwise.
        """
        curr = self.head

        while curr:
            if curr.data == item:
                return curr
            curr = curr.next

        return None


class CircularUnorderedLinkedList(UnorderedLinkedList):
    """
    Represents a circular unordered linked list.
    """

    def __init__(self):
        """
        Initialize an empty circular unordered linked list.
        """
        super().__init__()
        self.head = ListNode(-1)
        self.head.next = self.head

    def is_empty(self) -> bool:
        """
        Check if the circular linked list is empty.
        :return: True if the circular linked list is empty, False otherwise.
        """
        return self.head.next == self.head

    def insert(self, item, *args):
        """
        Insert an item at the beginning of the circular linked list.
        :param item: The item to be inserted.
        """
        new_node = ListNode(item)
        new_node.next = self.head.next
        self.head.next = new_node
        self.count += 1

    def insert_after(self, base, item):
        """
        Insert an item after a given base item in the circular linked list.
        :param base: The item after which the new item is to be inserted.
        :param item: The item to be inserted.
        """
        base_node = self.search(base)

        if not base_node:
            return

        new_node = ListNode(item)
        new_node.next = base_node.next
        base_node.next = new_node
        self.count += 1

    def search(self, item):
        """
        Search for an item in the circular linked list.
        :param item: The item to be searched.
        :return: The node containing the item if found, None otherwise.
        """
        curr = self.head.next

        while curr != self.head:
            if curr.data == item:
                return curr
            curr = curr.next

        return None

    def remove(self, base):
        """
        Remove the first occurrence of an item from the circular linked list.
        :param base: The item to be removed.
        """
        prev = self.head
        curr = self.head.next

        while curr != self.head:
            if curr.data == base:
                prev.next = curr.next
                self.count -= 1
                return
            else:
                prev = curr
                curr = curr.next


class UnorderedDoublyLinkedList(UnorderedLinkedList):
    """
    Represents an unordered doubly linked list.
    """

    def insert(self, item, *args):
        """
        Insert an item at the end of the linked list.
        :param item: The item to be inserted.
        """
        new_node = ListNode(item)
        new_node.next = self.head

        if self.head:
            self.head.previous = new_node

        self.head = new_node
        self.count += 1

    def insert_after(self, base, item):
        """
        Insert an item after a given base item.
        :param base: The item after which the new item is to be inserted.
        :param item: The item to be inserted.
        """
        base_node = self.search(base)
        if not base_node:
            return

        new_node = ListNode(item)
        new_node.next = base_node.next
        new_node.previous = base_node
        base_node.next = new_node

        if new_node.next:
            new_node.next.previous = new_node
        self.count += 1

    def remove(self, item):
        """
        Remove the first occurrence of an item from the linked list.
        :param item: The item to be removed.
        """
        curr = self.search(item)
        if not curr:
            return

        if curr.previous:
            curr.previous.next = curr.next
        else:
            self.head = curr.next
        if curr.next:
            curr.next.previous = curr.previous
        self.count -= 1


class EdgeUnorderedLinkedList(UnorderedDoublyLinkedList):
    """
    Represents an unordered linked list of edges.
    """

    def insert(self, item, dest=None, weight=None):
        """
        Insert an edge into the linked list.
        :param item: Not used, exists to match base class signature.
        :param dest: The destination vertex of the edge.
        :param weight: The weight of the edge.
        """
        if dest is None or weight is None:
            raise ValueError("Both dest and weight must be provided")

        new_edge = Edge(dest, weight)
        new_edge.next = self.head

        if self.head:
            self.head.previous = new_edge

        self.head = new_edge
        self.count += 1

    def remove(self, vertex):
        """
        Remove the first edge with the given destination vertex.
        :param vertex: The destination vertex of the edge to be removed.
        """
        e = self.head

        while e:
            if e.dest == vertex:
                if e.previous:
                    e.previous.next = e.next
                if e.next:
                    e.next.previous = e.previous
                self.count -= 1
                return

            e = e.next

    def search(self, vertex):
        """
        Search for an edge with the given destination vertex.
        :param vertex: The destination vertex of the edge to be searched.
        :return: The edge if found, None otherwise.
        """
        e = self.head

        while e:
            if e.dest == vertex:
                return e
            e = e.next

        return None

    def weight(self, vertex):
        """
        Get the weight of the edge with the given destination vertex.
        :param vertex: The destination vertex of the edge whose weight is to be retrieved.
        :return: The weight of the edge if found, None otherwise.
        """
        e = self.search(vertex)
        return e if e else None

