package ru.nsu.chepenkov.graph;

import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.Scanner;
import java.util.function.Function;

/**
 * Класс для описания file reader.
 *
 * <p>формат:
 * количество вершин
 * вершины
 * количество рёбер
 * рёбра(откуда, куда, вес)
 */

public class FileReader {

    /** Читает по определённому формату, описанному выше.*/
    public <T> void readFromFile(Graph<T> graph, String fileName,
                                 Function<String, T> parse) throws FileNotFoundException {

        String vertexName;
        String edgeInput;
        String[] edgeList;
        Vertex<T> vertex;
        Vertex<T> from;
        Vertex<T> to;
        int weight;
        Edge<T> edge;

        InputStream inputStream;
        inputStream = FileReader.class.getClassLoader().getResourceAsStream(fileName);
        if (inputStream == null) {
            throw new FileNotFoundException("File not found");
        }
        Scanner scanner;
        scanner = new Scanner(inputStream);
        int n;
        n = scanner.nextInt();
        scanner.nextLine();

        for (int i = 0; i < n; i++) {
            vertexName = scanner.nextLine();
            vertex = new Vertex<>(parse.apply(vertexName));
            graph.addVertex(vertex);
        }
        int m;
        m = scanner.nextInt();
        scanner.nextLine();
        for (int i = 0; i < m; i++) {
            edgeInput = scanner.nextLine();
            edgeList = edgeInput.split(" ");
            from = new Vertex<>(parse.apply(edgeList[0]));
            to = new Vertex<>(parse.apply(edgeList[1]));
            if (edgeList.length == 3) {
                weight = Integer.parseInt(edgeList[2]);
            } else {
                weight = 1;
            }
            edge = new Edge<>(from, to, weight);
            graph.addEdge(edge);
        }
        scanner.close();

    }
}

