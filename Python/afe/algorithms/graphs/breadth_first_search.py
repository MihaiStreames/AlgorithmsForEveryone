from collections import deque
from typing import Optional, Iterable, List

from afe.structs.graphs.graph import Graph


class BreadthFirstSearch:
    """
    Breadth-First Search for finding shortest paths in unweighted graphs.
    - Time Complexity: O(V + E)
    - Space Complexity: O(V)
    """

    def __init__(self, graph: Graph, source: int):
        self._graph = graph
        self._source = source
        self._marked: List[bool] = [False] * graph.V()
        self._edge_to: List[Optional[int]] = [None] * graph.V()
        self._dist_to: List[int] = [float('inf')] * graph.V()

        graph._validate_vertex(source)
        self._bfs(source)

    def _bfs(self, s: int):
        queue = deque([s])
        self._marked[s] = True
        self._dist_to[s] = 0

        while queue:
            v = queue.popleft()
            for w in self._graph.adj(v):
                if not self._marked[w]:
                    self._edge_to[w] = v
                    self._dist_to[w] = self._dist_to[v] + 1
                    self._marked[w] = True
                    queue.append(w)

    def has_path_to(self, v: int) -> bool:
        """Checks if a path exists from the source to vertex v."""
        self._graph._validate_vertex(v)
        return self._marked[v]

    def dist_to(self, v: int) -> int:
        """Returns the shortest distance from the source to vertex v."""
        self._graph._validate_vertex(v)
        return self._dist_to[v]

    def path_to(self, v: int) -> Optional[Iterable[int]]:
        """Returns the shortest path from the source to vertex v."""
        if not self.has_path_to(v):
            return None

        path = []
        x = v
        while x is not None:
            path.append(x)
            x = self._edge_to[x]

        return reversed(path)
