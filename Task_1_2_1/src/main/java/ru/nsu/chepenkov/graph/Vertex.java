package ru.nsu.chepenkov.graph;

import java.util.Objects;

/**
 * Класс для описания вершины графа.
 *
 * @author ArtemChepenkov
 */

public class Vertex<T> {
    private final T value;
    private static int vertCounter = 0;

    public Vertex(T value) {
        this.value = value;
        vertCounter++;
    }

    public static int getVertCounter() {
        return vertCounter;
    }

    @Override
    public boolean equals(Object object) {
        if (this == object) {
            return true;
        }
        if (!(object instanceof Vertex<?> vertex)) {
            return false;
        }
        return Objects.equals(value, vertex.value);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(value);
    }

}
