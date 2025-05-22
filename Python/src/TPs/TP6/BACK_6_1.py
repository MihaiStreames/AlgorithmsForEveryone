def is_valid(x, y, board):
    return 0 <= x < len(board) and 0 <= y < len(board[0]) and board[x][y] == -1

def valid_moves(x, y, board):
    moves = []

    move_x = [2, 1, -1, -2, -2, -1, 1, 2]
    move_y = [1, 2, 2, 1, -1, -2, -2, -1]

    for i in range(8):
        new_x = x + move_x[i]
        new_y = y + move_y[i]

        if is_valid(new_x, new_y, board):
            moves.append((new_x, new_y))

    return moves

def solve(x, y, move_i, board, n, m, end_cell):
    if move_i == n * m:
        return x == end_cell[0] and y == end_cell[1]

    for new_x, new_y in valid_moves(x, y, board):
        board[new_x][new_y] = move_i

        if solve(new_x, new_y, move_i + 1, board, n, m, end_cell):
            return True

        board[new_x][new_y] = -1

    return False

def knight_route(n, m, end_cell):
    if n < 1 or m < 1:
        return False, "Board is too small"

    board = [[-1 for _ in range(m)] for _ in range(n)]
    board[0][0] = 0  # Start position

    if solve(0, 0, 1, board, n, m, end_cell):
        return True, board
    else:
        return False, "Solution does not exist"

def print_solution(board):
    n, m = len(board), len(board[0])
    print(f"Number of moves: {n * m}")

    for row in board:
        print(" ".join(str(cell).rjust(2, ' ') for cell in row))

if __name__ == "__main__":
    n, m = 5, 5
    end_cell = (4, 0)
    solution_exists, result = knight_route(n, m, end_cell)

    if solution_exists:
        print_solution(result)
    else:
        print("No solution:", result)