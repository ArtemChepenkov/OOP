package ru.nsu.chepenkov.parser;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;


public class ParserTest {

    @Test
    @DisplayName("ParserTestEval")
    void ParserTestEval() {
        String text = "(x-y)";
        String variables = "x=3;y=6";
        String derivative = "x";

        Expression e = Parser.completeExpression(text);

        Double resExpr;
        Double resDer;

        resExpr = e.eval(Parser.parseVariables(variables));
        resDer = e.derivative(derivative).eval(Parser.parseVariables(variables));

        assert(resExpr == -3.0);
        assert(resDer == 1.0);
    }

//    @Test
//    @DisplayName("ParserTestLongEval")
//    void ParserTestLongEval() {
//        String text = "(x/y*x-x+y-123+x*7-y/2*100)";
//        String variables = "x=1;y=2;z=3";
//
//        Expression e = Parser.completeExpression(text);
//
//        Double resExpr;
//
//        resExpr = e.eval(Parser.parseVariables(variables));
//
//        assert(resExpr == -214.5);
//    }
}