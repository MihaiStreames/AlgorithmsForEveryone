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
     *
     * @param graph The graph to check
     * @return true if the graph is 2-edge-connected, false otherwise
     */
    public <T> boolean isTwoEdgeConnected(Graph<T> graph) {
        Set<Vertex<T>> vertices = graph.getVertices();
        int n = vertices.size();

        // If the graph is empty or has only one vertex, it is 2-edge-connected
        if (n <= 1) {
            return true;
        }

        // Check if the graph is connected using the DFS class
        Vertex<T> startVertex = vertices.iterator().next();
        List<Vertex<T>> dfsResult = DFS.traverse(graph, startVertex);

        // If DFS didn't visit all vertices, the graph is not connected
        if (dfsResult.size() != n) {
            return false;
        }

        // Now check for bridges
        Map<Vertex<T>, Integer> disc = new HashMap<>();
        Map<Vertex<T>, Integer> low = new HashMap<>();
        Map<Vertex<T>, Boolean> visited = new HashMap<>();
        Map<Vertex<T>, Vertex<T>> parent = new HashMap<>();

        // Initialize
        for (Vertex<T> v : vertices) {
            visited.put(v, false);
            parent.put(v, null);
        }

        int time = 0;

        // Start bridge finding algorithm from each unvisited vertex
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
        // Mark the current node as visited
        visited.put(u, true);

        // Initialize discovery time and low value
        disc.put(u, time);
        low.put(u, time);
        time++;

        // Go through all vertices adjacent to this
        for (Vertex<T> v : graph.getAdjacentVertices(u)) {
            // If v is not visited yet, then make it a child of u in DFS tree
            if (!visited.get(v)) {
                parent.put(v, u);

                // Recursive call to process the child
                if (findBridges(graph, v, visited, disc, low, parent, time)) {
                    return true; // Bridge found in child's subtree
                }

                // Check if the subtree rooted with v has a connection to
                // one of the ancestors of u
                low.put(u, Math.min(low.get(u), low.get(v)));

                // If the lowest vertex reachable from subtree under v is below u in DFS tree,
                // then u-v is a bridge
                if (low.get(v) > disc.get(u)) {
                    return true; // Found a bridge
                }
            }
            // Update low value of u for parent function calls
            else if (!v.equals(parent.get(u))) {
                low.put(u, Math.min(low.get(u), disc.get(v)));
            }
        }

        return false; // No bridge found
    }

    /**
     * Example usage
     */
    public static void main(String[] args) {
        Exercise6_3 solution = new Exercise6_3();

        // Example 1: 2-edge-connected graph (cycle with 3 vertices)
        Graph<String> graph1 = new Graph<>(false); // undirected graph
        Vertex<String> a = graph1.addVertex("A");
        Vertex<String> b = graph1.addVertex("B");
        Vertex<String> c = graph1.addVertex("C");

        graph1.addEdge(a, b);
        graph1.addEdge(b, c);
        graph1.addEdge(c, a);

        System.out.println("Graph 1 is 2-edge-connected: " + solution.isTwoEdgeConnected(graph1));

        // Example 2: Graph with a bridge
        Graph<String> graph2 = new Graph<>(false); // undirected graph
        Vertex<String> d = graph2.addVertex("D");
        Vertex<String> e = graph2.addVertex("E");
        Vertex<String> f = graph2.addVertex("F");
        Vertex<String> g = graph2.addVertex("G");

        graph2.addEdge(d, e);
        graph2.addEdge(e, f); // this is a bridge
        graph2.addEdge(f, g);

        System.out.println("Graph 2 is 2-edge-connected: " + solution.isTwoEdgeConnected(graph2));

        // Example 3: Another 2-edge-connected graph
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