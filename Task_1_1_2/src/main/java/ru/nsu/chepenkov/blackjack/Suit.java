package ru.nsu.chepenkov.blackjack;

/**
 * Описывает масти.
 */

public enum Suit {
    DIAMOND("Буби"),
    CLUB("Трефы"),
    HEART("Червы"),
    SPADE("Пики");

    private final String suit;

    Suit(String suit) {
        this.suit = suit;
    }
}