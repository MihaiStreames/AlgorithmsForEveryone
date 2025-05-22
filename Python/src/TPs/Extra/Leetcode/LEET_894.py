# Definition for a binary tree node.
# class TreeNode:
#     def __init__(self, val=0, left=None, right=None):
#         self.val = val
#         self.left = left
#         self.right = right

class Solution:
    def allPossibleFBT(self, n: int) -> List[Optional[TreeNode]]:
        def count(n, dp={}):
            if n in dp:
                return dp[n]
            # Base case: the only full binary tree with 1 node is a single root node with no children
            if n == 1:
                return [TreeNode(0)]
            # A full binary tree cannot have an even number of nodes !!!
            if n % 2 == 0:
                return []

            res = []
            # Iterate through all odd numbers less than n to split the nodes
            # between the left and right subtrees
            for x in range(1, n, 2):
                left_trees = count(x, dp)  # Recursively find all possible left subtrees with x nodes
                right_trees = count(n - 1 - x, dp)  # Recursively find all possible right subtrees with n - 1 - x nodes

                # Combine each pair of left and right trees to form a full binary tree
                # and append to the result list
                for left in left_trees:
                    for right in right_trees:
                        root = TreeNode(0)  # New root for each combination
                        root.left = left
                        root.right = right
                        res.append(root)

            # Memoize the result for n nodes
            dp[n] = res
            return res

        return count(n, {})