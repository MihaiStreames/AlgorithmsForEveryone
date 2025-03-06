from Blueprints.Data_Structures import DynamicGraph, StaticGraph


def dijkstra(graph, src):
    """
    Implement Dijkstra's algorithm to find the shortest paths from a source vertex to all other vertices in the graph.
    :param graph: The input graph, either DynamicGraph or StaticGraph.
    :param src: The source vertex.
    """
    V = graph.n
    dist = [float('inf')] * V
    dist[src] = 0
    spt_set = [False] * V

    def print_solution(dist):
        """
        Print the solution of the shortest paths.
        :param dist: The list of distances from the source vertex to each vertex.
        """
        print("Vertex \t Distance from Source")

        for i in range(V):
            print(f"{i} \t\t {dist[i]}")

    def min_distance(dist, spt_set):
        """
        Find the vertex with the minimum distance value from the set of vertices not yet included in the shortest path tree.
        :param dist: The list of distances from the source vertex to each vertex.
        :param spt_set: The set of vertices included in the shortest path tree.
        :return: The index of the vertex with the minimum distance value.
        """
        min_val = float('inf')
        min_idx = -1

        for v in range(V):
            if dist[v] < min_val and not spt_set[v]:
                min_val = dist[v]
                min_idx = v
        return min_idx

    # Main loop of Dijkstra's algorithm
    for _ in range(V - 1):
        u = min_distance(dist, spt_set)

        if u == -1:  # No more vertices are reachable
            break

        spt_set[u] = True

        # Get edges (neighbors) based on graph type
        if isinstance(graph, DynamicGraph):
            edges = graph.neighbors[u].out_edges
        elif isinstance(graph, StaticGraph):
            edges = [(v, graph.weight(u, v)) for v in range(V) if graph.has_edge(u, v)]
        else:
            raise ValueError("Graph type not supported")

        for edge in edges:
            if isinstance(graph, DynamicGraph):
                v, weight = edge.dest, edge.weight
            else:
                v, weight = edge

            if not spt_set[v] and dist[v] > dist[u] + weight:
                dist[v] = dist[u] + weight

    print_solution(dist)
