from typing import Optional, Iterable, List

from ...structs.graphs import Graph


class DepthFirstSearch:
    """Performs a depth-first search to find paths in a graph.

    Time complexity is O(V + E) where V is vertices and E is edges.
    """

    def __init__(self, graph: Graph, source: int):
        """Initializes and runs the DFS from a source vertex.

        Args:
            graph: The graph to search.
            source: The source vertex (integer).
        """
        self._graph = graph
        self._source = source
        self._marked: List[bool] = [False] * graph.V()
        self._edge_to: List[Optional[int]] = [None] * graph.V()

        graph._validate_vertex(source)
        self._dfs(source)

    def _dfs(self, v: int):
        self._marked[v] = True
        for w in self._graph.adj(v):
            if not self._marked[w]:
                self._edge_to[w] = v
                self._dfs(w)

    def has_path_to(self, v: int) -> bool:
        """Checks if a path exists from the source to vertex v."""
        self._graph._validate_vertex(v)
        return self._marked[v]

    def path_to(self, v: int) -> Optional[Iterable[int]]:
        """Returns a path from the source to vertex v, or None."""
        if not self.has_path_to(v):
            return None

        path: List[int] = []
        x: Optional[int] = v
        while x is not None:
            path.append(x)
            x = self._edge_to[x]

        return reversed(path)
