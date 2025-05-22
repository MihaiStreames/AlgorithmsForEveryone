def generate_subsets(arr):
    temp = []
    result = set()

    def helper(idx):
        if idx == len(arr):
            # if 0 < len(temp) < len(arr):  removes empty and the initial arr
            result.add(tuple(temp))
            return
        else:
            # Include the current element
            temp.append(arr[idx])
            helper(idx + 1)

            # Exclude the current element (backtrack)
            temp.pop()
            helper(idx + 1)

    helper(0)
    return [list(subset) for subset in result]

arr = [1, 2, 3, 4, 5, 6]
subsets = generate_subsets(arr)
print(len(subsets))