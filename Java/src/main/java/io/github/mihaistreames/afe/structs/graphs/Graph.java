package io.github.mihaistreames.afe.structs.graphs;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.Collections;
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
 *
 * @author Sincos Team
 * @version 1.0.0
 * @since 0.0.3
 */
public class Graph {

    private final int vertices;
    private int edges;
    private final List<List<Integer>> adjacencyList;

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
        return Collections.unmodifiableList(adjacencyList.get(vertex));
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
        validateVertex(v);
        validateVertex(w);

        // Check if edge already exists to avoid duplicates
        if (!adjacencyList.get(v).contains(w)) {
            adjacencyList.get(v).add(w);

            // For undirected graph, add edge in both directions (unless self-loop)
            if (v != w) {
                adjacencyList.get(w).add(v);
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
        validateVertex(v);
        validateVertex(w);

        // Remove edge if it exists
        if (adjacencyList.get(v).contains(w)) {
            adjacencyList.get(v).remove(Integer.valueOf(w));

            // For undirected graph, remove edge from both directions (unless self-loop)
            if (v != w) {
                adjacencyList.get(w).remove(Integer.valueOf(v));
            }

            edges--;
        }
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
        return adjacencyList.get(v).contains(w);
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