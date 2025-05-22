from Blueprints.Data_Structures import Stack

def is_bst_rec(tree, lower=-float('inf'), upper=+float('inf')):
    if not tree:
        return True

    if not (lower <= tree.get_root_val() < upper):
        return False

    return is_bst_rec(tree.get_left_child(), lower, tree.get_root_val()) and is_bst_rec(tree.get_right_child(), tree.get_root_val(), upper)

def is_bst_iter(tree):
    stack = Stack()
    prev = None

    while not stack.is_empty() or tree:
        while tree:
            stack.push(tree)
            tree = tree.get_left_child()
        tree = stack.pop()

        if prev and tree.get_root_val() <= prev.get_root_val():
            return False

        prev = tree
        tree = tree.get_right_child()

    return True

if __name__ == '__main__':
    from Blueprints.Data_Structures.Binary_Tree import BinaryTree

    tree1 = BinaryTree(2, BinaryTree(1), BinaryTree(3))
    tree2 = BinaryTree(1, BinaryTree(2, BinaryTree(4, BinaryTree(1))), BinaryTree(3))

    print(is_bst_rec(tree1))
    print(is_bst_rec(tree2))

    print(is_bst_iter(tree1))
    print(is_bst_iter(tree2))