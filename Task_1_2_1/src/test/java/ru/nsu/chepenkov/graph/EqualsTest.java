package ru.nsu.chepenkov.graph;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;


import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

/**
 * Класс для тестирования equals и hashcode.
 *
 * Вообще эти методы не используются,
 * мне просто coverage не хватало
 */
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
        Edge<Integer> edge3 = new Edge<>(vertexA, vertexB, 2);
        assertTrue(edge1.equals(edge2));
        assertFalse(edge1.equals(edge3));
        assertTrue(edge1.equals(edge1));
        assertEquals(edge1.hashCode(), edge2.hashCode());
    }
}
