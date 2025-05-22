class BinaryTree:
    def __init__(self, value):
        self.value = value
        self.left = None
        self.right = None

def reverse_path(root, data):
    # Trouver le chemin et collecter les valeurs
    path = []
    curr = root

    while curr is not None:
        path.append(curr)
        if data < curr.value:
            curr = curr.left
        elif data > curr.value:
            curr = curr.right
        else:
            break

    # Inverser les valeurs le long du chemin
    values = [node.value for node in path]
    values.reverse()

    # Réaffecter les valeurs inversées aux nœuds sur le chemin
    for i, node in enumerate(path):
        node.value = values[i]