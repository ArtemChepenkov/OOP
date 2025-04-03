package ru.nsu.chepenkov.snake;

import java.util.ArrayList;
import java.util.List;

/**
 * Класс, представляющий змейку в игре "Змейка".
 * Управляет положением, направлением движения и ростом змейки.
 */
public class Snake {
    private final List<Coordinate> body = new ArrayList<>();
    private Direction currentDirection;
    private Direction previousDirection;

    /**
     * Создаёт новую змейку с начальной позицией и направлением.
     *
     * @param startX начальная координата X головы змейки
     * @param startY начальная координата Y головы змейки
     */
    public Snake(int startX, int startY) {
        body.add(new Coordinate(startX, startY));
        currentDirection = Direction.RIGHT;
        previousDirection = Direction.RIGHT;
    }

    /**
     * Возвращает текущую длину змейки.
     *
     * @return количество сегментов тела змейки
     */
    public int getLength() {
        return body.size();
    }

    /**
     * Возвращает текущее направление движения змейки.
     *
     * @return направление движения
     */
    public Direction getDirection() {
        return currentDirection;
    }

    /**
     * Устанавливает новое направление движения, если оно не противоположно текущему.
     *
     * @param newDirection новое направление
     */
    public void setDirection(Direction newDirection) {
        if ((currentDirection == Direction.UP && newDirection != Direction.DOWN)
                || (currentDirection == Direction.DOWN && newDirection != Direction.UP)
                || (currentDirection == Direction.LEFT && newDirection != Direction.RIGHT)
                || (currentDirection == Direction.RIGHT && newDirection != Direction.LEFT)) {
            previousDirection = currentDirection;
            currentDirection = newDirection;
        }
    }

    /**
     * Возвращает список координат тела змейки.
     *
     * @return список координат сегментов
     */
    public List<Coordinate> getSnakeBody() {
        return new ArrayList<>(body); // Возвращаем копию для защиты от изменений
    }

    /**
     * Возвращает координаты головы змейки.
     *
     * @return координаты головы
     */
    public Coordinate getSnakeHead() {
        return body.get(0);
    }

    /**
     * Перемещает голову змейки в новую позицию.
     *
     * @param head новые координаты головы
     */
    public void setSnakeHead(Coordinate head) {
        body.add(0, head);
    }

    /**
     * Увеличивает длину змейки, добавляя новый сегмент в конец.
     */
    public void grow() {
        Coordinate tail = body.get(body.size() - 1);
        body.add(new Coordinate(tail.getX(), tail.getY()));
    }

    /**
     * Выполняет движение змейки в заданном направлении.
     *
     * @param direction направление движения
     * @param model модель игры для проверки границ
     * @return true, если движение успешно, иначе false
     */
    public boolean move(Direction direction, Model model) {
        previousDirection = currentDirection;
        Coordinate newHead = calculateNewHead(getSnakeHead(), direction);
        if (checkCollision(model, newHead)) {
            return false;
        }
        setSnakeHead(newHead);
        body.remove(body.size() - 1);
        return true;
    }

    /**
     * Проверяет столкновение головы змейки с границами или телом.
     *
     * @param model модель игры
     * @param head координаты новой головы
     * @return true, если произошло столкновение, иначе false
     */
    public boolean checkCollision(Model model, Coordinate head) {
        if (head.getX() >= model.getWidth() || head.getX() < 0
                || head.getY() >= model.getHeight() || head.getY() < 0) {
            return true;
        }
        for (Coordinate segment : body) {
            if (segment.equals(head)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Вычисляет новые координаты головы на основе текущего направления.
     *
     * @param head текущие координаты головы
     * @param direction направление движения
     * @return новые координаты головы
     */
    public Coordinate calculateNewHead(Coordinate head, Direction direction) {
        return switch (direction) {
            case UP -> new Coordinate(head.getX(), head.getY() - 1);
            case DOWN -> new Coordinate(head.getX(), head.getY() + 1);
            case LEFT -> new Coordinate(head.getX() - 1, head.getY());
            case RIGHT -> new Coordinate(head.getX() + 1, head.getY());
            default -> throw new IllegalStateException("Неизвестное направление: " + direction);
        };
    }
}