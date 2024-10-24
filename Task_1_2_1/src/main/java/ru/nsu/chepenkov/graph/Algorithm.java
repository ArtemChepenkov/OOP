package ru.nsu.chepenkov.graph;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Algorithm<T> {

    public void dfs(Graph<T> graph, Vertex<T> vertex,
                    Set<Vertex<T>> visited, List<Vertex<T>> res) {

        visited.add(vertex);
        for (Vertex<T> v : graph.getNeigbours(vertex)) {
            if (!visited.contains(v)) {
                dfs(graph, v, visited, res);
            }
        }
        res.add(vertex);
    }

    public List<Vertex<T>> toposort(Graph<T> graph) {
        final Set<Vertex<T>> visited = new HashSet<>();
        final List<Vertex<T>> res = new ArrayList<>();

        List<Vertex<T>> vertices = graph.getVertices();

        for (Vertex<T> vertex : vertices) {
            if (!visited.contains(vertex)) {
                dfs(graph, vertex, visited, res);
            }
        }
        return res;
    }


}
