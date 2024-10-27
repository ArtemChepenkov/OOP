package ru.nsu.chepenkov.graph;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class EqualsTest {

    @Test
    @DisplayName("EqualsTestVertex")
    void equalsTestVertex() {
        Vertex<Integer> vertexA = new Vertex<>(1);
        Vertex<Integer> vertexB = new Vertex<>(1);
        assertEquals(vertexA, vertexB);
    }

    @Test
    @DisplayName("EqualsTestEdge")
    void equalsTestEdge() {
        Vertex<Integer> vertexA = new Vertex<>(1);
        Vertex<Integer> vertexB = new Vertex<>(1);
        Edge<Integer> edge1 = new Edge<>(vertexA, vertexB, 1);
        Edge<Integer> edge2 = new Edge<>(vertexA, vertexB, 1);
        assertEquals(edge1, edge2);
    }
}
