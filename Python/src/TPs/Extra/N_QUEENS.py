def n_queens(n):
    res = []
    solve(n, 0, [], res)
    return res

def is_valid(col_placements):
    row_id = len(col_placements) - 1

    for i in range(row_id):
        diff = abs(col_placements[i] - col_placements[row_id])

        if diff == 0 or diff == row_id - i:
            return False

    return True

def solve(n, row, col_placements, res):
    if row == n:
        res.append(col_placements.copy())
    else:
        for col in range(n):
            col_placements.append(col)

            if is_valid(col_placements):
                solve(n, row + 1, col_placements, res)

            col_placements.pop()