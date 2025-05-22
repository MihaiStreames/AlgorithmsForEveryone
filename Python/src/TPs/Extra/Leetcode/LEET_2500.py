import heapq
from typing import List

class Solution:
    def deleteGreatestValue(self, grid: List[List[int]]) -> int:
        # given an m*n matrix, delete the greatest value in each row, and return the sum of the greatest values taken
        # the logic would be to make a heap of each row, and pop the greatest value from each row
        # then sum the greatest values taken

        temp = []
        res = 0

        while any(grid):
            for row in grid:
                heap = []

                for val in row:
                    heapq.heappush(heap, -val)

                temp.append(-heapq.heappop(heap))

                if temp[-1] in row:
                    row.remove(temp[-1])

            res += max(temp)
            temp = []

        return res

if __name__ == "__main__":
    test1 = [[1, 2, 4], [3, 3, 1]]
    test2 = [[58, 42, 8, 75, 28], [35, 21, 13, 21, 72]]

    sol = Solution()
    print(sol.deleteGreatestValue(test1))
    print(sol.deleteGreatestValue(test2))