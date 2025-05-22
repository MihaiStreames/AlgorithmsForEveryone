class Solution:
    def solveNQueens(self, n):
        res = []
        self.solve(n, 0, [], res)
        return res

    def is_valid(self, col_placements):
        row_id = len(col_placements) - 1
        
        for i in range(row_id):
            diff = abs(col_placements[i] - col_placements[row_id])

            if diff == 0 or diff == row_id - i:
                return False
        return True

    def solve(self, n, row, col_placements, res):
        if row == n:
            board = self.create_board(col_placements)
            res.append(board)
        else:
            for col in range(n):
                col_placements.append(col)

                if self.is_valid(col_placements):
                    self.solve(n, row + 1, col_placements, res)

                col_placements.pop()

    def create_board(self, col_placements):
        n = len(col_placements)
        board = []

        for i in range(n):
            row = ["."] * n
            row[col_placements[i]] = "Q"
            board.append("".join(row))

        return board