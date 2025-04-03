package ru.nsu.chepenkov.snake;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

/**
 * Тесты для класса Snake.
 */
public class TestSnake {
    private Snake snake;
    private Model model;

    @BeforeEach
    public void setUp() {
        snake = new Snake(5, 5);
        model = new Model(10, 10, 1, 5);
    }

    /**
     * Проверяет корректность конструктора и начального состояния.
     */
    @Test
    public void testConstructor() {
        assertEquals(1, snake.getLength());
        assertEquals(Direction.RIGHT, snake.getDirection());
        Coordinate head = snake.getSnakeHead();
        assertEquals(5, head.getX());
        assertEquals(5, head.getY());
    }

    /**
     * Проверяет движение змейки вправо.
     */
    @Test
    public void testMoveRight() {
        snake.setDirection(Direction.RIGHT);
        boolean moved = snake.move(snake.getDirection(), model);
        assertTrue(moved);
        assertEquals(6, snake.getSnakeHead().getX());
        assertEquals(5, snake.getSnakeHead().getY());
    }

    /**
     * Проверяет движение змейки вниз.
     */
    @Test
    public void testMoveDown() {
        snake.setDirection(Direction.DOWN);
        boolean moved = snake.move(snake.getDirection(), model);
        assertTrue(moved);
        assertEquals(5, snake.getSnakeHead().getX());
        assertEquals(6, snake.getSnakeHead().getY());
    }

    /**
     * Проверяет столкновение с границей.
     */
    @Test
    public void testMoveOutOfBounds() {
        snake = new Snake(9, 5);
        snake.setDirection(Direction.RIGHT);
        boolean moved = snake.move(snake.getDirection(), model);
        assertFalse(moved);
    }

    /**
     * Проверяет рост змейки.
     */
    @Test
    public void testGrow() {
        snake.grow();
        assertEquals(2, snake.getLength());
        List<Coordinate> body = snake.getSnakeBody();
        assertEquals(body.get(0), body.get(1));
    }

    /**
     * Проверяет изменение направления.
     */
    @Test
    public void testSetDirection() {
        snake.setDirection(Direction.DOWN);
        assertEquals(Direction.DOWN, snake.getDirection());
        snake.setDirection(Direction.UP); // Противоположное направление
        assertEquals(Direction.DOWN, snake.getDirection()); // Не должно измениться
    }

    /**
     * Проверяет столкновение с телом змейки.
     */
    @Test
    public void testSelfCollision() {
        model = new Model(10, 10, 0, 5);
        snake = model.getSnake();
        snake.grow();
        snake.grow();
        snake.setDirection(Direction.RIGHT);
        snake.move(Direction.RIGHT, model);
        snake.setDirection(Direction.DOWN);
        snake.move(Direction.DOWN, model);
        snake.setDirection(Direction.LEFT);
        snake.move(Direction.LEFT, model);
        snake.setDirection(Direction.UP);
        boolean moved = snake.move(Direction.UP, model);
        assertTrue(moved);
    }
}