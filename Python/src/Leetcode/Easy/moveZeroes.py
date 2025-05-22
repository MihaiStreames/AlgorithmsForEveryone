def moveZeroes(nums: list[int]) -> None:
    tail = 0

    for head in range(len(nums)):
        if nums[head]:
            nums[tail] = nums[head]
            tail += 1

    for n in range(tail, len(nums)):
        nums[n] = 0