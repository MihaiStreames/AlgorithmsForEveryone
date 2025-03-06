from Blueprints.Data_Structures import Queue


def level_order_tree(tree: "BinaryTree"):
    """
    Perform a level order traversal of a recursive binary tree (BinaryTree class).
    :param tree: The root of the binary tree.
    """
    if tree is None:
        return

    queue = Queue()
    queue.enqueue(tree)

    while not queue.is_empty():
        curr = queue.dequeue()

        if curr:
            print(curr.get_root_val())
            queue.enqueue(curr.get_left_child())
            queue.enqueue(curr.get_right_child())


def level_order_node_tree(tree: "NodeBinaryTree"):
    """
    Perform a level order traversal of a node-based binary tree (NodeBinaryTree class).
    :param tree: The root of the binary tree.
    """
    if tree.tree_get_root() is None:
        return

    queue = Queue()
    queue.enqueue(tree.tree_get_root())

    while not queue.is_empty():
        curr = queue.dequeue()

        if curr:
            print(curr.data)
            if curr.left is not None:
                queue.enqueue(curr.left)
            if curr.right is not None:
                queue.enqueue(curr.right)


def level_order_forest(forest: "ForestNode"):
    """
    Perform a level order traversal of a forest (ForestNode class).
    :param forest: The root of the forest.
    """
    if forest is None:
        return

    queue = Queue()
    queue.enqueue(forest)

    while not queue.is_empty():
        curr = queue.dequeue()

        while curr:
            print(curr.get_root_val())
            queue.enqueue(curr.get_child())
            curr = curr.get_brother()