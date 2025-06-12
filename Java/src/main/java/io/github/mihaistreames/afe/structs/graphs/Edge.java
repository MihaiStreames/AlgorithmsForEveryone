package io.github.mihaistreames.afe.structs.graphs;

import org.jetbrains.annotations.NotNull;

import java.util.Objects;

/**
 * Represents a weighted undirected edge in a graph.
 *
 * @param <T> The type of the vertices.
 */
public class Edge<T> implements Comparable<Edge<T>> {
    private final T v;
    private final T w;
    private final double weight;

    /**
     * Constructs an undirected edge.
     *
     * @param v      one vertex.
     * @param w      the other vertex.
     * @param weight the weight of the edge.
     */
    public Edge(T v, T w, double weight) {
        this.v = v;
        this.w = w;
        this.weight = weight;
    }

    /**
     * Returns the weight of the edge.
     *
     * @return the edge weight.
     */
    public double weight() {
        return weight;
    }

    /**
     * Returns one of the vertices of the edge.
     *
     * @return one vertex.
     */
    public T either() {
        return v;
    }

    /**
     * Returns the vertex opposite to the given vertex.
     *
     * @param vertex the vertex.
     * @return the other vertex.
     * @throws IllegalArgumentException if the vertex is not part of the edge.
     */
    public T other(@NotNull T vertex) {
        if (vertex.equals(v)) return w;
        if (vertex.equals(w)) return v;
        throw new IllegalArgumentException("Illegal endpoint");
    }

    @Override
    public int compareTo(@NotNull Edge<T> that) {
        return Double.compare(this.weight, that.weight);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Edge<?> edge = (Edge<?>) o;
        return Double.compare(edge.weight, weight) == 0 &&
                (Objects.equals(v, edge.v) && Objects.equals(w, edge.w) ||
                        Objects.equals(v, edge.w) && Objects.equals(w, edge.v));
    }

    @Override
    public int hashCode() {
        // Hashcode is order-independent for v and w
        return Objects.hash(Objects.hashCode(v) + Objects.hashCode(w), weight);
    }

    @Override
    public String toString() {
        return String.format("%s-%s %.2f", v.toString(), w.toString(), weight);
    }
}