package ru.nsu.chepenkov;

import java.util.Scanner;

public class Main {
    
    public static int checkHand(Table table, Card[] cards, int score, int index) {
        int subScore = 0;
        if (score > 21) {
            for (int i = 0; i < index; i++) {
                if (cards[i].getNumber() == 11){
                    subScore += 10;
                    cards[i].setNumber(1);
                }
            }
        }
        return subScore;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String curWinner;
        final int deckAmount = 2;
        final int roundAmount = 5;
        final int maxCards = 20;
        int playerChoice;
        int playerWins = 0;
        int dealerWins = 0;
        boolean needSkip;
        boolean isValid;
        Card tempCard;

        System.out.println("Добро пожаловать в Блэкджек!");
        for (int i = 1; i <= roundAmount; i++) {
            needSkip = false;
            System.out.println("Раунд " + i);
            System.out.println("Дилер раздал карты");

            Table table = new Table();
            CardDeck cardDeck = new CardDeck(deckAmount);
            Card[] dealerCards = new Card[maxCards];
            Card[] playerCards = new Card[maxCards];


            table.dealingCards(dealerCards, playerCards, cardDeck);

            table.showCards("player", playerCards, false);
            table.showCards("dealer", dealerCards, true);

            System.out.println("Ваш ход");
            System.out.println("-------");
            System.out.println("Введите “1”, чтобы взять карту, и “0”, чтобы остановиться .");

            playerChoice = scanner.nextInt();

            while (playerChoice == 1){

                tempCard = cardDeck.takeLastCard();

                if (tempCard.getName().equals("Туз") && table.getPlayerScore() > 11) {
                    tempCard.setNumber(1);
                }

                System.out.println("Вы открыли карту" + " " +
                        tempCard.getName() + " " + tempCard.getSuit() +
                        "(" + tempCard.getNumber() + ")");
                table.setPlayerScore(tempCard.getNumber());
                playerCards[table.getPlayerIndex()] = tempCard;
                table.increasePlayerIndex();
                int subScore = checkHand(table, playerCards, table.getPlayerScore(), table.getPlayerIndex());
                table.setPlayerScore(-subScore);
                table.showCards("player", playerCards, false);
                isValid = table.checkValid(table.getPlayerScore());
                if (!isValid) {
                    System.out.println("Сумма очков больше 21-го.\nВы проиграли раунд");
                    dealerWins++;
                    System.out.print("Счёт " + playerWins + ":" + dealerWins);
                    table.whoHasMoreWins(playerWins, dealerWins);
                    needSkip = true;
                    break;
                }
                playerChoice = scanner.nextInt();
            }

            if (needSkip){
                continue;
            }

            System.out.println("Ход диллера");
            System.out.println("-------");
            System.out.print("Диллер открывает закрытую карту ");
            System.out.print(dealerCards[table.getDealerIndex() - 1].getName() + " " +
                    dealerCards[table.getDealerIndex() - 1].getSuit() + " " +
                    "(" + dealerCards[table.getDealerIndex() - 1].getNumber() + ")\n");

            table.showCards("dealer", dealerCards, false);

            while (table.getDealerScore() <= 17) {
                tempCard = cardDeck.takeLastCard();
                if (tempCard.getName().equals("Туз") && table.getDealerScore() > 11) {
                    tempCard.setNumber(1);
                }
                dealerCards[table.getDealerIndex()] = tempCard;
                table.increaseDealerIndex();
                table.setDealerScore(tempCard.getNumber());
                isValid = table.checkValid(table.getDealerScore());
                int subScore = checkHand(table, dealerCards, table.getDealerScore(),
                        table.getDealerIndex());
                table.setDealerScore(-subScore);
                table.showCards("dealer", dealerCards, false);
                if (!isValid) {
                    playerWins++;
                    System.out.println("У диллера сумма очков больше 21-го.\nВы выиграли раунд");
                    System.out.print("Счёт " + playerWins + ":" + dealerWins);
                    table.whoHasMoreWins(playerWins, dealerWins);
                    needSkip = true;
                }
            }
            if (needSkip){
                continue;
            }
            System.out.println();
            curWinner = table.checkWinner();
            if (curWinner.equals("player")) {
                System.out.println("Вы выиграли раунд!");
                playerWins++;
            }

            if (curWinner.equals("dealer")) {
                System.out.println("Вы проиграли раунд!");
                dealerWins++;
            }

            if (curWinner.equals("draw")) {
                System.out.println("Раунд закончился вничью!");
            }

            System.out.print("Счёт " + playerWins + ":" + dealerWins);

            table.whoHasMoreWins(playerWins, dealerWins);
        }
        scanner.close();
    }
}