from DataStructs import StaticGraph, DynamicGraph

def DFSStatic(graph: StaticGraph, start: int) -> list:
    """
    Perform a depth-first search on a static graph (represented by an adjacency matrix).
    :param graph: The graph to search (static).
    :param start: The index of the starting vertex.
    :return: The list of vertices in the order they were visited.
    """
    visited = [False] * graph.n
    result = []

    def dfs(vertex):
        visited[vertex] = True
        result.append(vertex)

        for i in range(graph.n):
            if graph.adj[vertex][i] > 0 and not visited[i]:
                dfs(i)

    dfs(start)
    return result


def DFSDynamic(graph: DynamicGraph, start: int) -> list:
    """
    Perform a depth-first search on a dynamic graph (represented by a list of vertices).
    :param graph: The graph to search (dynamic).
    :param start: The index of the starting vertex.
    :return: The list of vertices in the order they were visited.
    """
    visited = [False] * graph.n
    result = []

    def dfs(vertex_idx):
        vertex = graph.neighbors[vertex_idx]
        visited[vertex.idx] = True
        result.append(vertex.idx)

        for edge in vertex.outEdges:
            if not visited[edge.dest]:
                dfs(edge.dest)

    dfs(start)
    return result
