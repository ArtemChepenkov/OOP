package ru.nsu.chepenkov.pizzeria;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static java.lang.Thread.sleep;
import static org.junit.jupiter.api.Assertions.*;

class BakerTest {

    private Storage storage;
    private OrderQueue orderQueue;
    private Baker baker;

    @BeforeEach
    void setUp() {
        storage = new Storage(100);
        orderQueue = new OrderQueue();
        baker = new Baker(1000, storage, orderQueue);
        storage.setBakerNumber(1);
    }

    @Test
    void testBakerProcessesOrder() throws InterruptedException {
        Order testOrder = new Order(1, 3);

        orderQueue.addOrder(testOrder);

        Thread bakerThread = new Thread(baker);
        bakerThread.start();

        sleep(3500);

        assertEquals(1, storage.takeOrder().getPizzaNumber());

        bakerThread.interrupt();
    }

    @Test
    void testBakerFinishesWork() throws InterruptedException {
        storage.closePizzeria();

        Thread bakerThread = new Thread(baker);
        bakerThread.start();

        sleep(1000);

        assertTrue(storage.needBakerFinish());
        assertFalse(storage.isOpened());

        bakerThread.interrupt();
    }
}