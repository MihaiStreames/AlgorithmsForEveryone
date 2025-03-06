from DataStructs import TreeNode


class BinaryTree:
    """
    A class representing a simple binary tree (recursive).
    """

    def __init__(self, init_data, left=None, right=None):
        """
        Initialize the binary tree with initial data and optional left and right children.
        :param init_data: The data to be stored in the root of the tree.
        :param left: The left child of the tree (default is None).
        :param right: The right child of the tree (default is None).
        """
        self._data = init_data
        self._left = left
        self._right = right

    def modifyLeft(self, item):
        """
        Modify the left child of the tree.
        :param item: The data to be stored in the new left child.
        """
        self._left = BinaryTree(item)

    def modifyRight(self, item):
        """
        Modify the right child of the tree.
        :param item: The data to be stored in the new right child.
        """
        self._right = BinaryTree(item)

    def getRootVal(self):
        """
        Get the value stored in the root of the tree.
        :return: The data stored in the root of the tree.
        """
        return self._data

    def setRootVal(self, item):
        """
        Set the value stored in the root of the tree.
        :param item: The new data to be stored in the root of the tree.
        """
        self._data = item

    def getLeftChild(self):
        """
        Get the left child of the tree.
        :return: The left child of the tree.
        """
        return self._left

    def getRightChild(self):
        """
        Get the right child of the tree.
        :return: The right child of the tree.
        """
        return self._right


class NodeBinaryTree:
    """
    A class representing a binary tree using TreeNode nodes (non-recursive).
    """

    def __init__(self, init_data):
        """
        Initialize the binary tree with initial data.
        :param init_data: The data to be stored in the root of the tree.
        """
        self.root = TreeNode(init_data)

    def treeGetRoot(self):
        """
        Get the root of the tree.
        :return: The root of the tree.
        """
        return self.root

    def treeGetLeft(self, base):
        """
        Get the left child of the base node.
        :param base: The base node to get the left child from.
        :return: The left child of the base node.
        """
        return base.left

    def treeSetLeft(self, base, item):
        """
        Modify the left child of the base node.
        :param base: The base node to modify.
        :param item: The data to be stored in the new left child.
        """
        base.left = TreeNode(item)

    def treeGetRight(self, base):
        """
        Get the right child of the base node.
        :param base: The base node to get the right child from.
        :return: The right child of the base node.
        """
        return base.right

    def treeSetRight(self, base, item):
        """
        Modify the right child of the base node.
        :param base: The base node to modify.
        :param item: The data to be stored in the new right child.
        """
        base.right = TreeNode(item)
