from typing import List, Tuple


class Graph:
    """An undirected graph represented using adjacency lists.

    Space complexity is O(V + E), where V is vertices and E is edges.
    """

    def __init__(self, vertices: int):
        """Initializes a graph with a set number of vertices.

        Args:
            vertices: The number of vertices, indexed from 0 to V-1.

        Raises:
            ValueError: If vertices is negative.
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

    def adj(self, vertex: int) -> Tuple[int, ...]:
        """Returns vertices adjacent to the given vertex as an immutable tuple."""
        self._validate_vertex(vertex)
        return tuple(self._adjacency_list[vertex])

    def add_edge(self, v: int, w: int):
        """Adds an undirected edge between two vertices."""
        self._validate_vertex(v)
        self._validate_vertex(w)

        # Check if edge already exists to avoid duplicates
        if w not in self._adjacency_list[v]:
            self._adjacency_list[v].append(w)

            # For undirected graph, add edge in both directions (unless self-loop)
            if v != w:
                self._adjacency_list[w].append(v)
            self._edges += 1

    def remove_edge(self, v: int, w: int):
        """Removes an undirected edge between two vertices if it exists."""
        self._validate_vertex(v)
        self._validate_vertex(w)

        try:
            # Remove edge if it exists
            self._adjacency_list[v].remove(w)

            # For undirected graph, remove edge from both directions (unless self-loop)
            if v != w:
                self._adjacency_list[w].remove(v)
            self._edges -= 1
        except ValueError:
            pass  # Edge did not exist

    def has_edge(self, v: int, w: int) -> bool:
        """Checks if an edge exists between two vertices."""
        self._validate_vertex(v)
        self._validate_vertex(w)
        return w in self._adjacency_list[v]

    def _validate_vertex(self, vertex: int):
        """Raises IndexError if the vertex is out of bounds."""
        if not (0 <= vertex < self._vertices):
            raise IndexError(
                f"Vertex {vertex} is not between 0 and {self._vertices - 1}"
            )
