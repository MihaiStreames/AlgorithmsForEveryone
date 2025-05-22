# Definition for a binary tree node.
# class TreeNode:
#     def __init__(self, val=0, left=None, right=None):
#         self.val = val
#         self.left = left
#         self.right = right

class Solution:
    def convertBST(self, root: Optional[TreeNode]) -> Optional[TreeNode]:
        # do an inorder traversal to get every value bigger than the smallest, replace it with the sum of all values bigger than it
        # repeat for every node

        def inorder(node, total):
            if node is None:
                return total

            total = inorder(node.right, total)
            total += node.val
            node.val = total
            total = inorder(node.left, total)

            return total

        inorder(root, 0)
        return root