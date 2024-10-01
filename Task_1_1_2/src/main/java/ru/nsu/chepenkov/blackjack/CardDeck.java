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
    Rank[] ranks = Rank.values();

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
        ArrayList<Card> cards = (ArrayList<Card>) list;
    }

    /**
     * Функция для доставания крайней карты.
     */
    public Card takeLastCard() {
        return list.remove(list.size() - 1);
    }
}
