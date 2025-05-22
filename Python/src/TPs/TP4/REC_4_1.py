def reverse(n):
    if n > 0:
        char = input()
        reverse(n - 1)
        print(char, end=' ')