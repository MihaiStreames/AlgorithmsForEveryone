class Solution:
    def totalNQueens(self, n):
        res = [0]
        self.solve(n, 0, [], res)
        return res[0]

    def is_valid(self, col_placements):
        row_id = len(col_placements) - 1

        for i in range(row_id):
            diff = abs(col_placements[i] - col_placements[row_id])

            if diff == 0 or diff == row_id - i:
                return False
        return True

    def solve(self, n, row, col_placements, res):
        if row == n:
            res[0] += 1
        else:
            for col in range(n):
                col_placements.append(col)

                if self.is_valid(col_placements):
                    self.solve(n, row + 1, col_placements, res)

                col_placements.pop()
                
# this is hilarious

# class Solution:
#     def totalNQueens(self, n: int) -> int:
#         return [0, 1, 0, 0, 2, 10, 4, 40, 92, 352][n]