package ru.nsu.chepenkov.blackjack;

/**
 * Класс, который реализует собой стол, на котором и просходит основная игра.
 *
 * @author Artem Chepenkov
 */
public class Table {
    private int dealerScore;
    private int playerScore;
    private int dealerIndex;
    private int playerIndex;

    /**
     * Конструктор класс.
     */
    public Table() {
        this.dealerIndex = 0;
        this.playerIndex = 0;
        this.dealerScore = 0;
        this.playerScore = 0;
    }

    /**
     * Функция для начальной раздачи карт.
     */
    public void dealingCards(Card[] dealerCards, Card[] playerCards, CardDeck cardDeck) {
        Card curCard1;
        Card curCard2;

        curCard1 = cardDeck.takeLastCard();
        curCard2 = cardDeck.takeLastCard();

        dealerCards[this.dealerIndex++] = curCard1;
        dealerCards[this.dealerIndex++] = curCard2;
        this.dealerScore += curCard1.getNumber() + curCard2.getNumber();



        curCard1 = cardDeck.takeLastCard();
        curCard2 = cardDeck.takeLastCard();

        playerCards[this.playerIndex++] = curCard1;
        playerCards[this.playerIndex++] = curCard2;
        this.playerScore += curCard1.getNumber() + curCard2.getNumber();
    }

    /**
     * Функция, которая выводит карты.
     *
     * @param needClosedCard - для случая дилера, когда нужно закрыть карту
     */
    public void showCards(String whoseCards, Card[] cards, boolean needClosedCard) {
        int curInd;
        int curScore;
        whoseCards = whoseCards.toLowerCase();
        if (whoseCards.equals("player")) {
            curInd = this.playerIndex;
            curScore = this.playerScore;
            System.out.print("\tВаши карты: [");
        } else {
            curInd = this.dealerIndex;
            curScore = this.dealerScore;
            System.out.print("\tКарты дилера: [");
        }

        for (int i = 0; i < curInd; i++) {
            if (needClosedCard && i == curInd - 1) {
                System.out.print("<закрытая карта>]\n");
                break;
            }
            System.out.print(cards[i].getName() + " "
                    + cards[i].getSuit()
                    + "(" + cards[i].getNumber() + ")");
            if (i != curInd - 1) {
                System.out.print(", ");
            } else {
                System.out.print("] => " + curScore + "\n");
            }
        }
    }

    /**
     * Функция для проверки счёта на правильность.
     */
    public boolean checkValid(int score) {
        return score <= 21;
    }

    /**
     * Функция для определения победителя.
     */
    public String checkWinner() {
        if (this.playerScore > this.dealerScore) {
            return "player";
        }

        if (this.playerScore < this.dealerScore) {
            return "dealer";
        }

        return "draw";

    }

    /**
     * Функция для определения в чью пользу общий счёт.
     */
    public void whoHasMoreWins(int playerWins, int dealerWins) {
        if (playerWins > dealerWins) {
            System.out.println(" в вашу пользу");
        }

        if (playerWins < dealerWins) {
            System.out.println(" в пользу дилера");
        }

        if (playerWins == dealerWins) {
            System.out.println(" вничью");
        }
    }

    /**
     * Геттер для индекса дилера.
     */
    public int getDealerIndex() {
        return this.dealerIndex;
    }

    /**
     * Геттер для индекса игрока.
     */
    public int getPlayerIndex() {
        return this.playerIndex;
    }

    /**
     * Геттер для счёта дилера.
     */
    public int getDealerScore() {
        return this.dealerScore;
    }

    /**
     * Геттер для счёта игрока.
     */
    public int getPlayerScore() {
        return this.playerScore;
    }

    /**
     * Сеттер для счёта дилера.
     */
    public void setDealerScore(int score) {
        this.dealerScore += score;
    }

    /**
     * Сеттер для счёта игрока.
     */
    public void setPlayerScore(int score) {
        this.playerScore += score;
    }
}
