from DataStructs import DynamicGraph, StaticGraph, Queue


def Moore(graph: DynamicGraph or StaticGraph, start, end):
    """
    Implement the Moore's algorithm to find the shortest path in a graph from start to end vertex.
    :param graph: The input graph, either DynamicGraph or StaticGraph.
    :param start: The starting vertex.
    :param end: The ending vertex.
    :return: The shortest path from start to end as a list of vertices.
    """
    # Initialize index with infinity and parent with -1
    index = [float('inf')] * graph.n
    index[start] = 0
    parent = [-1] * graph.n
    shortest_path = []

    # Initialize the queue and enqueue the start vertex
    q = Queue()
    q.enq(start)

    # BFS to find shortest path
    while not q.isEmpty():
        u = q.deq()

        # Get neighbors based on graph type
        if isinstance(graph, DynamicGraph):
            neighbors = [edge.dest for edge in graph.neighbors[u].edges]
        elif isinstance(graph, StaticGraph):
            neighbors = [v for v in range(graph.n) if graph.hasEdge(u, v)]
        else:
            raise ValueError("Graph type not supported")

        # Update index and parent for each neighbor
        for i in neighbors:
            if index[i] == float('inf'):
                q.enq(i)
                index[i] = index[u] + 1
                parent[i] = u

    # Reconstruct shortest path if end is reachable
    if index[end] != float('inf'):
        k = index[end]
        shortest_path = [end]

        while k > 0:
            shortest_path.insert(0, parent[shortest_path[0]])
            k -= 1

    return shortest_path
