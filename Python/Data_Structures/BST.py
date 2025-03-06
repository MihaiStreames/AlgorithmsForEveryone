class BinarySearchTree:
    def __init__(self, init_data):
        self.data = init_data
        self.left = None
        self.right = None
        self.parent = None

    def get_root_val(self):
        return self.data

    def set_root_val(self, value):
        self.data = value

    def get_global_root(self):
        curr = self
        while curr.parent is not None:
            curr = curr.parent
        return curr

    def get_next(self):
        if self.right is not None:
            curr = self.right

            while curr.left is not None:
                curr = curr.left
        else:
            child = self
            curr = self.parent

            while curr is not None and curr.right == child:
                child = curr
                curr = curr.parent
        return curr

    def get_previous(self):
        if self.left is not None:
            curr = self.left

            while curr.right is not None:
                curr = curr.right
        else:
            child = self
            curr = self.parent

            while curr is not None and curr.left == child:
                child = curr
                curr = curr.parent
        return curr

    def get_first(self):
        curr = self.get_global_root()
        while curr.left is not None:
            curr = curr.left
        return curr

    def get_last(self):
        curr = self.get_global_root()
        while curr.right is not None:
            curr = curr.right
        return curr

    def search(self, item):
        curr = self.get_global_root()

        while curr is not None and curr.data != item:
            if item < curr.data:
                curr = curr.left
            else:
                curr = curr.right
        return curr

    def insert(self, item):
        curr = self.get_global_root()
        parent = None

        while curr is not None:
            parent = curr
            if item < curr.data:
                if curr.left is None:
                    break
                curr = curr.left
            else:
                if curr.right is None:
                    break
                curr = curr.right

        new_node = BinarySearchTree(item)
        new_node.parent = parent

        if item < parent.data:
            parent.left = new_node
        else:
            parent.right = new_node

    def remove(self):
        if self.left is not None:
            predecessor = self.get_previous()
            self.data = predecessor.data
            predecessor.remove()
        elif self.right is not None:
            successor = self.get_next()
            self.data = successor.data
            successor.remove()
        else:
            if self.parent is not None:
                if self.parent.left == self:
                    self.parent.left = None
                else:
                    self.parent.right = None
            else:
                self.data = None
                self.left = None
                self.right = None
