def lengthOfLongestSubstrings(s: str) -> int:
    if not s:
        return 0
    if len(s) == 1:
        return 1

    i = 0
    j = 1
    max_len = 1

    while j < len(s):
        if s[j] not in s[i:j]:
            j += 1
            max_len = max(max_len, j - i)
        else:
            i += 1

    return max_len