# find optimal bst using dynamic programming, with only successful searches
# we need a 2D array to store the optimal cost and a 2D array to store the root of the optimal subtree
# i, j are the indexes of the 2D array

# we have 1, 2, 3, 4
# 1: 10, 2: 20, 3: 30, 4: 40
# frequency: 4, 2, 6, 3

import numpy as np

def optimal_bst(keys, freq):
    n = len(keys)
    cost = np.zeros((n, n))
    root = np.zeros((n, n))

    for i in range(n):
        cost[i][i] = freq[i]
        root[i][i] = i

    for l in range(2, n + 1):
        for i in range(n - l + 1):
            j = i + l - 1
            cost[i][j] = float('inf')

            for r in range(i, j + 1):
                c = 0

                if r > i:
                    c += cost[i][r - 1]
                if r < j:
                    c += cost[r + 1][j]

                c += sum(freq[i:j + 1])

                if c < cost[i][j]:
                    cost[i][j] = c
                    root[i][j] = r

    return cost, root

def construct(root, keys, start, end, parent, bool):
    if start > end:
        if bool:
            print(f"NULL <- {keys[parent]}")
        else:
            print(f"{keys[parent]} -> NULL")
        return

    node = int(root[start][end])

    if parent == -1:
        print(f"Root: {keys[node]}")
    elif bool:
        print(f"{keys[node]} <- {keys[parent]}")
    else:
        print(f"{keys[parent]} -> {keys[node]}")

    construct(root, keys, start, node - 1, node, True)
    construct(root, keys, node + 1, end, node, False)

if __name__ == '__main__':
    keys = [1, 2, 3, 4]
    values = [10, 20, 30, 40]
    freq = [2, 9, 3, 7]

    cost, root = optimal_bst(keys, freq)
    print(f"The optimal BST cost is: {int(cost[0][len(keys) - 1])}\n")
    construct(root, values, 0, len(keys) - 1, -1, True)