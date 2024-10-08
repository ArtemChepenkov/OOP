package ru.nsu.chepenkov.parser;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class MulTest {
    @Test
    @DisplayName("MulTestToString")
    void MulTestToString() {
        Expression e;
        e = new Mul(new Variable("x"), new Number(3));
        assertEquals("(x * 3.0)", e.toString());
    }

    @Test
    @DisplayName("MulTestEval")
    void MulTestEval() {
        Expression e;
        e = new Mul(new Number(3), new Variable("x"));
        Double res = e.eval(Parser.parseVariables("x=3"));
        assertEquals(9.0, res);
    }

    @Test
    @DisplayName("MulTestDerivative")
    void MulTestDerivative() {
        Expression e;
        e = new Mul(new Number(3), new Variable("x"));
        String actual = e.derivative("x").toString();
        String expected = "((0.0 * x) + (3.0 * 1.0))";
        assertEquals(expected, actual);
    }
}
