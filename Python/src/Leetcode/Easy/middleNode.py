def middleNode(head: ListNode) -> ListNode:
    if not head:
        return None
    if not head.next:
        return head

    slow = head
    fast = head

    while fast and fast.next:
        slow = slow.next
        fast = fast.next.next

    return slow