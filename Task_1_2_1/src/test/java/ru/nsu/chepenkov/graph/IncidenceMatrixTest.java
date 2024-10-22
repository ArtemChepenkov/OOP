package ru.nsu.chepenkov.graph;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.NoSuchElementException;

import static org.junit.jupiter.api.Assertions.*;

public class IncidenceMatrixTest {
    private IncidenceMatrix<String> graph;

    @BeforeEach
    void setUp() {
        graph = new IncidenceMatrix<>();
    }

    @Test void testAddVertex() {
        Vertex<String> v1 = new Vertex<>("A");
        Vertex<String> v2 = new Vertex<>("B");

        graph.addVertex(v1);
        graph.addVertex(v2);

        assertEquals(2, graph.getVertices().size());
        assertTrue(graph.getVertices().contains(v1));
        assertTrue(graph.getVertices().contains(v2));
    }

    @Test void testDelVertex() {
        Vertex<String> v1 = new Vertex<>("A");
        Vertex<String> v2 = new Vertex<>("B");
        graph.addVertex(v1);
        graph.addVertex(v2);

        graph.delVertex(v1);

        assertEquals(1, graph.getVertices().size());
        assertFalse(graph.getVertices().contains(v1));
    }

    @Test void testAddEdge() {
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

    @Test void testDelEdge() {
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

    @Test void testGetNeighbours() {
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

    @Test void testGetNeighboursNoSuchVertex() {
        Vertex<String> v1 = new Vertex<>("A");
        graph.addVertex(v1);

        assertThrows(NoSuchElementException.class, () -> {
            graph.getNeigbours(new Vertex<>("B"));
        });
    }
}
