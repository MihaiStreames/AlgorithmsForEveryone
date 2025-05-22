def sortArray(nums: list[int]) -> list[int]:
    if len(nums) > 1:
        mid = len(nums) // 2
        sub_array1 = nums[:mid]
        sub_array2 = nums[mid:]

        sortArray(sub_array1)
        sortArray(sub_array2)

        i = j = k = 0

        while i < len(sub_array1) and j < len(sub_array2):
            if sub_array1[i] < sub_array2[j]:
                nums[k] = sub_array1[i]
                i += 1
            else:
                nums[k] = sub_array2[j]
                j += 1
            k += 1
        while i < len(sub_array1):
            nums[k] = sub_array1[i]
            i += 1
            k += 1
        while j < len(sub_array2):
            nums[k] = sub_array2[j]
            j += 1
            k += 1
    return nums


def sortArrayTroll(nums: list[int]) -> list[int]:
    return sorted(nums)