def g1(n: int) -> int:
    # Meilleur cas: Theta(1) car si n == 1, alors n // 2 = 0
    # Moyen cas: Theta(log(n))
    # Pire cas: Theta(log(n)) - O(log(n)) / Omega(n)
    ret = 0
    while n > 0:
        n //= 2
        ret += 1
    return ret

def g2(n: int) -> int:
    # Theta(n) - O(2^n)) / Omega(n)
    ret = 0
    k = 1
    while k <= n:
        for j in range(k):
            ret += j
        k *= 2
    return ret