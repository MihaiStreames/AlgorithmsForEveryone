def isPalindrome(x):
    numbers = []

    for number in str(x):
        numbers.append(number)

    rev_numbers = numbers[::-1]

    if numbers == rev_numbers:
        return True
    return False