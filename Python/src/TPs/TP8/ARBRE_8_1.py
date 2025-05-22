from Blueprints.Data_Structures import BinaryTree

class BinaryTreeFather(BinaryTree):
    def __init__(self, root, left=None, right=None, father=None):
        super().__init__(root, left, right)
        self._father = father

    @property
    def father(self):
        return self._father

    @father.setter
    def father(self, data):
        self._father = data


def first(node):
    return node

def first_any(node):
    while node.father is not None:
        node = node.father
    return node

def next(node):
    if node.get_left_child():
        return node.get_left_child()
    if node.get_right_child():
        return node.get_right_child()

    found = False

    while not found:
        child = node
        node = node.father

        if node is None:
            found = True
        elif child is node.get_left_child() and node.get_right_child():
            node = node.get_right_child()
            found = True

    return node