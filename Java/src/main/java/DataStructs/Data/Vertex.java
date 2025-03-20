package DataStructs.Data;

import java.util.Objects;

/**
 * A generic Vertex class that can be used in graph data structures.
 *
 * @param <T> the type of data stored in the vertex
 */
public class Vertex<T> {
    private T data;

    /**
     * Constructs a new Vertex with the specified data.
     *
     * @param data the data to store in the vertex
     */
    public Vertex(T data) {
        this.data = data;
    }

    /**
     * Gets the data stored in this vertex.
     *
     * @return the vertex data
     */
    public T getData() {
        return data;
    }

    /**
     * Sets the data for this vertex.
     *
     * @param data the new data to store
     */
    public void setData(T data) {
        this.data = data;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Vertex<?> vertex = (Vertex<?>) o;
        return Objects.equals(data, vertex.data);
    }

    @Override
    public int hashCode() {
        return Objects.hash(data);
    }

    @Override
    public String toString() {
        return data != null ? data.toString() : "null";
    }
}

