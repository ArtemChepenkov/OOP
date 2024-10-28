package ru.nsu.chepenkov.graph;

import java.util.*;

public class Algorithm {

    public <T> void dfs(Graph<T> graph, Vertex<T> vertex,
                    Set<Vertex<T>> visited, List<Vertex<T>> res) {

        visited.add(vertex);
        for (Vertex<T> v : graph.getNeigbours(vertex)) {
            if (!visited.contains(v)) {
                dfs(graph, v, visited, res);
            }
        }
        res.add(vertex);
    }

    public <T> List<Vertex<T>> toposort(Graph<T> graph) {
        final Set<Vertex<T>> visited = new HashSet<>();
        List<Vertex<T>> res = new ArrayList<>();

        List<Vertex<T>> vertices = graph.getVertices();

        for (Vertex<T> vertex : vertices) {
            if (!visited.contains(vertex)) {
                dfs(graph, vertex, visited, res);
            }
        }
        Collections.reverse(res);
        return res;
    }


}