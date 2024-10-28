package ru.nsu.chepenkov.graph;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;

/**
 * Класс для описания списка смежности.
 *
 * @author ArtemChepenkov
 */
public class AdjacencyList<T> implements Graph<T> {
    private final Map<Vertex<T>, List<Edge<T>>> adjacencyList;
    private final List<Vertex<T>> vertices;

    AdjacencyList() {
        adjacencyList = new HashMap<>();
        vertices = new ArrayList<>();
    }

    @Override
    public void addVertex(Vertex<T> vertex) {
        vertices.add(vertex);
        adjacencyList.putIfAbsent(vertex, new ArrayList<>());
    }

    @Override
    public void delVertex(Vertex<T> vertex) throws NoSuchElementException {
        if (!vertices.contains(vertex)) {
            throw new NoSuchElementException("No such vertex");
        }

        vertices.remove(vertex);
        adjacencyList.remove(vertex);

        for (List<Edge<T>> edges : adjacencyList.values()) {
            edges.removeIf(edge -> edge.getTo().equals(vertex));
        }
    }

    @Override
    public void addEdge(Edge<T> edge) throws NoSuchElementException {
        if (!vertices.contains(edge.getFrom()) || !vertices.contains(edge.getTo())) {
            throw new NoSuchElementException("No suitable vertices");
        }

        adjacencyList.get(edge.getFrom()).add(edge);
    }

    @Override
    public void delEdge(Edge<T> edge) throws NoSuchElementException {
        if (!vertices.contains(edge.getFrom()) || !vertices.contains(edge.getTo())) {
            throw new NoSuchElementException("No suitable vertices");
        }

        List<Edge<T>> edges = adjacencyList.get(edge.getFrom());
        edges.remove(edge);
    }

    @Override
    public List<Vertex<T>> getNeigbours(Vertex<T> vertex) throws NoSuchElementException {
        if (!vertices.contains(vertex)) {
            throw new NoSuchElementException("No such vertex");
        }

        List<Vertex<T>> result = new ArrayList<>();
        for (Edge<T> edge : adjacencyList.get(vertex)) {
            result.add(edge.getTo());
        }

        return result;
    }

    @Override
    public List<Vertex<T>> getVertices() {
        return vertices;
    }

    public Map<Vertex<T>, List<Edge<T>>> getAdjacencyList() {
        return adjacencyList;
    }
}
