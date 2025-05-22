def longest_sequence(n: int):
    # if '0' not in s
    # return 0 cause no distance
    # else separate each '1'
    # get max of lengths

    binary_str = bin(n)[2:]

    def helper(s: str):
        if '0' not in s:
            return 0
        else:
            zeros = s.split('1')
            lengths = [len(z) for z in zeros]
            return max(lengths)

    return helper(binary_str)