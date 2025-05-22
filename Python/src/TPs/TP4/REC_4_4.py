def recursive_agm(x, y, eps):
    def helper(a_n, g_n, eps):
        if abs(a_n - g_n) < eps:
            return (a_n + g_n) / 2
        else:
            return helper((a_n + g_n) / 2, (a_n * g_n) ** 0.5, eps)

    return helper(x, y, eps)

def agm(x, y, eps):
    a_n, g_n = x, y

    while abs(a_n - g_n) >= eps:
        a_n, g_n = (a_n + g_n) / 2, (a_n * g_n) ** 0.5

    return (a_n + g_n) / 2