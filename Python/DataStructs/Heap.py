class Heap:
    """
    A class representing a generic heap data structure.
    """

    def __init__(self, data=None):
        """
        Initialize the heap with optional initial data.
        :param data: A list of initial elements for the heap.
        """
        if data is None:
            self._array = []
        else:
            self._array = data

        for i in range(len(self._array) // 2 - 1, -1, -1):  # i goes from the last array element to the first
            self.heapify(i)

    def __str__(self) -> str:
        """
        Return a string representation of the heap.
        :return: A string representation of the heap.
        """
        return str(self._array)

    @property
    def array(self):
        """
        Get the array representing the heap.
        :return: The array representing the heap.
        """
        return self._array

    @property
    def depth(self) -> int:
        """
        Get the depth of the heap.
        :return: The depth of the heap.
        """
        depth = 0
        while 2 ** depth - 1 < len(self._array):
            depth += 1
        return depth

    def insert(self, data):
        """
        Insert an element into the heap.
        :param data: The element to be inserted.
        """
        self._array.append(data)
        self.swimUp(len(self._array) - 1)

    def delete(self):
        """
        Remove and return the root element of the heap.
        :return: The root element of the heap if it exists, None otherwise.
        """
        if len(self._array) == 0:
            return None

        res = self._array[0]
        self._array[0] = self._array[-1]
        self._array.pop()
        self.swimDown(0)

        return res

    def heapify(self, idx):
        """
        Heapify the subtree rooted at the given index.
        :param idx: The index of the root of the subtree to heapify.
        """
        raise NotImplementedError("Please implement in subclass")

    def swimUp(self, idx):
        """
        Restore the heap property by swimming up the element at the given index.
        :param idx: The index of the element to swim up.
        """
        raise NotImplementedError("Please implement in subclass")

    def swimDown(self, idx):
        """
        Restore the heap property by swimming down the element at the given index.
        :param idx: The index of the element to swim down.
        """
        raise NotImplementedError("Please implement in subclass")

    @classmethod
    def heapSort(cls, array):
        """
        Perform heap sort on the given array.
        :param array: The array to be sorted.
        :return: The sorted array.
        """
        heap = cls(array)
        sorted_array = []

        while heap._array:
            sorted_array.append(heap.delete())
        # array[:] is used to modify the original array
        array[:] = sorted_array[::-1] if issubclass(cls,
                                                    MaxHeap) else sorted_array  # Reverses the array if it's a MaxHeap, otherwise it's left as is (MinHeap)


class MinHeap(Heap):
    """
    A class representing a min-heap data structure, which extends the generic Heap class.
    """

    def __init__(self, data=None):
        """
        Initialize the min-heap with optional initial data.
        :param data: A list of initial elements for the heap.
        """
        super().__init__(data)

    def heapify(self, idx):
        """
        Heapify the subtree rooted at the given index to maintain the min-heap property.
        :param idx: The index of the root of the subtree to heapify.
        """
        smallest = idx
        left = 2 * idx + 1
        right = 2 * idx + 2

        if left < len(self._array) and self._array[left] < self._array[smallest]:
            smallest = left

        if right < len(self._array) and self._array[right] < self._array[smallest]:
            smallest = right

        if smallest != idx:
            self._array[idx], self._array[smallest] = self._array[smallest], self._array[idx]
            self.heapify(smallest)

    def swimUp(self, idx):
        """
        Restore the min-heap property by swimming up the element at the given index.
        :param idx: The index of the element to swim up.
        """
        while idx > 0:
            parent = (idx - 1) // 2

            if self._array[parent] > self._array[idx]:
                self._array[parent], self._array[idx] = self._array[idx], self._array[parent]
                idx = parent
            else:
                break

    def swimDown(self, idx):
        """
        Restore the min-heap property by swimming down the element at the given index.
        :param idx: The index of the element to swim down.
        """
        smallest = idx
        left = 2 * idx + 1
        right = 2 * idx + 2

        if left < len(self._array) and self._array[left] < self._array[smallest]:
            smallest = left

        if right < len(self._array) and self._array[right] < self._array[smallest]:
            smallest = right

        if smallest != idx:
            self._array[idx], self._array[smallest] = self._array[smallest], self._array[idx]
            self.swimDown(smallest)


class MaxHeap(Heap):
    """
    A class representing a max-heap data structure, which extends the generic Heap class.
    """

    def __init__(self, data=None):
        """
        Initialize the max-heap with optional initial data.
        :param data:
        """
        super().__init__(data)

    def heapify(self, idx):
        """
        Heapify the subtree rooted at the given index to maintain the max-heap property.
        :param idx: The index of the root of the subtree to heapify.
        """
        largest = idx
        left = 2 * idx + 1
        right = 2 * idx + 2

        if left < len(self._array) and self._array[left] > self._array[largest]:
            largest = left

        if right < len(self._array) and self._array[right] > self._array[largest]:
            largest = right

        if largest != idx:
            self._array[idx], self._array[largest] = self._array[largest], self._array[idx]
            self.heapify(largest)

    def swimUp(self, idx):
        """
        Restore the max-heap property by swimming up the element at the given index.
        :param idx: The index of the element to swim up.
        """
        while idx > 0:
            parent = (idx - 1) // 2

            if self._array[parent] < self._array[idx]:
                self._array[parent], self._array[idx] = self._array[idx], self._array[parent]
                idx = parent
            else:
                break

    def swimDown(self, idx):
        """
        Restore the max-heap property by swimming down the element at the given index.
        :param idx: The index of the element to swim down.
        """
        largest = idx
        left = 2 * idx + 1
        right = 2 * idx + 2

        if left < len(self._array) and self._array[left] > self._array[largest]:
            largest = left

        if right < len(self._array) and self._array[right] > self._array[largest]:
            largest = right

        if largest != idx:
            self._array[idx], self._array[largest] = self._array[largest], self._array[idx]
            self.swimDown(largest)
