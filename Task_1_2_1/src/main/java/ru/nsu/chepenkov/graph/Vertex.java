package ru.nsu.chepenkov.graph;

import java.util.Objects;

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
        if (object == null || this.getClass() != object.getClass()){
            return false;
        }

        @SuppressWarnings("unchecked")
        Vertex<T> vertex = (Vertex<T>) object;
        return this.value.equals(vertex.value);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(value);
    }

    @Override
    public String toString() {
        return "" + value;
    }
}
