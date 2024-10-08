package ru.nsu.chepenkov.parser;

import java.util.Map;

public class Add extends Expression{
    private final Expression left;
    private final Expression right;

    public Add(Expression left, Expression right) {
        this.left = left;
        this.right = right;
    }

    @Override
    public void print() {
        System.out.println(this.toString());
    }

    @Override
    public Double eval(Map<String, Double> variables) {
        return this.left.eval(variables) + this.right.eval(variables);
    }

    @Override
    public Expression derivative(String var) {
        return new Add(this.left.derivative(var), this.right.derivative(var));
    }

    @Override
    public String toString() {
        return "(" + left.toString() + " + " + right.toString() + ")";
    }
}
