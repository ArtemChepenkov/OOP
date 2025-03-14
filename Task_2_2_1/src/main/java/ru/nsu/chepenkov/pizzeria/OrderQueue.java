package ru.nsu.chepenkov.pizzeria;

import java.util.LinkedList;
import java.util.List;

public class OrderQueue {
    private final List<Order> queue;
    private final int waitTime = 100;

    public OrderQueue() {
        this.queue = new LinkedList<>();
    }

    public synchronized void addOrder(Order order) {
        for (int i = 0; i < order.getPizzaNumber(); i++) {
            queue.add(new Order(1, -1, order.getOrderId()));
        }
        notifyAll();
    }

    public synchronized Order takeOrder() throws InterruptedException {
        while(queue.isEmpty()) {
            wait(waitTime);
        }
        Order order = queue.getLast();
        queue.removeLast();
        return order;
    }

    public synchronized boolean isEmpty() {
        return queue.isEmpty();
    }
}
