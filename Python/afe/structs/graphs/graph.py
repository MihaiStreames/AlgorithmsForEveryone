# -*- coding: utf-8 -*-

from typing import List, Tuple
from afe.structs.rls import Queue, Stack
from afe.exceptions import BaseGraphException
import uuid


class Graph:

    """
    An undirected graph represented using adjacency lists. \n
    Space complexity is O(V + E), where V is vertices and E is edges.

    """

    def __init__(self, vertices: int) -> None:

        """
        Initializes a graph with a set number of vertices.

        Args:
            vertices: The number of vertices, indexed from 0 to V-1.

        Raises:
            BaseGraphException: If vertices is negative.

        """

        if vertices < 0:
            raise BaseGraphException("Number of vertices cannot be negative")
        
        self._vertices: int = vertices
        self._edges: int = 0
        self._adjacency_list: List[List[int]] = [[] for _ in range(vertices)]
        self._uuid: str = str(uuid.uuid4())

    def V(self) -> int:

        """Returns the number of vertices."""

        return self._vertices

    def E(self) -> int:

        """Returns the number of edges."""

        return self._edges
    
    @property
    def uuid(self) -> str:

        """Returns the unique identifier of the graph instance."""

        return self._uuid

    def _validateVertex(self, vertex: int):

        """Raises BaseGraphException if the vertex is out of bounds."""

        if not (0 <= vertex < self._vertices):
            raise BaseGraphException(f"Vertex {vertex} is not between 0 and {self._vertices - 1}")

    def adj(self, vertex: int) -> Tuple[int, ...]:

        """Returns vertices adjacent to the given vertex as an immutable tuple."""

        self._validateVertex(vertex)
        return tuple(self._adjacency_list[vertex])

    def addEdge(self, v: int, w: int) -> None:

        """Adds an undirected edge between two vertices."""

        self._validateVertex(v)
        self._validateVertex(w)

        # Check if edge already exists to avoid duplicates
        if w not in self._adjacency_list[v]:

            self._adjacency_list[v].append(w)

            # For undirected graph, add edge in both directions (unless self-loop)
            if v != w:
                self._adjacency_list[w].append(v)
            self._edges += 1

    def removeEdge(self, v: int, w: int) -> None:
        
        """Removes an undirected edge between two vertices if it exists."""

        self._validateVertex(v)
        self._validateVertex(w)

        try:

            # Remove edge if it exists
            self._adjacency_list[v].remove(w)

            # For undirected graph, remove edge from both directions (unless self-loop)
            if v != w:
                self._adjacency_list[w].remove(v)
                
            self._edges -= 1

        except ValueError:
            pass  # Edge did not exist

    def hasEdge(self, v: int, w: int) -> bool:

        """Checks if an edge exists between two vertices."""

        self._validateVertex(v)
        self._validateVertex(w)

        return w in self._adjacency_list[v]
    
    def BFS(self, start: int) -> List[int]:

        """Performs a breadth-first search starting from the given vertex."""

        self._validateVertex(start)

        visited: List[bool] = [False] * self._vertices
        queue: Queue[int] = Queue()
        bfs_order: List[int] = []

        visited[start] = True
        queue.enqueue(start)

        # While there are vertices to process, continue BFS
        while not queue.isEmpty():

            vertex = queue.dequeue()
            bfs_order.append(vertex)

            # For each unvisited neighbor, mark as visited and enqueue
            for neighbor in self.adj(vertex):
                if not visited[neighbor]:
                    visited[neighbor] = True
                    queue.enqueue(neighbor)

        return bfs_order

    def DFS(self, start: int) -> List[int]:

        """Performs a depth-first search starting from the given vertex."""

        self._validateVertex(start)

        visited: List[bool] = [False] * self._vertices
        stack: Stack[int] = Stack()
        dfs_order: List[int] = []

        stack.push(start)

        # While there are vertices to process, continue DFS
        while not stack.isEmpty():

            vertex = stack.pop()

            if not visited[vertex]:
                visited[vertex] = True
                dfs_order.append(vertex)

                # For each unvisited neighbor, push onto stack
                for neighbor in reversed(self.adj(vertex)):
                    if not visited[neighbor]:
                        stack.push(neighbor)

        return dfs_order

    def findSCC(self) -> List[List[int]]:

        """
        Finds strongly connected components (SCCs) in the directed graph using Kosaraju's algorithm.

        Returns:
            A list of lists, where each inner list contains the vertices in one SCC.

        Raises:
            BaseGraphException: If the graph is undirected.
        """

        return Kosaraju.findSCC(self)

    @staticmethod
    def Transpose(graph: "Graph") -> "Graph":

        """
        Returns the transpose of the given directed graph.

        Args:
            graph: The directed graph to transpose.

        Returns:
            A new Graph instance that is the transpose of the input graph.
        """

        transposed: Graph = Graph(graph.V())
        for v in range(graph.V()):
            for w in graph.adj(v):
                transposed.addEdge(w, v)

        return transposed

    def __hash__(self) -> int:

        """Returns a hash of the graph based on its UUID."""

        return hash(self._uuid)
    
    def __eq__(self, other: object) -> bool:

        """Checks equality based on UUID and vertex count."""

        if not isinstance(other, Graph):
            return False
        return self._uuid == other.uuid and self._vertices == other.V()


