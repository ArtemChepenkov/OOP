package ru.nsu.chepenkov.parser;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class VariableTest {
    @Test
    @DisplayName("VariableTestToString")
    void VariableTestToString() {
        Variable v;
        v = new Variable("x");
        assertEquals("x", v.toString());
    }

    @Test
    @DisplayName("VariableTestEval")
    void VariableTestEval() {
        Variable v;
        v = new Variable("x");
        Double res = v.eval(Parser.parseVariables("x=3"));
        assertEquals(3.0, res);
    }

    @Test
    @DisplayName("VariableTestDerivative")
    void VariableTestDerivative() {
        Variable v;
        v = new Variable("x");
        String actual = v.derivative("x").toString();
        String expected = "1.0";
        assertEquals(expected, actual);
    }
}
