class Solution:
    def allPathsSourceTarget(self, graph: List[List[int]]) -> List[List[int]]:
        def dfs(node, path):
            path.append(node)
            # If the current node is the last node in the graph
            if node == len(graph) - 1:
                res.append(path.copy())
            else:
                # Recur for all the nodes that can be visited from the current node
                for neighbor in graph[node]:
                    dfs(neighbor, path)
            path.pop()

        res = []
        dfs(0, [])
        return res