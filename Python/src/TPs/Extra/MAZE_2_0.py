from Blueprints.Data_Structures.Heap import MinHeap


def is_near_mine(x, y, board):
    for dx, dy in [(0, 1), (1, 0), (0, -1), (-1, 0)]:

        nx, ny = x + dx, y + dy
        if 0 <= nx < len(board) and 0 <= ny < len(board[0]) and board[nx][ny] == "x":
            return True
    return False


def is_valid(x, y, board):
    return 0 <= x < len(board) and 0 <= y < len(board[0]) and board[x][y] == "_" and not is_near_mine(x, y, board)


def find_start_end_points(board):
    start_points = [(x, 0) for x in range(len(board)) if is_valid(x, 0, board)]
    end_points = [(x, len(board[0]) - 1) for x in range(len(board)) if is_valid(x, len(board[0]) - 1, board)]
    return start_points, end_points


def manhattan_distance(x, y, goals):
    return min(abs(x - gx) + abs(y - gy) for gx, gy in goals)


def a_star_search(board, start, valid_ends):
    queue = MinHeap([(0 + manhattan_distance(start[0], start[1], valid_ends), 0, start[0], start[1])])
    visited = {start}
    parent = {start: None}

    while queue.array:
        _, dist, x, y = queue.delete()

        if (x, y) in valid_ends:
            path = []
            while (x, y) != start:
                path.append((x, y))
                x, y = parent[(x, y)]
            path.append(start)
            path.reverse()
            return dist, path

        for dx, dy in [(0, 1), (1, 0), (0, -1), (-1, 0)]:
            nx, ny = x + dx, y + dy

            if (nx, ny) not in visited and is_valid(nx, ny, board):
                visited.add((nx, ny))
                parent[(nx, ny)] = (x, y)
                queue.insert((dist + 1 + manhattan_distance(nx, ny, valid_ends), dist + 1, nx, ny))

    return float('inf'), []


def print_solution(board):
    for row in board:
        print(" ".join(str(cell).rjust(2, ' ') for cell in row))


if __name__ == "__main__":
    import random

    def generate_large_board(size, mine_probability=0.13):
        board = []
        for _ in range(size):
            row = ['x' if random.random() < mine_probability else '_' for _ in range(size)]
            board.append(row)
        return board

    size = 20
    board = generate_large_board(size)
    start_points, end_points = find_start_end_points(board)
    end_set = set(end_points)
    shortest_path = float('inf')
    best_path = []

    for start in start_points:
        dist, path = a_star_search(board, start, end_set)
        if dist < shortest_path:
            shortest_path = dist
            best_path = path

    if best_path:
        for x, y in best_path:
            board[x][y] = 'o'
        print(f"Shortest path length is {shortest_path} steps")
        print_solution(board)
    else:
        print("No solution found")
