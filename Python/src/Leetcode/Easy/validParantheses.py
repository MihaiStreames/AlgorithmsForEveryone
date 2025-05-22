def isValid(s: str) -> bool:
    stack = []

    for i in s:
        if i == '(' or i == '{' or i == '[':
            stack.append(i)
        else:
            if len(stack) == 0:
                return False
            if i == ')' and stack.pop() != '(':
                return False
            if i == '}' and stack.pop() != '{':
                return False
            if i == ']' and stack.pop() != '[':
                return False

    if len(stack) == 0:
        return True
    return False