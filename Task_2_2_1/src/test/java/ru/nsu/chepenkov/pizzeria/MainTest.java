package ru.nsu.chepenkov.pizzeria;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;

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
