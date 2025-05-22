def split(matrix):
    row = len(matrix)
    col = len(matrix[0]) if matrix else 0
    row2, col2 = row // 2, col // 2

    return [
        [row[:col2] for row in matrix[:row2]],
        [row[col2:] for row in matrix[:row2]],
        [row[:col2] for row in matrix[row2:]],
        [row[col2:] for row in matrix[row2:]]
    ]

def add_matrix(a, b):
    return [[a[i][j] + b[i][j] for j in range(len(a[0]))] for i in range(len(a))]

def sub_matrix(a, b):
    return [[a[i][j] - b[i][j] for j in range(len(a[0]))] for i in range(len(a))]

def strassen(a, b):
    if len(a) == 1:
        return [[a[0][0] * b[0][0]]]

    a1, a2, a3, a4 = split(a)
    b1, b2, b3, b4 = split(b)

    m1 = strassen(add_matrix(a1, a4), add_matrix(b1, b4))
    m2 = strassen(add_matrix(a3, a4), b1)
    m3 = strassen(a1, sub_matrix(b2, b4))
    m4 = strassen(a4, sub_matrix(b3, b1))
    m5 = strassen(add_matrix(a1, a2), b4)
    m6 = strassen(sub_matrix(a1, a3), add_matrix(b1, b2))
    m7 = strassen(sub_matrix(a2, a4), add_matrix(b3, b4))

    c11 = sub_matrix(add_matrix(m1, m4), add_matrix(m5, m7))
    c12 = add_matrix(m3, m5)
    c21 = add_matrix(m2, m4)
    c22 = add_matrix(sub_matrix(m1, m2), add_matrix(m3, m6))

    top = [c11[i] + c12[i] for i in range(len(c11))]
    bottom = [c21[i] + c22[i] for i in range(len(c21))]

    return top + bottom