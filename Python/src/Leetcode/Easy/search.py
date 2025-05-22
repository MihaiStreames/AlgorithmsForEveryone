def search(nums: list[int], target:int) -> int:
    bi, bs = 0, len(nums)
    m = (bi + bs) // 2

    while bi < bs and nums[m] != target:
        m = (bi + bs) // 2
        if nums[m] > target:
            bs = m
        else:
            bi = m + 1

    if nums[m] != target or m > len(nums):
        return -1
    return m