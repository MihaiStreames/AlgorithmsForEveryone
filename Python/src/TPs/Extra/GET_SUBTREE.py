class Node:
    def __init__(self, weight: int, *children: 'Node'):
        self.weight = weight
        self.children = list(children)

    def add_node(self, node: 'Node'):
        self.children.append(node)

def get_subtree(root: 'Node', x: int) -> Node | None:
    if root is None:
        return None

    def helper(node: 'Node', current_sum: int, target_sum: int) -> Node | None:
        if node is None:
            return None

        current_sum += node.weight
        new_node = Node(node.weight)

        for child in node.children:
            sub_node = helper(child, current_sum, target_sum)

            if sub_node is not None:
                new_node.add_node(sub_node)

        if current_sum == target_sum or new_node.children:
            return new_node
        else:
            return None

    return helper(root, 0, x)

if __name__ == "__main__":
    tree = Node(2,
                Node(12,
                     Node(6,
                          Node(4)),
                     Node(-1,
                          Node(1))),
                Node(3,
                     Node(9),
                     Node(5)),
                Node(11,
                     Node(1),
                     Node(2),
                     Node(1)),
                Node(4,
                        Node(1),
                        Node(2),
                        Node(1)),
                Node(5,
                     Node(9,
                          Node(20,
                               Node(1),
                               Node(18)),
                            Node(8)),
                        Node(2),
                        Node(1))
                )

    trees = []

    for i in range(1, 30):
        subtree = get_subtree(tree, i)
        if subtree is not None:
            trees.append(subtree)

    def print_tree(node: 'Node', level: int):
        if node is None:
            return

        print("  " * level, node.weight)
        for child in node.children:
            print_tree(child, level + 1)

    for tree in trees:
        print_tree(tree, 0)
        print()