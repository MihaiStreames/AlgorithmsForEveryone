from ARBRE_8_1 import *

def mirror(tree):
    if tree is None:
        return None

    new_left = mirror(tree.get_right_child())
    new_right = mirror(tree.get_left_child())

    new_tree = BinaryTree(tree.get_root_val(), new_left, new_right)

    return new_tree

def mirror_father(tree):
    if tree is None:
        return None

    new_tree = BinaryTreeFather(tree.root)

    if tree.get_left_child() is not None:
        new_tree.modify_right(mirror_father(tree.left))
        new_tree.get_right_child().father = new_tree

    if tree.get_right_child() is not None:
        new_tree.modify_left(mirror_father(tree.right))
        new_tree.get_left_child().father = new_tree

    return new_tree