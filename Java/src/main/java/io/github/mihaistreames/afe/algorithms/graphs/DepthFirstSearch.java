package io.github.mihaistreames.afe.algorithms.graphs;

import io.github.mihaistreames.afe.structs.graphs.Graph;
import org.jetbrains.annotations.NotNull;

import java.util.*;

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
 *
 * @author Sincos Team
 * @version 1.0.0
 * @since 0.0.3
 */
public class DepthFirstSearch {

    private final boolean[] marked;    // Has vertex been visited?
    private final int[] edgeTo;        // Previous vertex on path to this vertex
    private final Graph graph;

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