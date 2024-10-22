package ru.nsu.chepenkov.graph;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

public class IncidenceList<T> implements Graph<T> {
    private final List<Vertex<T>> vertices = new ArrayList<>(); // Список вершин private final List<Edge<T>> edges; // Список рёбер public IncidenceList() {
    private final List<Edge<T>> edges = new ArrayList<>();


    @Override
    public void addVertex(Vertex<T> vertex) {
        vertices.add(vertex);
    }

    @Override
    public void delVertex(Vertex<T> vertex) throws NoSuchElementException {
        if (!vertices.contains(vertex)) {
            throw new NoSuchElementException("No such vertex");
        }

        // Удаляем все рёбра, связанные с удаляемой вершиной
        edges.removeIf(edge -> edge.getFrom().equals(vertex) || edge.getTo().equals(vertex));
        vertices.remove(vertex);
    }

    @Override
    public void addEdge(Edge<T> edge) throws NoSuchElementException {
        if (!vertices.contains(edge.getFrom()) || !vertices.contains(edge.getTo())) {
            throw new NoSuchElementException("No suitable vertices");
        }
        edges.add(edge);
    }

    @Override
    public void delEdge(Edge<T> edge) throws NoSuchElementException {
        if (!edges.contains(edge)) {
            throw new NoSuchElementException("No such edge");
        }
        edges.remove(edge);
    }

    @Override
    public List<Vertex<T>> getNeigbours(Vertex<T> vertex) throws NoSuchElementException {
        if (!vertices.contains(vertex)) {
            throw new NoSuchElementException("No such vertex");
        }

        List<Vertex<T>> neighbours = new ArrayList<>();
        for (Edge<T> edge : edges) {
            if (edge.getFrom().equals(vertex) && !neighbours.contains(edge.getTo())) {
                neighbours.add(edge.getTo());
            } else if (edge.getTo().equals(vertex) && !neighbours.contains(edge.getFrom())) {
                neighbours.add(edge.getFrom());
            }
        }
        return neighbours;
    }

    public List<Vertex<T>> getVertices() {
        return vertices;
    }

    public List<Edge<T>> getEdges() {
        return edges;
    }
}
