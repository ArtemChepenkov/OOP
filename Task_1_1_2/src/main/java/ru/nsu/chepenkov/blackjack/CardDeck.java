package ru.nsu.chepenkov.blackjack;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Класс, который реализует собой колоду карт(состоящую из нескольких колод).
 *
 * @author Artem Chepenkov
 */
public class CardDeck {
    private final List<Card> list = new ArrayList<>();
    final List<Card> cards;

    /**
     * Конструктор класса.
     */
    public CardDeck(int deckAmount) {
        Suit[] suits = Suit.values();
        for (int i = 0; i < deckAmount; i++) {
            for (Suit suit : suits) {
                Rank[] ranks = Rank.values();
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
