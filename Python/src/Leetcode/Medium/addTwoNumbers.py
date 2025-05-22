class ListNode:
    def __init__(self, val=0, next=None):
        self.val = val
        self.next = next

    def __str__(self):
        return str(self.val) + " -> " + str(self.next)

def addTwoNumbers(l1: "ListNode", l2: "ListNode") -> "ListNode":
    dummy = ListNode(0)
    current = dummy
    carry = 0

    # The idea is to keep track of the carry and add the values of the nodes at each step
    # If the sum is greater than 10, we carry the 1 to the next step
    # We keep going through the lists and carry until all of them are None or 0

    while l1 or l2 or carry:
        v1 = l1.val if l1 else 0
        v2 = l2.val if l2 else 0

        # Calculate the sum of current digits and carry
        # Update carry for the next iteration
        # Get the digit value by taking the remainder of the sum
        # Attach a new node to the current node with the calculated value

        val = v1 + v2 + carry
        carry = val // 10
        val = val % 10
        current.next = ListNode(val)

        # Update the current pointer, l1, and l2 to their next nodes

        current = current.next
        l1 = l1.next if l1 else None
        l2 = l2.next if l2 else None

    return dummy.next