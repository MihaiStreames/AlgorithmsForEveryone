from Blueprints.Algorithms.Sorts import insertion_sort


# Simple Quick Sort
class SimpleQuickSort:
    def __init__(self, array, type=1):
        self.array = array
        self.type = type
        self.quick_sort(0, len(self.array) - 1)
        print(self.array)

    def partition(self, type, l, r, array):
        if type == 1:
            return self.partition_left(l, r, array)
        else:
            return self.partition_right(l, r, array)

    def quick_sort(self, l, r):
        if l < r:
            pivot = self.partition(self.type, l, r, self.array)
            self.quick_sort(l, pivot - 1)
            self.quick_sort(pivot + 1, r)

    def partition_right(self, l, r, array):
        pivot = array[r]  # Choose the last element as the pivot
        i = l - 1

        for j in range(l, r):
            if array[j] <= pivot:
                i += 1
                array[i], array[j] = array[j], array[i]

        array[i + 1], array[r] = array[r], array[i + 1]
        return i + 1

    def partition_left(self, l, r, array):
        pivot = array[l]  # Choose the first element as the pivot
        i = l + 1
        j = r

        while True:
            while i <= j and array[i] <= pivot:
                i += 1
            while j >= i and array[j] >= pivot:
                j -= 1

            if i >= j:
                break
            array[i], array[j] = array[j], array[i]

        array[l], array[j] = array[j], array[l]
        return j


# Quick Sort with Insertion Sort + Pivot Optimization
class QuickSort:
    def __init__(self, array):
        self.array = array
        self.threshold = 16
        self.quick_sort(0, len(self.array) - 1)
        self.insertion_sort = insertion_sort(self.array)
        print(self.array)

    def partition(self, start, end):
        pivot = self.array[end]
        i = start - 1

        for j in range(start, end):
            if self.array[j] <= pivot:
                i += 1
                self.array[i], self.array[j] = self.array[j], self.array[i]

        self.array[i + 1], self.array[end] = self.array[end], self.array[i + 1]
        return i + 1

    # Optimized pivot, based on median of three:
    # 1. Choose the first, middle and last elements from array
    # 2. Sort them and choose the middle element as the pivot
    def optimize_pivot(self, start, end):
        middle = (start + end) // 2

        if self.array[middle] > self.array[end]:
            self.array[middle], self.array[end] = self.array[end], self.array[middle]

        if self.array[start] > self.array[middle]:
            self.array[start], self.array[middle] = self.array[middle], self.array[start]

        if self.array[end] > self.array[middle]:
            self.array[end], self.array[middle] = self.array[middle], self.array[end]

    # If the size of the array is less than the threshold, use insertion sort
    def quick_sort(self, start, end):
        while end - start + 1 > self.threshold:
            self.optimize_pivot(start, end)
            pivot = self.partition(start, end)

            if pivot - start < end - pivot:
                self.quick_sort(start, pivot - 1)
                start = pivot + 1
            else:
                self.quick_sort(pivot + 1, end)
                end = pivot - 1


# Quick Sort using the Dutch flag method of sorting
class QuickSortDutchFlag(QuickSort):
    def __init__(self, array):
        super().__init__(array)

    def partition(self, start, end):
        pivot = self.array[end]
        low = start - 1
        high = end
        i = start

        while i <= high:
            if self.array[i] < pivot:
                low += 1
                self.array[low], self.array[i] = self.array[i], self.array[low]
                i += 1
            elif self.array[i] > pivot:
                self.array[i], self.array[high] = self.array[high], self.array[i]
                high -= 1
            else:
                i += 1

        return low + 1, high - 1

    def quick_sort(self, start, end):
        while end - start + 1 > self.threshold:
            self.optimize_pivot(start, end)
            low, high = self.partition(start, end)

            if low - start < end - high:
                self.quick_sort(start, low - 1)
                start = high + 1
            else:
                self.quick_sort(high + 1, end)
                end = low - 1
