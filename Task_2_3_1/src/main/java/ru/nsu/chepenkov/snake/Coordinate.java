package ru.nsu.chepenkov.snake;

/**
 * Класс, представляющий координаты на двумерной плоскости.
 * Используется для хранения и сравнения позиций объектов в игре "Змейка".
 */
public class Coordinate {

    private final int x;
    private final int y;

    /**
     * Создаёт новый объект координат с заданными значениями X и Y.
     *
     * @param x координата по оси X
     * @param y координата по оси Y
     */
    public Coordinate(int x, int y) {
        this.x = x;
        this.y = y;
    }

    /**
     * Сравнивает текущий объект с другим объектом на равенство координат.
     *
     * @param object объект для сравнения
     * @return true, если координаты совпадают, иначе false
     */
    @Override
    public boolean equals(Object object) {
        if (object instanceof Coordinate) {
            Coordinate other = (Coordinate) object;
            return this.x == other.getX() && this.y == other.getY();
        }
        return false;
    }

    /**
     * Вычисляет хэш-код на основе координат X и Y.
     *
     * @return хэш-код объекта
     */
    @Override
    public int hashCode() {
        return super.hashCode();
    }

    /**
     * Возвращает координату по оси X.
     *
     * @return значение X
     */
    public int getX() {
        return x;
    }

    /**
     * Возвращает координату по оси Y.
     *
     * @return значение Y
     */
    public int getY() {
        return y;
    }
}