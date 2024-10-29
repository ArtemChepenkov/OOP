package ru.nsu.chepenkov.graph;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

/**
 * Класс для тестирования матрицы смежности.
 *
 * <p>Тут ещё и топосорт тестируется
 */
public class AdjacencyMatrixTest {

    @Test
    @DisplayName("AdjacencyMatrixTestConstructor")
    void adjacencyMatrixTestConstructor() {
        AdjacencyMatrix<Object> adjacencyMatrix = new AdjacencyMatrix<>();
        assertTrue(true);
    }

    @Test
    @DisplayName("AdjacencyMatrixTestAddVertex")
    void adjacencyMatrixTestAddVertex() {
        AdjacencyMatrix<Integer> adjacencyMatrix = new AdjacencyMatrix<>();
        Vertex<Integer> v1 = new Vertex<>(1);
        Vertex<Integer> v2 = new Vertex<>(2);
        Vertex<Integer> v3 = new Vertex<>(3);
        adjacencyMatrix.addVertex(v1);
        adjacencyMatrix.addVertex(v2);
        adjacencyMatrix.addVertex(v3);

        List<Vertex<Integer>> vertList = adjacencyMatrix.getVertices();

        int vertSize;
        vertSize = vertList.size();

        assertEquals(v1, vertList.get(0));
        assertEquals(v2, vertList.get(1));
        assertEquals(v3, vertList.get(2));

        List<List<Integer>> matrix = adjacencyMatrix.getMatrix();
        for (int i = 0; i < vertSize; i++) {
            for (int j = 0; j < vertSize; j++) {
                assertEquals(matrix.get(i).get(j), -1);
            }
        }
    }

    @Test
    @DisplayName("AdjacencyMatrixTestDelVertex")
    void adjacencyMatrixTestDelVertex() {
        AdjacencyMatrix<Integer> adjacencyMatrix = new AdjacencyMatrix<>();
        Vertex<Integer> v1 = new Vertex<>(1);
        Vertex<Integer> v2 = new Vertex<>(2);
        Vertex<Integer> v3 = new Vertex<>(3);
        adjacencyMatrix.addVertex(v1);
        adjacencyMatrix.addVertex(v2);
        adjacencyMatrix.addVertex(v3);

        int prevSize = adjacencyMatrix.getVertices().size();
        adjacencyMatrix.delVertex(v2);
        int curSize = adjacencyMatrix.getVertices().size();

        ArrayList<Vertex<Integer>> vertList = (ArrayList<Vertex<Integer>>) adjacencyMatrix.getVertices();

        for (int i = 0; i < curSize; i++) {
            assertNotEquals(v2, vertList.get(i));
        }
        assertNotEquals(prevSize, curSize);

    }

    @Test
    @DisplayName("AdjacencyMatrixTestAddEdge")
    void adjacencyMatrixTestAddEdge() {
        AdjacencyMatrix<Integer> adjacencyMatrix = new AdjacencyMatrix<>();
        Vertex<Integer> v1 = new Vertex<>(1);
        Vertex<Integer> v2 = new Vertex<>(2);
        Vertex<Integer> v3 = new Vertex<>(3);
        adjacencyMatrix.addVertex(v1);
        adjacencyMatrix.addVertex(v2);
        adjacencyMatrix.addVertex(v3);
        Edge<Integer> edge1 = new Edge<>(v1, v2, 5);
        Edge<Integer> edge2 = new Edge<>(v2, v3, 5);
        Edge<Integer> edge3 = new Edge<>(v3, v2, 5);
        adjacencyMatrix.addEdge(edge1);
        adjacencyMatrix.addEdge(edge2);
        adjacencyMatrix.addEdge(edge3);
        ArrayList<Edge<Integer>> edges = (ArrayList<Edge<Integer>>) adjacencyMatrix.getEdges();

        assertEquals(3, edges.size());
        assertEquals(edge1, edges.get(0));
        assertEquals(edge2, edges.get(1));
        assertEquals(edge3, edges.get(2));
    }

