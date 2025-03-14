package ru.nsu.chepenkov.pizzeria;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * Тест хранилища.
 *
 * @author ArtemChepenkov
 */

class StorageTest {

    private Storage storage;

    @BeforeEach
    void setUp() {
        storage = new Storage(10);
    }

    @Test
    void testPutOrder() throws InterruptedException {
        Order order = new Order(1, 3);
        storage.putOrder(order);
        assertEquals(1, storage.takeOrder().getPizzaNumber());
    }

    @Test
    void testTakeOrder() throws InterruptedException {
        Order order = new Order(1, 3);
        storage.putOrder(order);
        assertEquals(1, storage.takeOrder().getPizzaNumber());
    }

    @Test
    void testClosePizzeria() {
        storage.closePizzeria();
        assertTrue(storage.needBakerFinish());
    }

    @Test
    void testNeedBakerFinish() {
        assertFalse(storage.needBakerFinish());
        storage.closePizzeria();
        assertTrue(storage.needBakerFinish());
    }
}