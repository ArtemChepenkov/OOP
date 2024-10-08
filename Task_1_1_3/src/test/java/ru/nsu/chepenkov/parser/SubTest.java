package ru.nsu.chepenkov.parser;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class SubTest {
    @Test
    @DisplayName("SubTestToString")
    void SubTestToString() {
        Expression e;
        e = new Sub(new Variable("x"), new Number(3));
        assertEquals("(x - 3.0)", e.toString());
    }

    @Test
    @DisplayName("SubTestEval")
    void SubTestEval() {
        Expression e;
        e = new Sub(new Number(3), new Variable("x"));
        Double res =e.eval(Parser.parseVariables("x=3"));
        assertEquals(0.0, res);
    }

    @Test
    @DisplayName("SubTestDerivative")
    void SubTestDerivative() {
        Expression e;
        e = new Sub(new Number(3), new Variable("x"));
        String actual = e.derivative("x").toString();
        String expected = "(0.0 - 1.0)";
        assertEquals(expected, actual);
    }
}
