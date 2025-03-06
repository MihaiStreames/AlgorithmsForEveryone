from Blueprints.Data_Structures import Stack


def inorder_rec_tree(tree: "BinaryTree"):
    """
    Perform an inorder traversal of a recursive binary tree (BinaryTree class).
    :param tree: The root of the binary tree.
    """
    if tree is not None:
        inorder_rec_tree(tree.get_left_child())
        print(tree.get_root_val())
        inorder_rec_tree(tree.get_right_child())


def inorder_iter_tree(tree: "BinaryTree"):
    """
    Perform an iterative inorder traversal of a recursive binary tree (BinaryTree class).
    :param tree: The root of the binary tree.
    """
    stack = Stack()
    curr = tree

    while not stack.is_empty() or curr is not None:
        if curr is not None:
            stack.push(curr)
            curr = curr.get_left_child()
        else:
            curr = stack.pop()
            print(curr.get_root_val())
            curr = curr.get_right_child()


def inorder_node_tree(tree: "NodeBinaryTree"):
    """
    Perform an inorder traversal of a node-based binary tree (NodeBinaryTree class).
    :param tree: The root of the binary tree.
    """
    def inorder_node(node: "TreeNode"):
        if node is not None:
            inorder_node(tree.tree_get_left(node))
            print(node.data)
            inorder_node(tree.tree_get_right(node))

    inorder_node(tree.tree_get_root())


def inorder_iter_node_tree(tree: "NodeBinaryTree"):
    """
    Perform an iterative inorder traversal of a node-based binary tree (NodeBinaryTree class).
    :param tree: The root of the binary tree.
    """
    stack = Stack()
    curr = tree.tree_get_root()

    while not stack.is_empty() or curr is not None:
        if curr is not None:
            stack.push(curr)
            curr = tree.tree_get_left(curr)
        else:
            curr = stack.pop()
            print(curr.data)
            curr = tree.tree_get_right(curr)
