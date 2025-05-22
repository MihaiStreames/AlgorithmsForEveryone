"""
Implémentez les méthodes d’insertion et de recherche d’une table de hachage à adressage ouvert
utilisant le double hachage h(k,j) = h1(k) + jh2(k) où h1 est la fonction K&R et h2 est la fonction DJB2.
"""

class HashTable:
    def __init__(self, size):
        self.size = size
        self.table = [None] * size
        self.deleted = object()  # A marker for deleted elements

    def h1(self, key):
        # K&R hash function
        val = 0

        for char in key:
            val = (val * 31 + ord(char)) % self.size
        return val

    def h2(self, key):
        # DJB2 hash function
        val = 5381

        for char in key:
            val = (val * 33 + ord(char)) % self.size
        return val

    def h(self, key, j):
        return (self.h1(key) + j * self.h2(key)) % self.size

    def insert(self, key, value):
        for i in range(self.size):
            hash_idx = self.h(key, i)

            if self.table[hash_idx] is None or self.table[hash_idx] is self.deleted:
                self.table[hash_idx] = (key, value)
                return

        raise Exception("Hash table is full")

    def search(self, key):
        for i in range(self.size):
            hash_idx = self.h(key, i)

            if self.table[hash_idx] is None:
                return None
            if self.table[hash_idx] is not self.deleted and self.table[hash_idx][0] == key:
                return self.table[hash_idx][1]

        return None

    def delete(self, key):
        for i in range(self.size):
            hash_idx = self.h(key, i)

            if self.table[hash_idx] is None:
                return

            if self.table[hash_idx] is not self.deleted and self.table[hash_idx][0] == key:
                self.table[hash_idx] = self.deleted
                return

def main():
    hash_table = HashTable(10)
    hash_table.insert("key1", "value1")
    hash_table.insert("key2", "value2")

    print(hash_table.search("key1"))
    print(hash_table.search("key2"))
    print(hash_table.search("key3"))

    hash_table.delete("key1")
    print(hash_table.search("key1"))

if __name__ == '__main__':
    main()