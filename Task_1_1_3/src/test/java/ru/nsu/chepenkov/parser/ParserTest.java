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
        String text = "((x*x*x*x)/y-(z*y-z*y*x*100)/2+(5*(x-y)-z*(x/x)))";
        String variables = "x=1;y=2;z=3";

        Expression e = Parser.completeExpression(text);

        Double resExpr;

        resExpr = e.eval(Parser.parseVariables(variables));

        assert(resExpr == 289.5);
    }
}