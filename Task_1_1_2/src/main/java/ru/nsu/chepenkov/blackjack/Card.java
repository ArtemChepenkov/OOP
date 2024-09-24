package ru.nsu.chepenkov.blackjack;

/**
 * Класс, описывающий карту.
 * Описывается масть, название, номинал
 */
public class Card {
    final private String suit;
    final private String name;
    private int number;

    /**
     * Конструктор класса.
     */
    public Card(Rank name, String suit) {
        this.suit = suit;
        this.name = name.getRankName();
        this.number = name.getRankValue();
    }

    /**
     * Геттер для номинала.
     */
    public int getNumber() {
        return number;
    }

    /**
     * Геттер для имени.
     */
    public String getName() {
        return name;
    }

    /**
     * Геттер для масти.
     */
    public String getSuit() {
        return suit;
    }

    /**
     * Сеттер для номинала.
     */
    public void setNumber(int number) {
        this.number = number;
    }
}
