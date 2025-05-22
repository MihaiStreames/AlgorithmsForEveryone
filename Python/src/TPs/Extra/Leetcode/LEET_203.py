# Definition for singly-linked list.
# class ListNode:
#     def __init__(self, val=0, next=None):
#         self.val = val
#         self.next = next

class Solution:
    def removeElements(self, head: Optional[ListNode], val: int) -> Optional[ListNode]:
        if head is None:
            return None

        self.prev = None
        self.curr = head

        def helper(curr):
            if curr is None:
                return None

            curr.next = helper(curr.next)

            if curr.val == val:
                return curr.next
            else:
                return curr

        head = helper(self.curr)
        return head