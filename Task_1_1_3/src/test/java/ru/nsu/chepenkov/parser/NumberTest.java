package ru.nsu.chepenkov.parser;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class NumberTest {
    @Test
    @DisplayName("NumberTestToString")
    void NumberTestToString() {
        Number n;
        n = new Number(1.0);
        assertEquals("1.0", n.toString());
    }

    @Test
    @DisplayName("NumberTestEval")
    void NumberTestEval() {
        Number n;
        n = new Number(1.0);
        Double res = n.eval(Parser.parseVariables("x=3"));
        assertEquals(1.0, res);
    }

    @Test
    @DisplayName("NumberTestDerivative")
    void NumberTestDerivative() {
        Number n;
        n = new Number(1.0);
        String actual = n.derivative("x").toString();
        String expected = "0.0";
        assertEquals(expected, actual);
    }
}
