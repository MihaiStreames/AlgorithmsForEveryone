package io.github.mihaistreames.afe.structs.graphs;

import java.util.*;

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
 * @param <T> The type of the vertices.
 */
public class Graph<T> {

    private final Map<T, List<T>> adj;
    private int edges;

    /**
     * Constructs an empty graph.
     */
    public Graph() {
        this.adj = new HashMap<>();
        this.edges = 0;
    }

    /**
     * Returns the number of vertices in the graph.
     *
     * @return The number of vertices.
     */
    public int V() {
        return adj.size();
    }

    /**
     * Returns the number of edges in the graph.
     *
     * @return The number of edges.
     */
    public int E() {
        return edges;
    }

    /**
     * Adds a vertex to the graph.
     * If the vertex already exists, this method does nothing.
     *
     * @param vertex The vertex to add.
     */
    public void addVertex(T vertex) {
        adj.putIfAbsent(vertex, new ArrayList<>());
    }

    /**
     * Adds an undirected edge between two vertices.
     * If the vertices do not exist, they are added to the graph.
     *
     * @param v One vertex.
     * @param w The other vertex.
     */
    public void addEdge(T v, T w) {
        addVertex(v);
        addVertex(w);

        // To avoid duplicate edges, we can check if the edge already exists
        if (!adj.get(v).contains(w)) {
            adj.get(v).add(w);
            adj.get(w).add(v);
            edges++;
        }
    }

    /**
     * Returns the vertices adjacent to a given vertex.
     *
     * @param v The vertex.
     * @return An iterable of adjacent vertices, or null if the vertex is not in the graph.
     */
    public Iterable<T> adj(T v) {
        return adj.get(v);
    }

    /**
     * Returns a set of all vertices in the graph.
     *
     * @return A set view of the vertices.
     */
    public Set<T> vertices() {
        return adj.keySet();
    }
}