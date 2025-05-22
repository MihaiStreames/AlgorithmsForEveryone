class Heap:
    def __init__(self, key=lambda x, y: x > y):
        self.heapList = []
        self.size = 0
        self.key = key

    def parent(self, index):
        return (index - 1) // 2

    def leftChild(self, index):
        return (index * 2) + 1

    def rightChild(self, index):
        return (index * 2) + 2

    def exists(self, index):
        return index < self.size

    def swap(self, index1, index2):
        self.heapList[index1], self.heapList[index2] = self.heapList[index2], self.heapList[index1]

    def priorityUp(self, index):
        while index > 0 and self.key(self.heapList[index], self.heapList[self.parent(index)]):
            self.swap(index, self.parent(index))
            index = self.parent(index)

    def priorityChild(self, index):
        if not self.exists(self.leftChild(index)):
            return -1
        elif not self.exists(self.rightChild(index)):
            return self.leftChild(index)
        else:
            if self.key(self.heapList[self.leftChild(index)], self.heapList[self.rightChild(index)]):
                return self.leftChild(index)
            else:
                return self.rightChild(index)

    def priorityDown(self, index):
        while self.exists(self.leftChild(index)):
            mc = self.priorityChild(index)

            if mc == -1 or not self.key(self.heapList[mc], self.heapList[index]):
                break

            self.swap(index, mc)
            index = mc

    def insert(self, item):
        self.heapList.append(item)
        self.size += 1
        self.priorityUp(self.size - 1)

    def delete(self):
        if self.size == 0:
            return None

        retval = self.heapList[0]
        self.heapList[0] = self.heapList[self.size - 1]
        self.size -= 1
        self.heapList.pop()
        self.priorityDown(0)
        return retval

def taxicab(k, N):
    limit = int(N ** (1/3)) + 1
    sums = dict()
    h = Heap(lambda x, y: x[0] < y[0])  # Min-heap based on cube sum

    for a in range(1, limit):
        for b in range(a, limit):
            cubes = a ** 3 + b ** 3

            if cubes <= N:
                if cubes not in sums:
                    sums[cubes] = []
                sums[cubes].append((a, b))
            if cubes > N:
                break

    for cubes, pairs in sums.items():
        if len(pairs) >= k:
            h.insert((cubes, pairs))

    while h.size > 0:
        smallest = h.delete()
        print(f"Sum: {smallest[0]}, Pairs: {smallest[1]}")