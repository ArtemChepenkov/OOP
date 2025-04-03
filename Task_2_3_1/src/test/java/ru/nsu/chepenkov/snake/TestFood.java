package ru.nsu.chepenkov.snake;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

/**
 * Тесты для класса Food.
 */
public class TestFood {

    /**
     * Проверяет корректность конструктора и наследования от Coordinate.
     */
    @Test
    public void testConstructor() {
        Food food = new Food(2, 3);
        assertEquals(2, food.getX());
        assertEquals(3, food.getY());
    }

    /**
     * Проверяет исключение при отрицательных координатах.
     */
    @Test
    public void testConstructorNegativeCoordinates() {
        assertThrows(IllegalArgumentException.class, () -> new Food(-1, 2));
        assertThrows(IllegalArgumentException.class, () -> new Food(2, -1));
    }
}