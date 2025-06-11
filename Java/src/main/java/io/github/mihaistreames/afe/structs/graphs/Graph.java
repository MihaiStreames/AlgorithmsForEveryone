package io.github.mihaistreames.afe.structs.graphs;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

/**
 * Implementation of an undirected graph using adjacency lists.
 * <p>
 * This graph representation stores vertices as integers from 0 to V-1, where V is the
 * total number of vertices. Each vertex maintains a list of its adjacent vertices.
 * </p>
 * <p>
 * <strong>Time Complexity:</strong><br>
 * - Add edge: O(1)<br>
 * - Get adjacent vertices: O(1)<br>
 * - Space: O(V + E) where V is vertices and E is edges<br>
 * </p>
 */
public class Graph {

    private final int vertices;
    private int edges;
    private final List<List<Edge>> adjacencyList;

    /**
     * Constructs an empty graph with the specified number of vertices.
     * <p>
     * Creates a graph with vertices numbered from 0 to V-1, with no edges initially.
     * All adjacency lists are initialized as empty.
     * </p>
     *
     * @param vertices the number of vertices in the graph
     * @throws IllegalArgumentException if vertices is negative
     */
    public Graph(final int vertices) {
        if (vertices < 0) throw new IllegalArgumentException("Number of vertices cannot be negative");

        this.vertices = vertices;
        this.edges = 0;
        this.adjacencyList = new ArrayList<>(vertices);

        // Initialize adjacency lists for each vertex
        for (int i = 0; i < vertices; i++) {
            adjacencyList.add(new ArrayList<>());
        }
    }

    // ========== PUBLIC API ==========

    /**
     * Returns the number of vertices in this graph.
     *
     * @return the number of vertices
     */
    public int V() {
        return vertices;
    }

    /**
     * Returns the number of edges in this graph.
     *
     * @return the number of edges
     */
    public int E() {
        return edges;
    }

    /**
     * Returns the vertices adjacent to the specified vertex.
     *
     * @param vertex the vertex whose adjacent vertices to return
     * @return an immutable list of vertices adjacent to the specified vertex
     * @throws IllegalArgumentException if vertex is not between 0 and V-1
     */
    @NotNull
    public List<Integer> adj(final int vertex) {
        validateVertex(vertex);
        List<Edge> edges = adjacencyList.get(vertex);
        List<Integer> adjacentVertices = new ArrayList<>(edges.size());
        for (Edge edge : edges) {
            adjacentVertices.add(edge.other(vertex));
        }
        return adjacentVertices;
    }

    /**
     * Returns the vertices adjacent to the specified vertex.
     *
     * @param vertex the vertex whose adjacent vertices to return
     * @return an immutable list of vertices adjacent to the specified vertex
     * @throws IllegalArgumentException if vertex is not between 0 and V-1
     */
    @NotNull
    public List<Edge> adjEdge(final int vertex) {
        validateVertex(vertex);
        return adjacencyList.get(vertex);
    }

    /**
     * Adds an undirected edge between the specified vertices.
     * <p>
     * If the edge already exists, this method does nothing.
     * The edge is added to both vertices' adjacency lists.
     * </p>
     *
     * @param v one vertex of the edge
     * @param w the other vertex of the edge
     * @throws IllegalArgumentException if either vertex is not between 0 and V-1
     */
    public void addEdge(final int v, final int w) {
        addEdge(v, w, 1);
    }

    /**
     * Adds an undirected edge between the specified vertices.
     * <p>
     * If the edge already exists, this method does nothing.
     * The edge is added to both vertices' adjacency lists.
     * </p>
     *
     * @param v one vertex of the edge
     * @param w the other vertex of the edge
     * @throws IllegalArgumentException if either vertex is not between 0 and V-1
     */
    public void addEdge(final int v, final int w, double weight) {
        validateVertex(v);
        validateVertex(w);

        Edge edge = new Edge(v, w, weight);

        // Check if edge already exists to avoid duplicates
        if (!adjacencyList.get(v).contains(edge)) {
            adjacencyList.get(v).add(edge);

            // For undirected graph, add edge in both directions (unless self-loop)
            if (v != w) {
                adjacencyList.get(w).add(edge);
            }
            edges++;
        }
    }

    /**
     * Removes an undirected edge between the specified vertices.
     * <p>
     * If the edge does not exist, this method does nothing.
     * The edge is removed from both vertices' adjacency lists.
     * </p>
     *
     * @param v one vertex of the edge
     * @param w the other vertex of the edge
     * @throws IllegalArgumentException if either vertex is not between 0 and V-1
     */
    public void removeEdge(final int v, final int w) {
        removeEdge(v, w, 1);
    }

    public void removeEdge(final int v, final int w, double weight) {
        validateVertex(v);
        validateVertex(w);

        Edge edge = new Edge(v, w, weight);
        removeEdge(edge);
    }

    public void removeEdge(final Edge edge) {
        if (edge == null) {
            throw new IllegalArgumentException("Edge cannot be null");
        }

        int v = edge.either();
        int w = edge.other(v);

        validateVertex(v);
        validateVertex(w);

        // Remove edge from both vertices' adjacency lists
        adjacencyList.get(v).remove(edge);
        adjacencyList.get(w).remove(edge);

        edges--;
    }

    /**
     * Checks if there is an edge between the specified vertices.
     *
     * @param v one vertex
     * @param w the other vertex
     * @return true if there is an edge between v and w, false otherwise
     * @throws IllegalArgumentException if either vertex is not between 0 and V-1
     */
    public boolean hasEdge(final int v, final int w) {
        validateVertex(v);
        validateVertex(w);
        Edge edge = new Edge(v, w, 1);
        return adjacencyList.get(v).contains(edge);
    }

    // ========== PRIVATE HELPER METHODS ==========

    /**
     * Validates that the vertex is within the valid range [0, V-1].
     *
     * @param vertex the vertex to validate
     * @throws IllegalArgumentException if vertex is not between 0 and V-1
     */
    private void validateVertex(final int vertex) {
        if (vertex < 0 || vertex >= vertices) {
            throw new IllegalArgumentException(
                    "Vertex " + vertex + " is not between 0 and " + (vertices - 1)
            );
        }
    }
}