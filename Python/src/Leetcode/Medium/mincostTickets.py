def mincostTickets(days: list[int], costs: list[int]) -> int:
    day_prices = [0] * (days[-1] + 1)

    for i in range(1, len(day_prices)):
        if i not in days:
            day_prices[i] = day_prices[i - 1]
        else:
            day_prices[i] = min(
                day_prices[max(0, i - 1)] + costs[0],
                day_prices[max(0, i - 7)] + costs[1],
                day_prices[max(0, i - 30)] + costs[2]
            )
    return day_prices[-1]