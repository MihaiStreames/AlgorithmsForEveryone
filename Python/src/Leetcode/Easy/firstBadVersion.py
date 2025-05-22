def firstBadVersion(n: int) -> int:
    bi, bs = 0, n
    m = (bi + bs) // 2

    while bi < bs:
        if isBadVersion(m):
            bs = m
        else:
            bi = m + 1
        m = (bi + bs) // 2

    return m