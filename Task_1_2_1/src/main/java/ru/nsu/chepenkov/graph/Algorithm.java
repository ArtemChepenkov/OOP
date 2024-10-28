package ru.nsu.chepenkov.graph;


import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Класс для описания дфса и топосорта.
 *
 * @author ArtemChepenkov
 */

public class Algorithm {

    /**
     * Функция для опсиания дфса.
     *
     * <p>Записывает результат в массив, который передаётся в аргументы
     */
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

    /**
     * Функция для топосорта.*/
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
