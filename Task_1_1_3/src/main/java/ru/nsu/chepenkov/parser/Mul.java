package ru.nsu.chepenkov.parser;

import java.util.Map;

public class Mul extends Expression {

    private final Expression left;
    private final Expression right;

    public Mul(Expression left, Expression right) {
        this.left = left;
        this.right = right;
    }

    @Override
    public void print() {
        System.out.println(this.toString());
    }

    @Override
    public Double eval(Map<String, Double> variables) {
        return left.eval(variables) * right.eval(variables);
    }

    @Override
    public Expression derivative(String var) {
        return new Add(new Mul(left.derivative(var), right),
                new Mul(left, right.derivative(var)));
    }

    @Override
    public String toString() {
        return "(" + left.toString() + " * " + right.toString() + ")";
    }
}
