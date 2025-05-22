def generate_subsets_size(k, n):
    if k > n:
        return []

    temp = []
    result = []

    def helper(start, current_size):
        # Only add to result when the current size of temp matches k
        if current_size == k:
            result.append(tuple(temp))
            return

        for i in range(start, n + 1):
            temp.append(i)
            helper(i + 1, current_size + 1)
            temp.pop()

    helper(1, 0)
    return [list(subset) for subset in result]

print(*generate_subsets_size(2, 4))