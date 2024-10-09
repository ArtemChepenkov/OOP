package ru.nsu.chepenkov.parser;

import java.util.Map;

/**
 * Класс для описания переменной.
 *
 * @author ArtemChepenkov
 */

public class Variable extends Expression {
    private String variable;

    public Variable(String variable) {
        this.variable = variable;
    }
    @Override
    public void print() {
        System.out.print(this.toString());
    }

    @Override
    public Double eval(Map<String, Double> variables) {
        return variables.get(variable);
    }

    @Override
    public Expression derivative(String var) {
        return var.equals(this.variable) ? new Number(1) : new Number(0);
    }

    @Override
    public String toString() {
        return variable;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj == null || obj.getClass() != this.getClass()) {
            return false;
        }
        Variable var = (Variable) obj;
        return (this.variable.compareTo(var.variable) == 0);
    }
}
