def max_elem(vec: list[int], idx: int, max: int) -> int:
    if idx == len(vec):
        return max

    if vec[idx] > max:
        max = vec[idx]

    return max_elem(vec, idx + 1, max)