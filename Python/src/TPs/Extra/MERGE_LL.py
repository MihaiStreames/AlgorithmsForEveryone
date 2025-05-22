class Node:
    def __init__(self, key, next=None):
        self.key = key
        self.next = next

def mergesort_ll(head: Node) -> Node:
    if head is None or head.next is None:
        return head

    middle = get_middle(head)
    next_to_middle = middle.next

    # Split the list into two halves
    middle.next = None

    left = mergesort_ll(head)
    right = mergesort_ll(next_to_middle)

    sorted_list = merge(left, right)
    return sorted_list

def get_middle(head: Node) -> Node:
    if head is None:
        return head

    slow = head
    fast = head.next

    while fast is not None:
        fast = fast.next
        if fast is not None:
            slow = slow.next
            fast = fast.next

    return slow

def merge(left: Node, right: Node) -> Node:
    if left is None:
        return right
    if right is None:
        return left

    if left.key < right.key:
        result = left
        result.next = merge(left.next, right)
    elif left.key > right.key:
        result = right
        result.next = merge(left, right.next)
    else:
        # Skip the duplicate
        result = left
        result.next = merge(left.next, right.next)

    return result

def print_list(node: Node):
    while node is not None:
        print(node.key, end=" ")
        node = node.next
    print()

# Example usage
head = Node(6, Node(3, Node(1, Node(5, Node(4, Node(1, Node(2, Node(3, Node(3, Node(8, Node(4, Node(2, Node(1, Node(
    5))))))))))))))
sorted_head = mergesort_ll(head)
print_list(sorted_head)