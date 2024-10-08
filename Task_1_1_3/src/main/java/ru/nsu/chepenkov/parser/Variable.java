package ru.nsu.chepenkov.parser;

import java.util.Map;

public class Variable extends Expression {
    private String variable;

    public Variable(String variable) {
        this.variable = variable;
    }
    @Override
    public void print() {
        System.out.print(this.toString());
    }

    @Override
    public Double eval(Map<String, Double> variables) {
        return variables.get(variable);
    }

    @Override
    public Expression derivative(String var) {
        return var.equals(this.variable) ? new Number(1) : new Number(0);
    }

    @Override
    public String toString() {
        return variable;
    }
}
