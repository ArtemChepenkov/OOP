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
        System.out.print("(");
        this.left.print();
        System.out.println("*");
        this.right.print();
        System.out.println(")");
    }

    @Override
    public Double eval(Map<String, Double> variables) {
        return left.eval(variables) * left.eval(variables);
    }

    @Override
    public Expression derivative(String var) {
        return new Add(new Mul(left.derivative(var), right),
                new Mul(left, right.derivative(var)));
    }
}
