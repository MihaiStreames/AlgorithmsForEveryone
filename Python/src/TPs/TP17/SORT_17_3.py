def quicksort(array, key=lambda x: x):
    def partition(low, high):
        pivot = key(array[high])
        i = low - 1

        for j in range(low, high):
            if key(array[j]) <= pivot:
                i += 1
                array[i], array[j] = array[j], array[i]

        array[i + 1], array[high] = array[high], array[i + 1]
        return i + 1

    def quicksort_recursive(low, high):
        if low < high:
            pi = partition(low, high)
            quicksort_recursive(low, pi - 1)
            quicksort_recursive(pi + 1, high)

    quicksort_recursive(0, len(array) - 1)

if __name__ == "__main__":
    data = [3, 6, 8, 10, 1, 2, 1]
    quicksort(data, key=lambda x: x)
    print(data)