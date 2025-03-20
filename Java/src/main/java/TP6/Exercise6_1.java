package TP6;

import DataStructs.Data.Vertex;
import DataStructs.Graphs.Graph;

import java.util.*;

public class Exercise6_1 {

    /**
     * 1. Tests if an undirected graph is acyclic.
     * An undirected graph is acyclic if and only if it's a forest (a collection of trees).
     * <p>
     * Time complexity: O(V + E) where V is the number of vertices and E is the number of edges
     *
     * @param graph the graph to check
     * @return true if the graph is acyclic, false otherwise
     */
    public static <T> boolean isAcyclic(Graph<T> graph) {
        if (graph.isDirected()) {
            throw new IllegalArgumentException("This method only works for undirected graphs");
        }

        Set<Vertex<T>> vertices = graph.getVertices();
        Map<Vertex<T>, Boolean> visited = new HashMap<>();

        // Initialize all vertices as not visited
        for (Vertex<T> vertex : vertices) {
            visited.put(vertex, false);
        }

        // Check each component of the graph
        for (Vertex<T> vertex : vertices) {
            if (!visited.get(vertex)) {
                if (hasCycleDFS(graph, vertex, null, visited)) {
                    return false;
                }
            }
        }

        return true;
    }

    private static <T> boolean hasCycleDFS(Graph<T> graph, Vertex<T> current, Vertex<T> parent,
                                           Map<Vertex<T>, Boolean> visited) {
        visited.put(current, true);

        for (Vertex<T> neighbor : graph.getAdjacentVertices(current)) {
            // If neighbor is not visited, then continue DFS
            if (!visited.get(neighbor)) {
                if (hasCycleDFS(graph, neighbor, current, visited)) {
                    return true;
                }
            }
            // If neighbor is visited and not the parent, then we found a cycle
            else if (parent != null && !neighbor.equals(parent)) {
                return true;
            }
        }

        return false;
    }

    /**
     * 2. Tests if an undirected graph is bipartite.
     * A graph is bipartite if its vertices can be divided into two disjoint sets such that
     * every edge connects a vertex in one set to a vertex in the other set.
     * <p>
     * Time complexity: O(V + E) where V is the number of vertices and E is the number of edges
     *
     * @param graph the graph to check
     * @return true if the graph is bipartite, false otherwise
     */
    public static <T> boolean isBipartite(Graph<T> graph) {
        if (graph.isDirected()) {
            throw new IllegalArgumentException("This method only works for undirected graphs");
        }

        Set<Vertex<T>> vertices = graph.getVertices();
        Map<Vertex<T>, Integer> colors = new HashMap<>();

        // Initialize all vertices as not colored (0)
        for (Vertex<T> vertex : vertices) {
            colors.put(vertex, 0); // 0: not colored, 1: color 1, -1: color 2
        }

        // Check each component of the graph
        for (Vertex<T> vertex : vertices) {
            if (colors.get(vertex) == 0) { // Not colored yet
                if (!colorBFS(graph, vertex, colors)) {
                    return false;
                }
            }
        }

        return true;
    }

    private static <T> boolean colorBFS(Graph<T> graph, Vertex<T> start, Map<Vertex<T>, Integer> colors) {
        Queue<Vertex<T>> queue = new LinkedList<>();
        queue.offer(start);
        colors.put(start, 1); // Color the start vertex

        while (!queue.isEmpty()) {
            Vertex<T> current = queue.poll();

            for (Vertex<T> neighbor : graph.getAdjacentVertices(current)) {
                if (colors.get(neighbor) == 0) { // Not colored yet
                    // Color with the opposite color
                    colors.put(neighbor, -colors.get(current));
                    queue.offer(neighbor);
                } else if (colors.get(neighbor).equals(colors.get(current))) {
                    // Same color for adjacent vertices means not bipartite
                    return false;
                }
            }
        }

        return true;
    }

