package ru.nsu.chepenkov.parser;

import java.util.Map;
import java.util.Objects;

/**
 * Класс для описания числа.
 *
 * @author ArtemChepenkov
 */

public class Number extends Expression {
    private double value;

    /**Конструктор класса.*/
    public Number(double value) {
        this.value = value;
    }

    /**Функция для вывода с использованием toString.*/
    @Override
    public void print() {
        System.out.print(this.toString());
    }

    /**Функция для подсчёта выражения.*/
    @Override
    public Double eval(Map<String, Double> variables) {
        return this.value;
    }

    @Override
    public Expression derivative(String var) {
        return new Number(0);
    }

    @Override
    public String toString() {
        return Double.toString(value);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || obj.getClass() != this.getClass()) {
            return false;
        }
        Number number = (Number) obj;
        return number.value == this.value;
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(value);
    }
}
