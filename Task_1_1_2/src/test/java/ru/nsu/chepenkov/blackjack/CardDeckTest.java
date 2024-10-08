package ru.nsu.chepenkov.blackjack;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

/**
 * Класс CardDeckTest предназначен для тестирования класса CardDeck.
 *
 * @author Artem Chepenkov
 */
public class CardDeckTest {

    /**
     * Функция тестирует, как создаётся общая колода.
     * Я взял четыре карты, и проверил лежат ли они в колоде
     * По моему предположению, если есть эти четыре карты, то в колоде есть и все карты
     */
    @Test
    @DisplayName("cardDeckTestClassConstructor")
    void cardDeckTestClassConstructor() {
        final int deckAmount = 2;
        int counter = 0;
        CardDeck cardDeck = new CardDeck(deckAmount);
        Card[] testCards = {new Card(Rank.THREE, Suit.CLUB),
                            new Card(Rank.KING, Suit.HEART),
                            new Card(Rank.NINE, Suit.DIAMOND),
                            new Card(Rank.ACE, Suit.SPADE)};
        for (int i = 0; i < testCards.length; i++) {
            for (int j = 0; j < 52 * deckAmount; j++) {
                if (testCards[i].getNumber() == cardDeck.cards.get(j).getNumber()
                        && testCards[i].getName().equals(cardDeck.cards.get(j).getName())
                        && testCards[i].getSuit().equals(cardDeck.cards.get(j).getSuit())) {
                    counter++;
                }
            }
        }
        assertEquals(counter, testCards.length * deckAmount);
        assertNotNull(cardDeck);
    }

    /**
     * Функция проверяет достаётся ли крайняя карта.
     */
    @Test
    @DisplayName("cardDeckTestTakeLastCard")
    void cardDeckTestTakeLastCard() {
        final int deckAmount = 2;
        CardDeck cardDeck = new CardDeck(deckAmount);
        Card card;
        card = cardDeck.takeLastCard();
        assertNotNull(card);
    }
}
