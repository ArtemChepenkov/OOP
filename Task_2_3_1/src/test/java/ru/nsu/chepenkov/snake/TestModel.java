package ru.nsu.chepenkov.snake;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Тесты для класса Model.
 */
public class TestModel {
    private Model model;

    @BeforeEach
    public void setUp() {
        model = new Model(10, 10, 2, 5);
    }

    /**
     * Проверяет корректность конструктора и начального состояния.
     */
    @Test
    public void testConstructor() {
        assertEquals(10, model.getWidth());
        assertEquals(10, model.getHeight());
        assertEquals(2, model.getFoodCount());
        assertEquals(5, model.getTargetScore());
        assertFalse(model.isGameOver());
        assertFalse(model.isWon());
    }

    /**
     * Проверяет исключение при некорректных параметрах конструктора.
     */
    @Test
    public void testConstructorInvalidParameters() {
        assertThrows(IllegalArgumentException.class, () -> new Model(-1, 10, 1, 5));
        assertThrows(IllegalArgumentException.class, () -> new Model(10, 0, 1, 5));
        assertThrows(IllegalArgumentException.class, () -> new Model(10, 10, 1, -5));
    }

    /**
     * Проверяет движение и потребление еды.
     */
    @Test
    public void testMovementEatFood() {
        model = new Model(5, 5, 1, 5);
        model.getFood().clear();
        model.getFood().add(new Food(3, 2));
        model.getSnake().setDirection(Direction.RIGHT);
        model.movement(); // Голова в (3,2)
        assertEquals(1, model.getScore());
        assertEquals(1, model.getFoodCount());
    }

    /**
     * Проверяет проигрыш при столкновении с границей.
     */
    @Test
    public void testGameOver() {
        model.getSnake().setDirection(Direction.LEFT);
        for (int i = 0; i < 6; i++) {
            model.movement();
        }
        assertTrue(model.isGameOver());
    }

    /**
     * Проверяет условие победы.
     */
    @Test
    public void testWinCondition() {
        model = new Model(5, 5, 5, 2);
        model.getSnake().grow();
        model.getSnake().grow();
        model.movement();
        assertTrue(model.isWon());
    }
}