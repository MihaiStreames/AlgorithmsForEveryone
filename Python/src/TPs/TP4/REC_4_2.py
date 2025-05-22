def even_odds(n):
    if n > 0:
        num = int(input())

        if num % 2:             # Odd
            even_odds(n - 1)    # Print in reverse
            print(num, end=' ')
        else:                   # Even
            print(num, end=' ') # Print in order
            even_odds(n - 1)