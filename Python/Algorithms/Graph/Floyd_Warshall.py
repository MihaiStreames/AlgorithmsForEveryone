def floyd_warshall(graph):
    """
    Implement the Floyd-Warshall algorithm to find the shortest paths between all pairs of vertices in a static graph.
    :param graph: The input static graph.
    :return: A tuple (D, P) where D is the distance matrix and P is the predecessor matrix.
    """
    # Initialize distance and predecessor matrices
    D = [[float('inf')] * graph.n for _ in range(graph.n)]
    P = [[None] * graph.n for _ in range(graph.n)]

    # Set initial distances based on the adjacency matrix
    for i in range(graph.n):
        for j in range(graph.n):
            if i == j:
                D[i][j] = 0
            elif graph.adj[i][j] != 0:
                D[i][j] = graph.adj[i][j]
                P[i][j] = i
            else:
                D[i][j] = float('inf')
                P[i][j] = None

    # Update distances using intermediate vertices
    for k in range(graph.n):
        for i in range(graph.n):
            for j in range(graph.n):
                if D[i][k] != float('inf') and D[k][j] != float('inf'):
                    new_distance = D[i][k] + D[k][j]
                    if new_distance < D[i][j]:
                        D[i][j] = new_distance
                        P[i][j] = P[k][j]

    # Check for negative-weight cycles
    for i in range(graph.n):
        if D[i][i] < 0:
            raise ValueError("Graph contains a negative-weight cycle")

    return D, P
