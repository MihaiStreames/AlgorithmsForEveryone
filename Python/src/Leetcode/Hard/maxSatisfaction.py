def maxSatisfaction(satisfaction: list[int]) -> int:
    if not satisfaction:
        return 0
    satisfaction.sort()

    total_sum = 0
    partial_sum = 0
    max_value = 0

    for i in range(len(satisfaction) - 1, -1, -1):
        partial_sum += satisfaction[i]
        if partial_sum > 0:
            total_sum += partial_sum
            max_value = max(max_value, total_sum)
        else:
            break

    return max_value