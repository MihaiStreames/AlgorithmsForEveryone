from ADT_1_1 import ComplexNumber

class ComplexMatrix:
    def __init__(self, rows: int, columns: int):
        self._rows = rows
        self._columns = columns
        self.mat = [[ComplexNumber() for _ in range(columns)] for _ in range(rows)]

    @property
    def rows(self):
        return self._rows

    @property
    def columns(self):
        return self._columns

    def __repr__(self):
        return self.__str__()

    def __str__(self):
        return '\n'.join(['; '.join([str(item) for item in row]) for row in self.mat])

    def __getitem__(self, pos: tuple[int, int]):
        row, col = pos
        return self.mat[row][col]

    def __setitem__(self, pos: tuple[int, int], item: ComplexNumber):
        row, col = pos
        self.mat[row][col] = item

    def __imul__(self, other: ComplexNumber):
        for r in range(self.rows):
            for c in range(self.columns):
                self[r, c] *= other

        return self