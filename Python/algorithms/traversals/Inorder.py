from DataStructs import Stack, BinaryTree, NodeBinaryTree, TreeNode


def inorderRecTree(tree: BinaryTree):
    """
    Perform an inorder traversal of a recursive binary tree (BinaryTree class).
    :param tree: The root of the binary tree.
    """
    if tree is not None:
        inorderRecTree(tree.getLeftChild())
        print(tree.getRootVal())
        inorderRecTree(tree.getRightChild())


def inorderIterTree(tree: BinaryTree):
    """
    Perform an iterative inorder traversal of a recursive binary tree (BinaryTree class).
    :param tree: The root of the binary tree.
    """
    stack = Stack()
    curr = tree

    while not stack.isEmpty() or curr is not None:
        if curr is not None:
            stack.push(curr)
            curr = curr.getLeftChild()
        else:
            curr = stack.pop()
            print(curr.getRootVal())
            curr = curr.getRightChild()


def inorderNodeTree(tree: NodeBinaryTree):
    """
    Perform an inorder traversal of a node-based binary tree (NodeBinaryTree class).
    :param tree: The root of the binary tree.
    """

    def inorderNode(node: TreeNode):
        if node is not None:
            inorderNode(tree.treeGetLeft(node))
            print(node.data)
            inorderNode(tree.treeGetRight(node))

    inorderNode(tree.treeGetRoot())


def inorderIterNodeTree(tree: NodeBinaryTree):
    """
    Perform an iterative inorder traversal of a node-based binary tree (NodeBinaryTree class).
    :param tree: The root of the binary tree.
    """
    stack = Stack()
    curr = tree.treeGetRoot()

    while not stack.isEmpty() or curr is not None:
        if curr is not None:
            stack.push(curr)
            curr = tree.treeGetLeft(curr)
        else:
            curr = stack.pop()
            print(curr.data)
            curr = tree.treeGetRight(curr)
