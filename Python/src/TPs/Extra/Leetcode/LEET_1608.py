class Solution:
    def specialArray(self, nums: List[int]) -> int:
        nums.sort()
        n = len(nums)

        # Check potential x from 0 to n
        for x in range(n + 1):
            # Find the first index where nums[index] >= x
            low, high = 0, n

            while low < high:
                mid = (low + high) // 2

                if nums[mid] >= x:
                    high = mid
                else:
                    low = mid + 1
            # Check if the remaining elements are exactly x
            if n - low == x:
                return x
        return -1
