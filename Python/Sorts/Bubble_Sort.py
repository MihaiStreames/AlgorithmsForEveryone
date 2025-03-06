def bubble_sort(array):
    for end_idx in range(len(array) - 1, 0, -1):
        for curr in range(end_idx):
            if array[curr] > array[curr + 1]:
                array[curr], array[curr + 1] = array[curr + 1], array[curr]


def optimized_bubble_sort(array):
    swapped = True
    last_unsorted_idx = len(array) - 1

    while last_unsorted_idx > 0 and swapped:
        swapped = False

        for curr in range(last_unsorted_idx):
            if array[curr] > array[curr + 1]:
                swapped = True
                array[curr], array[curr + 1] = array[curr + 1], array[curr]

        last_unsorted_idx -= 1
