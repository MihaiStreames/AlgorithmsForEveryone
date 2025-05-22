def max_subarray(arr):
    max = arr[0]
    curr_sum = arr[0]

    start = 0
    end = 0
    s = 0

    for i in range(1, len(arr)):
        if arr[i] > curr_sum + arr[i]:
            curr_sum = arr[i]
            s = i
        else:
            curr_sum += arr[i]

        if curr_sum > max:
            max = curr_sum
            start = s
            end = i

    return arr[start:end + 1], max

x = [1, 2, 4, -4, 10, -5, -6, -12]
subarray, max_sum = max_subarray(x)
print("The subarray with the maximum sum is:", subarray)
print("The maximum sum of the subarray is:", max_sum)