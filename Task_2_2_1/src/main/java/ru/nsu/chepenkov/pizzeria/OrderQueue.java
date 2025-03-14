package ru.nsu.chepenkov.pizzeria;

import java.util.LinkedList;

/**
 * Заказы, которые заполняем из main.
 *
 * @author ArtemChepenkov
 */

public class OrderQueue {
    private final LinkedList<Order> queue;
    private final int waitTime = 100;
    private static int subOrderId = 1;

    /**Конструктор.*/
    public OrderQueue() {
        this.queue = new LinkedList<>();
    }

    /**Добавление заказа.*/
    public synchronized void addOrder(Order order) {
        for (int i = 0; i < order.getPizzaNumber(); i++) {
            queue.add(new Order(1, subOrderId++, order.getOrderId()));
        }
        notifyAll();
    }

    /**Взятие заказа.*/
    public synchronized Order takeOrder() throws InterruptedException {
        while(queue.isEmpty()) {
            wait(waitTime);
        }
        Order order = queue.getLast();
        queue.removeLast();
        return order;
    }

    /**Проверка на пустоту.*/
    public synchronized boolean isEmpty() {
        return queue.isEmpty();
    }
}
