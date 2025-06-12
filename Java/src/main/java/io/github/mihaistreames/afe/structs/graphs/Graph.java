package io.github.mihaistreames.afe.structs.graphs;

import org.jetbrains.annotations.NotNull;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * A generic undirected graph implemented using an adjacency list of edges.
 * This structure can represent both weighted and unweighted graphs.
 *
 * @param <T> The type of the vertices.
 */
public class Graph<T> {
    private final Map<T, Set<Edge<T>>> adj;
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
     * Adds an unweighted, undirected edge between two vertices.
     *
     * @param v one vertex.
     * @param w the other vertex.
     */
    public void addEdge(@NotNull T v, @NotNull T w) {
        addEdge(v, w, 1.0); // Default weight of 1.0 for unweighted graphs
    }

    /**
     * Adds a weighted, undirected edge between two vertices.
     *
     * @param v      one vertex.
     * @param w      the other vertex.
     * @param weight the weight of the edge.
     */
    public void addEdge(@NotNull T v, @NotNull T w, double weight) {
        addVertex(v);
        addVertex(w);
        Edge<T> edge = new Edge<>(v, w, weight);
        // Add edge only if it's new to avoid duplicates and double-counting
        if (adj.get(v).add(edge)) {
            adj.get(w).add(edge);
            edgeCount++;
        }
    }

    /**
     * Returns the edges connected to a given vertex.
     *
     * @param v the vertex.
     * @return an iterable of connected edges.
     */
    public Iterable<Edge<T>> adj(T v) {
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

    /**
     * Returns all edges in the graph.
     *
     * @return an iterable of all edges.
     */
    public Iterable<Edge<T>> edges() {
        Set<Edge<T>> allEdges = new HashSet<>();
        for (Set<Edge<T>> edgeSet : adj.values()) {
            allEdges.addAll(edgeSet);
        }
        return allEdges;
    }
}