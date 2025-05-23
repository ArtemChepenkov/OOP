package ru.nsu.chepenkov.pizzeria;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * Тест очереди заказов, которую заполняем из main.
 *
 * @author ArtemChepenkov
 */

class OrderQueueTest {

    private OrderQueue orderQueue;

    @BeforeEach
    void setUp() {
        orderQueue = new OrderQueue();
    }

    @Test
    void testAddOrder() {
        Order order = new Order(1, 3);
        orderQueue.addOrder(order);
        assertFalse(orderQueue.isEmpty());
    }

    @Test
    void testTakeOrder() throws InterruptedException {
        Order order = new Order(1, 3);
        orderQueue.addOrder(order);
        assertEquals(1, orderQueue.takeOrder().getPizzaNumber());
    }

    @Test
    void testIsEmpty() {
        assertTrue(orderQueue.isEmpty());
        Order order = new Order(1, 3);
        orderQueue.addOrder(order);
        assertFalse(orderQueue.isEmpty());
    }
}