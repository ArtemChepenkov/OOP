package ru.nsu.chepenkov;

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

    @Test
    @DisplayName("cardDeckTestClassConstructor")
    void cardDeckTestClassConstructor() {
        final int deckAmount = 2;
        int counter = 0;
        CardDeck cardDeck = new CardDeck(deckAmount);
        Card[] testCards = {new Card("Червы", "Тройка", 3),
                new Card("Буби", "Король", 10),
                new Card("Пики", "Девятка", 9),
                new Card("Трефы", "Туз", 11)};
        for (int i = 0; i < testCards.length; i++) {
            for (int j = 0; j < 52 * deckAmount; j++) {
                if (testCards[i].getNumber() == cardDeck.cards[j].getNumber() &&
                        testCards[i].getName().equals(cardDeck.cards[j].getName()) &&
                        testCards[i].getSuit().equals(cardDeck.cards[j].getSuit())) {
                    counter++;
                }
            }
        }
        assertEquals(counter, testCards.length * deckAmount);
        assertNotNull(cardDeck);
    }

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
