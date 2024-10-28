package ru.nsu.chepenkov.graph;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.FileNotFoundException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

/**
 * Класс для тестрирования правильного чтения из файла.
 * <p>Тут тестируюстя int, double, string
 */
public class FileReaderTest {

    @Test
    @DisplayName("FileReaderTestInteger")
    void fileReaderTestInteger() throws FileNotFoundException {
        AdjacencyMatrix<Integer> adjacencyMatrix = new AdjacencyMatrix<>();
        FileReader fileReader = new FileReader();
        String test1 = "test1.txt";
        fileReader.readFromFile(adjacencyMatrix, test1, Integer::parseInt);
        assertEquals(5, adjacencyMatrix.getVertices().size());
    }

    @Test
    @DisplayName("FileReaderTestString")
    void fileReaderTestString() throws FileNotFoundException {
        AdjacencyList<String> adjacencyList = new AdjacencyList<>();
        FileReader fileReader = new FileReader();
        String test2 = "test2.txt";
        fileReader.readFromFile(adjacencyList, test2, s -> s);
        assertEquals(3, adjacencyList.getVertices().size());
    }

    @Test
    @DisplayName("FileReaderTestDouble")
    void fileReaderTestDouble() throws FileNotFoundException {
        IncidenceMatrix<Double> incidenceMatrix = new IncidenceMatrix<>();
        FileReader fileReader = new FileReader();
        String test3 = "test3.txt";
        fileReader.readFromFile(incidenceMatrix, test3, Double::parseDouble);
        assertEquals(2, incidenceMatrix.getVertices().size());
    }
}
