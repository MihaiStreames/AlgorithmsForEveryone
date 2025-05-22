def det(matrix):
    size = len(matrix)

    if size == 1:
        return matrix[0][0]

    deter = 0

    for col_index in range(size):
        sub_matrix = []

        for row_index, row in enumerate(matrix):
            if col_index == row_index:
                continue

            sub_matrix.append(row[1:])

        deter += (-1) ** col_index * matrix[col_index][0] * det(sub_matrix)

    return deter