class Node:
    """
    Simple node class used for basic data storage within a linked list.
    """

    def __init__(self, data):
        """
        Initialize a Node with data.
        :param data: The data stored in the node.
        """
        self._data = data

    def __str__(self):
        """
        Return the string representation of the data stored in the node.
        :return: The string representation of the data stored in the node.
        """
        return str(self._data)

    @property
    def data(self):
        """
        Get the data stored in the node.
        :return: The data stored in the node.
        """
        return self._data

    @data.setter
    def data(self, new_data):
        """
        Set the data stored in the node.
        :param new_data: The new data to be stored in the node.
        """
        self._data = new_data


class ListNode(Node):
    """
    Node used for linked lists with optional previous and next pointers.
    """

    def __init__(self, data, previous=None, next=None):
        """
        Initialize a ListNode with data, and optional previous and next pointers.
        :param data: The data stored in the node.
        :param previous: The previous node in the linked list.
        :param next: The next node in the linked list.
        """
        super().__init__(data)
        self._previous = previous
        self._next = next

    @property
    def next(self):
        """
        Get the next node in the linked list.
        :return: The next node.
        """
        return self._next

    @property
    def prev(self):
        """
        Get the previous node in the linked list.
        :return: The previous node.
        """
        return self._previous

    @next.setter
    def next(self, new_next):
        """
        Set the next node in the linked list.
        :param new_next: The new next node.
        """
        self._next = new_next

    @prev.setter
    def prev(self, new_previous):
        """
        Set the previous node in the linked list.
        :param new_previous: The new previous node.
        """
        self._previous = new_previous


class Edge:
    """
    Edge class for use in graphs, extends ListNode for linked list capabilities.
    """

    def __init__(self, vertex, weight, previous=None, next=None):
        """
        Initialize an Edge with a destination vertex, weight, and optional previous and next pointers.
        :param vertex: The destination vertex this edge points to.
        :param weight: The weight of the edge.
        :param previous: The previous edge in the linked list.
        :param next: The next edge in the linked list.
        """
        self.node = ListNode(None, previous, next)  # ListNode to manage linked list connectivity
        self._dest = vertex
        self._weight = weight

    @property
    def dest(self):
        """
        Get the destination vertex this edge points to.
        :return: The destination vertex.
        """
        return self._dest

    @property
    def weight(self):
        """
        Get the weight of the edge.
        :return: The weight of the edge.
        """
        return self._weight

    @weight.setter
    def weight(self, new_weight):
        """
        Set the weight of the edge.
        :param new_weight: The new weight of the edge.
        """
        self._weight = new_weight


class TreeNode(Node):
    """
    Node class for binary trees with left and right child pointers.
    """

    def __init__(self, data):
        """
        Initialize a tree node with data and optional left and right children.
        :param data: The data stored in the node.
        """
        super().__init__(data)
        self._left = None
        self._right = None

    @property
    def left(self):
        """
        Get the left child of the node.
        :return: The left child of the node.
        """
        return self._left

    @property
    def right(self):
        """
        Get the right child of the node.
        :return: The right child of the node.
        """
        return self._right

    @left.setter
    def left(self, new_left):
        """
        Set the left child of the node.
        :param new_left: The new left child of the node.
        """
        self._left = new_left

    @right.setter
    def right(self, new_right):
        """
        Set the right child of the node.
        :param new_right: The new right child of the node.
        """
        self._right = new_right
