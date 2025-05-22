def eratosthenes(N):
    # Theta(n log(log(n))) - Consistent behavior across different sizes of N.
    # O(n log(log(n))) - Decreasing frequency of primes and sum of operations across all primes.
    # Omega(n) - Unavoidable linear scan through 2 to N, marking non-primes.
    is_prime = [True] * (N + 1)
    primes = []
    for p in range(2, N + 1):
        if not is_prime[p]:
            continue
        primes.append(p)
        k = 2
        while k * p <= N:
            is_prime[k * p] = False
            k += 1
    return primes