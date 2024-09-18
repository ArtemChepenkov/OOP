package ru.nsu.chepenkov;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;


import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

/**
 * Класс TableTest предназначен для тестирования класса Table.
 *
 * @author Artem Chepenkov
 */
public class TableTest {

    @Test
    @DisplayName("tableTestClassConstructor")
    void tableTestClassConstructor() {
        Table table = new Table();
        assertNotNull(table);
    }

    @Test
    @DisplayName("tableTestDealingCards")
    void tableTestDealingCards() {
        final int deckAmount = 2;
        final int maxCards = 52 * deckAmount;
        Table table = new Table();
        CardDeck cardDeck = new CardDeck(deckAmount);
        Card[] playerCards = new Card[maxCards];
        Card[] dealerCards = new Card[maxCards];
        table.dealingCards(dealerCards, playerCards, cardDeck);
        assertEquals(table.getDealerIndex(),2);
        assertEquals(table.getPlayerIndex(), 2);
    }

    @Test
    @DisplayName("tableTestShowCards")
    void tableTestShowCards() {
        Table table = new Table();
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));

        table.increasePlayerIndex();
        table.increasePlayerIndex();
        table.setPlayerScore(5);
        Card[] cards = {new Card("Трефы", "Двойка", 2),
        new Card("Буби", "Тройка", 3)};

        table.showCards("player", cards, false);

        String expectedOutput = "Ваши карты: [Двойка Трефы(2), Тройка Буби(3)] => 5";
        assertEquals(expectedOutput, outContent.toString().trim());
    }

    @Test
    @DisplayName("tableTestCheckValid")
    void tableTestCheckValid() {
        Table table = new Table();
        boolean res = table.checkValid(22);
        assertFalse(res);
    }

    @Test
    @DisplayName("tableTestCheckWinner")
    void tableTestCheckWinner() {
        Table table = new Table();
        table.setDealerScore(10);
        table.setPlayerScore(11);
        String res = table.checkWinner();
        assertEquals("player", res);
    }

    @Test
    @DisplayName("tableTestWhoHasMoreWins")
    void tableTestWhoHasMoreWins() {
        Table table = new Table();
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));

        table.whoHasMoreWins(1,2);

        String expectedOutput = "в пользу дилера";
        assertEquals(expectedOutput, outContent.toString().trim());
    }

    @Test
    @DisplayName("tableTestGetDealerIndex")
    void tableTestGetDealerIndex() {
        Table table = new Table();
        assertEquals(0, table.getDealerIndex());
    }

    @Test
    @DisplayName("tableTestGetPlayerIndex")
    void tableTestGetPlayerIndex() {
        Table table = new Table();
        assertEquals(0, table.getPlayerIndex());
    }

    @Test
    @DisplayName("tableTestGetDealerScore")
    void tableTestGetDealerScore() {
        Table table = new Table();
        assertEquals(0, table.getDealerScore());
    }

    @Test
    @DisplayName("tableTestGetPlayerScore")
    void tableTestGetPlayerScore() {
        Table table = new Table();
        assertEquals(0, table.getPlayerScore());
    }

    @Test
    @DisplayName("tableTestSetDealerScore")
    void tableTestSetDealerScore() {
        Table table = new Table();
        table.setDealerScore(11);
        assertEquals(11, table.getDealerScore());
    }

    @Test
    @DisplayName("tableTestSetPlayerScore")
    void tableTestSetPlayerScore() {
        Table table = new Table();
        table.setPlayerScore(11);
        assertEquals(11, table.getPlayerScore());
    }

    @Test
    @DisplayName("tableTestIncreasePlayerIndex")
    void tableTestIncreasePlayerIndex() {
        Table table = new Table();
        table.increasePlayerIndex();
        assertEquals(1, table.getPlayerIndex());
    }

    @Test
    @DisplayName("tableTestIncreaseDealerIndex")
    void tableTestIncreaseDealerIndex() {
        Table table = new Table();
        table.increaseDealerIndex();
        assertEquals(1, table.getDealerIndex());
    }
}
