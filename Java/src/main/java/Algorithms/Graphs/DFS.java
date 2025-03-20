package Algorithms.Graphs;

import DataStructs.Data.Vertex;
import DataStructs.Graphs.Graph;

import java.util.*;

/**
 * Implementation of Depth-First Search algorithm for graphs.
 */
public class DFS {

    /**
     * Performs a depth-first search traversal starting from a vertex.
     *
     * @param <T>   the type of data stored in the vertices
     * @param graph the graph to traverse
     * @param start the starting vertex
     * @return list of vertices in DFS order
     */
    public static <T> List<Vertex<T>> traverse(Graph<T> graph, Vertex<T> start) {
        List<Vertex<T>> result = new ArrayList<>();

        // Return empty list if the start vertex is not in the graph
        if (!graph.containsVertex(start)) {
            return result;
        }

        Set<Vertex<T>> visited = new HashSet<>();
        dfsRecursive(graph, start, visited, result);

        return result;
    }

    private static <T> void dfsRecursive(Graph<T> graph, Vertex<T> current,
                                         Set<Vertex<T>> visited, List<Vertex<T>> result) {
        visited.add(current);
        result.add(current);

        for (Vertex<T> neighbor : graph.getAdjacentVertices(current)) {
            if (!visited.contains(neighbor)) {
                dfsRecursive(graph, neighbor, visited, result);
            }
        }
    }

    /**
     * Performs an iterative depth-first search traversal starting from a vertex.
     *
     * @param <T>   the type of data stored in the vertices
     * @param graph the graph to traverse
     * @param start the starting vertex
     * @return list of vertices in DFS order
     */
    public static <T> List<Vertex<T>> traverseIterative(Graph<T> graph, Vertex<T> start) {
        List<Vertex<T>> result = new ArrayList<>();

        // Return empty list if the start vertex is not in the graph
        if (!graph.containsVertex(start)) {
            return result;
        }

        Stack<Vertex<T>> stack = new Stack<>();
        Set<Vertex<T>> visited = new HashSet<>();

        stack.push(start);

        while (!stack.isEmpty()) {
            Vertex<T> current = stack.pop();

            if (!visited.contains(current)) {
                visited.add(current);
                result.add(current);

                // Add neighbors in reverse order to maintain similar traversal order to recursive approach
                List<Vertex<T>> neighbors = new ArrayList<>(graph.getAdjacentVertices(current));
                for (int i = neighbors.size() - 1; i >= 0; i--) {
                    Vertex<T> neighbor = neighbors.get(i);
                    if (!visited.contains(neighbor)) {
                        stack.push(neighbor);
                    }
                }
            }
        }

        return result;
    }

    /**
     * Checks if the graph contains cycles using DFS.
     *
     * @param <T>   the type of data stored in the vertices
     * @param graph the graph to check
     * @return true if the graph contains a cycle, false otherwise
     */
    public static <T> boolean hasCycle(Graph<T> graph) {
        Set<Vertex<T>> visited = new HashSet<>();
        Set<Vertex<T>> recursionStack = new HashSet<>();

        for (Vertex<T> vertex : graph.getVertices()) {
            if (!visited.contains(vertex)) {
                if (hasCycleDFS(graph, vertex, visited, recursionStack)) {
                    return true;
                }
            }
        }

        return false;
    }

    private static <T> boolean hasCycleDFS(Graph<T> graph, Vertex<T> current,
                                           Set<Vertex<T>> visited, Set<Vertex<T>> recursionStack) {
        visited.add(current);
        recursionStack.add(current);

        for (Vertex<T> neighbor : graph.getAdjacentVertices(current)) {
            if (!visited.contains(neighbor)) {
                if (hasCycleDFS(graph, neighbor, visited, recursionStack)) {
                    return true;
                }
            } else if (recursionStack.contains(neighbor)) {
                return true;
            }
        }

        recursionStack.remove(current);
        return false;
    }

    /**
     * Performs a topological sort on a directed acyclic graph (DAG).
     *
     * @param <T>   the type of data stored in the vertices
     * @param graph the graph to sort
     * @return list of vertices in topological order, or empty list if the graph is not a DAG
     */
    public static <T> List<Vertex<T>> topologicalSort(Graph<T> graph) {
        // Check if the graph has a cycle
        if (hasCycle(graph)) {
            return new ArrayList<>();  // Cannot perform topological sort on cyclic graph
        }

        LinkedList<Vertex<T>> result = new LinkedList<>();
        Set<Vertex<T>> visited = new HashSet<>();

        for (Vertex<T> vertex : graph.getVertices()) {
            if (!visited.contains(vertex)) {
                topologicalSortDFS(graph, vertex, visited, result);
            }
        }

        return result;
    }

    private static <T> void topologicalSortDFS(Graph<T> graph, Vertex<T> current,
                                               Set<Vertex<T>> visited, LinkedList<Vertex<T>> result) {
        visited.add(current);

        for (Vertex<T> neighbor : graph.getAdjacentVertices(current)) {
            if (!visited.contains(neighbor)) {
                topologicalSortDFS(graph, neighbor, visited, result);
            }
        }

        // Add vertex to the front of the list after visiting all its neighbors
        result.addFirst(current);
    }
}