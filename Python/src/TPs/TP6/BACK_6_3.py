from Blueprints.Data_Structures import Deque

def is_valid(x, y, board):
    return 0 <= x < len(board) and 0 <= y < len(board[0]) and board[x][y] == "_"

def valid_moves(x, y, board):
    moves = []

    move_x = [0, 1, 0, -1]
    move_y = [1, 0, -1, 0]

    for i in range(4):
        new_x = x + move_x[i]
        new_y = y + move_y[i]

        if is_valid(new_x, new_y, board):
            moves.append((new_x, new_y))

    return moves

def bfs(board):
    n, m = len(board), len(board[0])

    if n < 1 or m < 1 or board[0][0] == "x" or board[n - 1][m - 1] == "x":
        return False, "Invalid start or end position"

    queue = Deque()
    queue.add_rear((0, 0, 0))
    board[0][0] = "o"

    while not queue.is_empty():
        x, y, dist = queue.remove_front()

        if x == n - 1 and y == m - 1:
            return True, dist + 1

        for new_x, new_y in valid_moves(x, y, board):
            board[new_x][new_y] = "o"
            queue.add_rear((new_x, new_y, dist + 1))

    return False, "Solution does not exist"

def print_solution(board):
    for row in board:
        print(" ".join(str(cell).rjust(2, ' ') for cell in row))

if __name__ == "__main__":
    board = [
        ["_", "x", "_", "x", "_"],
        ["_", "x", "x", "x", "_"],
        ["_", "_", "_", "_", "_"],
        ["_", "_", "_", "x", "_"],
        ["_", "_", "_", "x", "_"]
    ]

    solution_exists, result = bfs(board)

    if solution_exists:
        print("Shortest path length:", result)
    else:
        print("No solution:", result)