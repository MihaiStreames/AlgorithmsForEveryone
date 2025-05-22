import heapq
from typing import List

class Solution:
    def findRelativeRanks(self, score: List[int]) -> List[str]:
        # heapify the scores, pop the top 3 scores and assign medals, then assign the rest of the scores

        medals = ["Gold Medal", "Silver Medal", "Bronze Medal"]
        res = [0] * len(score)

        heap = [(-s, i) for i, s in enumerate(score)]
        heapq.heapify(heap)

        for i in range(3):
            if heap:
                _, idx = heapq.heappop(heap)
                res[idx] = medals[i]

        for i in range(3, len(score)):
            _, idx = heapq.heappop(heap)
            res[idx] = str(i + 1)

        return res