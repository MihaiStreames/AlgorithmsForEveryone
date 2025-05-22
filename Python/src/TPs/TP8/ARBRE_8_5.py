class TrieNode:
    def __init__(self):
        self.children = {}
        self.is_end = False

class Trie:
    def __init__(self):
        self.root = TrieNode()

    def insert(self, key):
        node = self.root
        # Iterating through the characters of the key and creating a new node for each character if it doesn't exist
        for char in key:
            if char not in node.children:
                node.children[char] = TrieNode()

            node = node.children[char]
        # Marking the last node as the end of the key (because it needs to be a word)
        node.is_end = True

    def contains(self, key):
        node = self.root
        # Iterating through the characters of the key and returning False if a character doesn't exist
        for char in key:
            if char not in node.children:
                return False

            node = node.children[char]
        # Returning True if the last node is marked as the end of the key, which means it's the word we're looking for
        return node.is_end

    def delete(self, key):
        self._delete(self.root, key, 0)

    def _delete(self, node, key, index):
        if index == len(key):
            if not node.is_end:
                return False

            node.is_end = False

            return len(node.children) == 0

        char = key[index]

        if char not in node.children:
            return False

        to_delete = self._delete(node.children[char], key, index + 1)

        if to_delete:
            del node.children[char]
            return len(node.children) == 0

        return False

if __name__ == '__main__':
    trie = Trie()

    trie.insert('algo')
    trie.insert('algorithmique')
    trie.insert('algorithm')

    print(trie.contains('algo'))
    print(trie.contains('algorithmique'))
    print(trie.contains('algorithm'))