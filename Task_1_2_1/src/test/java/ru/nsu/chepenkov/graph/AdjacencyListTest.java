package ru.nsu.chepenkov.graph;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.HashSet;
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
    @DisplayName("AdjacencyListSetUp")
    void adjacencyListSetUp() {
        graph = new AdjacencyList<>();
        vertexA = new Vertex<>(1);
        vertexB = new Vertex<>(2);
        vertexC = new Vertex<>(3);
        edgeAB = new Edge<>(vertexA, vertexB, 5);
        edgeAC = new Edge<>(vertexA, vertexC, 10);
        edgeBC = new Edge<>(vertexB, vertexC, 15);
    }

    @Test
    @DisplayName("AdjacencyListTestAddVertex")
    void adjacencyListTestAddVertex() {
        graph.addVertex(vertexA);
        assertTrue(graph.getVertices().contains(vertexA));
    }

    @Test
    @DisplayName("AdjacencyListTestAddEdge")
    void adjacencyListTestAddEdge() {
        graph.addVertex(vertexA);
        graph.addVertex(vertexB);
        graph.addEdge(edgeAB);
        List<Vertex<Integer>> neighbours = graph.getNeigbours(vertexA);
        assertTrue(neighbours.contains(vertexB));
    }

    @Test
    @DisplayName("AdjacencyListTestDelVertex")
    void adjacencyListTestDelVertex() {
        graph.addVertex(vertexA);
        graph.addVertex(vertexB);
        graph.addEdge(edgeAB);
        graph.delVertex(vertexA);
        assertFalse(graph.getVertices().contains(vertexA));
        assertThrows(NoSuchElementException.class, () -> graph.getNeigbours(vertexA));
    }

    @Test
    @DisplayName("AdjacencyListTestDelEdge")
    void adjacencyListTestDelEdge() {
        graph.addVertex(vertexA);
        graph.addVertex(vertexB);
        graph.addEdge(edgeAB);
        graph.delEdge(edgeAB);
        List<Vertex<Integer>> neighbours = graph.getNeigbours(vertexA);
        assertFalse(neighbours.contains(vertexB));
    }

    @Test
    @DisplayName("AdjacencyListTestGetNeighbours")
    void adjacencyListTestGetNeighbours() {
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
    @DisplayName("AdjacencyListTestDelVertexRemovesEdges")
    void adjacencyListTestDelVertexRemovesEdges() {
        graph.addVertex(vertexA);
        graph.addVertex(vertexB);
        graph.addVertex(vertexC);
        graph.addEdge(edgeAB);
        graph.addEdge(edgeBC);
        graph.addEdge(edgeAC);
        graph.delVertex(vertexB);
        List<Vertex<Integer>> neighbours = graph.getNeigbours(vertexA);
        assertFalse(neighbours.contains(vertexB));
        assertTrue(neighbours.contains(vertexC));
    }

    @Test
    @DisplayName("AdjacencyListTestAddEdgeNoSuchVertex")
    void adjacencyListTestAddEdgeNoSuchVertex() {
        assertThrows(NoSuchElementException.class, () -> graph.addEdge(edgeAB));
    }

    @Test
    @DisplayName("AdjacencyListTestTopoSort")
    void adjacencyListTestTopoSort() {
        Algorithm algorithm = new Algorithm();
        List<Vertex<Integer>> res = new ArrayList<>();
        List<Vertex<Integer>> expected = new ArrayList<>();
        vertexA = new Vertex<>(1);
        vertexB = new Vertex<>(2);
        vertexC = new Vertex<>(3);
        Vertex<Integer> vertexD = new Vertex<>(4);
        Vertex<Integer> vertexE = new Vertex<>(5);
        Vertex<Integer> vertexF = new Vertex<>(6);
        graph.addVertex(vertexA);
        graph.addVertex(vertexB);
        graph.addVertex(vertexC);
        graph.addVertex(vertexD);
        graph.addVertex(vertexE);
        graph.addVertex(vertexF);
        Edge<Integer> edgeDC = new Edge<>(vertexD, vertexC, 1);
        Edge<Integer> edgeEC = new Edge<>(vertexE, vertexC, 1);
        Edge<Integer> edgeDB = new Edge<>(vertexD, vertexB, 1);
        Edge<Integer> edgeEF = new Edge<>(vertexE, vertexF, 1);
        Edge<Integer> edgeBA = new Edge<>(vertexB, vertexA, 1);
        Edge<Integer> edgeAF = new Edge<>(vertexA, vertexF, 1);
        graph.addEdge(edgeDC);
        graph.addEdge(edgeEC);
        graph.addEdge(edgeDB);
        graph.addEdge(edgeEF);
        graph.addEdge(edgeBA);
        graph.addEdge(edgeAF);
        res = algorithm.toposort(graph);
        expected.add(vertexE);
        expected.add(vertexD);
        expected.add(vertexC);
        expected.add(vertexB);
        expected.add(vertexA);
        expected.add(vertexF);
        assertEquals(expected, res);
    }
}