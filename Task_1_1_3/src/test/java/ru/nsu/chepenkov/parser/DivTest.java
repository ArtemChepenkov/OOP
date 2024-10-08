package ru.nsu.chepenkov.parser;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;



public class DivTest {
    @Test
    @DisplayName("DivTestToString")
    void DivTestToString() {
        Expression e;
        e = new Div(new Variable("x"), new Number(3));
        assertEquals("(x / 3.0)", e.toString());
    }

    @Test
    @DisplayName("DivTestEval")
    void DivTestEval() {
        Expression e;
        e = new Div(new Number(3), new Variable("x"));
        Double res =e.eval(Parser.parseVariables("x=3"));
        assertEquals(1.0, res);
    }

    @Test
    @DisplayName("DivTestDerivative")
    void DivTestDerivative() {
        Expression e;
        e = new Div(new Number(3), new Variable("x"));
        String actual = e.derivative("x").toString();
        String expected = "(((0.0 * x) - (3.0 * 1.0)) / (1.0 * 1.0))";
        assertEquals(expected, actual);
    }
}
