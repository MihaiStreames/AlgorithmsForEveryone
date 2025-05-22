def runningSum(nums):
    for n in range(1, len(nums)):
        nums[n] += nums[n - 1]
    return nums