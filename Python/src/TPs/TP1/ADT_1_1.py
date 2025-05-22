class ComplexNumber:
    def __init__(self, r=0, i=0):
        self._re = r
        self._im = i

    @property
    def re(self):
        return self._re

    @re.setter
    def re(self, value: int):
        self._re = value

    @property
    def im(self):
        return self._im

    @im.setter
    def im(self, value: int):
        self._im = value

    def __str__(self):
        return f"{self.re:g} + {self.im:g}i"

    def __iadd__(self, other):
        if isinstance(other, (int, float)):
            self.re += other
        elif isinstance(other, ComplexNumber):
            self.re += other.re
            self.im += other.im
        else:
            return NotImplemented

        return self

    def __add__(self, other):
        if isinstance(other, (int, float)):
            other = ComplexNumber(other, 0)
        elif not isinstance(other, ComplexNumber):
            return NotImplemented

        return ComplexNumber(self.re + other.re, self.im + other.im)

    def __imul__(self, other):
        if isinstance(other, (int, float)):
            other = ComplexNumber(other, 0)
        elif not isinstance(other, ComplexNumber):
            return NotImplemented

        temp_re = self.re * other.re - self.im * other.im
        self.im = self.re * other.im + self.im * other.re
        self.re = temp_re

        return self

    def __mul__(self, other):
        if isinstance(other, (int, float)):
            other = ComplexNumber(other, 0)
        elif not isinstance(other, ComplexNumber):
            return NotImplemented

        return ComplexNumber(self.re * other.re - self.im * other.im, self.re * other.im + self.im * other.re)