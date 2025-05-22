from Blueprints.Data_Structures.Stack import Stack

def infix_to_postfix(str: str) -> str:
    """
    1. Create an empty stack called op_stack for keeping operators. Create an empty list for
    output.
    2. Convert the input infix string to a list by using the string method split.
    3. Scan the token list from left to right.
    • If the token is an operand, append it to the end of the output list.
    • If the token is a left parenthesis, push it on the op_stack.
    • If the token is a right parenthesis, pop the op_stack until the corresponding left parenthesis is removed. Append each operator to the end of the output list.
    • If the token is an operator, *, /, +, or −, push it on the op_stack.
    However, first remove any operators already on the op_stack that have higher or equal precedence and append them to the output list.
    When the input expression has been completely processed, check the op_stack. Any operators still on the stack can be removed and appended to the end of the output list.
    """
    weight = {'+': 1, '-': 1, '*': 2, '/': 2}
    op_stack = Stack()
    output = []
    chars = str.split()

    for c in chars:
        if c.isalpha() or c.isdigit():
            output.append(c)
        elif c == '(':
            op_stack.push(c)
        elif c == ')':
            while not op_stack.is_empty() and op_stack.peek() != '(':
                output.append(op_stack.pop())
            op_stack.pop()
        else:
            while not op_stack.is_empty() and weight.get(op_stack.peek(), 0) >= weight[c]:
                output.append(op_stack.pop())
            op_stack.push(c)

    while not op_stack.is_empty():
        output.append(op_stack.pop())

    return ' '.join(output)

test_1 = "a + b * ( c - d )"
test_2 = "( ( a + b ) * c ) - d"
test_3 = "( a + b * c - d / e ) * ( f - g ) + h / ( i + j * k - l )"

print(infix_to_postfix(test_1))
print(infix_to_postfix(test_2))
print(infix_to_postfix(test_3))

def postfix_eval(str: str) -> (int, float):
    """
    1. Create an empty stack called operand_stack.
    2. Convert the string to a list by using the string method split.
    3. Scan the token list from left to right.
    • If the token is an operand, convert it from a string to an integer and push the value onto the operand_stack.
    • If the token is an operator, *, /, +, or −, it will need two operands. Pop the operand_stack twice.
    The first pop is the second operand, and the second pop is the first operand. Perform the arithmetic operation. Push the result back on the operand_stack.
    4. When the input expression has been completely processed, the result is on the stack. Pop the operand_stack and return the value.
    """
    operand_stack = Stack()
    chars = str.split()

    for c in chars:
        if c.isalpha():
            raise NotImplemented
        if c.isdigit():
            operand_stack.push(int(c))
        else:
            b, a = operand_stack.pop(), operand_stack.pop()
            operand_stack.push(math(c, a, b))
    return operand_stack.pop()

def math(op: str, a: int, b: int) -> (int, float):
    if op == "*":
        return a * b
    elif op == "/":
        return a / b
    elif op == "+":
        return a + b
    else:
        return a - b