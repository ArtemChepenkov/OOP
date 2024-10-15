package ru.nsu.chepenkov.parser;

import java.util.Map;

/**
 * Абстракный ласс для описания выражения.
 *
 * @author ArtemChepenkov
 */

public abstract class Expression {
    private String expression;

    public abstract void print();

    public abstract Double eval(Map<String, Double> variables);

    public abstract Expression derivative(String var);

    public abstract String toString();
}
