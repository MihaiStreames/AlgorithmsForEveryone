from Blueprints.Data_Structures import Stack

def max_decreasing_subsequence_sum():
    stack = Stack()
    number = int(input("Enter number (or '-1' to end input): "))
    max_sum = 0

    while number != -1:
        while not stack.is_empty() and stack.peek() < number:
            stack.pop()

        stack.push(number)
        max_sum = max(max_sum, sum(stack.items))

    return max_sum, stack