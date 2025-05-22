from Blueprints.Data_Structures import Stack

def reverse_stack(stack: Stack):
    n = len(stack)
    temp = Stack()

    for i in range(n, 1, -1):
        item = stack.pop()

        for j in range(1, i):
            temp.push(stack.pop())
        stack.push(item)

        for j in range(1, i):
            stack.push(temp.pop())