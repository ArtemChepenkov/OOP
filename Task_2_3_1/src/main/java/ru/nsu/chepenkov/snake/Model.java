package ru.nsu.chepenkov.snake;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Класс модели игры "Змейка".
 * Управляет логикой игры, включая движение змейки, генерацию еды и проверку условий окончания.
 */
public class Model {
    private final int width;
    private final int height;
    private final int targetScore;

    private Snake snake;
    private final List<Food> foodList = new ArrayList<>();
    private boolean gameOver;
    private boolean isWon;
    private final Random random = new Random();

    /**
     * Создаёт модель игры с заданными параметрами.
     *
     * @param width ширина игрового поля в клетках
     * @param height высота игрового поля в клетках
     * @param foodCount количество объектов еды для генерации
     * @param targetScore счёт, необходимый для победы
     * @throws IllegalArgumentException если размеры поля или счёт отрицательные
     */
    public Model(int width, int height, int foodCount, int targetScore) {
        if (width <= 0 || height <= 0 || targetScore <= 0) {
            throw new IllegalArgumentException("Размеры поля и целевой "
                    + "счёт должны быть положительными");
        }
        this.width = width;
        this.height = height;
        this.targetScore = targetScore;
        this.snake = new Snake(width / 2, height / 2);
        this.gameOver = false;
        this.isWon = false;
        for (int i = 0; i < foodCount; i++) {
            generateFood();
        }
    }

    /**
     * Выполняет шаг игры: перемещает змейку, проверяет столкновения и еду.
     */
    public void movement() {
        if (gameOver) {
            return;
        }

        if (!snake.move(snake.getDirection(), this)) {
            gameOver = true;
            return;
        }

        Coordinate head = snake.getSnakeHead();
        if (consumeFood(head)) {
            snake.grow();
        }

        if (snake.getLength() - 1 >= targetScore) {
            isWon = true;
        }
    }

    /**
     * Проверяет и удаляет еду, если змейка её съела, генерируя новую.
     *
     * @param head координаты головы змейки
     * @return true, если еда была съедена, иначе false
     */
    private boolean consumeFood(Coordinate head) {
        for (int i = 0; i < foodList.size(); i++) {
            if (foodList.get(i).equals(head)) {
                foodList.remove(i);
                generateFood();
                return true;
            }
        }
        return false;
    }

    /**
     * Генерирует новую еду на случайной свободной позиции.
     */
    void generateFood() {
        Food newFood;
        do {
            newFood = new Food(random.nextInt(width), random.nextInt(height));
        } while (isPositionOccupied(newFood));

        foodList.add(newFood);
    }

    /**
     * Проверяет, занята ли заданная позиция змейкой или другой едой.
     *
     * @param position проверяемая позиция
     * @return true, если позиция занята, иначе false
     */
    private boolean isPositionOccupied(Food position) {
        for (Coordinate coord : snake.getSnakeBody()) {
            if (coord.equals(position)) {
                return true;
            }
        }
        for (Food food : foodList) {
            if (food.equals(position)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Возвращает ширину игрового поля.
     *
     * @return ширина поля
     */
    public int getWidth() {
        return width;
    }

    /**
     * Возвращает высоту игрового поля.
     *
     * @return высота поля
     */
    public int getHeight() {
        return height;
    }

    /**
     * Возвращает текущий счёт игрока (длина змейки минус начальная длина).
     *
     * @return текущий счёт
     */
    public int getScore() {
        return snake.getLength() - 1;
    }

    /**
     * Возвращает количество объектов еды на поле.
     *
     * @return размер списка еды
     */
    public int getFoodCount() {
        return foodList.size();
    }

    /**
     * Проверяет, достиг ли игрок победного условия.
     *
     * @return true, если игрок победил, иначе false
     */
    public boolean isWon() {
        return isWon;
    }

    /**
     * Проверяет, закончилась ли игра.
     *
     * @return true, если игра окончена, иначе false
     */
    public boolean isGameOver() {
        return gameOver;
    }

    /**
     * Возвращает целевой счёт для победы.
     *
     * @return целевой счёт
     */
    public int getTargetScore() {
        return targetScore;
    }

    /**
     * Возвращает объект змейки.
     *
     * @return объект змейки
     */
    public Snake getSnake() {
        return snake;
    }

    /**
     * Возвращает список объектов еды на поле.
     *
     * @return список еды
     */
    public List<Food> getFood() {
        return foodList;
    }
}