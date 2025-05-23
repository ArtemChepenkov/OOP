package ru.nsu.chepenkov.snake;

/**
 * Класс, представляющий еду на игровом поле в игре "Змейка".
 * Наследуется от Coordinate для хранения позиции еды.
 */
public class Food extends Coordinate {

    /**
     * Создаёт новый объект еды с заданными координатами.
     *
     * @param x координата X еды
     * @param y координата Y еды
     * @throws IllegalArgumentException если координаты отрицательные
     */
    public Food(int x, int y) {
        super(x, y);
        if (x < 0 || y < 0) {
            throw new IllegalArgumentException("Координаты еды "
                    + "не могут быть отрицательными: x=" + x + ", y=" + y);
        }
    }
}