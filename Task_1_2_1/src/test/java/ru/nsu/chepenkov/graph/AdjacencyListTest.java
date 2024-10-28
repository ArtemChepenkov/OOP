package ru.nsu.chepenkov.graph;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

/**
 * Класс для тестирования списка смежности.
 *
 * @author ArtemChepenkov
 */

class AdjacencyListTest {

    private AdjacencyList<Integer> graph;
    private Vertex<Integer> vertexA;
    private Vertex<Integer> vertexB;
    private Vertex<Integer> vertexC;
    private Edge<Integer> edgeAb;
    private Edge<Integer> edgeAc;
    private Edge<Integer> edgeBc;

    @BeforeEach
    @DisplayName("AdjacencyListSetUp")
    void adjacencyListSetUp() {
        graph = new AdjacencyList<>();
        vertexA = new Vertex<>(1);
        vertexB = new Vertex<>(2);
        vertexC = new Vertex<>(3);
        edgeAb = new Edge<>(vertexA, vertexB, 5);
        edgeAc = new Edge<>(vertexA, vertexC, 10);
        edgeBc = new Edge<>(vertexB, vertexC, 15);
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
        graph.addEdge(edgeAb);
        List<Vertex<Integer>> neighbours = graph.getNeigbours(vertexA);
        assertTrue(neighbours.contains(vertexB));
    }

    @Test
    @DisplayName("AdjacencyListTestDelVertex")
    void adjacencyListTestDelVertex() {
        graph.addVertex(vertexA);
        graph.addVertex(vertexB);
        graph.addEdge(edgeAb);
        graph.delVertex(vertexA);
        assertFalse(graph.getVertices().contains(vertexA));
        assertThrows(NoSuchElementException.class, () -> graph.getNeigbours(vertexA));
    }

    @Test
    @DisplayName("AdjacencyListTestDelEdge")
    void adjacencyListTestDelEdge() {
        graph.addVertex(vertexA);
        graph.addVertex(vertexB);
        graph.addEdge(edgeAb);
        graph.delEdge(edgeAb);
        List<Vertex<Integer>> neighbours = graph.getNeigbours(vertexA);
        assertFalse(neighbours.contains(vertexB));
    }

    @Test
    @DisplayName("AdjacencyListTestGetNeighbours")
    void adjacencyListTestGetNeighbours() {
        graph.addVertex(vertexA);
        graph.addVertex(vertexB);
        graph.addVertex(vertexC);
        graph.addEdge(edgeAb);
        graph.addEdge(edgeAc);
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
        graph.addEdge(edgeAb);
        graph.addEdge(edgeBc);
        graph.addEdge(edgeAc);
        graph.delVertex(vertexB);
        List<Vertex<Integer>> neighbours = graph.getNeigbours(vertexA);
        assertFalse(neighbours.contains(vertexB));
        assertTrue(neighbours.contains(vertexC));
    }

    @Test
    @DisplayName("AdjacencyListTestAddEdgeNoSuchVertex")
    void adjacencyListTestAddEdgeNoSuchVertex() {
        assertThrows(NoSuchElementException.class, () -> graph.addEdge(edgeAb));
    }

    @Test
    @DisplayName("AdjacencyListTestTopoSort")
    void adjacencyListTestTopoSort() {
        Algorithm algorithm;
        algorithm = new Algorithm();
        List<Vertex<Integer>> res;
        res = new ArrayList<>();
        List<Vertex<Integer>> expected;
        expected = new ArrayList<>();
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
        Edge<Integer> edgeDc = new Edge<>(vertexD, vertexC, 1);
        Edge<Integer> edgeEc = new Edge<>(vertexE, vertexC, 1);
        Edge<Integer> edgeDb = new Edge<>(vertexD, vertexB, 1);
        Edge<Integer> edgeEf = new Edge<>(vertexE, vertexF, 1);
        Edge<Integer> edgeBa = new Edge<>(vertexB, vertexA, 1);
        Edge<Integer> edgeAf = new Edge<>(vertexA, vertexF, 1);
        graph.addEdge(edgeDc);
        graph.addEdge(edgeEc);
        graph.addEdge(edgeDb);
        graph.addEdge(edgeEf);
        graph.addEdge(edgeBa);
        graph.addEdge(edgeAf);
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