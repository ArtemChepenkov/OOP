package ru.nsu.chepenkov.graph;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.NoSuchElementException;

import static org.junit.jupiter.api.Assertions.*;

class AdjacencyListTest {

    private AdjacencyList<Integer> graph;
    private Vertex<Integer> vertexA;
    private Vertex<Integer> vertexB;
    private Vertex<Integer> vertexC;
    private Edge<Integer> edgeAB;
    private Edge<Integer> edgeAC;
    private Edge<Integer> edgeBC;

    @BeforeEach
    @DisplayName("setUp")
    void setUp() {
        graph = new AdjacencyList<>();
        vertexA = new Vertex<>(1);
        vertexB = new Vertex<>(2);
        vertexC = new Vertex<>(3);
        edgeAB = new Edge<>(vertexA, vertexB, 5);
        edgeAC = new Edge<>(vertexA, vertexC, 10);
        edgeBC = new Edge<>(vertexB, vertexC, 15);
    }

    @Test
    @DisplayName("testAddVertex")
    void testAddVertex() {
        graph.addVertex(vertexA);
        assertTrue(graph.getVertices().contains(vertexA));
    }

    @Test
    @DisplayName("testAddEdge")
    void testAddEdge() {
        graph.addVertex(vertexA);
        graph.addVertex(vertexB);
        graph.addEdge(edgeAB);
        List<Vertex<Integer>> neighbours = graph.getNeigbours(vertexA);
        assertTrue(neighbours.contains(vertexB));
    }

    @Test
    @DisplayName("testDelVertex")
    void testDelVertex() {
        graph.addVertex(vertexA);
        graph.addVertex(vertexB);
        graph.addEdge(edgeAB);
        graph.delVertex(vertexA);
        assertFalse(graph.getVertices().contains(vertexA));
        assertThrows(NoSuchElementException.class, () -> graph.getNeigbours(vertexA));
    }

    @Test
    @DisplayName("testDelEdge")
    void testDelEdge() {
        graph.addVertex(vertexA);
        graph.addVertex(vertexB);
        graph.addEdge(edgeAB);
        graph.delEdge(edgeAB);
        List<Vertex<Integer>> neighbours = graph.getNeigbours(vertexA);
        assertFalse(neighbours.contains(vertexB));
    }

    @Test
    @DisplayName("testGetNeighbours")
    void testGetNeighbours() {
        graph.addVertex(vertexA);
        graph.addVertex(vertexB);
        graph.addVertex(vertexC);
        graph.addEdge(edgeAB);
        graph.addEdge(edgeAC);
        List<Vertex<Integer>> neighbours = graph.getNeigbours(vertexA);
        assertTrue(neighbours.contains(vertexB));
        assertTrue(neighbours.contains(vertexC));
        assertEquals(2, neighbours.size());
    }

    @Test
    @DisplayName("testDelVertexRemovesEdges")
    void testDelVertexRemovesEdges() {
        graph.addVertex(vertexA);
        graph.addVertex(vertexB);
        graph.addVertex(vertexC);
        graph.addEdge(edgeAB);
        graph.addEdge(edgeBC);
        graph.addEdge(edgeAC);
        graph.delVertex(vertexB);
        List<Vertex<Integer>> neighbours = graph.getNeigbours(vertexA);
        assertFalse(neighbours.contains(vertexB)); // B should be removed
        assertTrue(neighbours.contains(vertexC));  // C should still be there
    }

//    @Test void testAddEdgeNoSuchVertex() {
//        assertThrows(NoSuchElementException.class, () -> graph.addEdge(edgeAB));
//    }
}