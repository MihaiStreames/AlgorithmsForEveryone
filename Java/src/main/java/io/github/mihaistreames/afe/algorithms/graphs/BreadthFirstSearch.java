package io.github.mihaistreames.afe.algorithms.graphs;

import io.github.mihaistreames.afe.structs.graphs.Graph;
import io.github.mihaistreames.afe.structs.queues.Queue;
import org.jetbrains.annotations.NotNull;

import java.util.Arrays;
import java.util.Objects;
import java.util.Stack;

/**
 * Breadth-First Search implementation for graphs.
 * <p>
 * BFS explores a graph by visiting all vertices at distance k from the source
 * before visiting vertices at distance k+1. This implementation can find shortest
 * paths (in terms of number of edges) between vertices in unweighted graphs.
 * </p>
 * <p>
 * <strong>Time Complexity:</strong> O(V + E) where V is vertices and E is edges<br>
 * <strong>Space Complexity:</strong> O(V) for the queue and data structures
 * </p>
 */
public class BreadthFirstSearch {

    private final boolean[] marked;    // Has vertex been visited?
    private final int[] edgeTo;        // Previous vertex on path to this vertex
    private final int[] distTo;        // Distance from source to this vertex
    private final Graph graph;
    private final int source;

    /**
     * Performs BFS from the specified source vertex.
     *
     * @param graph  the graph to search
     * @param source the source vertex to start BFS from
     * @throws NullPointerException     if graph is null
     * @throws IllegalArgumentException if source vertex is invalid
     */
    public BreadthFirstSearch(@NotNull final Graph graph, final int source) {
        this.graph = Objects.requireNonNull(graph, "Graph cannot be null");
        this.source = source;

        // Validate source vertex
        if (source < 0 || source >= graph.V()) {
            throw new IllegalArgumentException("Source vertex " + source + " is not valid");
        }

        this.marked = new boolean[graph.V()];
        this.edgeTo = new int[graph.V()];
        this.distTo = new int[graph.V()];

        // Initialize arrays
        Arrays.fill(edgeTo, -1);
        Arrays.fill(distTo, Integer.MAX_VALUE);

        bfs(source);
    }

    // ========== PUBLIC API ==========

    /**
     * Checks if there is a path from the source vertex to the specified vertex.
     *
     * @param vertex the vertex to check reachability for
     * @return true if there is a path from source to vertex, false otherwise
     * @throws IllegalArgumentException if vertex is not valid
     */
    public boolean hasPathTo(final int vertex) {
        if (vertex < 0 || vertex >= graph.V()) {
            throw new IllegalArgumentException("Vertex " + vertex + " is not valid");
        }
        return marked[vertex];
    }

    /**
     * Returns the shortest path from the source vertex to the specified vertex.
     *
     * @param vertex the target vertex
     * @return an iterable of vertices representing the shortest path, or null if no path exists
     * @throws IllegalArgumentException if vertex is not valid
     */
    public Iterable<Integer> pathTo(final int vertex) {
        if (!hasPathTo(vertex)) return null;

        final Stack<Integer> path = new Stack<>();
        for (int x = vertex; x != source; x = edgeTo[x]) {
            path.push(x);
        }
        path.push(source);

        return path;
    }

    /**
     * Returns the distance (number of edges) from the source vertex to the specified vertex.
     *
     * @param vertex the target vertex
     * @return the distance from source to vertex, or Integer.MAX_VALUE if no path exists
     * @throws IllegalArgumentException if vertex is not valid
     */
    public int distTo(final int vertex) {
        if (vertex < 0 || vertex >= graph.V()) {
            throw new IllegalArgumentException("Vertex " + vertex + " is not valid");
        }
        return distTo[vertex];
    }

    // ========== PRIVATE IMPLEMENTATION ==========

    /**
     * BFS implementation using a queue.
     *
     * @param source the source vertex to start BFS from
     */
    private void bfs(final int source) {
        final Queue<Integer> queue = new Queue<>();

        marked[source] = true;
        distTo[source] = 0;
        queue.enqueue(source);

        while (!queue.isEmpty()) {
            final int vertex = queue.dequeue();

            for (final int adjacentVertex : graph.adj(vertex)) {
                if (!marked[adjacentVertex]) {
                    marked[adjacentVertex] = true;
                    edgeTo[adjacentVertex] = vertex;
                    distTo[adjacentVertex] = distTo[vertex] + 1;
                    queue.enqueue(adjacentVertex);
                }
            }
        }
    }
}