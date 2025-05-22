# Definition for singly-linked list.
# class ListNode:
#     def __init__(self, val=0, next=None):
#         self.val = val
#         self.next = next

class Solution:
    def reverseList(self, head: Optional[ListNode]) -> Optional[ListNode]:
        if not head:
            return None

        res = ListNode(0)

        def helper(curr):
            if curr is None:
                return res.next

            next_node = helper(curr.next)

            if next_node is None:
                res.next = curr

            if curr.next is not None:
                curr.next.next = curr

            curr.next = None
            return curr

        helper(head)
        return res.next