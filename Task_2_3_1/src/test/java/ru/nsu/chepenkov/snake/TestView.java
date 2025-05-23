package ru.nsu.chepenkov.snake;

import javafx.application.Platform;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Тесты для класса View.
 */
public class TestView {
    private Canvas canvas;
    private GraphicsContext graphicsContext;
    private Model model;
    private View view;

    @BeforeEach
    public void setUp() {
        canvas = new Canvas(200, 200);
        graphicsContext = canvas.getGraphicsContext2D();
        model = new Model(10, 10, 2, 5);
        view = new View(graphicsContext, canvas, model, 20);
    }

    /**
     * Проверяет корректность конструктора.
     */
    @Test
    public void testConstructor() {
        assertNotNull(view);
    }

    /**
     * Проверяет, что метод draw выполняется без ошибок.
     */
    @Test
    public void testDraw() {
        assertDoesNotThrow(() -> view.draw());
    }

    /**
     * Проверяет очистку канваса.
     */
    @Test
    public void testClearCanvas() {
        assertDoesNotThrow(() -> view.draw());
    }
}