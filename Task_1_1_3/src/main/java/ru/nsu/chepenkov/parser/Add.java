package ru.nsu.chepenkov.parser;

import java.util.Map;
import java.util.Objects;

/**
 * Класс для описания операции сложения.
 *
 * @author ArtemChepenkov
 */

public class Add extends Expression {
    private final Expression left;
    private final Expression right;

    /**Конструктор класса.*/
    public Add(Expression left, Expression right) {
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

    @Override
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj == null || obj.getClass() != this.getClass()) {
            return false;
        }
        Add sum = (Add) obj;
        return left.equals(sum.left) && right.equals(sum.right);
    }

    @Override
    public int hashCode() {
        return Objects.hash(left, right);
    }
}
