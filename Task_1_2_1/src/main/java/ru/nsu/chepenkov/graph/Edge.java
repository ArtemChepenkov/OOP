package ru.nsu.chepenkov.graph;

import java.util.Objects;

/**
 * Класс для описания ребра графа.
 *
 * @author ArtemChepenkov
 */

public class Edge<T> {
    private final Vertex<T> from;
    private final Vertex<T> to;
    private final int weight;

    public Edge(Vertex<T> from, Vertex<T> to, int weight) {
        this.from = from;
        this.to = to;
        this.weight = weight;
    }

    @Override
    public boolean equals(Object object) {
        if (this == object) {
            return true;
        }
        if (!(object instanceof Edge<?> edge)) {
            return false;
        }
        return Objects.equals(from, edge.from) && Objects.equals(to, edge.to)
                && Objects.equals(weight, edge.weight);
    }

    @Override
    public int hashCode() {
        return Objects.hash(from, to);
    }

    public Vertex<T> getFrom() {
        return from;
    }

    public Vertex<T> getTo() {
        return to;
    }

    public int getWeight() {
        return weight;
    }
}
