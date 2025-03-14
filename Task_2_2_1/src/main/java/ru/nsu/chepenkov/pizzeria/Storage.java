package ru.nsu.chepenkov.pizzeria;

import java.util.LinkedList;
import java.util.List;

/**
 * Хранилище.
 *
 * @author ArtemChepenkov
 */

public class Storage {
    private final int capacity;
    private final int waitTime = 100;
    private int currentNumber;
    private final List<Order> queue;
    private boolean openCondition;
    private boolean bakerFinished = false;
    private int bakerNumber;
    private static int bakersGoneHome = 0;

    /**Конструктор.*/
    public Storage(int capacity) {
        this.capacity = capacity;
        this.currentNumber = 0;
        this.queue = new LinkedList<>();
        this.openCondition = true;
    }

    /**Положить заказ.*/
    public synchronized void putOrder(Order order) throws InterruptedException {
        while (capacity < currentNumber + order.getPizzaNumber()) {
            wait(waitTime);
        }
        currentNumber += order.getPizzaNumber();
        queue.add(order);
        notifyAll();
    }

    /**Взять заказ.*/
    public synchronized Order takeOrder() throws InterruptedException {
        while(queue.isEmpty()) {
            wait(waitTime);
            if(!isOpened()) {
                return null;
            }
        }

        Order order = queue.removeLast();
        currentNumber -= order.getPizzaNumber();
        return order;
    }

    /**Проверка на то открыто ли.*/
    public synchronized boolean isOpened() {
        return openCondition;
    }

    /**Вызываем из main, чтоюы закрыть пиццерию.*/
    public synchronized void closePizzeria() {
        bakerFinished = true;
    }

    /**Отправляем пекарей домой.*/
    public synchronized void bakerGoHome() {
        bakersGoneHome++;
    }

    /**Проверяем ушли ли все пекари и закрываем пиццерию.*/
    public synchronized void tryClosePizzeria() {
        if (bakersGoneHome == bakerNumber) {
            openCondition = false;
        }
    }

    /**Тут пекарь смотрит нужно ли ему завершать.*/
    public synchronized boolean needBakerFinish() {
        return bakerFinished;
    }

    /**Устанавливаем количество пекарей.*/
    public void setBakerNumber(int bakerNumber) {
        this.bakerNumber = bakerNumber;
    }
}
