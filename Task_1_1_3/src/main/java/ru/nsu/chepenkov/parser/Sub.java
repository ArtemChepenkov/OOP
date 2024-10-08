package ru.nsu.chepenkov.parser;

import java.util.Map;

public class Sub extends Expression {

    private final Expression left;
    private final Expression right;

    public Sub(Expression left, Expression right) {
        this.left = left;
        this.right = right;
    }

    public void print() {
        System.out.println(this.toString());
    }


    public Double eval(Map<String, Double> map) {
        return left.eval(map) - right.eval(map);
    }

    public Expression derivative(String var) {
        return new Sub(left.derivative(var), right.derivative(var));
    }

    @Override
    public String toString() {
        return "(" + left.toString() + " - " + right.toString() + ")";
    }
}