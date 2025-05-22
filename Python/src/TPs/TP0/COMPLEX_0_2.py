def belongs_to1(vector: list[int], x: int) -> bool:
    # Meilleur cas: Theta(1) - O(1) / Omega(1)
    # Moyen cas: Theta(n) car x en pos k, donc 1/n * sum(k=0 -> n-1 of k) = 1/n * Theta(n^2) = Theta(n)
    # Pire cas: Theta(n) - O(n) / Omega(n)
    for e in vector:
        if e == x:
            return True
    return False

def belongs_to2(vector: list[int], x: int) -> bool:
    # Meilleur cas: Theta(1) - O(1) / Omega(1)
    # Moyen cas: Theta(1)
    # Pire cas: Theta(1) - O(1) / Omega(1)
    for e in vector:
        return e == x

def belongs_to3(vector: list[int], x: int) -> bool:
    # Meilleur cas: Theta(n) - O(n) / Omega(n)
    # Moyen cas: Theta(n) - O(n) / Omega(n)
    # Pire cas: Theta(n) - O(n) / Omega(n)
    i = 0
    ret = False
    while i < len(vector):
        if vector[i] == x:
            ret = True
        i += 1
    return ret