package ru.nsu.chepenkov.pizzeria;

public class Order {
    private final int pizzaNumber;
    private final int orderId;
    private final int parentOrderId;

    public Order(int pizzaNumber, int orderId) {
        this.pizzaNumber = pizzaNumber;
        this.orderId = orderId;
        this.parentOrderId = -1;
    }

    public Order(int pizzaNumber, int orderId, int parentOrderId) {
        this.pizzaNumber = pizzaNumber;
        this.orderId = orderId;
        this.parentOrderId = parentOrderId;
    }

    public int getPizzaNumber() {
        return pizzaNumber;
    }

    public int getOrderId() {
        return orderId;
    }

    public int getParentOrderId() {
        return parentOrderId;
    }
}
