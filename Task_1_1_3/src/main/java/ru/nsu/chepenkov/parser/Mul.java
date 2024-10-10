package ru.nsu.chepenkov.parser;

import java.util.Map;
import java.util.Objects;

/**
 * Класс для описания операции умножения.
 *
 * @author ArtemChepenkov
 */

public class Mul extends Expression {

    private final Expression left;
    private final Expression right;

    /**Конструктор класса.*/
    public Mul(Expression left, Expression right) {
        this.left = left;
        this.right = right;
    }

    /**Функция для вывода с использованием toString.*/
    @Override
    public void print() {
        System.out.print(this.toString());
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

    @Override
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj == null || obj.getClass() != this.getClass()) {
            return false;
        }
        Mul mul = (Mul) obj;
        return left.equals(mul.left) && right.equals(mul.right);
    }

    @Override
    public int hashCode() {
        return Objects.hash(left, right);
    }
}
