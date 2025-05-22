class List:
    def __init__(self):
        self._data = empty_list(1)
        self._count = 0
        self._capacity = 1

    @property
    def count(self):
        return self._count

    @count.setter
    def count(self, n):
        self.count = n

    @property
    def capacity(self):
        return self._capacity

    @capacity.setter
    def capacity(self, n):
        self.capacity = n

    def __str__(self):
        return "[" + ", ".join(str(self._data[i]) for i in range(self.count)) + "]"

    def __len__(self):
        return self.count

    def __iter__(self):
        for i in range(self.count):
            yield self._data[i]

    def __getitem__(self, index):
        if index < 0:
            index += self.count

        if 0 <= index < self.count:
            return self._data[index]

        raise IndexError('Index out of range')

    def __setitem__(self, index, value):
        if index < 0:
            index += self.count

        if 0 <= index < self.count:
            self._data[index] = value
        else:
            raise IndexError('Index out of range')

    def __delitem__(self, index):
        if index < 0:
            index += self.count

        if 0 <= index < self.count:
            for i in range(index, self.count - 1):
                self._data[i] = self._data[i + 1]

            self._data[self.count - 1] = None
            self.count -= 1
        else:
            raise IndexError('Index out of range')

    def __contains__(self, item):
        for i in range(self.count):
            if self._data[i] == item:
                return True

        return False

    def __eq__(self, other):
        if isinstance(other, List) and self.count == other.count:
            for i in range(self.count):
                if self._data[i] != other._data[i]:
                    return False

            return True

        return False

    def __ne__(self, other):
        return not self.__eq__(other)

    def _resize(self, new_capacity):
        new_data = empty_list(new_capacity)

        for i in range(self.count):
            new_data[i] = self._data[i]

        self._data = new_data
        self.capacity = new_capacity

    def append(self, item):
        if self.count == self.capacity:
            self._resize(2 * self.capacity)

        self._data[self._count] = item
        self.count += 1

    def insert(self, index, item):
        if self.count == self.capacity:
            self._resize(2 * self.capacity)

        if index < 0:
            index += self.count

        if 0 <= index <= self.count:
            for i in range(self.count, index, -1):
                self._data[i] = self._data[i - 1]

            self._data[index] = item
            self.count += 1
        else:
            raise IndexError('Index out of range')

    def pop(self, index=-1):
        if index < 0:
            index += self.count

        if 0 <= index < self.count:
            item = self._data[index]
            self.__delitem__(index)
            return item

        raise IndexError('Index out of range')

def empty_list(n):
    return [None] * n