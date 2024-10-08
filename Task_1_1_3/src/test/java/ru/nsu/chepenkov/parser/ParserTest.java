package ru.nsu.chepenkov.parser;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;


public class ParserTest {

    @Test
    @DisplayName("MainTestEval")
    void MainTestEval() {
        String text = "(x/y*x-x+y)";
        String variables = "x=3;y=6";
        String derivative = "y";

        Expression e = Parser.completeExpression(text);

        Double resExpr;
        Double resDer;

        resExpr = e.eval(Parser.parseVariables(variables));
        resDer = e.derivative(derivative).eval(Parser.parseVariables(variables));

        assert(resExpr == 4.5);
        assert(resDer == -8.0);
    }

    @Test
    @DisplayName("MainTestLongEval")
    void MainTestLongEval() {
        String text = "((x/y-x*z*y*100)/(x+6*z)*((z*y/x+123)+(y-x+9)))";
        String variables = "x=7;y=11;z=13";

        Expression e = Parser.completeExpression(text);

        Double resExpr;

        resExpr = e.eval(Parser.parseVariables(variables));

        assert(resExpr == -184216.47593582884);
    }
}