# Definition for singly-linked list.
# class ListNode:
#     def __init__(self, val=0, next=None):
#         self.val = val
#         self.next = next

import heapq
from typing import List, Optional

class Solution:
    def mergeKLists(self, lists: List[Optional[ListNode]]) -> Optional[ListNode]:
        # use a heap to get the smallest element from each list
        # pop and make res list
        # handle empty cases

        heap = []

        for i, l in enumerate(lists):
            if l:
                heapq.heappush(heap, (l.val, i))

        res = ListNode()
        head = res

        while heap:
            val, i = heapq.heappop(heap)
            res.next = ListNode(val)
            res = res.next

            if lists[i].next:
                lists[i] = lists[i].next
                heapq.heappush(heap, (lists[i].val, i))

        return head.next