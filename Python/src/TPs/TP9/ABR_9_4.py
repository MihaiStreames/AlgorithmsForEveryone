from Blueprints.Data_Structures import BinaryTree

def find_x(root, x):
    if root is None:
        return None
    elif root.get_root_val() == x:
        return root
    elif x < root.get_root_val():
        return find_x(root.get_left_child(), x)
    else:
        return find_x(root.get_right_child(), x)

def insert_bst(root, val):
    if root is None:
        return BinaryTree(val)
    if val < root.get_root_val():
        root.modify_left(insert_bst(root.get_left_child(), val))
    elif val > root.get_root_val():
        root.modify_right(insert_bst(root.get_right_child(), val))
    return root

def rebuild(root, new_root):
    if root is None:
        return
    if root.get_root_val() != new_root.get_root_val():
        insert_bst(new_root, root.get_root_val())

    rebuild(root.get_left_child(), new_root)
    rebuild(root.get_right_child(), new_root)

def create_with_x(root, x):
    new_root = find_x(root, x)
    if not new_root:
        return None
    rebuild(root, new_root)
    return new_root

if __name__ == '__main__':
    tree = BinaryTree(5, BinaryTree(3, BinaryTree(2), BinaryTree(4)), BinaryTree(8, BinaryTree(7), BinaryTree(9)))

    x = 3
    new_tree = create_with_x(tree, x)
    print(new_tree.get_root_val())