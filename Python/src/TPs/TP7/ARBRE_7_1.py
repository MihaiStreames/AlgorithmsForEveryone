from Blueprints.Algorithms.Traversals import *
from Blueprints.Data_Structures import BinaryTree, NodeBinaryTree

tree = BinaryTree(1,
                  BinaryTree(2,
                             BinaryTree(4),
                             BinaryTree(5,
                                        BinaryTree(8)
                                        )
                             ),
                  BinaryTree(3,
                             BinaryTree(6,
                                        None,
                                        BinaryTree(9)
                                        ),
                             BinaryTree(7)
                             )
                  )

node_tree = NodeBinaryTree(1)
node_tree.tree_set_left(node_tree.tree_get_root(), 2)
node_tree.tree_set_right(node_tree.tree_get_root(), 3)

node_tree.tree_set_left(node_tree.tree_get_left(node_tree.tree_get_root()), 4)
node_tree.tree_set_right(node_tree.tree_get_left(node_tree.tree_get_root()), 5)

node_tree.tree_set_left(node_tree.tree_get_right(node_tree.tree_get_root()), 6)
node_tree.tree_set_right(node_tree.tree_get_right(node_tree.tree_get_root()), 7)

node_tree.tree_set_left(node_tree.tree_get_right(node_tree.tree_get_left(node_tree.tree_get_root())), 8)
node_tree.tree_set_right(node_tree.tree_get_left(node_tree.tree_get_right(node_tree.tree_get_root())), 9)

# Cool way to capture the output of the print function
def capture_traversal(traversal_function, tree):
    output = []

    def print_capture(data):
        output.append(data)

    original_print = __builtins__.print  # Didn't know you could do this
    __builtins__.print = print_capture   # Replacing the print function with my own

    traversal_function(tree)

    __builtins__.print = original_print  # Restoring the original print function

    return output

def concatenate_traversal_output(outputs):
    return " -> ".join(map(str, outputs))

def main():
    print("Inorder for the recursive tree: \n")
    print(concatenate_traversal_output(capture_traversal(inorder_rec_tree, tree)))
    print(concatenate_traversal_output(capture_traversal(inorder_iter_tree, tree)))

    print("\nPreorder for the recursive tree: \n")
    print(concatenate_traversal_output(capture_traversal(preorder_rec_tree, tree)))
    print(concatenate_traversal_output(capture_traversal(preorder_iter_tree, tree)))

    print("\nPostorder for the recursive tree: \n")
    print(concatenate_traversal_output(capture_traversal(postorder_rec_tree, tree)))
    print(concatenate_traversal_output(capture_traversal(postorder_iter_tree, tree)))

    print("\nInorder for the node tree: \n")
    print(concatenate_traversal_output(capture_traversal(inorder_node_tree, node_tree)))
    print(concatenate_traversal_output(capture_traversal(inorder_iter_node_tree, node_tree)))

    print("\nPreorder for the node tree: \n")
    print(concatenate_traversal_output(capture_traversal(preorder_node_tree, node_tree)))
    print(concatenate_traversal_output(capture_traversal(preorder_iter_node_tree, node_tree)))

    print("\nPostorder for the node tree: \n")
    print(concatenate_traversal_output(capture_traversal(postorder_node_tree, node_tree)))
    print(concatenate_traversal_output(capture_traversal(postorder_iter_node_tree, node_tree)))

main()