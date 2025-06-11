package io.github.mihaistreames.afe.structs.graphs;

/**
 * Represents a generic, directed, weighted edge, extending the base Edge class.
 * The type parameter T represents the type of the vertices.
 *
 * @param <T> the type of the vertices
 */
public class DirectedEdge<T> extends Edge<T> {

    /**
     * Initializes a directed edge from a source vertex to a destination vertex.
     *
     * @param from   the source vertex
     * @param to     the destination vertex
     * @param weight the weight of the directed edge
     */
    public DirectedEdge(T from, T to, double weight) {
        super(from, to, weight);
    }

    /**
     * Returns the source vertex of the directed edge.
     *
     * @return the source vertex
     */
    public T from() {
        return super.vertex1;
    }

    /**
     * Returns the destination vertex of the directed edge.
     *
     * @return the destination vertex
     */
    public T to() {
        return super.vertex2;
    }

    /**
     * Returns a string representation of the directed edge.
     *
     * @return a string in the format "from->to weight"
     */
    @Override
    public String toString() {
        return String.format("%s->%s %.5f", from(), to(), weight());
    }
}