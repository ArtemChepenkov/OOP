package ru.nsu.chepenkov.graph;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Objects;

public class IncidenceMatrix<T> implements Graph<T> {
    List<List<Integer>> matrix;
    List<Vertex<T>> vertices;
    List<Edge<T>> edges;

    public IncidenceMatrix() {
        matrix = new ArrayList<>();
        vertices = new ArrayList<>();
        edges = new ArrayList<>();
    }

    @Override
    public void addVertex(Vertex<T> vertex) {
        vertices.add(vertex);
        List<Integer> newArr = new ArrayList<>();
        for (int i = 0; i < edges.size(); i++) {
            newArr.add(0);
        }
        matrix.add(newArr);
    }

    @Override
    public void delVertex(Vertex<T> vertex) {
        Vertex<T> v = vertices.stream().filter(ver -> ver.equals(vertex)).findAny().orElse(null);
        int idx = vertices.indexOf(v);
        matrix.remove(idx);
        vertices.remove(vertex);
    }

    @Override
    public void addEdge(Edge<T> edge) {
        edges.add(edge);
        for (int i = 0; i < vertices.size(); i++) {
            Vertex<T> vertex = vertices.get(i);
            if (vertex.equals(edge.getFrom()) || vertex.equals(edge.getTo())) {
                matrix.get(i).add(edge.getWeight());
            } else {
                matrix.get(i).add(0);
            }
        }
    }

    @Override
    public void delEdge(Edge<T> edge) {
        Edge<T> e = edges.stream().filter(ver -> ver.equals(edge)).findAny().orElse(null);
        int idx = edges.indexOf(e);
        for (int j = 0; j < vertices.size(); j++) {
            matrix.get(j).remove(idx);
        }
        edges.remove(edge);
    }

    @Override
    public List<Vertex<T>> getNeigbours(Vertex<T> vertex) {
        List<Vertex<T>> neighbours = new ArrayList<>();
        int size;
        int index = vertices.indexOf(vertex);
        if (index == -1) {
            throw new NoSuchElementException("No such vertex");
        }
        size = matrix.get(index).size();
        for (int i = 0; i < size; i++) {
            if (matrix.get(index).get(i) > 0) {
                neighbours.add(edges.get(i).getTo());
            }
        }
        return neighbours;
    }

    @Override
    public List<Vertex<T>> getVertices() {
        return vertices;
    }

    public List<Edge<T>> getEdges() {
        return edges;
    }
}
