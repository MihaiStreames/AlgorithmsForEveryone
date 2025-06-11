package io.github.mihaistreames.afe.algorithms.graphs;

import io.github.mihaistreames.afe.structs.graphs.Graph;
import org.jetbrains.annotations.NotNull;

import java.util.Arrays;
import java.util.Objects;
import java.util.Stack;

/**
 * Depth-First Search implementation for graphs.
 * <p>
 * DFS explores a graph by going as deep as possible along each branch before backtracking.
 * This implementation can find paths between vertices and determine reachability.
 * </p>
 * <p>
 * <strong>Time Complexity:</strong> O(V + E) where V is vertices and E is edges<br>
 * <strong>Space Complexity:</strong> O(V) for the recursion stack and data structures
 * </p>
 */
public class DepthFirstSearch {

    private final boolean[] marked;    // Has vertex been visited?
    private final int[] edgeTo;        // Previous vertex on path to this vertex
    private final Graph graph;
    private final int source;

    /**
     * Performs DFS from the specified source vertex.
     *
     * @param graph  the graph to search
     * @param source the source vertex to start DFS from
     * @throws NullPointerException     if graph is null
     * @throws IllegalArgumentException if source vertex is invalid
     */
    public DepthFirstSearch(@NotNull final Graph graph, final int source) {
        this.graph = Objects.requireNonNull(graph, "Graph cannot be null");
        this.source = source;

        // Validate source vertex
        if (source < 0 || source >= graph.V()) {
            throw new IllegalArgumentException("Source vertex " + source + " is not valid");
        }

        this.marked = new boolean[graph.V()];
        this.edgeTo = new int[graph.V()];

        // Initialize edgeTo array with -1 (no predecessor)
        Arrays.fill(edgeTo, -1);

        dfs(source);
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
     * Returns a path from the source vertex to the specified vertex.
     *
     * @param vertex the target vertex
     * @return an iterable of vertices representing a path, or null if no path exists
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

    // ========== PRIVATE IMPLEMENTATION ==========

    /**
     * Recursive DFS implementation.
     *
     * @param vertex the current vertex to explore
     */
    private void dfs(final int vertex) {
        marked[vertex] = true;

        for (final int adjacentVertex : graph.adj(vertex)) {
            if (!marked[adjacentVertex]) {
                edgeTo[adjacentVertex] = vertex;
                dfs(adjacentVertex);
            }
        }
    }
}