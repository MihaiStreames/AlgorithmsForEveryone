def quickselect(arr, k):
    def partition(left, right, pivot_index):
        pivot = arr[pivot_index]
        arr[pivot_index], arr[right] = arr[right], arr[pivot_index]
        idx = left

        for i in range(left, right):
            if arr[i] < pivot:
                arr[i], arr[idx] = arr[idx], arr[i]
                idx += 1

        arr[right], arr[idx] = arr[idx], arr[right]
        return idx

    def select(left, right, k):
        if left == right:
            return arr[left]

        pivot_idx = partition(left, right, (left + right) // 2)

        if k == pivot_idx:
            return arr[k]
        elif k < pivot_idx:
            return select(left, pivot_idx - 1, k)
        else:
            return select(pivot_idx + 1, right, k)

    return select(0, len(arr) - 1, k)