    @Test
    @DisplayName("AdjacencyMatrixTestDelEdge")
    void adjacencyMatrixTestDelEdge() {
        AdjacencyMatrix<Integer> adjacencyMatrix = new AdjacencyMatrix<>();
        Vertex<Integer> v1 = new Vertex<>(1);
        Vertex<Integer> v2 = new Vertex<>(2);
        Vertex<Integer> v3 = new Vertex<>(3);
        adjacencyMatrix.addVertex(v1);
        adjacencyMatrix.addVertex(v2);
        adjacencyMatrix.addVertex(v3);
        Edge<Integer> edge1 = new Edge<>(v1, v2, 5);
        Edge<Integer> edge2 = new Edge<>(v2, v3, 5);
        Edge<Integer> edge3 = new Edge<>(v3, v2, 5);
        adjacencyMatrix.addEdge(edge1);
        adjacencyMatrix.addEdge(edge2);
        adjacencyMatrix.addEdge(edge3);
        ArrayList<Edge<Integer>> edges = (ArrayList<Edge<Integer>>) adjacencyMatrix.getEdges();

        adjacencyMatrix.delEdge(edge1);

        assertEquals(2, edges.size());
        assertEquals(edge2, edges.get(0));
        assertEquals(edge3, edges.get(1));
    }

    @Test
    @DisplayName("AdjacencyMatrixTestAssertThrowsOnDelVertex")
    void adjacencyMatrixTestAssertThrowsOnDelVertex() {
        Vertex<Integer> vertex = new Vertex<>(1);
        AdjacencyMatrix<Integer> adjacencyMatrix = new AdjacencyMatrix<>();
        assertThrows(NoSuchElementException.class, () -> adjacencyMatrix.delVertex(vertex));
    }

    @Test
    @DisplayName("AdjacencyMatrixTestAssertThrowsOnAddEdge")
    void adjacencyMatrixTestAssertThrowsOnAddEdge() {
        Edge<Integer> edge = new Edge<>(new Vertex<>(1), new Vertex<>(2), 3);
        AdjacencyMatrix<Integer> adjacencyMatrix = new AdjacencyMatrix<>();
        assertThrows(NoSuchElementException.class, () -> adjacencyMatrix.addEdge(edge));
    }

    @Test
    @DisplayName("AdjacencyMatrixTestAssertThrowsOnDelEdge")
    void adjacencyMatrixTestAssertThrowsOnDelEdge() {
        Edge<Integer> edge = new Edge<>(new Vertex<>(1), new Vertex<>(2), 3);
        AdjacencyMatrix<Integer> adjacencyMatrix = new AdjacencyMatrix<>();
        assertThrows(NoSuchElementException.class, () -> adjacencyMatrix.delEdge(edge));
    }

    @Test
    @DisplayName("AdjacencyMatrixTestGetNeighbours")
    void adjacencyMatrixTestGetNeighbours() {
        AdjacencyMatrix<Integer> adjacencyMatrix = new AdjacencyMatrix<>();
        Vertex<Integer> v1 = new Vertex<>(1);
        Vertex<Integer> v2 = new Vertex<>(2);
        Vertex<Integer> v3 = new Vertex<>(3);
        adjacencyMatrix.addVertex(v1);
        adjacencyMatrix.addVertex(v2);
        adjacencyMatrix.addVertex(v3);
        Edge<Integer> edge1 = new Edge<>(v1, v2, 5);
        Edge<Integer> edge2 = new Edge<>(v2, v3, 5);
        Edge<Integer> edge3 = new Edge<>(v3, v2, 5);
        adjacencyMatrix.addEdge(edge1);
        adjacencyMatrix.addEdge(edge2);
        adjacencyMatrix.addEdge(edge3);
        assertEquals(2, adjacencyMatrix.getNeigbours(v2).size());
    }
}

