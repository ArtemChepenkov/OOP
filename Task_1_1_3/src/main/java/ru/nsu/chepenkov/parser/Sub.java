package ru.nsu.chepenkov.parser;

import java.util.Map;
import java.util.Objects;

/**
 * Класс для описания операции вычитания.
 *
 * @author ArtemChepenkov
 */

public class Sub extends Expression {

    private final Expression left;
    private final Expression right;

    /**Конструктор класса.*/
    public Sub(Expression left, Expression right) {
        this.left = left;
        this.right = right;
    }

    @Override
    public void print() {
        System.out.print(this.toString());
    }

    @Override
    public Double eval(Map<String, Double> map) {
        return left.eval(map) - right.eval(map);
    }

    @Override
    public Expression derivative(String var) {
        return new Sub(left.derivative(var), right.derivative(var));
    }

    @Override
    public String toString() {
        return "(" + left.toString() + " - " + right.toString() + ")";
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj == null || obj.getClass() != this.getClass()) {
            return false;
        }
        Sub sub = (Sub) obj;
        return left.equals(sub.left) && right.equals(sub.right);
    }

    @Override
    public int hashCode() {
        return Objects.hash(left, right);
    }
}