from Blueprints.Data_Structures import Deque


def BFSStatic(graph: StaticGraph, start: int) -> list:
    """
    Perform a breadth-first search on a static graph (represented by an adjacency matrix).
    :param graph: The graph to search (static).
    :param start: The index of the starting vertex.
    :return: The list of vertices in the order they were visited.
    """
    visited = [False] * graph.n
    queue = Deque()
    queue.add_rear(start)
    result = []

    while not queue.isEmpty():
        vertex = queue.remove_front()

        if not visited[vertex]:
            visited[vertex] = True
            result.append(vertex)

            for i in range(graph.n):
                if graph.adj[vertex][i] > 0 and not visited[i]:
                    queue.add_rear(i)

    return result


def bfs_dynamic(graph: "DynamicGraph", start: int) -> list:
    """
    Perform a breadth-first search on a dynamic graph (represented by a list of vertices).
    :param graph: The graph to search (dynamic).
    :param start: The index of the starting vertex.
    :return: The list of vertices in the order they were visited.
    """
    visited = [False] * graph.n
    queue = Deque()
    queue.add_rear(start)
    result = []

    while not queue.isEmpty():
        vertex_idx = queue.remove_front()

        if not visited[vertex_idx]:
            vertex = graph.neighbors[vertex_idx]
            visited[vertex_idx] = True
            result.append(vertex.idx)

            for edge in vertex.outEdges:
                if not visited[edge.dest]:
                    queue.add_rear(edge.dest)

    return result
