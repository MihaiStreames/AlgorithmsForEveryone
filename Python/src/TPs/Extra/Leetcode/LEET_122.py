class Solution:
    def maxProfit(self, prices):
        if not prices:
            return 0

        n = len(prices)
        dp = [0] * n
        # max_diff tracks the maximum of dp[j - 1] - prices[j] seen so far; initialized with the first day
        max_diff = -prices[0]

        for i in range(1, n):
            # Calculate maximum profit for day i by either not selling (dp[i - 1]) or selling on day i
            # prices[i] + max_diff effectively means considering selling on day i where max_diff incorporates
            # the maximum profitable buy price from previous days
            dp[i] = max(dp[i - 1], prices[i] + max_diff)
            # Update max_diff to include the current day's buy potential
            # dp[i - 1] - prices[i] calculates the potential profit if buying on day i and selling later
            max_diff = max(max_diff, dp[i - 1] - prices[i])

        # The last element in dp array will have the maximum profit achievable with the given stock prices
        return dp[n - 1]