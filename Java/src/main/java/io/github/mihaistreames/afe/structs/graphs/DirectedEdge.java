package io.github.mihaistreames.afe.structs.graphs;

import org.jetbrains.annotations.NotNull;

import java.util.Objects;

/**
 * Represents a weighted directed edge in a graph.
 *
 * @param <T> The type of the vertices.
 */
public record DirectedEdge<T>(T from, T to, double weight) {
    /**
     * Constructs a directed edge.
     *
     * @param from   the source vertex.
     * @param to     the destination vertex.
     * @param weight the weight of the edge.
     */
    public DirectedEdge {
    }

    /**
     * Returns the source vertex of the edge.
     *
     * @return the source vertex.
     */
    @Override
    public T from() {
        return from;
    }

    /**
     * Returns the destination vertex of the edge.
     *
     * @return the destination vertex.
     */
    @Override
    public T to() {
        return to;
    }

    /**
     * Returns the weight of the edge.
     *
     * @return the edge weight.
     */
    @Override
    public double weight() {
        return weight;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DirectedEdge<?> that = (DirectedEdge<?>) o;
        return Double.compare(that.weight, weight) == 0 &&
                Objects.equals(from, that.from) &&
                Objects.equals(to, that.to);
    }

    @Override
    public @NotNull String toString() {
        return from + "->" + to + " (" + String.format("%.2f", weight) + ")";
    }
}