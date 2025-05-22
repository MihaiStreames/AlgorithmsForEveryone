import heapq
from typing import List

class Solution:
    def pickGifts(self, gifts: List[int], k: int) -> int:
        # idea is to convert the gifts array to a max heap, pop k times, square root the values popped
        # add the new values to a new heap
        # repeat until done

        heap = [-x for x in gifts]
        heapq.heapify(heap)

        #print(heap)

        for i in range(k):
            val = heapq.heappop(heap)
            val = -val
            val = int(val ** 0.5)
            heapq.heappush(heap, -val)

        return -sum(heap)