    /**
     * 3. Tests if an undirected graph has an Eulerian cycle.
     * A graph has an Eulerian cycle if and only if:
     * - All vertices have even degree
     * - All vertices with non-zero degree are connected
     * <p>
     * Time complexity: O(V + E) where V is the number of vertices and E is the number of edges
     *
     * @param graph the graph to check
     * @return true if the graph has an Eulerian cycle, false otherwise
     */
    public static <T> boolean hasEulerianCycle(Graph<T> graph) {
        if (graph.isDirected()) {
            throw new IllegalArgumentException("This method only works for undirected graphs");
        }

        Set<Vertex<T>> vertices = graph.getVertices();

        // Check if all vertices have even degree
        for (Vertex<T> vertex : vertices) {
            int degree = graph.getAdjacentVertices(vertex).size();
            if (degree % 2 != 0) {
                return false;
            }
        }

        // Find a vertex with non-zero degree
        Vertex<T> startVertex = null;
        for (Vertex<T> vertex : vertices) {
            if (!graph.getAdjacentVertices(vertex).isEmpty()) {
                startVertex = vertex;
                break;
            }
        }

        // If graph is empty or has only isolated vertices
        if (startVertex == null) {
            return true;
        }

        // Check if all non-isolated vertices are connected using DFS
        Map<Vertex<T>, Boolean> visited = new HashMap<>();
        for (Vertex<T> vertex : vertices) {
            visited.put(vertex, false);
        }

        dfsConnected(graph, startVertex, visited);

        // Check if all vertices with non-zero degree are visited
        for (Vertex<T> vertex : vertices) {
            if (!graph.getAdjacentVertices(vertex).isEmpty() && !visited.get(vertex)) {
                return false;
            }
        }

        return true;
    }

    private static <T> void dfsConnected(Graph<T> graph, Vertex<T> current, Map<Vertex<T>, Boolean> visited) {
        visited.put(current, true);
        for (Vertex<T> neighbor : graph.getAdjacentVertices(current)) {
            if (!visited.get(neighbor)) {
                dfsConnected(graph, neighbor, visited);
            }
        }
    }

    /**
     * Main method to demonstrate the algorithms.
     */
    public static void main(String[] args) {
        // Example usage

        // Create a tree (acyclic)
        Graph<String> tree = new Graph<>(false); // Undirected graph
        Vertex<String> v0 = tree.addVertex("0");
        Vertex<String> v1 = tree.addVertex("1");
        Vertex<String> v2 = tree.addVertex("2");
        Vertex<String> v3 = tree.addVertex("3");
        Vertex<String> v4 = tree.addVertex("4");

        tree.addEdge(v0, v1);
        tree.addEdge(v0, v2);
        tree.addEdge(v1, v3);
        tree.addEdge(v1, v4);

        System.out.println("Tree is acyclic: " + isAcyclic(tree));
        System.out.println("Tree is bipartite: " + isBipartite(tree));
        System.out.println("Tree has Eulerian cycle: " + hasEulerianCycle(tree));

        System.out.println();

        // Create a cycle (not acyclic)
        Graph<String> cycle = new Graph<>(false); // Undirected graph
        Vertex<String> c0 = cycle.addVertex("0");
        Vertex<String> c1 = cycle.addVertex("1");
        Vertex<String> c2 = cycle.addVertex("2");
        Vertex<String> c3 = cycle.addVertex("3");

        cycle.addEdge(c0, c1);
        cycle.addEdge(c1, c2);
        cycle.addEdge(c2, c3);
        cycle.addEdge(c3, c0);

        System.out.println("Cycle is acyclic: " + isAcyclic(cycle));
        System.out.println("Cycle is bipartite: " + isBipartite(cycle));
        System.out.println("Cycle has Eulerian cycle: " + hasEulerianCycle(cycle));

        System.out.println();

        // Create a complete graph K4 (not bipartite)
        Graph<String> k4 = new Graph<>(false); // Undirected graph
        Vertex<String> k0 = k4.addVertex("0");
        Vertex<String> k1 = k4.addVertex("1");
        Vertex<String> k2 = k4.addVertex("2");
        Vertex<String> k3 = k4.addVertex("3");

        k4.addEdge(k0, k1);
        k4.addEdge(k0, k2);
        k4.addEdge(k0, k3);
        k4.addEdge(k1, k2);
        k4.addEdge(k1, k3);
        k4.addEdge(k2, k3);

        System.out.println("K4 is acyclic: " + isAcyclic(k4));
        System.out.println("K4 is bipartite: " + isBipartite(k4));
        System.out.println("K4 has Eulerian cycle: " + hasEulerianCycle(k4));
    }
}