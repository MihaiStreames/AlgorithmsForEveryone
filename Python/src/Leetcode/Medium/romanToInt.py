def romanToInt(s):
    roman_nums = {"I": 1, "V": 5, "X": 10, "L": 50, "C": 100, "D": 500, "M": 1000}

    number = []
    s = s.replace("IV", "IIII").replace("IX", "VIIII").replace("XL", "XXXX")\
        .replace("XC", "LXXXX").replace("CD", "CCCC").replace("CM", "DCCCC")

    for n in s.strip():
        number.append(roman_nums[n])