'''
Écrivez une version récursive et non-récursive d’un parcours préfixé et suffixé d’un arbre d-aire.
'''

tree_1 = [1, [2, [4], [5]], [3, [6], [7]]]
tree_2 = [1, [2, [5], [6], [7]], [3], [4, [8]]]
tree_3 = [1, [2, [9], [10], [11], [12]], [3, [13]], [4], [5, [14], [15]]]
tree_4 = [1, [2, [4], [5, [8]]], [3]]
tree_5 = [1, [2, [6]], [3, [7], [8], [9]], [4]]

trees = [tree_1, tree_2, tree_3, tree_4, tree_5]

def ppr(tree: list):
    if not tree:
        return
    print(tree[0])
    for child in tree[1:]:
        ppr(child)

def psr(tree: list):
    if not tree:
        return
    for child in tree[1:]:
        psr(child)
    print(tree[0])

def ppi(tree: list):
    if not tree:
        return
    stack = [tree]
    while stack:
        current = stack.pop()
        print(current[0])
        stack.extend(reversed(current[1:]))

def psi(tree: list):
    if not tree:
        return
    stack = [tree]
    done = []
    while stack:
        current = stack[-1]
        if current in done:
            stack.pop()
            print(current[0])
        else:
            done.append(current)
            for child in reversed(current[1:]):
                stack.append(child)