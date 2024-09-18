package ru.nsu.chepenkov;

/**
 * Класс, описывающий карту.
 * 
 * Описывается масть, название, номинал
 */
public class Card {
    private String suit, name;
    private int number;

    /**
     * Конструктор класса.
     */
    public Card(String suit, String name, int number) {
        this.suit = suit;
        this.name = name;
        this.number = number;
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
