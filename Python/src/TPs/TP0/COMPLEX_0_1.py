def f1(vector: list[int]) -> int:
    # Theta(n) - O(n) / Omega(n)
    ret = 0
    for i in range(len(vector)):
        ret += vector[i]
    return ret

def f2(vector: list[int]) -> int:
    # Theta(n)
    ret = 0
    for e in reversed(vector):
        ret += e
    return ret

def f3(n: int) -> int:
    # Theta(n) - O(n) + O(1) / Omega(n)
    ret = 0
    for i in range(n):
        ret += i
    return ret