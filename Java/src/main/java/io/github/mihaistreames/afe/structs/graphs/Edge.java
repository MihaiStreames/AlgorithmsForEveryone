package io.github.mihaistreames.afe.structs.graphs;

import org.jetbrains.annotations.NotNull;

import java.util.Objects;

/**
 * Represents a generic, undirected, weighted edge.
 * The type parameter T represents the type of the vertices.
 *
 * @param <T> the type of the vertices
 */
public class Edge<T> implements Comparable<Edge<T>> {

    protected final T vertex1;
    protected final T vertex2;
    private final double weight;

    /**
     * Initializes an edge between two vertices with a given weight.
     *
     * @param vertex1 the first vertex
     * @param vertex2 the second vertex
     * @param weight  the weight of the edge
     * @throws NullPointerException     if either vertex is null
     * @throws IllegalArgumentException if the weight is NaN
     */
    public Edge(T vertex1, T vertex2, double weight) {
        this.vertex1 = Objects.requireNonNull(vertex1, "Vertex cannot be null");
        this.vertex2 = Objects.requireNonNull(vertex2, "Vertex cannot be null");

        if (Double.isNaN(weight)) {
            throw new IllegalArgumentException("Weight cannot be NaN");
        }

        this.weight = weight;
    }

    /**
     * Returns the weight of the edge.
     *
     * @return the edge weight
     */
    public double weight() {
        return weight;
    }

    /**
     * Returns one of the two vertices of the edge.
     *
     * @return one of the edge's vertices
     */
    public T either() {
        return vertex1;
    }

    /**
     * Given one vertex of the edge, returns the other.
     *
     * @param vertex one endpoint of the edge
     * @return the other endpoint of the edge
     * @throws IllegalArgumentException if the given vertex is not part of this edge
     */
    public T other(@NotNull T vertex) {
        if (vertex.equals(vertex1)) {
            return vertex2;
        } else if (vertex.equals(vertex2)) {
            return vertex1;
        } else {
            throw new IllegalArgumentException("Illegal endpoint");
        }
    }

    /**
     * Compares this edge to another edge by weight.
     *
     * @param that the other edge to compare to
     * @return a negative integer, zero, or a positive integer as this edge's
     * weight is less than, equal to, or greater than the other edge's weight
     */
    @Override
    public int compareTo(@NotNull Edge<T> that) {
        return Double.compare(this.weight, that.weight);
    }

    /**
     * Returns a string representation of the edge.
     *
     * @return a string in the format "v1-v2 weight"
     */
    @Override
    public String toString() {
        return String.format("%s-%s %.5f", vertex1, vertex2, weight);
    }
}