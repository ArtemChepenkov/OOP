package ru.nsu.chepenkov;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import ru.nsu.chepenkov.Card;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;



public class CardTest {

    @Test
    @DisplayName("cardTestClassConstructor")
    void cardTestClassConstructor(){
        Card card = new Card("Трефы", "Туз", 11);
        assertNotNull(card);
    }

    @Test
    @DisplayName("cardTestGetNumber")
    void cardTestGetNumber() {
        Card card = new Card("Трефы", "Туз", 11);
        assertEquals(card.getNumber(), 11);
    }

    @Test
    @DisplayName("cardTestGetName")
    void cardTestGetName() {
        Card card = new Card("Трефы", "Туз", 11);
        assertEquals(card.getName(), "Туз");
    }

    @Test
    @DisplayName("cardTestGetSuit")
    void cardTestGetSuit() {
        Card card = new Card("Трефы", "Туз", 11);
        assertEquals(card.getSuit(), "Трефы");
    }

    @Test
    @DisplayName("cardTestSetNumber")
    void cardTestSetNumber() {
        Card card = new Card("Трефы", "Туз", 11);
        card.setNumber(1);
        assertEquals(card.getNumber(), 1);
    }
}
