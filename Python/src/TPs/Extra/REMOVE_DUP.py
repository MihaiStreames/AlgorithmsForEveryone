class TreeNode:
    def __init__(self, key):
        self.left = None
        self.right = None
        self.val = key

class BinaryTree:
    def __init__(self):
        self.root = None

    def insert(self, val):
        if self.root is None:
            self.root = TreeNode(val)
        else:
            self._insert(self.root, val)

    def _insert(self, node, val):
        if val < node.val:
            if node.left is None:
                node.left = TreeNode(val)
            else:
                self._insert(node.left, val)
        else:
            if node.right is None:
                node.right = TreeNode(val)
            else:
                self._insert(node.right, val)

def inorder(tree, res):
    if tree:
        inorder(tree.left, res)
        res.append(tree.val)
        inorder(tree.right, res)

def remove_duplicates(tree: BinaryTree) -> BinaryTree:
    values = []
    inorder(tree.root, values)

    unique_values = list(set(values))
    unique_values.sort()

    new_tree = BinaryTree()
    for value in unique_values:
        new_tree.insert(value)

    return new_tree