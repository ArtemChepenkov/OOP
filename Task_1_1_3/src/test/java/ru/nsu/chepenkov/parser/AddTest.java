package ru.nsu.chepenkov.parser;

import org.junit.jupiter.api.DisplayName;
import java.io.PrintStream;
import java.io.ByteArrayOutputStream;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Класс для тестирования Add.
 *
 * @author ArtemChepenkov
 */

public class AddTest {

    Number number = new Number(4);
    Variable variable = new Variable("x");
    Add add = new Add(number, variable);

    @Test
    @DisplayName("addTestConstructor")
    void addTestConstructor() {
        Add add1 = new Add(number, variable);

        assertTrue(true);
    }

    @Test
    @DisplayName("addTestPrint")
    void addTestPrint() {
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));

        add.print();
        String output = outContent.toString();

        assertEquals("(4.0 + x)", output);

        System.setOut(System.out);
    }

    @Test
    @DisplayName("addTestEval")
    void addTestEval() {
        double res = add.eval(Parser.parseVariables("x=4"));

        assertEquals(8.0, res);
    }

    @Test
    @DisplayName("addTestDerivative")
    void addTestDerivative() {
        Expression res = add.derivative("x");
        Expression expected = new Add(new Number(0), new Number(1));

        assertEquals(expected, res);
    }

    @Test
    @DisplayName("addTestEquals")
    void addTestEquals() {
        Add add = new Add(new Number(0), new Number(1));
        Add add1 = new Add(new Number(0), new Number(1));
        Add add2 = new Add(new Number(0), new Number(2));
        Add add3 = null;

        assertTrue(add.equals(add1));
        assertFalse(add.equals(add2));
        assertFalse(add.equals(add3));
    }
}
