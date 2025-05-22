def deleteGreatestValues(grid):
    answer = 0

    while grid:
        max_elements = [max(row) for row in grid]
        answer += max(max_elements)
        grid = [[val for val in row if val != max(row)] for row in grid]
        grid = [row for row in grid if row]

<<<<<<< Updated upstream
    return answer
=======
    return answer


print(deleteGreatestValues([[1, 2, 4], [3, 3, 1]]))
>>>>>>> Stashed changes
