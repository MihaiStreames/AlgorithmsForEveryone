def climbStairs(n: int) -> int:
    if n <= 1:
        return 1

    # We keep track of the solutions one step and two steps ago (with one_step and two_step)
    # The idea is to keep track of the number of ways to reach the last two steps
    # At each step, the number of ways to reach the current step is the sum of the ways
    # to reach the last two steps

    one_step, two_step = 1, 1

    for _ in range(2, n + 1):
        curr = one_step + two_step
        two_step, one_step = one_step, curr

    return one_step