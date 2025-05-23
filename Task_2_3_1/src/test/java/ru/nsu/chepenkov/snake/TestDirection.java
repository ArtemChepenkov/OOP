package ru.nsu.chepenkov.snake;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Тесты для перечисления Direction.
 */
public class TestDirection {

    /**
     * Проверяет, что все значения перечисления существуют.
     */
    @Test
    public void testDirectionValues() {
        Direction[] directions = Direction.values();
        assertEquals(4, directions.length);
        assertEquals(Direction.UP, directions[0]);
        assertEquals(Direction.RIGHT, directions[1]);
        assertEquals(Direction.DOWN, directions[2]);
        assertEquals(Direction.LEFT, directions[3]);
    }

    /**
     * Проверяет корректность метода valueOf.
     */
    @Test
    public void testValueOf() {
        assertEquals(Direction.UP, Direction.valueOf("UP"));
        assertEquals(Direction.RIGHT, Direction.valueOf("RIGHT"));
        assertThrows(IllegalArgumentException.class, () -> Direction.valueOf("INVALID"));
    }
}