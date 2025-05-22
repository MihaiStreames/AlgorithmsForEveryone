from TP1.ADT_1_2 import ComplexMatrix

class MultComplexMatrix(ComplexMatrix):
    def __init__(self, rows: int, columns: int):
        super().__init__(rows, columns)

    def __matmul__(self, other: ComplexMatrix):
        if self.columns != other.rows:
            raise ArithmeticError("Incompatible dimensions for matrix multiplication.")

        res = ComplexMatrix(self.rows, other.columns)

        for i in range(self.rows):
            for j in range(other.columns):
                for k in range(self.columns):
                    res[i, j] += self[i, k] * other[k, j]

        return res