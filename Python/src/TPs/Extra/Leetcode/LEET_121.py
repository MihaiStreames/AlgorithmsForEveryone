class Solution:
    def maxProfit(self, prices: List[int]) -> int:
        dp = float('inf')
        profit = 0

        for price in prices:
            # Update the minimum price so far if a lower price is found
            if price < dp:
                dp = price

            # Calculate potential profit
            curr = price - dp

            # Modify if better profit is found
            if curr > profit:
                profit = curr

        return profit