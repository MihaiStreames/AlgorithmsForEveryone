package io.github.mihaistreames.afe.algorithms.graphs;

import io.github.mihaistreames.afe.structs.graphs.Digraph;
import io.github.mihaistreames.afe.structs.stacks.Stack;
import org.jetbrains.annotations.NotNull;

import java.util.HashSet;
import java.util.Set;

public class DepthFirstOrder<T> {
    private final Set<T> marked;
    private final Stack<T> reversePost; // vertices in reverse postorder

    public DepthFirstOrder(@NotNull Digraph<T> G) {
        marked = new HashSet<>();
        reversePost = new Stack<>();
        for (T v : G.vertices()) {
            if (!marked.contains(v)) {
                dfs(G, v);
            }
        }
    }

    private void dfs(@NotNull Digraph<T> G, T v) {
        marked.add(v);
        for (var edge : G.adj(v)) {
            T w = edge.to();
            if (!marked.contains(w)) {
                dfs(G, w);
            }
        }
        reversePost.push(v);
    }

    public Iterable<T> reversePost() {
        return reversePost;
    }
}