class Kosaraju:

    """
    A class to perform Kosaraju's algorithm for finding strongly connected components in a directed graph.
    Will raise an exception if the graph is undirected.
    """

    @staticmethod
    def _isDirected(graph: Graph) -> bool:

        """
        Checks if the graph is directed. Raises an exception if it is undirected.

        Args:
            graph: The graph to check.

        Returns:
            True if the graph is directed, False otherwise.

        Raises:
            ValueError: If the graph is undirected.
        """

        if not isinstance(graph, Graph):
            raise BaseGraphException("The provided graph must be an instance of Graph.")
        
        # Check if the graph is directed by verifying that each edge is not bidirectional
        for v in range(graph.V()):
            for w in graph.adj(v):
                if v in graph.adj(w) and w in graph.adj(v):
                    return False
                
        # If we reach here, the graph is directed
        return True

    @staticmethod
    def _KosarajuDFS(graph: Graph, vertex: int, visited: List[bool], finish_order: List[int]) -> None:

        """
        A helper method to perform DFS and record the finish order of vertices.

        Args:
            graph: The directed graph to analyze.
            vertex: The current vertex being visited.
            visited: A list to track visited vertices.
            finish_order: A list to record the order of finishing times.
        """

        visited[vertex] = True
        for neighbor in graph.adj(vertex):
            if not visited[neighbor]:
                Kosaraju._KosarajuDFS(graph, neighbor, visited, finish_order)
        finish_order.append(vertex)

    @staticmethod
    def findSCC(graph: Graph) -> List[List[int]]:

        """
        Finds strongly connected components (SCCs) in a directed graph using Kosaraju's algorithm.

        Args:
            graph: The directed graph to analyze.

        Returns:
            A list of lists, where each inner list contains the vertices in one SCC.
        """

        if not Kosaraju._isDirected(graph):
            raise ValueError("Kosaraju's algorithm is only applicable to directed graphs.")

        visited: List[bool] = [False] * graph.V()
        finish_order: List[int] = []

        # Step 1: Perform DFS to get finish order
        for vertex in range(graph.V()):
            if not visited[vertex]:
                Kosaraju._KosarajuDFS(graph, vertex, visited, finish_order)

        # Step 2: Transpose the graph
        transposed_graph: Graph = Graph.Transpose(graph)
        visited = [False] * graph.V()
        sccs: List[List[int]] = []

        # Step 3: Perform DFS on transposed graph in reverse finish order
        while finish_order:
            vertex = finish_order.pop()
            if not visited[vertex]:
                scc: List[int] = []
                Kosaraju._KosarajuDFS(transposed_graph, vertex, visited, scc)
                sccs.append(scc)
        
        return sccs

