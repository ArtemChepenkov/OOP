package ru.nsu.chepenkov.parser;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

/**
 * Класс для тестирования Variable.
 *
 * @author ArtemChepenkov
 */

public class VariableTest {
    @Test
    @DisplayName("variableTestToString")
    void variableTestToString() {
        Variable v;
        v = new Variable("x");

        assertEquals("x", v.toString());
    }

    @Test
    @DisplayName("variableTestEval")
    void variableTestEval() {
        Variable v;
        v = new Variable("x");
        Double res = v.eval(Parser.parseVariables("x=3"));

        assertEquals(3.0, res);
    }

    @Test
    @DisplayName("variableTestDerivative")
    void variableTestDerivative() {
        Variable v;
        v = new Variable("x");
        String actual = v.derivative("x").toString();
        String expected = "1.0";

        assertEquals(expected, actual);
    }

    @Test
    @DisplayName("variableTestEquals")
    void variableTestEquals() {
        Variable variable0 = new Variable("x");
        Variable variable1 = new Variable("x");
        Variable variable2 = new Variable("y");

        assert(variable0.equals(variable1));
        assertFalse(variable0.equals(variable2));
    }
}
