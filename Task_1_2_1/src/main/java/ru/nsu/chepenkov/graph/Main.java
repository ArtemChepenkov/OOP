package ru.nsu.chepenkov.graph;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        AdjacencyMatrix<Object> adjacencyMatrix = new AdjacencyMatrix<>();
        //try use getresources
        adjacencyMatrix.readFile("src/main/resources/test.txt");
        List<Vertex<Object>> toposort = adjacencyMatrix.topoSort();

        for (int i = 0; i < toposort.size(); i++) {
            System.out.print(toposort.get(i).toString() + " ");
        }
        System.out.println();

    }
}