from Blueprints.Data_Structures import Stack

def hanoi(n: int):
    tower_a = Stack()
    tower_b = Stack()
    tower_c = Stack()

    for i in range(n, 0, -1):
        tower_a.push(i)

    def helper(n: int,
               source: Stack, auxiliary: Stack, destination: Stack,
               s: str, a: str, d: str):
        # If only one disk, move from source to dest
        if n == 1:
            destination.push(source.pop())
            print(f"Déplacer un disque de {s} à {d}")
            return
        # Move n - 1 disks from source to aux (B), using dest as temp storage
        helper(n - 1, source, destination, auxiliary, s, d, a)
        # Move nth disk from source to dest
        destination.push(source.pop())
        print(f"Déplacer un disque de {s} à {d}")
        # Move n - 1 disks from aux to dest, using source as temp storage
        helper(n - 1, auxiliary, source, destination, a, s, d)

    helper(n, tower_a, tower_b, tower_c, 'A', 'B', 'C')