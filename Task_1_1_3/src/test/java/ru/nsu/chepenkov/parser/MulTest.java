package ru.nsu.chepenkov.parser;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

public class MulTest {


    @Test
    @DisplayName("mulTestToString")
    void mulTestToString() {
        Expression e;
        e = new Mul(new Variable("x"), new Number(3));
        assertEquals("(x * 3.0)", e.toString());
    }

    @Test
    @DisplayName("MulTestEval")
    void mulTestEval() {
        Expression e;
        e = new Mul(new Number(3), new Variable("x"));
        Double res = e.eval(Parser.parseVariables("x=3"));
        assertEquals(9.0, res);
    }

    @Test
    @DisplayName("MulTestDerivative")
    void mulTestDerivative() {
        Expression e;
        e = new Mul(new Number(3), new Variable("x"));
        String actual = e.derivative("x").toString();
        String expected = "((0.0 * x) + (3.0 * 1.0))";
        assertEquals(expected, actual);
    }

    @Test
    @DisplayName("mulTestEquals")
    void mulTestEquals() {
        Mul mul = new Mul(new Number(0), new Number(1));
        Mul mul1 = new Mul(new Number(0), new Number(1));
        Mul mul2 = new Mul(new Number(0), new Number(2));
        Mul mul3 = null;
        assert(mul.equals(mul1));
        assertFalse(mul.equals(mul2));
        assertFalse(mul.equals(mul3));
    }

    @Test
    @DisplayName("mulTestHashcode")
    void mulTestHashcode() {
        Mul mul = new Mul(new Number(0), new Number(1));
        int res = mul.hashCode();
        assert(true);
    }
}
