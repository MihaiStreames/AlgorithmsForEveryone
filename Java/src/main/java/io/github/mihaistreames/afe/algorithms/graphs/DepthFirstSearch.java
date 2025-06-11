package io.github.mihaistreames.afe.algorithms.graphs;

import io.github.mihaistreames.afe.structs.graphs.Graph;
import org.jetbrains.annotations.NotNull;

import java.util.HashMap;
import java.util.Map;
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
 *
 * @param <T> The type of the vertices.
 */
public class DepthFirstSearch<T> {

    private final Map<T, Boolean> marked;
    private final Map<T, T> edgeTo;
    private final T source;

    /**
     * Performs DFS from a source vertex.
     *
     * @param graph  The graph to search.
     * @param source The source vertex.
     */
    public DepthFirstSearch(@NotNull final Graph<T> graph, final T source) {
        this.marked = new HashMap<>();
        this.edgeTo = new HashMap<>();
        this.source = source;

        for (T vertex : graph.vertices()) {
            marked.put(vertex, false);
        }

        dfs(graph, source);
    }

    // ========== PUBLIC API ==========

    /**
     * Is there a path from the source to the given vertex?
     *
     * @param v The vertex.
     * @return true if there is a path, false otherwise.
     */
    public boolean hasPathTo(T v) {
        return marked.getOrDefault(v, false);
    }

    /**
     * Returns the path from the source to the given vertex.
     *
     * @param v The vertex.
     * @return An iterable of the path, or null if no path exists.
     */
    public Iterable<T> pathTo(T v) {
        if (!hasPathTo(v)) return null;

        Stack<T> path = new Stack<>();
        for (T x = v; x != null && !x.equals(source); x = edgeTo.get(x)) {
            path.push(x);
        }
        path.push(source);

        return path;
    }

    // ========== PRIVATE IMPLEMENTATION ==========

    private void dfs(@NotNull Graph<T> graph, T v) {
        marked.put(v, true);
        if (graph.adj(v) == null) return; // Handle disconnected vertices

        for (T w : graph.adj(v)) {
            if (!marked.get(w)) {
                edgeTo.put(w, v);
                dfs(graph, w);
            }
        }
    }
}