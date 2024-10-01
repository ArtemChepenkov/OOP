package ru.nsu.chepenkov.blackjack;

/**
 * Описывает карты.
 */

public enum Rank {
    TW0(2, "Двойка"),
    THREE(3, "Тройка"),
    FOUR(4, "Четвёрка"),
    FIVE(5, "Пятёрка"),
    SIX(6, "Шестёрка"),
    SEVEN(7, "Семёрка"),
    EIGHT(8, "Восьмёрка"),
    NINE(9, "Девятка"),
    TEN(10, "Десятка"),
    JACK(10, "Валет"),
    QUEEN(10, "Дама"),
    KING(10, "Король"),
    ACE(11, "Туз");
    private final int value;
    private final String name;

    Rank(int value, String name) {
        this.value = value;
        this.name = name;
    }

    public int getRankValue() {
        return value;
    }

    public String getRankName() {
        return name;
    }

}