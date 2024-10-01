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
        System.out.print("(");
        this.left.print();
        System.out.println("+");
        this.right.print();
        System.out.println(")");
    }

    @Override
    public Double eval(Map<String, Double> variables) {
        return this.left.eval(variables) + this.left.eval(variables);
    }

    @Override
    public Expression derivative(String var) {
        return new Add(this.left.derivative(var), this.right.derivative(var));
    }
}
