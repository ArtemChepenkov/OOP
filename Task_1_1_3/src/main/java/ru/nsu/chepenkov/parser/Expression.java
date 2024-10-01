package ru.nsu.chepenkov.parser;

import java.util.Map;

public abstract class Expression {
    private String expression;
    public abstract void print();
    public abstract Double eval(Map<String, Double> variables);
    public abstract Expression derivative(String var);
}
