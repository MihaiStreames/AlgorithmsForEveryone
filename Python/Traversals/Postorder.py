from Blueprints.Stack import Stack


def postorder_rec_tree(tree: "BinaryTree"):
    """
    Perform a recursive postorder traversal of a recursive binary tree (BinaryTree class).
    :param tree: The root of the binary tree.
    """
    if tree is not None:
        postorder_rec_tree(tree.get_left_child())
        postorder_rec_tree(tree.get_right_child())
        print(tree.get_root_val())


def postorder_iter_tree(tree: "BinaryTree"):
    """
    Perform an iterative postorder traversal of a recursive binary tree (BinaryTree class).
    :param tree: The root of the binary tree.
    """
    if tree is None:
        return

    stack = Stack()
    out = Stack()
    stack.push(tree)

    while not stack.is_empty():
        curr = stack.pop()
        out.push(curr)

        if curr.get_left_child() is not None:
            stack.push(curr.get_left_child())
        if curr.get_right_child() is not None:
            stack.push(curr.get_right_child())

    while not out.is_empty():
        curr = out.pop()
        print(curr.get_root_val())


def postorder_node_tree(tree: "NodeBinaryTree"):
    """
    Perform a recursive postorder traversal of a node-based binary tree (NodeBinaryTree class).
    :param tree: The binary tree.
    """
    def postorder_node(node: "TreeNode"):
        if node is not None:
            postorder_node(tree.tree_get_left(node))
            postorder_node(tree.tree_get_right(node))
            print(node.data)

    postorder_node(tree.tree_get_root())


def postorder_iter_node_tree(tree: "NodeBinaryTree"):
    """
    Perform an iterative postorder traversal of a node-based binary tree (NodeBinaryTree class).
    :param tree: The root of the binary tree.
    """
    if tree.tree_get_root() is None:
        return

    stack = Stack()
    out = Stack()
    stack.push(tree.tree_get_root())

    while not stack.is_empty():
        curr = stack.pop()
        out.push(curr)

        if curr.left is not None:
            stack.push(curr.left)
        if curr.right is not None:
            stack.push(curr.right)

    while not out.is_empty():
        curr = out.pop()
        print(curr.data)


def postorder_forest(forest: "ForestNode"):
    """
    Perform a postorder traversal of a forest (ForestNode class).
    :param forest: The forest root.
    """
    while forest is not None:
        postorder_forest(forest.get_child())
        print(forest.get_root_val())
        forest = forest.get_brother()
