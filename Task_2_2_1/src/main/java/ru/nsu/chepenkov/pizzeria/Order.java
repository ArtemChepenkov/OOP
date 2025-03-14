package ru.nsu.chepenkov.pizzeria;

/**
 * Класс для заказов.
 *
 * @author ArtemChepenkov
 */

public class Order {
    private final int pizzaNumber;
    private final int orderId;
    private final int parentOrderId;

    /**Конструктор.*/
    public Order(int pizzaNumber, int orderId) {
        this.pizzaNumber = pizzaNumber;
        this.orderId = orderId;
        this.parentOrderId = -1;
    }

    /**Конструктор.*/
    public Order(int pizzaNumber, int orderId, int parentOrderId) {
        this.pizzaNumber = pizzaNumber;
        this.orderId = orderId;
        this.parentOrderId = parentOrderId;
    }

    /**Геттер.*/
    public int getPizzaNumber() {
        return pizzaNumber;
    }

    /**Геттер.*/
    public int getOrderId() {
        return orderId;
    }

    /**Геттер.*/
    public int getParentOrderId() {
        return parentOrderId;
    }
}
