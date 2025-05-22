from Blueprints.Data_Structures import Stack

def mirror(sequence: str):
    stack = Stack()
    check = False

    for char in sequence:
        if char == '*':
            check = True
            continue
        if not check:
            stack.push(char)
        else:
            if stack.is_empty() or stack.pop() != char:
                return False

    return stack.is_empty()

def mirror_input():
    char = input("Enter char: ")
    stack = Stack()

    while char != '*':
        stack.push(char)
        char = input("Enter char: ")

    check = True
    char = input("Enter char: ")

    while char != '#' and check:
        if stack.is_empty() or stack.pop() != char:
            check = False
            break
        char = input("Enter char: ")

    if check and stack.is_empty():
        return False
    else:
        return True