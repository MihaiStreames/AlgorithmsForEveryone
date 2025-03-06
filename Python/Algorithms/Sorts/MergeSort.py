def mergeSort(array):
    print("Splitting", array)

    if len(array) > 1:
        mid = len(array) // 2
        left_half = array[:mid]
        right_half = array[mid:]

        mergeSort(left_half)
        mergeSort(right_half)

        left_idx = 0
        right_idx = 0
        merged_idx = 0

        while left_idx < len(left_half) and right_idx < len(right_half):
            if left_half[left_idx] < right_half[right_idx]:
                array[merged_idx] = left_half[left_idx]
                left_idx += 1
            else:
                array[merged_idx] = right_half[right_idx]
                right_idx += 1
            merged_idx += 1

        while left_idx < len(left_half):
            array[merged_idx] = left_half[left_idx]
            left_idx += 1
            merged_idx += 1

        while right_idx < len(right_half):
            array[merged_idx] = right_half[right_idx]
            right_idx += 1
            merged_idx += 1

    print("Merging", array)
