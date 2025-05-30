package ru.nsu.chepenkov.graph;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;
import java.util.NoSuchElementException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

/**
 * Класс для тестирования матрицы инцидентности.
 *
 * @author ArtemChepenkov
 */

public class IncidenceMatrixTest {
    private IncidenceMatrix<String> graph;

    @BeforeEach
    @DisplayName("IncidenceMatrixSetUp")
    void incidenceMatrixSetUp() {
        graph = new IncidenceMatrix<>();
    }

    @Test
    @DisplayName("IncidenceMatrixTestAddVertex")
    void incidenceMatrixTestAddVertex() {
        Vertex<String> v1 = new Vertex<>("A");
        Vertex<String> v2 = new Vertex<>("B");

        graph.addVertex(v1);
        graph.addVertex(v2);

        assertEquals(2, graph.getVertices().size());
        assertTrue(graph.getVertices().contains(v1));
        assertTrue(graph.getVertices().contains(v2));
    }

    @Test
    @DisplayName("IncidenceMatrixTestDelVertex")
    void incidenceMatrixTestDelVertex() {
        Vertex<String> v1 = new Vertex<>("A");
        Vertex<String> v2 = new Vertex<>("B");
        graph.addVertex(v1);
        graph.addVertex(v2);

        graph.delVertex(v1);

        assertEquals(1, graph.getVertices().size());
        assertFalse(graph.getVertices().contains(v1));
    }

    @Test
    @DisplayName("IncidenceMatrixTestAddEdge")
    void incidenceMatrixTestAddEdge() {
        Vertex<String> v1 = new Vertex<>("A");
        Vertex<String> v2 = new Vertex<>("B");
        graph.addVertex(v1);
        graph.addVertex(v2);

        Edge<String> edge = new Edge<>(v1, v2, 1);
        graph.addEdge(edge);

        assertEquals(1, graph.getEdges().size());
        assertTrue(graph.getEdges().contains(edge));
        assertEquals(1, graph.getNeigbours(v1).size());
        assertTrue(graph.getNeigbours(v1).contains(v2));
    }

    @Test
    @DisplayName("IncidenceMatrixTestDelEdge")
    void incidenceMatrixTestDelEdge() {
        Vertex<String> v1 = new Vertex<>("A");
        Vertex<String> v2 = new Vertex<>("B");
        graph.addVertex(v1);
        graph.addVertex(v2);

        Edge<String> edge = new Edge<>(v1, v2, 1);
        graph.addEdge(edge);
        graph.delEdge(edge);

        assertEquals(0, graph.getEdges().size());
        assertFalse(graph.getEdges().contains(edge));
    }

    @Test
    @DisplayName("IncidenceMatrixTestGetNeighbours")
    void incidenceMatrixTestGetNeighbours() {
        Vertex<String> v1 = new Vertex<>("A");
        Vertex<String> v2 = new Vertex<>("B");
        Vertex<String> v3 = new Vertex<>("C");
        graph.addVertex(v1);
        graph.addVertex(v2);
        graph.addVertex(v3);

        Edge<String> edge1 = new Edge<>(v1, v2, 1);
        Edge<String> edge2 = new Edge<>(v2, v3, 1);
        graph.addEdge(edge1);
        graph.addEdge(edge2);

        List<Vertex<String>> neighbours = graph.getNeigbours(v2);
        assertEquals(2, neighbours.size());
        assertFalse(neighbours.contains(v1));
        assertTrue(neighbours.contains(v3));
    }

    @Test
    @DisplayName("IncidenceMatrixTestGetNeighboursNoSuchVertex")
    void incidenceMatrixTestGetNeighboursNoSuchVertex() {
        Vertex<String> v1 = new Vertex<>("A");
        graph.addVertex(v1);

        assertThrows(NoSuchElementException.class, () -> {
            graph.getNeigbours(new Vertex<>("B"));
        });
    }
}
