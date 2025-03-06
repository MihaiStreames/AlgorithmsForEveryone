def gap_insertion_sort(array, start, gap):
    for i in range(start + gap, len(array), gap):
        curr_val = array[i]
        curr_pos = i

        while curr_pos >= gap and array[curr_pos - gap] > curr_val:
            array[curr_pos] = array[curr_pos - gap]
            curr_pos -= gap

        array[curr_pos] = curr_val


def shell_sort(array):
    sublist_count = len(array) // 2

    while sublist_count > 0:
        for start_pos in range(sublist_count):
            gap_insertion_sort(array, start_pos, sublist_count)

        print("After increments of size", sublist_count, "the list is", array)
        sublist_count //= 2
