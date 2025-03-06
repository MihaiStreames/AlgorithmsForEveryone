def roy_warshall(graph):
    """
    Implement the Roy-Warshall algorithm to compute the transitive closure of a static graph.
    :param graph: The input static graph.
    :return: The transitive closure of the static graph.
    """
    # Initialize the matrix M with the same values as the graph's adjacency matrix
    M = [[int(e) for e in row] for row in graph.adj]

    # Set the diagonal elements to 1 (self-loops)
    for i in range(graph.n):
        M[i][i] = 1

    # Update the matrix M to include paths found via intermediate vertices
    for k in range(graph.n):
        for i in range(graph.n):
            if M[i][k]:
                for j in range(graph.n):
                    M[i][j] |= M[k][j]

    return M
