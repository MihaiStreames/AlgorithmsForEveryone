package io.github.mihaistreames.afe.structs.graphs;

import org.jetbrains.annotations.NotNull;

import java.util.*;

/**
 * Implementation of a directed graph (digraph) using adjacency lists.
 * <p>
 * This graph representation stores vertices as generic types and maintains a set of directed edges
 * from each vertex to its adjacent vertices.
 * </p>
 * <strong>Time Complexity:</strong><br>
 * - Add edge: O(1)<br>
 * - Get adjacent vertices: O(1)<br>
 * - Space: O(V + E) where V is vertices and E is edges<br>
 * </p>
 *
 * @param <T> the type of the vertices
 */
public class Digraph<T> {

    private final Map<T, Set<DirectedEdge<T>>> adj;
    private int edges;

    /**
     * Initializes an empty digraph.
     */
    public Digraph() {
        this.adj = new HashMap<>();
        this.edges = 0;
    }

    /**
     * Returns the number of vertices in the digraph.
     *
     * @return the number of vertices
     */
    public int V() {
        return adj.size();
    }

    /**
     * Returns the number of edges in the digraph.
     *
     * @return the number of edges
     */
    public int E() {
        return edges;
    }

    /**
     * Adds a vertex to the digraph. If the vertex already exists, this method has no effect.
     *
     * @param vertex the vertex to add
     */
    public void addVertex(T vertex) {
        adj.putIfAbsent(vertex, new HashSet<>());
    }

    /**
     * Adds a directed edge to the digraph. The vertices of the edge are automatically added if not already present.
     *
     * @param e the directed edge to add
     */
    public void addEdge(@NotNull DirectedEdge<T> e) {
        T v = e.from();
        T w = e.to();
        addVertex(v);
        addVertex(w);
        adj.get(v).add(e);
        edges++;
    }

    /**
     * Returns the edges outgoing from a given vertex.
     *
     * @param vertex the vertex
     * @return an iterable collection of edges outgoing from the vertex
     */
    public Iterable<DirectedEdge<T>> adj(T vertex) {
        return adj.getOrDefault(vertex, Collections.emptySet());
    }

    /**
     * Returns a set containing all vertices in the digraph.
     *
     * @return an unmodifiable set of all vertices
     */
    public Set<T> vertices() {
        return Collections.unmodifiableSet(adj.keySet());
    }

    /**
     * Returns all edges in the digraph.
     *
     * @return an iterable collection of all edges in the digraph
     */
    public Iterable<DirectedEdge<T>> edges() {
        Set<DirectedEdge<T>> list = new HashSet<>();
        for (T v : adj.keySet()) {
            list.addAll(adj.get(v));
        }
        return list;
    }
}