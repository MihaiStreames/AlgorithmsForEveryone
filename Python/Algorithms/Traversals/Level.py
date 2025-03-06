from DataStructs import Queue, BinaryTree, NodeBinaryTree, ForestNode


def levelOrderTree(tree: BinaryTree):
    """
    Perform a level order traversal of a recursive binary tree (BinaryTree class).
    :param tree: The root of the binary tree.
    """
    if tree is None:
        return

    queue = Queue()
    queue.enq(tree)

    while not queue.isEmpty():
        curr = queue.deq()

        if curr:
            print(curr.getRootVal())
            queue.enq(curr.getLeftChild())
            queue.enq(curr.getRightChild())


def levelOrderNodeTree(tree: NodeBinaryTree):
    """
    Perform a level order traversal of a node-based binary tree (NodeBinaryTree class).
    :param tree: The root of the binary tree.
    """
    if tree.treeGetRoot() is None:
        return

    queue = Queue()
    queue.enq(tree.treeGetRoot())

    while not queue.isEmpty():
        curr = queue.deq()

        if curr:
            print(curr.data)
            if curr.left is not None:
                queue.enq(curr.left)
            if curr.right is not None:
                queue.enq(curr.right)


def levelOrderForest(forest: ForestNode):
    """
    Perform a level order traversal of a forest (ForestNode class).
    :param forest: The root of the forest.
    """
    if forest is None:
        return

    queue = Queue()
    queue.enq(forest)

    while not queue.isEmpty():
        curr = queue.deq()

        while curr:
            print(curr.getRootVal())
            queue.enq(curr.getChild())
            curr = curr.getBrother()
