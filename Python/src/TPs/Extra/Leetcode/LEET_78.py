class Solution:
    def subsets(self, nums: List[int]) -> List[List[int]]:
        temp = []
        res = set()

        def helper(idx):
            if idx == len(nums):
                res.add(tuple(temp))
                return
            else:
                temp.append(nums[idx])
                helper(idx + 1)

                temp.pop()
                helper(idx + 1)

        helper(0)
        return [list(sub) for sub in res]