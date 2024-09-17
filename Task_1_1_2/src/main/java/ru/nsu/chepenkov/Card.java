package ru.nsu.chepenkov;

public class Card {
    private String suit, name;
    private int number;

    public Card(String suit, String name, int number){
        this.suit = suit;
        this.name = name;
        this.number = number;
    }

    public int getNumber() {
        return number;
    }

    public String getName() {
        return name;
    }

    public String getSuit() {
        return suit;
    }

    public void setNumber(int number) {
        this.number = number;
    }
}
