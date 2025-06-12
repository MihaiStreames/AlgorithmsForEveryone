from typing import Optional, Iterable, List

from ...structs.graphs import Graph
from ...structs.rls import Queue


class BreadthFirstSearch:
    """Performs a breadth-first search to find shortest paths in a graph.

    Time complexity is O(V + E) where V is vertices and E is edges.
    """

    def __init__(self, graph: Graph, source: int):
        """Initializes and runs the BFS from a source vertex.

        Args:
            graph: The graph to search.
            source: The source vertex (integer).
        """
        self._graph = graph
        self._source = source
        self._marked: List[bool] = [False] * graph.V()
        self._edge_to: List[Optional[int]] = [None] * graph.V()
        self._dist_to: List[int] = [float('inf')] * graph.V()

        graph._validate_vertex(source)
        self._bfs(source)

    def _bfs(self, s: int):
        queue = Queue[int]()

        self._marked[s] = True
        self._dist_to[s] = 0
        queue.enqueue(s)

        while not queue.is_empty():
            v = queue.dequeue()
            for w in self._graph.adj(v):
                if not self._marked[w]:
                    self._edge_to[w] = v
                    self._dist_to[w] = self._dist_to[v] + 1
                    self._marked[w] = True
                    queue.enqueue(w)

    def has_path_to(self, v: int) -> bool:
        """Checks if a path exists from the source to vertex v."""
        self._graph._validate_vertex(v)
        return self._marked[v]

    def dist_to(self, v: int) -> int:
        """Returns the shortest distance (edge count) from source to v."""
        self._graph._validate_vertex(v)
        return self._dist_to[v]

    def path_to(self, v: int) -> Optional[Iterable[int]]:
        """Returns the shortest path from the source to vertex v, or None."""
        if not self.has_path_to(v):
            return None

        path: List[int] = []
        x: Optional[int] = v
        while x is not None:
            path.append(x)
            x = self._edge_to[x]

        return reversed(path)
