def are_equal(tree1, tree2):
    if tree1 is None and tree2 is None:
        return True

    if tree1 is None or tree2 is None or tree1.get_root_val() != tree2.get_root_val():
        return False

    return are_equal(tree1.get_left_child(), tree2.get_left_child()) and are_equal(tree1.get_right_child(), tree2.get_right_child())