def get_change(coins: list, value: int) -> list:
    # dynamic programming table that stores the minimum number of coins needed to make change for each value
    dp = [0] + [float('inf')] * value

    for i in range(1, value + 1):
        for coin in coins:
            if i >= coin:
                dp[i] = min(dp[i], dp[i - coin] + 1)

    # reconstruct the coins used to make change
    res = [0] * len(coins)
    target = value

    while target > 0:
        for idx, coin in enumerate(coins):
            if target >= coin and dp[target] == dp[target - coin] + 1:
                res[idx] += 1
                target -= coin
                break

    return res

print(get_change([1, 3, 4], 6))