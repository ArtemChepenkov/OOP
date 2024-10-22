package ru.nsu.chepenkov.graph;

import javax.swing.text.html.parser.Parser;
import java.io.*;
import java.util.Scanner;
import java.util.function.Function;

public class FileReader {
    public <T> void readFromFile(Graph<T> graph, String fileName,
                                 Function<String, T> parse) throws FileNotFoundException {

        int n;
        int m;
        String vertexName;
        String edgeInput;
        String[] edgeList;
        Vertex<T> vertex;
        Vertex<T> from;
        Vertex<T> to;
        int weight;
        Edge<T> edge;

        InputStream inputStream;
        inputStream = Parser.class.getClassLoader().getResourceAsStream(fileName);
        if (inputStream == null) {
            throw new FileNotFoundException("File not found");
        }
        Scanner scanner;
        scanner = new Scanner(inputStream);

        n = scanner.nextInt();
        scanner.nextLine();

        for (int i = 0; i < n; i++) {
            vertexName = scanner.nextLine();
            vertex = new Vertex<>(parse.apply(vertexName));
            graph.addVertex(vertex);
        }

        m = scanner.nextInt();
        scanner.nextLine();
        for (int i = 0; i < m; i++) {
            edgeInput = scanner.nextLine();
            edgeList = edgeInput.split(" ");
            from = new Vertex(parse.apply(edgeList[0]));
            to = new Vertex(parse.apply(edgeList[1]));
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

