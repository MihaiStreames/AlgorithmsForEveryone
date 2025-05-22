class MinMaxHeap:
    def __init__(self, array):
        self.array = array

    def __len__(self):
        return len(self.array)

    def father(self, i):
        return (i - 1) // 2

    def depth(self, idx):
        ret = 0
        while idx > 0:
            idx = self.father(idx)
            ret += 1
        return ret

    def __str__(self):
        return str(self.array)

    def swap(self, i, j):
        self.array[i], self.array[j] = self.array[j], self.array[i]

    def insert(self, e):  # Each level alternates between max / min heaps
        idx = len(self)
        self.array.append(e)
        father_idx = self.father(idx)

        if self.depth(idx) % 2 == 0:    # Even depth: max heap
            if idx > 0 and self.array[idx] > self.array[father_idx]:
                self.swap(idx, father_idx)
                self.swim_max(father_idx)
            else:
                self.swim_min(idx)
        else:                           # Odd depth: min heap
            if idx > 0 and self.array[idx] < self.array[father_idx]:
                self.swap(idx, father_idx)
                self.swim_min(father_idx)
            else:
                self.swim_max(idx)

    def swim_max(self, idx):
        father_idx = self.father(idx)

        while father_idx > 0 and self.array[idx] > self.array[self.father(father_idx)]:
            self.swap(idx, self.father(father_idx))
            idx = self.father(father_idx)
            father_idx = self.father(idx)

    def swim_min(self, idx):
        father_idx = self.father(idx)

        while father_idx > 0 and self.array[idx] < self.array[self.father(father_idx)]:
            self.swap(idx, self.father(father_idx))
            idx = self.father(father_idx)
            father_idx = self.father(idx)

if __name__ == "__main__":
    heap = MinMaxHeap([2, 7, 8, 4, 2, 7, 6, 5, 6, 3])

    heap.insert(10)
    print(heap)
    heap.insert(1)
    print(heap)