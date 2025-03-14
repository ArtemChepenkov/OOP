package ru.nsu.chepenkov.pizzeria;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static java.lang.Thread.sleep;
import static org.junit.jupiter.api.Assertions.*;

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

//    @Test
//    void testIsOpened() throws InterruptedException {
//        assertTrue(storage.isOpened());
//        storage.closePizzeria();
//        sleep(3000);
//        assertFalse(storage.isOpened());
//    }

    @Test
    void testClosePizzeria() {
        storage.closePizzeria();
        assertTrue(storage.needBakerFinish());
    }

//    @Test
//    void testTryClosePizzeria() {
//        storage.setBakerNumber(1);
//        storage.bakerGoHome();
//        assertFalse(storage.isOpened());
//    }

    @Test
    void testNeedBakerFinish() {
        assertFalse(storage.needBakerFinish());
        storage.closePizzeria();
        assertTrue(storage.needBakerFinish());
    }
}