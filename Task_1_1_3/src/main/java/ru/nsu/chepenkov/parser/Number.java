package ru.nsu.chepenkov.parser;

import java.util.Map;

public class Number extends Expression {
    private double value;

    public Number(double value) {
        this.value = value;
    }

    @Override
    public void print() {
        System.out.print(this.value);
    }

    @Override
    public Double eval(Map<String, Double> variables) {
        return this.value;
    }

    @Override
    public Expression derivative(String var) {
        return new Number(0);
    }
}
