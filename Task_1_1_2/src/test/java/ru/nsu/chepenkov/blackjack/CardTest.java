package ru.nsu.chepenkov.blackjack;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

/**
 * Класс CardTest предназначен для тестирования класса Card.
 *
 * @author Artem Chepenkov
 */
public class CardTest {

    @Test
    @DisplayName("cardTestClassConstructor")
    void cardTestClassConstructor() {
        Card card = new Card(Rank.ACE, Suit.CLUB);
        assertNotNull(card);
    }

    @Test
    @DisplayName("cardTestGetNumber")
    void cardTestGetNumber() {
        Card card = new Card(Rank.ACE, Suit.CLUB);
        assertEquals(card.getNumber(), 11);
    }

    @Test
    @DisplayName("cardTestGetName")
    void cardTestGetName() {
        Card card = new Card(Rank.ACE, Suit.CLUB);
        assertEquals(card.getName(), "Туз");
    }

    @Test
    @DisplayName("cardTestGetSuit")
    void cardTestGetSuit() {
        Card card = new Card(Rank.ACE, Suit.CLUB);
        assertEquals(card.getSuit(), Suit.CLUB.getSuit());
    }

    @Test
    @DisplayName("cardTestSetNumber")
    void cardTestSetNumber() {
        Card card = new Card(Rank.ACE, Suit.CLUB);
        card.setNumber(1);
        assertEquals(card.getNumber(), 1);
    }
}
