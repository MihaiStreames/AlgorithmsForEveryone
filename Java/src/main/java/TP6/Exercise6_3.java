package TP6;

import Algorithms.Graphs.DFS;
import DataStructs.Data.Vertex;
import DataStructs.Graphs.Graph;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class Exercise6_3 {
    /**
     * Determines if a graph is 2-edge-connected (has no bridges)
     * A 2-edge-connected graph remains connected even if any single edge is removed
     *
     * @param graph The graph to check
     * @return true if the graph is 2-edge-connected, false otherwise
     */
    public <T> boolean isTwoEdgeConnected(Graph<T> graph) {
        Set<Vertex<T>> vertices = graph.getVertices();
        int n = vertices.size();

        // If the graph is empty or has only one vertex, it is 2-edge-connected
        // (since removing any edge wouldn't disconnect it - there are no edges to remove)
        if (n <= 1) {
            return true;
        }

        // First, check if the graph is connected using regular DFS
        // If the graph isn't even connected to begin with, it can't be 2-edge-connected
        Vertex<T> startVertex = vertices.iterator().next();
        List<Vertex<T>> dfsResult = DFS.traverse(graph, startVertex);

        // If DFS didn't visit all vertices, the graph is not connected
        if (dfsResult.size() != n) {
            return false;
        }

        // Now check for bridges using 4 maps to track the DFS information:

        // 1. disc: When each vertex was first discovered (like a timestamp)
        Map<Vertex<T>, Integer> disc = new HashMap<>();

        // 2. low: The earliest discovered vertex reachable from this vertex's subtree
        //    (including back edges, but excluding the direct edge from its parent)
        Map<Vertex<T>, Integer> low = new HashMap<>();

        // 3. visited: Keeps track of which vertices we've already processed
        Map<Vertex<T>, Boolean> visited = new HashMap<>();

        // 4. parent: Keeps track of each vertex's parent in the DFS tree
        Map<Vertex<T>, Vertex<T>> parent = new HashMap<>();

        // Initialize all vertices as not visited and with no parent
        for (Vertex<T> v : vertices) {
            visited.put(v, false);
            parent.put(v, null);
        }

        int time = 0;

        // Start bridge finding algorithm from each unvisited vertex
        // (though if the graph is connected, we'll only need one iteration)
        for (Vertex<T> v : vertices) {
            if (!visited.get(v)) {
                if (findBridges(graph, v, visited, disc, low, parent, time)) {
                    return false; // Bridge found, not 2-edge-connected
                }
            }
        }

        return true; // No bridges found, graph is 2-edge-connected
    }

    /**
     * Modified DFS to find bridges in a graph
     *
     * @return true if a bridge is found, false otherwise
     */
    private <T> boolean findBridges(Graph<T> graph, Vertex<T> u,
                                    Map<Vertex<T>, Boolean> visited,
                                    Map<Vertex<T>, Integer> disc,
                                    Map<Vertex<T>, Integer> low,
                                    Map<Vertex<T>, Vertex<T>> parent,
                                    int time) {
        // Mark the current vertex as visited
        visited.put(u, true);

        // Initialize discovery time and low value for this vertex
        // At first, a vertex can only reach itself, so low = disc
        disc.put(u, time);
        low.put(u, time);
        time++;

        // Visit all neighbors of current vertex
        for (Vertex<T> v : graph.getAdjacentVertices(u)) {

            // If neighbor hasn't been visited yet
            if (!visited.get(v)) {
                // Make u the parent of v in our DFS tree
                parent.put(v, u);

                // Recursively process v and its subtree
                if (findBridges(graph, v, visited, disc, low, parent, time)) {
                    return true; // Bridge found in v's subtree
                }

                // After exploring v's subtree, update u's low value
                // This checks if v's subtree has a back edge to an ancestor of u
                low.put(u, Math.min(low.get(u), low.get(v)));

                // THE KEY CHECK: If the earliest vertex that v's subtree can reach
                // is still later than when u was discovered, then (u,v) is a bridge
                if (low.get(v) > disc.get(u)) {
                    return true; // Found a bridge! The edge (u,v) is a bridge
                }
            }
            // If v is already visited but is not u's parent (a back edge)
            else if (!v.equals(parent.get(u))) {
                // Update u's low value - it can reach at least as early as v was discovered
                // This is crucial for detecting cycles - it means there's an alternate path
                low.put(u, Math.min(low.get(u), disc.get(v)));
            }
        }

        return false; // No bridge found in this subtree
    }

    /**
     * Example usage
     */
    public static void main(String[] args) {
        Exercise6_3 solution = new Exercise6_3();

        // Example 1: 2-edge-connected graph (cycle with 3 vertices)
        // A --- B
        // |     |
        // |     |
        // C ----
        Graph<String> graph1 = new Graph<>(false); // undirected graph
        Vertex<String> a = graph1.addVertex("A");
        Vertex<String> b = graph1.addVertex("B");
        Vertex<String> c = graph1.addVertex("C");
        graph1.addEdge(a, b);
        graph1.addEdge(b, c);
        graph1.addEdge(c, a);
        System.out.println("Graph 1 is 2-edge-connected: " + solution.isTwoEdgeConnected(graph1));

        // Example 2: Graph with a bridge
        // D --- E --- F --- G
        //           ↑
        //         bridge
        Graph<String> graph2 = new Graph<>(false); // undirected graph
        Vertex<String> d = graph2.addVertex("D");
        Vertex<String> e = graph2.addVertex("E");
        Vertex<String> f = graph2.addVertex("F");
        Vertex<String> g = graph2.addVertex("G");
        graph2.addEdge(d, e);
        graph2.addEdge(e, f); // this is a bridge
        graph2.addEdge(f, g);
        System.out.println("Graph 2 is 2-edge-connected: " + solution.isTwoEdgeConnected(graph2));

        // Example 3: Another 2-edge-connected graph (a square)
        // H --- I
        // |     |
        // |     |
        // K --- J
        Graph<String> graph3 = new Graph<>(false);
        Vertex<String> h = graph3.addVertex("H");
        Vertex<String> i = graph3.addVertex("I");
        Vertex<String> j = graph3.addVertex("J");
        Vertex<String> k = graph3.addVertex("K");
        graph3.addEdge(h, i);
        graph3.addEdge(i, j);
        graph3.addEdge(j, k);
        graph3.addEdge(k, h);
        System.out.println("Graph 3 is 2-edge-connected: " + solution.isTwoEdgeConnected(graph3));
    }
}