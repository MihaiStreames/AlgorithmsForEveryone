package io.github.mihaistreames.afe.algorithms.graphs;

import io.github.mihaistreames.afe.structs.graphs.Graph;
import io.github.mihaistreames.afe.structs.queues.Queue;
import org.jetbrains.annotations.NotNull;

import java.util.HashMap;
import java.util.Map;
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
 *
 * @param <T> The type of the vertices.
 */
public class BreadthFirstSearch<T> {

    private final Map<T, Boolean> marked;
    private final Map<T, T> edgeTo;
    private final Map<T, Integer> distTo;
    private final T source;

    /**
     * Performs BFS from a source vertex.
     *
     * @param graph  The graph to search.
     * @param source The source vertex.
     */
    public BreadthFirstSearch(@NotNull final Graph<T> graph, final T source) {
        this.marked = new HashMap<>();
        this.edgeTo = new HashMap<>();
        this.distTo = new HashMap<>();
        this.source = source;

        for (T vertex : graph.vertices()) {
            marked.put(vertex, false);
            distTo.put(vertex, Integer.MAX_VALUE);
        }

        bfs(graph, source);
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
        for (T x = v; !x.equals(source); x = edgeTo.get(x)) {
            path.push(x);
        }
        path.push(source);

        return path;
    }

    /**
     * Returns the distance from the source to the given vertex.
     *
     * @param v The vertex.
     * @return The distance as an integer, or -1 if no path exists.
     */
    public int distTo(T v) {
        if (!hasPathTo(v)) return -1;
        return distTo.getOrDefault(v, -1);
    }

    // ========== PRIVATE IMPLEMENTATION ==========

    private void bfs(Graph<T> graph, T s) {
        Queue<T> queue = new Queue<>();
        marked.put(s, true);
        distTo.put(s, 0);
        queue.enqueue(s);

        while (!queue.isEmpty()) {
            T v = queue.dequeue();
            if (graph.adj(v) == null) continue; // Handle disconnected vertices
            for (T w : graph.adj(v)) {
                if (!marked.get(w)) {
                    edgeTo.put(w, v);
                    marked.put(w, true);
                    distTo.put(w, distTo.get(v) + 1);
                    queue.enqueue(w);
                }
            }
        }
    }
}