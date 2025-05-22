def isIsomorphic(s, t):
    s_1 = []
    s_2 = []

    for i in s:
        s_1.append(s.index(i))
    for i in t:
        s_2.append(t.index(i))

    if s_1 == s_2:
        return True
    return False

def isIsomorphicShort(s, t):
    return len(set(s)) == len(set(zip(s, t))) == len(set(t))