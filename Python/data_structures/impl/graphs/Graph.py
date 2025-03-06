from DataStructs import EdgeUnorderedLinkedList


class Vertex:
    """
    Represents a vertex in a graph.
    """

    def __init__(self, vertex_idx):
        """
        Initialize a vertex with a given index and an empty list of edges.
        :param vertex_idx: The index of the vertex.
        """
        self._idx = vertex_idx
        self._edges = EdgeUnorderedLinkedList()

    def __str__(self):
        """
        Return a string representation of the vertex and its edges.
        :return: A string representation of the vertex and its edges.
        """
        res = ', '.join(f"(to: {edge.dest}, weight: {edge.weight})" for edge in self.edges)
        return f"Vertex {self.idx} -> [{res}]"

    def __repr__(self):
        """
        Return a string representation of the vertex for debugging purposes.
        :return: A string representation of the vertex for debugging purposes.
        """
        return self.__str__()

    @property
    def idx(self) -> int:
        """
        Get the index of the vertex.
        :return: The index of the vertex.
        """
        return self._idx

    @property
    def edges(self):
        """
        Get the list of edges of the vertex.
        :return: The list of edges of the vertex.
        """
        return self._edges

    @property
    def outEdges(self):
        """
        Get the list of outgoing edges of the vertex.
        :return: The list of outgoing edges of the vertex.
        """
        return self.edges

    def addEdge(self, dest, weight):
        """
        Add an edge to the vertex.
        :param dest: The destination vertex of the edge.
        :param weight: The weight of the edge.
        """
        self.edges.insert(dest, weight)


class StaticGraph:
    """
    Uses an adjacency matrix to represent a graph.
    """

    def __init__(self, n: int):
        """
        Initialize a static graph with n vertices.
        :param n: The number of vertices in the graph.
        """
        self.V = n
        self.adj = [[0] * n for _ in range(n)]

    @property
    def n(self) -> int:
        """
        Get the number of vertices in the graph.
        :return: The number of vertices in the graph.
        """
        return len(self.adj)

    def link(self, i: int, j: int, weight: int = 1):
        """
        Add an edge between vertex i and vertex j with a given weight.
        :param i: The starting vertex of the edge.
        :param j: The ending vertex of the edge.
        :param weight: The weight of the edge. Default is 1.
        """
        self.adj[i][j] = weight

    def unlink(self, i: int, j: int):
        """
        Remove the edge between vertex i and vertex j.
        :param i: The starting vertex of the edge.
        :param j: The ending vertex of the edge.
        """
        self.adj[i][j] = 0

    def hasEdge(self, i: int, j: int) -> bool:
        """
        Check if there is an edge between vertex i and vertex j.
        :param i: The starting vertex of the edge.
        :param j: The ending vertex of the edge.
        :return: True if there is an edge, False otherwise.
        """
        return self.adj[i][j] > 0

    def weight(self, i: int, j: int) -> int:
        """
        Get the weight of the edge between vertex i and vertex j.
        :param i: The starting vertex of the edge.
        :param j: The ending vertex of the edge.
        :return: The weight of the edge.
        """
        return self.adj[i][j]

    def toDynamic(self):
        """
        Convert the static graph to a dynamic graph representation.
        :return: A dynamic graph with the same edges and weights.
        """
        G_dyn = DynamicGraph(self.n)

        for i in range(self.n):
            for j in range(self.n):
                weight = self.weight(i, j)

                if weight > 0:
                    G_dyn.link(i, j, weight)

        return G_dyn


class DynamicGraph:
    """
    Uses successor lists to represent a graph.
    """

    def __init__(self, n: int):
        """
        Initialize a dynamic graph with n vertices.
        :param n: The number of vertices in the graph.
        """
        self.neighbors = [Vertex(i) for i in range(n)]

    @property
    def n(self) -> int:
        """
        Get the number of vertices in the graph.
        :return: The number of vertices in the graph.
        """
        return len(self.neighbors)

    def link(self, i: int, j: int, weight: int = 1):
        """
        Add an edge from vertex i to vertex j with a given weight.
        :param i: The starting vertex of the edge.
        :param j: The ending vertex of the edge.
        :param weight: The weight of the edge. Default is 1.
        """
        self.neighbors[i].addEdge(j, weight)

    def unlink(self, i: int, j: int):
        """
        Remove the edge from vertex i to vertex j.
        :param i: The starting vertex of the edge.
        :param j: The ending vertex of the edge.
        """
        self.neighbors[i].edges.remove(j)

    def hasEdge(self, i: int, j: int) -> bool:
        """
        Check if there is an edge from vertex i to vertex j.
        :param i: The starting vertex of the edge.
        :param j: The ending vertex of the edge.
        :return: True if there is an edge, False otherwise.
        """
        return self.neighbors[i].edges.search(j) is not None

    def weight(self, i: int, j: int) -> int:
        """
        Get the weight of the edge from vertex i to vertex j.
        :param i: The starting vertex of the edge.
        :param j: The ending vertex of the edge.
        :return: The weight of the edge if it exists, None otherwise.
        """
        return self.neighbors[i].edges.weight(j)

    def toStatic(self):
        """
        Convert the dynamic graph to a static graph representation.
        :return: A static graph with the same edges and weights.
        """
        G_stat = StaticGraph(self.n)

        for i in range(self.n):
            for e in self.neighbors[i].outEdges:
                G_stat.link(i, e.dest, e.weight)

        return G_stat
