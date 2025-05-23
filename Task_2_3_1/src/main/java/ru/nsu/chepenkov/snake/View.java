package ru.nsu.chepenkov.snake;

import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

/**
 * Класс представления для отрисовки игры "Змейка" на JavaFX Canvas.
 * Отвечает за визуализацию змейки, еды и игрового поля.
 */
public class View {
    private final GraphicsContext graphicsContext;
    private final Canvas canvas;
    private final Model gameModel;
    private final int cellSize;

    /**
     * Создаёт новое представление для игры "Змейка".
     *
     * @param graphicsContext контекст для отрисовки на канвасе
     * @param canvas канвас для отрисовки
     * @param gameModel модель игры
     * @param cellSize размер клетки в пикселях
     */
    public View(GraphicsContext graphicsContext, Canvas canvas, Model gameModel, int cellSize) {
        this.graphicsContext = graphicsContext;
        this.canvas = canvas;
        this.gameModel = gameModel;
        this.cellSize = cellSize;
    }

    /**
     * Отрисовывает текущее состояние игры: фон, змейку и еду.
     */
    public void draw() {
        clearCanvas();
        drawGrid();
        renderSnake();
        renderFood();
    }

    /**
     * Очищает канвас, заливая его фоновым цветом.
     */
    private void clearCanvas() {
        graphicsContext.setFill(Color.DARKGRAY);
        graphicsContext.fillRect(0, 0, canvas.getWidth(), canvas.getHeight());
    }

    /**
     * Отрисовывает сетку игрового поля.
     */
    private void drawGrid() {
        graphicsContext.setStroke(Color.LIGHTGRAY);
        graphicsContext.setLineWidth(0.5);
        for (int x = 0; x <= gameModel.getWidth(); x++) {
            graphicsContext.strokeLine(x * cellSize, 0,
                    x * cellSize, gameModel.getHeight() * cellSize);
        }
        for (int y = 0; y <= gameModel.getHeight(); y++) {
            graphicsContext.strokeLine(0, y * cellSize,
                    gameModel.getWidth() * cellSize, y * cellSize);
        }
    }

    /**
     * Отрисовывает змейку, выделяя голову другим цветом.
     */
    private void renderSnake() {
        for (int i = 0; i < gameModel.getSnake().getLength(); i++) {
            Coordinate segment = gameModel.getSnake().getSnakeBody().get(i);
            graphicsContext.setFill(i == 0 ? Color.LIME : Color.FORESTGREEN);
            graphicsContext.fillRect(segment.getX() * cellSize,
                    segment.getY() * cellSize, cellSize, cellSize);
        }
    }

    /**
     * Отрисовывает все объекты еды на поле.
     */
    private void renderFood() {
        graphicsContext.setFill(Color.ORANGERED);
        gameModel.getFood().forEach(food -> graphicsContext.fillRect(
                food.getX() * cellSize, food.getY() * cellSize, cellSize, cellSize));
    }
}