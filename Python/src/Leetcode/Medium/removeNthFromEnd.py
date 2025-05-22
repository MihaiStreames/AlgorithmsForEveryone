def removeNthFromEnd(head: ListNode, n: int) -> ListNode:
    f = s = head

    for i in range(n):
        f = f.next

    if not f:
        return head.next

    while f.next:
        f = f.next
        s = s.next

    s.next = s.next.next
    return head