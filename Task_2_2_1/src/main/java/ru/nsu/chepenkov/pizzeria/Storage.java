package ru.nsu.chepenkov.pizzeria;

import java.util.LinkedList;
import java.util.List;

public class Storage {
    private final int capacity;
    private final int waitTime = 100;
    private int currentNumber;
    private final List<Order> queue;
    private boolean openCondition;
    public Storage(int capacity) {
        this.capacity = capacity;
        this.currentNumber = 0;
        this.queue = new LinkedList<>();
        this.openCondition = true;
    }

    public synchronized void putOrder(Order order) throws InterruptedException {
        //дробить заказы
        while (capacity < currentNumber + order.getPizzaNumber()) {
            wait(waitTime);
        }
        currentNumber += order.getPizzaNumber();
        queue.add(order);
        notifyAll();
    }

    public synchronized Order takeOrder() throws InterruptedException {
        while(queue.isEmpty()) {
            wait(waitTime);
        }

        Order order = queue.removeLast();
        currentNumber -= order.getPizzaNumber();
        return order;
    }

    public synchronized boolean isOpened() {
        return openCondition;
    }

    public synchronized void closePizzeria() {
        openCondition = false;
    }
}
