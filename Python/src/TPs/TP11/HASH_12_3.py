def xxhash(t: tuple) -> int:
    L = len(t)

    X = 3527539
    P = 2870177450012600261
    N = 14029467366897019727
    M = 11400714785074694791

    x = P

    for k in t:
        x += (h(k, t) * N) % 2 ** 64
        x = rot_l(x, 31)
        x = (x * M) % 2 ** 64

    return x + (L ^ P ^ X) % 2 ** 64

def rot_l(x: int, n: int) -> int:
    return (x << n) | (x >> (64 - n))

def h(k: int, t: tuple) -> int:
    return k % len(t)

if __name__ == '__main__':
    hash1 = xxhash((1, 2, 3))

    print(hash1)
    print(hex(hash1))