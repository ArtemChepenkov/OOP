package ru.nsu.chepenkov.graph;

import java.util.Objects;

public class Edge<T> {
    private final Vertex<T> from;
    private final Vertex<T> to;
    private final boolean isDirected;

    public Edge(Vertex<T> from, Vertex<T> to, boolean isDirected) {
        this.from = from;
        this.to = to;
        this.isDirected = isDirected;
    }

    @Override
    public boolean equals(Object object) {
        if (this == object) {
            return true;
        }
        if (object == null || this.getClass() != object.getClass()) {
            return false;
        }

        //Edge<?> edge = (Edge<?>) object; IDEA says i have to use this
        @SuppressWarnings("unchecked")
        Edge<T> edge = (Edge<T>) object;

        return this.isDirected == edge.isDirected
                && this.from.equals(edge.from)
                && this.to.equals(edge.to);
    }

    @Override
    public int hashCode() {
        return Objects.hash(from, to, isDirected);
    }

    @Override
    public String toString() {
        return "Edge{" +
                "from=" + from +
                ", to=" + to +
                ", isDirected=" + isDirected +
                '}';
    }

    public Vertex<T> getFrom() {
        return from;
    }

    public Vertex<T> getTo() {
        return to;
    }
}
