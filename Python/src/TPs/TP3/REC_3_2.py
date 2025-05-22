def mirrored(vec: list[int], idx: int):
    if idx >= len(vec) // 2:
        return

    vec[idx], vec[-idx - 1] = vec[-idx - 1], vec[idx]

    mirrored(vec, idx + 1)