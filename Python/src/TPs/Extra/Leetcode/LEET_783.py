# Definition for a binary tree node.
# class TreeNode:
#     def __init__(self, val=0, left=None, right=None):
#         self.val = val
#         self.left = left
#         self.right = right

class Solution:
    def minDiffInBST(self, root: Optional[TreeNode]) -> int:
        def inorder(node: TreeNode) -> List[int]:
            if node is None:
                return []
            return inorder(node.left) + [node.val] + inorder(node.right)

        values = inorder(root)
        return min(values[i] - values[i - 1] for i in range(1, len(values)))