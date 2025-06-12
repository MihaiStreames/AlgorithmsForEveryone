package io.github.mihaistreames.afe.structs.graphs;

import org.jetbrains.annotations.NotNull;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * A generic undirected graph implemented using an adjacency list.
 *
 * @param <T> The type of the vertices.
 */
public class Graph<T> {
    private final Map<T, Set<T>> adj;
    private int edgeCount;

    /**
     * Constructs an empty graph.
     */
    public Graph() {
        this.adj = new HashMap<>();
        this.edgeCount = 0;
    }

    /**
     * Returns the number of vertices in the graph.
     *
     * @return the number of vertices.
     */
    public int V() {
        return adj.size();
    }

    /**
     * Returns the number of edges in the graph.
     *
     * @return the number of edges.
     */
    public int E() {
        return edgeCount;
    }

    /**
     * Adds a vertex to the graph.
     *
     * @param v the vertex to add.
     */
    public void addVertex(T v) {
        adj.putIfAbsent(v, new HashSet<>());
    }

    /**
     * Adds an undirected edge between two vertices.
     *
     * @param v one vertex.
     * @param w the other vertex.
     */
    public void addEdge(@NotNull T v, @NotNull T w) {
        addVertex(v);
        addVertex(w);
        // Add edge and increment count only if it's a new edge
        if (adj.get(v).add(w)) {
            adj.get(w).add(v);
            edgeCount++;
        }
    }

    /**
     * Returns the vertices adjacent to a given vertex.
     *
     * @param v the vertex.
     * @return an iterable of adjacent vertices.
     */
    public Iterable<T> adj(T v) {
        return adj.getOrDefault(v, new HashSet<>());
    }

    /**
     * Returns all vertices in the graph.
     *
     * @return an iterable of all vertices.
     */
    public Iterable<T> vertices() {
        return adj.keySet();
    }
}