from DataStructs import TreeNode, ForestNode, BinaryTree, NodeBinaryTree, Stack

def preorderRecTree(tree: BinaryTree):
    """
    Perform a recursive preorder traversal of a recursive binary tree (BinaryTree class).
    :param tree: The root of the binary tree.
    """
    if tree is not None:
        print(tree.getRootVal())
        preorderRecTree(tree.getLeftChild())
        preorderRecTree(tree.getRightChild())


def preorderIterTree(tree: BinaryTree):
    """
    Perform an iterative preorder traversal of a recursive binary tree (BinaryTree class).
    :param tree: The root of the binary tree.
    """
    if tree is None:
        return

    stack = Stack()
    stack.push(tree)

    while not stack.isEmpty():
        curr = stack.pop()
        print(curr.getRootVal())

        if curr.getRightChild() is not None:
            stack.push(curr.getRightChild())
        if curr.getLeftChild() is not None:
            stack.push(curr.getLeftChild())


def preorderNodeTree(tree: NodeBinaryTree):
    """
    Perform a recursive preorder traversal of a node-based binary tree (NodeBinaryTree class).
    :param tree: The binary tree.
    """

    def preorderNode(node: TreeNode):
        if node is not None:
            print(node.data)
            preorderNode(tree.treeGetLeft(node))
            preorderNode(tree.treeGetRight(node))

    preorderNode(tree.treeGetRoot())


def preorderIterNodeTree(tree: NodeBinaryTree):
    """
    Perform an iterative preorder traversal of a node-based binary tree (NodeBinaryTree class).
    :param tree: The binary tree.
    """
    if tree.treeGetRoot() is None:
        return

    stack = Stack()
    stack.push(tree.treeGetRoot())

    while not stack.isEmpty():
        curr = stack.pop()
        print(curr.data)

        if curr.right is not None:
            stack.push(curr.right)
        if curr.left is not None:
            stack.push(curr.left)


def preorderForest(forest: ForestNode):
    """
    Perform a preorder traversal of a forest (ForestNode class).
    :param forest: The forest root.
    """
    while forest is not None:
        print(forest.getRootVal())
        preorderForest(forest.getChild())
        forest = forest.getBrother()
