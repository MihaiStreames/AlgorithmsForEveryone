from DataStructs import DynamicGraph, StaticGraph


def Djikstra(graph: DynamicGraph or StaticGraph, src):
    """
    Implement Dijkstra's algorithm to find the shortest paths from a source vertex to all other vertices in the graph.
    :param graph: The input graph, either DynamicGraph or StaticGraph.
    :param src: The source vertex.
    """
    V = graph.n
    dist = [float('inf')] * V
    dist[src] = 0
    spt_set = [False] * V

    def printSolution(dist_list):
        """
        Print the solution of the shortest paths.
        :param dist_list: The list of distances from the source vertex to each vertex.
        """
        print("Vertex \t Distance from Source")

        for i in range(V):
            print(f"{i} \t\t {dist_list[i]}")

    def minDist(dist_list, shortest_set):
        """
        Find the vertex with the minimum distance value from the set of vertices not yet included in the shortest path tree.
        :param dist_list: The list of distances from the source vertex to each vertex.
        :param shortest_set: The set of vertices included in the shortest path tree.
        :return: The index of the vertex with the minimum distance value.
        """
        min_val = float('inf')
        min_idx = -1

        for v in range(V):
            if dist_list[v] < min_val and not shortest_set[v]:
                min_val = dist_list[v]
                min_idx = v
        return min_idx

    # Main loop of Dijkstra's algorithm
    for _ in range(V - 1):
        u = minDist(dist, spt_set)

        if u == -1:  # No more vertices are reachable
            break

        spt_set[u] = True

        # Get edges (neighbors) based on graph type
        if isinstance(graph, DynamicGraph):
            edges = graph.neighbors[u].outEdges
        elif isinstance(graph, StaticGraph):
            edges = [(v, graph.weight(u, v)) for v in range(V) if graph.hasEdge(u, v)]
        else:
            raise ValueError("Graph type not supported")

        for edge in edges:
            if isinstance(graph, DynamicGraph):
                v, weight = edge.dest, edge.weight
            else:
                v, weight = edge

            if not spt_set[v] and dist[v] > dist[u] + weight:
                dist[v] = dist[u] + weight

    printSolution(dist)
