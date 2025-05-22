from Blueprints.Data_Structures import Stack

def is_open(char: str, tokens: list) -> bool:
    for array in tokens:
        if array[0] == char:
            return True
    return False

def matches(open: str, close: str, tokens: list) -> bool:
    for array in tokens:
        if array[0] == open:
            return array[1] == close
    return False

def is_balanced() -> bool:
    tokens = [['(', ')'], ['[', ']'], ['{', '}']]
    stack = Stack()

    while True:
        c = input("Enter char (or '#' to end input): ")
        if c == '#':
            break

        if is_open(c, tokens):
            stack.push(c)
        else:
            if stack.is_empty() or not matches(stack.pop(), c, tokens):
                return False

    return stack.is_empty()