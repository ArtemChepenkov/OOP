package ru.nsu.chepenkov.pizzeria;

import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

/**
 * Тест main.
 *
 * @author ArtemChepenkov
 */

public class MainTest {

    @Test
    void testMain() throws InterruptedException {
        String[] testData = new String[]{};
        Main.main(testData);
        assertTrue(true);
    }
}
