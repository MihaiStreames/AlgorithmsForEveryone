package io.github.mihaistreames.afe.structs.graphs;

import org.jetbrains.annotations.NotNull;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * A generic directed graph (digraph) implemented using an adjacency list.
 *
 * @param <T> The type of the vertices.
 */
public class Digraph<T> {
    private final Map<T, Set<DirectedEdge<T>>> adj;
    private int edgeCount;

    /**
     * Constructs an empty digraph.
     */
    public Digraph() {
        this.adj = new HashMap<>();
        this.edgeCount = 0;
    }

    /**
     * Returns the number of vertices in the digraph.
     *
     * @return the number of vertices.
     */
    public int V() {
        return adj.size();
    }

    /**
     * Returns the number of edges in the digraph.
     *
     * @return the number of edges.
     */
    public int E() {
        return edgeCount;
    }

    /**
     * Adds a vertex to the digraph.
     *
     * @param v the vertex to add.
     */
    public void addVertex(T v) {
        adj.putIfAbsent(v, new HashSet<>());
    }

    /**
     * Adds a directed edge to the digraph.
     *
     * @param edge the directed edge to add.
     */
    public void addEdge(@NotNull DirectedEdge<T> edge) {
        T from = edge.from();
        T to = edge.to();
        addVertex(from);
        addVertex(to);
        if (adj.get(from).add(edge)) {
            edgeCount++;
        }
    }

    /**
     * Returns the edges adjacent to a given vertex.
     *
     * @param v the vertex.
     * @return an iterable of adjacent edges.
     */
    public Iterable<DirectedEdge<T>> adj(T v) {
        return adj.getOrDefault(v, new HashSet<>());
    }

    /**
     * Returns all vertices in the digraph.
     *
     * @return an iterable of all vertices.
     */
    public Iterable<T> vertices() {
        return adj.keySet();
    }

    /**
     * Returns all edges in the digraph.
     *
     * @return an iterable of all edges.
     */
    public Iterable<DirectedEdge<T>> edges() {
        Set<DirectedEdge<T>> allEdges = new HashSet<>();
        for (Set<DirectedEdge<T>> edgeSet : adj.values()) {
            allEdges.addAll(edgeSet);
        }
        return allEdges;
    }

    /**
     * Returns a new digraph that is the reverse of this digraph.
     *
     * @return The reversed digraph.
     */
    public Digraph<T> reverse() {
        Digraph<T> reversed = new Digraph<>();
        for (T v : this.vertices()) {
            reversed.addVertex(v);
        }
        for (DirectedEdge<T> e : this.edges()) {
            reversed.addEdge(new DirectedEdge<>(e.to(), e.from(), e.weight()));
        }
        return reversed;
    }
}