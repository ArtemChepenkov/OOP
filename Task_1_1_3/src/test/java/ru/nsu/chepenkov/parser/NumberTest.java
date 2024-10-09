package ru.nsu.chepenkov.parser;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

public class NumberTest {
    @Test
    @DisplayName("numberTestToString")
    void numberTestToString() {
        Number n;
        n = new Number(1.0);
        assertEquals("1.0", n.toString());
    }

    @Test
    @DisplayName("NumberTestEval")
    void numberTestEval() {
        Number n;
        n = new Number(1.0);
        Double res = n.eval(Parser.parseVariables("x=3"));
        assertEquals(1.0, res);
    }

    @Test
    @DisplayName("NumberTestDerivative")
    void numberTestDerivative() {
        Number n;
        n = new Number(1.0);
        String actual = n.derivative("x").toString();
        String expected = "0.0";
        assertEquals(expected, actual);
    }

    @Test
    @DisplayName("numberTestEquals")
    void numberTestEquals() {
        Number number0 = new Number(0);
        Number number1 = new Number(0);
        Number number2 = new Number(1);
        assert(number0.equals(number1));
        assertFalse(number0.equals(number2));
    }
}
