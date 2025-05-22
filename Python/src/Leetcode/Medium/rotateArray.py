def rotate(nums: list[int], k: int) -> None:
    for i in range(k):
        nums.insert(0, nums.pop())