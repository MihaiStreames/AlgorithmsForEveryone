def insertion_sort(array):
    for i in range(1, len(array)):
        curr_val = array[i]
        curr_pos = i - 1

        while curr_pos >= 0 and array[curr_pos] > curr_val:
            array[curr_pos + 1] = array[curr_pos]
            curr_pos -= 1

        array[curr_pos + 1] = curr_val
