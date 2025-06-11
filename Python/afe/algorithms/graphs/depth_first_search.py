from typing import Optional, Iterable, List

from afe.structs.graphs.graph import Graph


class DepthFirstSearch:
    """
    Depth-First Search for finding paths in a graph.
    - Time Complexity: O(V + E)
    - Space Complexity: O(V)
    """

    def __init__(self, graph: Graph, source: int):
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
        """Returns a path from the source to vertex v."""
        if not self.has_path_to(v):
            return None

        path = []
        x = v
        while x is not None:
            path.append(x)
            x = self._edge_to[x]

        return reversed(path)
