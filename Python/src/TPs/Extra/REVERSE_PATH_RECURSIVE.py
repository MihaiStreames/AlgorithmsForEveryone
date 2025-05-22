from Blueprints.Data_Structures import BinaryTree
from typing import List

def get_path(root: BinaryTree, data:int):
    retval = []
    while root is not None:
        retval.append(root.get_root_val())
        if data > root.get_root_val():
            root = root.get_right_child()
        elif data < root.get_root_val():
            root = root.get_left_child()
        else:
            break
    return retval

def get_node(root: BinaryTree, data: int) -> BinaryTree:
    while root is not None:
        if data > root.get_root_val():
            root = root.get_right_child()
        elif data < root.get_root_val():
            root = root.get_left_child()
        else:
            return root


def recursive(root: BinaryTree, path: List):
    if len(path) > 1:
        last = get_node(root, path[-1])
        root_val = root.get_root_val()
        root.set_root_val(last.get_root_val())
        last.set_root_val(root_val)
        root = root.get_left_child() if path[1] < root.get_root_val() else root.get_right_child()
        recursive(root, path[1:-1])
    else:
        return

def reverse_path(root: BinaryTree, data: int) -> BinaryTree:
    path = get_path(root, data)
    recursive(root, path)

tree = BinaryTree(8)
tree.modify_left(4)
tree.get_left_child().modify_left(3)
tree.get_left_child().modify_right(7)
tree.modify_right(9)
tree.get_right_child().modify_right(15)

reverse_path(tree, 7)

print(tree)