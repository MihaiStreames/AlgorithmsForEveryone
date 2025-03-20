package DataStructs.Graphs;

import DataStructs.Data.Vertex;

import java.util.*;

/**
 * An implementation of a Graph data structure using adjacency lists.
 * This Graph can be directed or undirected, and can store any type of object as vertex data.
 */
public class Graph<T> {
    // Map each vertex to its list of adjacent vertices
    private final Map<Vertex<T>, List<Vertex<T>>> adjacencyList;
    private final boolean isDirected;

    /**
     * Constructs a new Graph.
     *
     * @param isDirected true if the graph is directed, false if undirected
     */
    public Graph(boolean isDirected) {
        this.adjacencyList = new HashMap<>();
        this.isDirected = isDirected;
    }

    /**
     * Checks if the graph is directed.
     *
     * @return true if the graph is directed, false if undirected
     */
    public boolean isDirected() {
        return isDirected;
    }

    /**
     * Adds a vertex to the graph.
     *
     * @param data the data to store in the vertex
     * @return the created vertex
     */
    public Vertex<T> addVertex(T data) {
        Vertex<T> newVertex = new Vertex<>(data);
        if (!adjacencyList.containsKey(newVertex)) {
            adjacencyList.put(newVertex, new ArrayList<>());
        }
        return newVertex;
    }

    /**
     * Adds an existing vertex to the graph.
     *
     * @param vertex the vertex to add
     */
    public void addVertex(Vertex<T> vertex) {
        if (!adjacencyList.containsKey(vertex)) {
            adjacencyList.put(vertex, new ArrayList<>());
        }
    }

    /**
     * Checks if a vertex is in the graph.
     *
     * @param vertex the vertex to check
     * @return true if the vertex is in the graph, false otherwise
     */
    public boolean containsVertex(Vertex<T> vertex) {
        return adjacencyList.containsKey(vertex);
    }

    /**
     * Adds an edge between two vertices.
     *
     * @param source      the source vertex
     * @param destination the destination vertex
     */
    public void addEdge(Vertex<T> source, Vertex<T> destination) {
        if (!adjacencyList.containsKey(source)) {
            adjacencyList.put(source, new ArrayList<>());
        }
        if (!adjacencyList.containsKey(destination)) {
            adjacencyList.put(destination, new ArrayList<>());
        }

        adjacencyList.get(source).add(destination);

        // If the graph is undirected, add an edge in the other direction as well
        if (!isDirected) {
            adjacencyList.get(destination).add(source);
        }
    }

    /**
     * Gets all vertices in the graph.
     *
     * @return a set of all vertices
     */
    public Set<Vertex<T>> getVertices() {
        return adjacencyList.keySet();
    }

    /**
     * Gets all adjacent vertices to a given vertex.
     *
     * @param vertex the vertex to get adjacents for
     * @return a list of adjacent vertices
     */
    public List<Vertex<T>> getAdjacentVertices(Vertex<T> vertex) {
        return adjacencyList.getOrDefault(vertex, new ArrayList<>());
    }

    /**
     * Removes a vertex and all its incoming/outgoing edges from the graph.
     *
     * @param vertex the vertex to remove
     */
    public void removeVertex(Vertex<T> vertex) {
        // Remove all edges pointing to this vertex
        for (List<Vertex<T>> edges : adjacencyList.values()) {
            edges.remove(vertex);
        }

        // Remove the vertex and its outgoing edges
        adjacencyList.remove(vertex);
    }

    /**
     * Removes an edge between two vertices.
     *
     * @param source      the source vertex
     * @param destination the destination vertex
     */
    public void removeEdge(Vertex<T> source, Vertex<T> destination) {
        if (adjacencyList.containsKey(source)) {
            adjacencyList.get(source).remove(destination);
        }

        // If the graph is undirected, remove the edge in the other direction as well
        if (!isDirected && adjacencyList.containsKey(destination)) {
            adjacencyList.get(destination).remove(source);
        }
    }

    /**
     * Checks if an edge exists between two vertices.
     *
     * @param source      the source vertex
     * @param destination the destination vertex
     * @return true if the edge exists, false otherwise
     */
    public boolean hasEdge(Vertex<T> source, Vertex<T> destination) {
        return adjacencyList.containsKey(source) &&
                adjacencyList.get(source).contains(destination);
    }

    /**
     * Gets the number of vertices in the graph.
     *
     * @return the number of vertices
     */
    public int getVertexCount() {
        return adjacencyList.size();
    }

    /**
     * Gets the number of edges in the graph.
     *
     * @return the number of edges
     */
    public int getEdgeCount() {
        int count = 0;
        for (List<Vertex<T>> edges : adjacencyList.values()) {
            count += edges.size();
        }

        // If the graph is undirected, each edge is counted twice
        return isDirected ? count : count / 2;
    }
}

