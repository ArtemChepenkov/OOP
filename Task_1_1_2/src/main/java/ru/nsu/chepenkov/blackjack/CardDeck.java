package ru.nsu.chepenkov.blackjack;

import java.util.ArrayList;
import java.util.Collections;

/**
 * Класс, который реализует собой колоду карт(состоящую из нескольких колод).
 *
 * @author Artem Chepenkov
 */
public class CardDeck {
    private final ArrayList<Card> list = new ArrayList<>();
    Rank[] ranks = new Rank[] {Rank.TW0, Rank.THREE, Rank.FOUR, Rank.FIVE, Rank.SIX,
            Rank.SEVEN, Rank.EIGHT, Rank.NINE, Rank.TEN,
            Rank.JACK, Rank.QUEEN, Rank.KING, Rank.ACE};
    final ArrayList<Card> cards;
    private int counter;

    /**
     * Конструктор класса.
     */
    public CardDeck(int deckAmount) {
        String[] suits = {"Червы", "Буби", "Трефы", "Пики"};
        for (int i = 0; i < deckAmount; i++) {
            for (String suit : suits) {
                for (Rank rank : ranks) {
                    list.add(new Card(rank, suit));
                }
            }
        }
        Collections.shuffle(list);
        this.cards = list;
   }

    /**
     * Функция для доставания крайней карты.
     */
    public Card takeLastCard() {
        return list.remove(list.size() - 1);
    }
}
