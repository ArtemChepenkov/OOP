package ru.nsu.chepenkov.graph;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.NoSuchElementException;

import static org.junit.jupiter.api.Assertions.*;

public class IncidenceListTest {
    private IncidenceList<String> graph;
    private Vertex<String> vertexA;
    private Vertex<String> vertexB;
    private Vertex<String> vertexC;
    private Edge<String> edgeAB;
    private Edge<String> edgeAC;

    @BeforeEach
    @DisplayName("setUp")
    public void setUp() {
        graph = new IncidenceList<>();
        vertexA = new Vertex<>("A");
        vertexB = new Vertex<>("B");
        vertexC = new Vertex<>("C");
        edgeAB = new Edge<>(vertexA, vertexB, 5);
        edgeAC = new Edge<>(vertexA, vertexC, 10);
    }

    @Test
    @DisplayName("testAddVertex")
    public void testAddVertex() {
        graph.addVertex(vertexA);
        assertTrue(graph.getVertices().contains(vertexA));
    }

    @Test
    @DisplayName("testDelVertex")
    public void testDelVertex() {
        graph.addVertex(vertexA);
        graph.delVertex(vertexA);
        assertFalse(graph.getVertices().contains(vertexA));
    }

    @Test
    @DisplayName("testDelVertexWithEdges")
    public void testDelVertexWithEdges() {
        graph.addVertex(vertexA);
        graph.addVertex(vertexB);
        graph.addEdge(edgeAB);
        graph.delVertex(vertexA);
        assertFalse(graph.getVertices().contains(vertexA));
        assertFalse(graph.getEdges().contains(edgeAB));
    }

    @Test
    @DisplayName("testAddVertex")
    public void testAddEdge() {
        graph.addVertex(vertexA);
        graph.addVertex(vertexB);
        graph.addEdge(edgeAB);
        assertTrue(graph.getEdges().contains(edgeAB));
    }

    @Test
    @DisplayName("testDelVertex")
    public void testDelEdge() {
        graph.addVertex(vertexA);
        graph.addVertex(vertexB);
        graph.addEdge(edgeAB);
        graph.delEdge(edgeAB);
        assertFalse(graph.getEdges().contains(edgeAB));
    }

    @Test
    @DisplayName("testGetNeighbours")
    public void testGetNeighbours() {
        graph.addVertex(vertexA);
        graph.addVertex(vertexB);
        graph.addVertex(vertexC);
        graph.addEdge(edgeAB);
        graph.addEdge(edgeAC);
        List<Vertex<String>> neighbours = graph.getNeigbours(vertexA);
        assertTrue(neighbours.contains(vertexB));
        assertTrue(neighbours.contains(vertexC));
    }

    @Test public void testGetNeighboursNoSuchVertex() {
        assertThrows(NoSuchElementException.class, () -> graph.getNeigbours(vertexA));
    }

    @Test public void testDelEdgeNoSuchEdge() {
        graph.addVertex(vertexA);
        graph.addVertex(vertexB);
        assertThrows(NoSuchElementException.class, () -> graph.delEdge(edgeAB));
    }

    @Test public void testDelVertexNoSuchVertex() {
        assertThrows(NoSuchElementException.class, () -> graph.delVertex(vertexA));
    }

    @Test public void testAddEdgeNoSuchVertex() {
        assertThrows(NoSuchElementException.class, () -> graph.addEdge(edgeAB));
    }
}
