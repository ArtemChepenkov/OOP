package ru.nsu.chepenkov.graph;

import java.io.File;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Set;

public interface Graph<T> {

    void addVertex(Vertex<T> vertex);

    void delVertex(Vertex<T> vertex) throws NoSuchElementException;

    void addEdge(Edge<T> edge) throws NoSuchElementException;

    void delEdge(Edge<T> edge) throws NoSuchElementException;

    List<Vertex<T>> getNeigbours(Vertex<T> vertex) throws NoSuchElementException;

    void readFile(String path);
}
