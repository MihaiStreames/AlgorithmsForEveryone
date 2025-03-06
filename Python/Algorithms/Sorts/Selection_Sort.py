def selection_sort(array):
    for last in range(len(array) - 1, 0, -1):
        max_pos = 0

        for location in range(1, last + 1):
            if array[location] > array[max_pos]:
                max_pos = location

        array[max_pos], array[last] = array[last], array[max_pos]
