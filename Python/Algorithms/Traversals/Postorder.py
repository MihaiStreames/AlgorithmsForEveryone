from DataStructs import Stack, BinaryTree, NodeBinaryTree, TreeNode, ForestNode


def postorderRecTree(tree: BinaryTree):
    """
    Perform a recursive postorder traversal of a recursive binary tree (BinaryTree class).
    :param tree: The root of the binary tree.
    """
    if tree is not None:
        postorderRecTree(tree.getLeftChild())
        postorderRecTree(tree.getRightChild())
        print(tree.getRootVal())


def postorderIterTree(tree: BinaryTree):
    """
    Perform an iterative postorder traversal of a recursive binary tree (BinaryTree class).
    :param tree: The root of the binary tree.
    """
    if tree is None:
        return

    stack = Stack()
    out = Stack()
    stack.push(tree)

    while not stack.isEmpty():
        curr = stack.pop()
        out.push(curr)

        if curr.getLeftChild() is not None:
            stack.push(curr.getLeftChild())
        if curr.getRightChild() is not None:
            stack.push(curr.getRightChild())

    while not out.isEmpty():
        curr = out.pop()
        print(curr.getRootVal())


def postorderNodeTree(tree: NodeBinaryTree):
    """
    Perform a recursive postorder traversal of a node-based binary tree (NodeBinaryTree class).
    :param tree: The binary tree.
    """

    def postorder_node(node: TreeNode):
        if node is not None:
            postorder_node(tree.treeGetLeft(node))
            postorder_node(tree.treeGetRight(node))
            print(node.data)

    postorder_node(tree.treeGetRoot())


def postorderIterNodeTree(tree: NodeBinaryTree):
    """
    Perform an iterative postorder traversal of a node-based binary tree (NodeBinaryTree class).
    :param tree: The root of the binary tree.
    """
    if tree.treeGetRoot() is None:
        return

    stack = Stack()
    out = Stack()
    stack.push(tree.treeGetRoot())

    while not stack.isEmpty():
        curr = stack.pop()
        out.push(curr)

        if curr.left is not None:
            stack.push(curr.left)
        if curr.right is not None:
            stack.push(curr.right)

    while not out.isEmpty():
        curr = out.pop()
        print(curr.data)


def postorderForest(forest: ForestNode):
    """
    Perform a postorder traversal of a forest (ForestNode class).
    :param forest: The forest root.
    """
    while forest is not None:
        postorderForest(forest.getChild())
        print(forest.getRootVal())
        forest = forest.getBrother()
