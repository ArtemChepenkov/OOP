package ru.nsu.chepenkov.parser;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

/**
 * Класс для тестирования Div.
 *
 * @author ArtemChepenkov
 */

public class DivTest {
    @Test
    @DisplayName("divTestToString")
    void divTestToString() {
        Expression e;
        e = new Div(new Variable("x"), new Number(3));

        assertEquals("(x / 3.0)", e.toString());
    }

    @Test
    @DisplayName("divTestEval")
    void divTestEval() {
        boolean flag = false;
        Expression e;
        e = new Div(new Number(3), new Variable("x"));
        Double res = e.eval(Parser.parseVariables("x=3"));
        try {
            Double resException = e.eval(Parser.parseVariables("x=0"));
        } catch (ArithmeticException exception) {
            flag = true;
        }

        assert(flag);

        assertEquals(1.0, res);
    }

    @Test
    @DisplayName("divTestDerivative")
    void divTestDerivative() {
        Expression e;
        e = new Div(new Number(3), new Variable("x"));
        String actual = e.derivative("x").toString();
        String expected = "(((0.0 * x) - (3.0 * 1.0)) / (1.0 * 1.0))";

        assertEquals(expected, actual);
    }

    @Test
    @DisplayName("divTestEquals")
    void divTestEquals() {
        Div div = new Div(new Number(0), new Number(1));
        Div div1 = new Div(new Number(0), new Number(1));
        Div div2 = new Div(new Number(0), new Number(2));

        assert(div.equals(div1));

        assertFalse(div.equals(div2));
    }
}
