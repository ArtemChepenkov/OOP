package ru.nsu.chepenkov.graph;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class EqualsTest {

    @Test
    @DisplayName("EqualsTestVertex")
    void equalsTestVertex() {
        Vertex<Integer> vertexA = new Vertex<>(1);
        Vertex<Integer> vertexB = new Vertex<>(1);
        assertTrue(vertexA.equals(vertexB));
    }

    @Test
    @DisplayName("EqualsTestEdge")
    void equalsTestEdge() {
        Vertex<Integer> vertexA = new Vertex<>(1);
        Vertex<Integer> vertexB = new Vertex<>(1);
        Edge<Integer> edge1 = new Edge<>(vertexA, vertexB, 1);
        Edge<Integer> edge2 = new Edge<>(vertexA, vertexB, 1);
        assertTrue(edge1.equals(edge2));
    }
}
