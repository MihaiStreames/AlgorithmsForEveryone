package org.sincos.afe.old.Graphs;

import org.sincos.afe.old.structs.Data.Vertex;
import org.sincos.afe.old.structs.Graphs.Graph;

import java.util.*;

/**
 * Implementation of Breadth-First Search algorithm for graphs.
 */
public class BFS {

    /**
     * Performs a breadth-first search traversal starting from a vertex.
     *
     * @param <T>   the type of data stored in the vertices
     * @param graph the graph to traverse
     * @param start the starting vertex
     * @return list of vertices in BFS order
     */
    public static <T> List<Vertex<T>> traverse(Graph<T> graph, Vertex<T> start) {
        List<Vertex<T>> result = new ArrayList<>();

        // Return empty list if the start vertex is not in the graph
        if (!graph.containsVertex(start)) {
            return result;
        }

        Queue<Vertex<T>> queue = new LinkedList<>();
        Set<Vertex<T>> visited = new HashSet<>();

        queue.offer(start);
        visited.add(start);

        while (!queue.isEmpty()) {
            Vertex<T> current = queue.poll();
            result.add(current);

            for (Vertex<T> neighbor : graph.getAdjacentVertices(current)) {
                if (!visited.contains(neighbor)) {
                    visited.add(neighbor);
                    queue.offer(neighbor);
                }
            }
        }

        return result;
    }

    /**
     * Checks if there is a path between two vertices using BFS.
     *
     * @param <T>   the type of data stored in the vertices
     * @param graph the graph to search in
     * @param start the starting vertex
     * @param end   the ending vertex
     * @return true if a path exists, false otherwise
     */
    public static <T> boolean hasPath(Graph<T> graph, Vertex<T> start, Vertex<T> end) {
        // Return false if either vertex is not in the graph
        if (!graph.containsVertex(start) || !graph.containsVertex(end)) {
            return false;
        }

        // Return true if start and end are the same vertex
        if (start.equals(end)) {
            return true;
        }

        Queue<Vertex<T>> queue = new LinkedList<>();
        Set<Vertex<T>> visited = new HashSet<>();

        queue.offer(start);
        visited.add(start);

        while (!queue.isEmpty()) {
            Vertex<T> current = queue.poll();

            for (Vertex<T> neighbor : graph.getAdjacentVertices(current)) {
                if (neighbor.equals(end)) {
                    return true;
                }

                if (!visited.contains(neighbor)) {
                    visited.add(neighbor);
                    queue.offer(neighbor);
                }
            }
        }

        return false;
    }

    /**
     * Finds the shortest path between two vertices using BFS.
     *
     * @param <T>   the type of data stored in the vertices
     * @param graph the graph to search in
     * @param start the starting vertex
     * @param end   the ending vertex
     * @return list of vertices representing the shortest path, or empty list if no path exists
     */
    public static <T> List<Vertex<T>> shortestPath(Graph<T> graph, Vertex<T> start, Vertex<T> end) {
        // Return empty list if either vertex is not in the graph
        if (!graph.containsVertex(start) || !graph.containsVertex(end)) {
            return new ArrayList<>();
        }

        // Return list with just the start vertex if start and end are the same
        if (start.equals(end)) {
            List<Vertex<T>> path = new ArrayList<>();
            path.add(start);
            return path;
        }

        Queue<Vertex<T>> queue = new LinkedList<>();
        Map<Vertex<T>, Vertex<T>> parentMap = new HashMap<>();
        Set<Vertex<T>> visited = new HashSet<>();

        queue.offer(start);
        visited.add(start);

        boolean found = false;

        // BFS to find the shortest path
        while (!queue.isEmpty() && !found) {
            Vertex<T> current = queue.poll();

            for (Vertex<T> neighbor : graph.getAdjacentVertices(current)) {
                if (!visited.contains(neighbor)) {
                    visited.add(neighbor);
                    parentMap.put(neighbor, current);
                    queue.offer(neighbor);

                    if (neighbor.equals(end)) {
                        found = true;
                        break;
                    }
                }
            }
        }

        // Reconstruct the path if one exists
        if (!found) {
            return new ArrayList<>();
        }

        List<Vertex<T>> path = new ArrayList<>();
        for (Vertex<T> at = end; at != null; at = parentMap.get(at)) {
            path.add(at);
        }

        Collections.reverse(path);
        return path;
    }
}