package ru.nsu.chepenkov.pizzeria;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Тест курьера.
 *
 * @author ArtemChepenkov
 */

class CourierTest {

    private Storage storage;
    private Courier courier;

    @BeforeEach
    void setUp() {
        storage = new Storage(10);
        courier = new Courier(1000, storage, 3);
    }


    @Test
    void testCourierStopsWhenStorageClosed() throws InterruptedException {
        storage.closePizzeria();

        Thread courierThread = new Thread(courier);
        courierThread.start();

        Thread.sleep(1000);

        assertTrue(storage.needBakerFinish());

        courierThread.interrupt();
    }
}