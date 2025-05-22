def twoSum(nums: list[int], target: int) -> list[int]:
    solution = {}

    for i, num in enumerate(nums):
        if target - num in solution:
            return [solution[target - num], i]
        solution[num] = i