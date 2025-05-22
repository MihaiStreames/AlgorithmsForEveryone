# Definition for singly-linked list.
# class ListNode:
#     def __init__(self, val=0, next=None):
#         self.val = val
#         self.next = next

# recursive solution

class Solution:
    def isPalindrome(self, head: Optional[ListNode]) -> bool:
        self.front = head

        def helper(curr):
            if curr is not None:
                if not helper(curr.next):
                    return False
                if self.front.val != curr.val:
                    return False
                self.front = self.front.next
            return True

        return helper(head)