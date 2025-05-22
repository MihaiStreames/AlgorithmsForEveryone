def sol_eq_lin_rec(a, y, b, n, sol):
    # Base case: if solution's length equals n, check if the dot product of a and sol equals y
    if len(sol) == n:
        if sum(a[i] * sol[i] for i in range(n)) == y:
            print(sol)  # Print solution if it solves the equation
        return

    # Recursive step: try adding each possible value of x (from 0 to b) to the solution
    for x in range(b + 1):
        sol_eq_lin_rec(a, y, b, n, sol + [x])  # add each x from 0 to b

#sol_eq_lin_rec([1, 2, 3, 4], 15, 3, 4, [])

def sol_eq_lin_iter(a, y, b, n):
    sol = [0] * n  # Initialize solution with all coefficients set to 0

    while True:
        # Check if the current combination of coefficients solves the equation
        if sum(a[i] * sol[i] for i in range(n)) == y:
            print(sol)

        # Increment coefficients to generate the next combination
        for i in range(n):
            sol[i] += 1  # Increment coefficient at position i

            if sol[i] > b:  # If coefficient exceeds the bound, reset it
                sol[i] = 0

                if i == n - 1:  # If all coefficients have been reset, terminate
                    return
            else:
                break  # New combination

#sol_eq_lin_iter([1, 2, 3, 4], 30, 3, 4)