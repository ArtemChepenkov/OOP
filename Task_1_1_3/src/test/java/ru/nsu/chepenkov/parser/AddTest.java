package ru.nsu.chepenkov.parser;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

public class AddTest {

    Number number = new Number(4);
    Variable variable = new Variable("x");
    Add add = new Add(number, variable);

    @Test
    @DisplayName("addTestConstructor")
    void addTestConstructor() {
        Add add1 = new Add(number, variable);
        assert(true);
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
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));

        Expression res = add.derivative("x");
        res.print();
        String output = outContent.toString();

        assertEquals("(0.0 + 1.0)", output);
        System.setOut(System.out);
    }
}
