class Solution:
    def subsetsWithDup(self, nums: List[int]) -> List[List[int]]:
        nums.sort()
        temp = []
        res = []

        def helper(idx):
            if idx == len(nums):
                res.append(list(temp))
                return

            temp.append(nums[idx])
            helper(idx + 1)
            temp.pop()

            # Skip duplicates
            while idx + 1 < len(nums) and nums[idx] == nums[idx + 1]:
                idx += 1

            helper(idx + 1)

        helper(0)
        return res