package ru.nsu.chepenkov.parser;

import java.util.Map;
import java.util.Objects;

/**
 * Класс для описания операции Делениям.
 *
 * @author ArtemChepenkov
 */

public class Div extends Expression {

    private final Expression left;
    private final Expression right;

    /**Конструктор класса.*/
    public Div(Expression left, Expression right) {
        this.left = left;
        this.right = right;
    }

    /**Функция для вывода с использованием toString.*/
    @Override
    public void print() {
        System.out.print(this.toString());
    }

    /**Функция для подсчёта выражения.*/
    public Double eval(Map<String, Double> map) {
        if (right.eval(map) == 0) {
            throw new ArithmeticException("/0");
        }
        return left.eval(map) / right.eval(map);
    }

    @Override
    public Expression derivative(String var) {
        return new Div(new Sub(new Mul(left.derivative(var), right),
                new Mul(left, right.derivative(var))),
                new Mul(right.derivative(var), right.derivative(var)));
    }

    @Override
    public String toString() {
        return "(" + left.toString() + " / " + right.toString() + ")";
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj == null || obj.getClass() != this.getClass()) {
            return false;
        }
        Div div = (Div) obj;
        return left.equals(div.left) && right.equals(div.right);
    }

    @Override
    public int hashCode() {
        return Objects.hash(left, right);
    }
}