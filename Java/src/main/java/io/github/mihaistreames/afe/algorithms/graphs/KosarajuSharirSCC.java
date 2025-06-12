package io.github.mihaistreames.afe.algorithms.graphs;

import io.github.mihaistreames.afe.structs.graphs.Digraph;
import org.jetbrains.annotations.NotNull;

import java.util.HashMap;
import java.util.Map;

public class KosarajuSharirSCC<T> {
    private final Map<T, Boolean> marked;
    private final Map<T, Integer> id; // id of strong component containing v
    private int count; // number of strong components

    public KosarajuSharirSCC(@NotNull Digraph<T> G) {
        marked = new HashMap<>();
        id = new HashMap<>();
        // 1. Compute reverse graph
        Digraph<T> reversedG = G.reverse();

        // 2. Run DFS on reverse graph to get reverse postorder
        DepthFirstOrder<T> order = new DepthFirstOrder<>(reversedG);

        // 3. Run DFS on original graph in reverse postorder
        for (T v : order.reversePost()) {
            if (!marked.getOrDefault(v, false)) {
                dfs(G, v);
                count++;
            }
        }
    }

    private void dfs(@NotNull Digraph<T> G, T v) {
        marked.put(v, true);
        id.put(v, count);
        for (var edge : G.adj(v)) {
            T w = edge.to();
            if (!marked.getOrDefault(w, false)) {
                dfs(G, w);
            }
        }
    }

    public int count() {
        return count;
    }

    public boolean stronglyConnected(T v, T w) {
        return id.getOrDefault(v, -1).equals(id.getOrDefault(w, -1));
    }

    public int id(T v) {
        return id.getOrDefault(v, -1);
    }
}