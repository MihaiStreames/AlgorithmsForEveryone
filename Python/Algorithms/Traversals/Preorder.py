from Blueprints.Data_Structures import Stack


def preorder_rec_tree(tree: "BinaryTree"):
    """
    Perform a recursive preorder traversal of a recursive binary tree (BinaryTree class).
    :param tree: The root of the binary tree.
    """
    if tree is not None:
        print(tree.get_root_val())
        preorder_rec_tree(tree.get_left_child())
        preorder_rec_tree(tree.get_right_child())


def preorder_iter_tree(tree: "BinaryTree"):
    """
    Perform an iterative preorder traversal of a recursive binary tree (BinaryTree class).
    :param tree: The root of the binary tree.
    """
    if tree is None:
        return

    stack = Stack()
    stack.push(tree)

    while not stack.is_empty():
        curr = stack.pop()
        print(curr.get_root_val())

        if curr.get_right_child() is not None:
            stack.push(curr.get_right_child())
        if curr.get_left_child() is not None:
            stack.push(curr.get_left_child())


def preorder_node_tree(tree: "NodeBinaryTree"):
    """
    Perform a recursive preorder traversal of a node-based binary tree (NodeBinaryTree class).
    :param tree: The binary tree.
    """
    def preorder_node(node: "TreeNode"):
        if node is not None:
            print(node.data)
            preorder_node(tree.tree_get_left(node))
            preorder_node(tree.tree_get_right(node))

    preorder_node(tree.tree_get_root())


def preorder_iter_node_tree(tree: "NodeBinaryTree"):
    """
    Perform an iterative preorder traversal of a node-based binary tree (NodeBinaryTree class).
    :param tree: The binary tree.
    """
    if tree.tree_get_root() is None:
        return

    stack = Stack()
    stack.push(tree.tree_get_root())

    while not stack.is_empty():
        curr = stack.pop()
        print(curr.data)

        if curr.right is not None:
            stack.push(curr.right)
        if curr.left is not None:
            stack.push(curr.left)


def preorder_forest(forest: "ForestNode"):
    """
    Perform a preorder traversal of a forest (ForestNode class).
    :param forest: The forest root.
    """
    while forest is not None:
        print(forest.get_root_val())
        preorder_forest(forest.get_child())
        forest = forest.get_brother()
