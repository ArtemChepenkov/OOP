package ru.nsu.chepenkov;

public class Table {
    private int dealerScore, playerScore, dealerIndex, playerIndex;

    public Table() {
        this.dealerIndex = 0;
        this.playerIndex = 0;
        this.dealerScore = 0;
        this.playerScore = 0;
    }

    public void dealingCards(Card[] dealerCards, Card[] playerCards, CardDeck cardDeck){
        Card curCard1, curCard2;

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

    public void showCards(String whoseCards, Card[] cards, boolean needClosedCard) {
        int curInd;
        int curScore;
        whoseCards = whoseCards.toLowerCase();
        if(whoseCards.equals("player")){
            curInd = this.playerIndex;
            curScore = this.playerScore;
            System.out.print("\tВаши карты: [");
        } else{
            curInd = this.dealerIndex;
            curScore = this.dealerScore;
            System.out.print("\tКарты дилера: [");
        }

        for(int i = 0; i < curInd; i++){
            if (needClosedCard && i == curInd - 1) {
                System.out.print("<закрытая карта>]\n");
                break;
            }
            System.out.print(cards[i].getName() + " " +
                    cards[i].getSuit() +
                    "(" + cards[i].getNumber() + ")");
            if(i != curInd - 1) {
                System.out.print(", ");
            } else {
                System.out.print("] => " + curScore +"\n");
            }
        }
    }

    public boolean checkValid(int score) {
        if (score > 21) {
            return false;
        }
        return true;
    }

    public String checkWinner() {
        if (this.playerScore > this.dealerScore) {
            return "player";
        }

        if (this.playerScore < this.dealerScore) {
            return "dealer";
        }

        return "draw";

    }

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

    public int getDealerIndex() {
        return dealerIndex;
    }

    public int getPlayerIndex() {
        return playerIndex;
    }

    public int getDealerScore() {
        return dealerScore;
    }

    public int getPlayerScore() {
        return playerScore;
    }

    public void setDealerScore(int score) {
        this.dealerScore += score;
    }

    public void setPlayerScore(int score) {
        this.playerScore += score;
    }

    public void increasePlayerIndex() {
        this.playerIndex++;
    }

    public void increaseDealerIndex() {
        this.dealerIndex++;
    }
}
