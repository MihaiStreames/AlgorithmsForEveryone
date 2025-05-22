def pivotIndex(nums):
    sum_l = 0
    sum_r = sum(nums)

    for i in range(len(nums)):
        sum_r -= nums[i]
        if sum_l == sum_r:
            return i
        sum_l += nums[i]
    return -1