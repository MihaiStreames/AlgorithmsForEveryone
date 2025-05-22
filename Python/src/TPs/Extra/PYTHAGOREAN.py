from Blueprints.Data_Structures.Heap import MinHeap


def alpha(a, b, c):
    return a - 2 * b + 2 * c, 2 * a - b + 2 * c, 2 * a - 2 * b + 3 * c


def beta(a, b, c):
    return 2 * a + b + 2 * c, a + 2 * b + 2 * c, 2 * a + 2 * b + 3 * c


def gamma(a, b, c):
    return -2 * a + b + 2 * c, -a + 2 * b + 2 * c, -2 * a + 2 * b + 3 * c


def triples(n):
    root = (3, 4, 5)

    heap = MinHeap()
    heap.insert(root)

    seen = set()
    seen.add(root)

    while heap.array:
        triplet = heap.delete()

        if triplet[2] > n:
            continue
        yield triplet

        for f in (alpha, beta, gamma):
            new = f(*triplet)

            if new not in seen and new[2] <= n:
                seen.add(new)
                heap.insert(new)
    return


def main():
    for triplet in triples(100):
        print(triplet)


if __name__ == '__main__':
    main()
