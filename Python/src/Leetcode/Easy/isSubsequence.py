def isSubsequence(s, t):
    s_1 = [*s]
    s_2 = [*t]

    for sub in s_1:
        if sub in s_2:
            print(sub)
            return True