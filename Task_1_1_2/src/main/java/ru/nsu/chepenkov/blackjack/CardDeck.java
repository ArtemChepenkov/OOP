package ru.nsu.chepenkov.blackjack;

import java.util.Random;

/**
 * Класс, который реализует собой колоду карт(состоящую из нескольких колод).
 *
 * @author Artem Chepenkov
 */
public class CardDeck {
    private String[] suits = {"Червы", "Буби", "Трефы", "Пики"};
    private String[] names = {"Двойка", "Тройка", "Четвёрка",
                              "Пятёрка", "Шестёрка", "Семёрка", "Восьмёрка",
                              "Девятка", "Десятка", "Валет", "Дама",
                              "Король", "Туз"};
    Card[] cards;
    private int counter;

    /**
     * Конструктор класса.
     */
    public CardDeck(int deckAmount) {
        Random random = new Random();
        Card[] array = new Card[52 * deckAmount];
        Card temp;
        this.counter = 52 * deckAmount;
        for (int k = 0; k < deckAmount; k++) {
            for (int i = 0; i < 4; i++) {
                for (int j = 0; j <= 12; j++) {
                    array[(13 * i + j) + 52 * k] = new Card(suits[i],
                            names[j], j == 12 ? 11 : (j > 8 ? 10 : j + 2));
                }
            }
            this.cards = array;
        }
        for (int i = 52 * deckAmount - 1; i > 0; i--) {
            int j = random.nextInt(i + 1);
            temp = this.cards[i];
            this.cards[i] = this.cards[j];
            this.cards[j] = temp;
        }
    }

    /**
     * Функция для достования крайней карты.
     */
    public Card takeLastCard() {
        this.counter--;
        return this.cards[this.counter];

    }
}
