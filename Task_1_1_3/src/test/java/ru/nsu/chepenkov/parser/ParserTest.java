package ru.nsu.chepenkov.parser;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

/**
 * Класс для тестирования Parser.
 *
 * @author ArtemChepenkov
 */

public class ParserTest {

    @Test
    @DisplayName("parserTestEval")
    void parserTestEval() {
        String text = "(x/y*x-x+y)";
        String variables = "x=3;y=6";
        String derivative = "y";
        Double resExpr;
        Double resDer;
        Expression e = Parser.parseExpr(text);
        resExpr = e.eval(Parser.parseVariables(variables));
        resDer = e.derivative(derivative).eval(Parser.parseVariables(variables));

        assertEquals(4.5, resExpr);
        assertEquals(-8.0, resDer);
    }

    @Test
    @DisplayName("ParserTestLongEval")
    void parserTestLongEval() {
        String text = "(x/y*x-x+y-123+x*7-y/2*100)";
        String variables = "x=1;y=2;z=3";

        Expression e = Parser.completeExpression(text);

        Double resExpr;

        resExpr = e.eval(Parser.parseVariables(variables));

        assertEquals(-214.5, (double) resExpr);
    }
}