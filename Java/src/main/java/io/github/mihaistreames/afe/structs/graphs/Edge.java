package io.github.mihaistreames.afe.structs.graphs;

public class Edge implements Comparable<Edge>{
    private final int vertex1;  // One endpoint of the edge
    private final int vertex2;  // The other endpoint of the edge
    private final double weight;

    /**
     * Constructs an edge between two vertices with a specified weight.
     *
     * @param vertex1 the first vertex
     * @param vertex2 the second vertex
     * @throws IllegalArgumentException if either vertex is negative or weight is negative
     */
    public Edge(int vertex1, int vertex2) {
        if (vertex1 < 0 || vertex2 < 0) {
            throw new IllegalArgumentException("Vertex indices must be non-negative");
        }
        this.vertex1 = vertex1;
        this.vertex2 = vertex2;
        this.weight = 1.0; // Default weight set to 1.0
    }

    /**
     * Constructs an edge between two vertices with a specified weight.
     *
     * @param vertex1 the first vertex
     * @param vertex2 the second vertex
     * @param weight  the weight of the edge
     * @throws IllegalArgumentException if either vertex is negative or weight is negative
     */
    public Edge(int vertex1, int vertex2, double weight) {
        if (vertex1 < 0 || vertex2 < 0) {
            throw new IllegalArgumentException("Vertex indices must be non-negative");
        }
        if (weight < 0) {
            throw new IllegalArgumentException("Weight must be non-negative");
        }
        this.vertex1 = vertex1;
        this.vertex2 = vertex2;
        this.weight = weight;
    }

    // ========== PUBLIC API ==========
    /**
     * Returns the weight of the edge.
     *
     * @return the weight of the edge
     */
    public double weight() {
        return weight;
    }

    /**
     * Returns the first vertex of the edge.
     *
     * @return the first vertex
     */
    public int either() {
        return vertex1;
    }

    /**
     * Returns the other vertex of the edge given one vertex.
     *
     * @param vertex one endpoint of the edge
     * @return the other vertex
     * @throws IllegalArgumentException if the provided vertex is not part of this edge
     */
    public int other(int vertex) {
        if (vertex < 0 || (vertex != vertex1 && vertex != vertex2)) {
            throw new IllegalArgumentException("Vertex " + vertex + " is not part of this edge");
        }
        if (vertex == vertex1) {
            return vertex2;
        } else if (vertex == vertex2) {
            return vertex1;
        } else {
            throw new IllegalArgumentException("Vertex " + vertex + " is not part of this edge");
        }

    }

    @Override
    public int compareTo(Edge that) {
        if (that == null) {
            throw new NullPointerException("Cannot compare to null edge");
        }
        return Double.compare(this.weight, that.weight);
    }

    /**
     * Returns a string representation of this edge.
     *
     * @return a string representation of this edge
     */
    public String toString() {
        return String.format("%d-%d %.5f", vertex1, vertex2, weight);
    }
}
