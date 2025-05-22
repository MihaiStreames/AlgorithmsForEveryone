from Blueprints.Data_Structures import Stack

def are_equiv(bst1, bst2):
    s1, s2 = Stack(), Stack()
    curr1, curr2 = bst1, bst2

    while (not s1.is_empty() or curr1) and (not s2.is_empty() or curr2):
        while curr1:
            s1.push(curr1)
            curr1 = curr1.get_left_child()
        while curr2:
            s2.push(curr2)
            curr2 = curr2.get_left_child()

        if s1.is_empty() or s2.is_empty():
            return False

        curr1 = s1.pop()
        curr2 = s2.pop()

        if curr1.get_root_val() != curr2.get_root_val():
            return False

        curr1 = curr1.get_right_child()
        curr2 = curr2.get_right_child()

    return not (not s1.is_empty() or curr1 or not s2.is_empty() or curr2)

if __name__ == '__main__':
    from Blueprints.Data_Structures.Binary_Tree import BinaryTree

    tree1 = BinaryTree(1, None, BinaryTree(2, None, BinaryTree(3)))
    tree2 = BinaryTree(2, BinaryTree(1), BinaryTree(3))
    tree3 = BinaryTree(3, BinaryTree(2, BinaryTree(1)))

    print(are_equiv(tree1, tree2))
    print(are_equiv(tree3, tree1))