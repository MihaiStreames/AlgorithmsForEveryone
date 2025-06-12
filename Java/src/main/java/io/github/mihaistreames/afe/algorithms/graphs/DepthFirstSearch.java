package io.github.mihaistreames.afe.algorithms.graphs;

import io.github.mihaistreames.afe.structs.graphs.Edge;
import io.github.mihaistreames.afe.structs.graphs.Graph;
import org.jetbrains.annotations.NotNull;

import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

/**
 * Finds paths in a graph using the Depth-First Search algorithm.
 * <p>
 * Time Complexity: O(V + E) <br>
 * Space Complexity: O(V)
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

    /**
     * Checks if there is a path from the source to the given vertex.
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

    private void dfs(@NotNull Graph<T> graph, T v) {
        marked.put(v, true);

        for (Edge<T> edge : graph.adj(v)) {
            T w = edge.other(v); // Get the other vertex from the edge
            if (!marked.get(w)) {
                edgeTo.put(w, v);
                dfs(graph, w);
            }
        }
    }
}