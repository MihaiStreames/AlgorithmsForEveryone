class ForestNode:
    """
    A class representing a node in an n-ary tree.
    Each node can have a child (subtree) and a brother (sibling tree).
    """

    def __init__(self, item):
        """
        Initialize the node with initial data.
        :param item: The data to be stored in the node.
        """
        self._info = item
        self._child = None  # Child will be another ForestNode (subtree)
        self._brother = None  # Brother will be another ForestNode (sibling tree)

    def getRootVal(self):
        """
        Get the value stored in the node.
        :return: The data stored in the node.
        """
        return self._info

    def setRootVal(self, item):
        """
        Set the value stored in the node.
        :param item: The new data to be stored in the node.
        """
        self._info = item

    def getChild(self):
        """
        Get the child of the node.
        :return: The child of the node.
        """
        return self._child

    def setChild(self, new_node):
        """
        Set the child of the node.
        :param new_node: The data to be stored in the new child.
        """
        self._child = ForestNode(new_node)

    def getBrother(self):
        """
        Get the brother of the node.
        :return: The brother of the node.
        """
        return self._brother

    def setBrother(self, new_node):
        """
        Set the brother of the node.
        :param new_node: The data to be stored in the new brother.
        """
        self._brother = ForestNode(new_node)


class NAryTree:
    """
    A class representing an n-ary tree.
    Each node can have multiple children stored in a list.
    """

    def __init__(self, item):
        """
        Initialize the n-ary tree with initial data.
        :param item: The data to be stored in the root of the tree.
        """
        self._root = ForestNode(item)
        self._children = []

    def getRoot(self):
        """
        Get the root node of the n-ary tree.
        :return: The root node of the n-ary tree.
        """
        return self._root

    def getChildren(self):
        """
        Get the children of the root node.
        :return: The children of the root node.
        """
        return self._children

    def addChild(self, item):
        """
        Add a child to the root node.
        :param item: The data to be stored in the new child.
        """
        new_child = ForestNode(item)
        self._children.append(new_child)

        if not self._root.getChild():
            self._root.setChild(new_child)
        else:
            last_child = self._root.getChild()

            while last_child.getBrother():
                last_child = last_child.getBrother()

            last_child.setBrother(new_child)


class Forest:
    """
    A class representing a forest, which is a collection of disjoint n-ary trees.
    """

    def __init__(self):
        """
        Initialize the forest as an empty collection of trees.
        """
        self._trees = []

    def addTree(self, tree):
        """
        Add a new n-ary tree to the forest.
        :param tree: An instance of NAryTree to be added to the forest.
        """
        self._trees.append(tree)

    def getTrees(self):
        """
        Get the list of trees in the forest.
        :return: The list of n-ary trees in the forest.
        """
        return self._trees
