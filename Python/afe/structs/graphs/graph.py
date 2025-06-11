from typing import List, Iterable


class Graph:
    """
    Implementation of an undirected graph using an adjacency list.
    Vertices are represented by integers from 0 to V-1.

    - Add edge: O(1)
    - Get adjacent vertices: O(degree(v))
    - Space: O(V + E)
    """

    def __init__(self, vertices: int):
        """
        Constructs an empty graph.
        Args:
            vertices: The number of vertices in the graph.
        """
        if vertices < 0:
            raise ValueError("Number of vertices cannot be negative")
        self._vertices = vertices
        self._edges = 0
        self._adjacency_list: List[List[int]] = [[] for _ in range(vertices)]

    def V(self) -> int:
        """Returns the number of vertices."""
        return self._vertices

    def E(self) -> int:
        """Returns the number of edges."""
        return self._edges

    def _validate_vertex(self, v: int):
        """Raises ValueError if vertex is invalid."""
        if not (0 <= v < self._vertices):
            raise ValueError(f"Vertex {v} is not between 0 and {self._vertices - 1}")

    def add_edge(self, v: int, w: int):
        """Adds an undirected edge between two vertices."""
        self._validate_vertex(v)
        self._validate_vertex(w)

        # Avoid parallel edges
        if w not in self._adjacency_list[v]:
            self._adjacency_list[v].append(w)
            if v != w:  # Avoid adding self-loop twice
                self._adjacency_list[w].append(v)
            self._edges += 1

    def adj(self, v: int) -> Iterable[int]:
        """Returns the vertices adjacent to a given vertex."""
        self._validate_vertex(v)
        return self._adjacency_list[v]

    def has_edge(self, v: int, w: int) -> bool:
        """Checks if there is an edge between two vertices."""
        self._validate_vertex(v)
        self._validate_vertex(w)
        return w in self._adjacency_list[v]
