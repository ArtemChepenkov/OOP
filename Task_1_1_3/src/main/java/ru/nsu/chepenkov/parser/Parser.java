package ru.nsu.chepenkov.parser;

import java.util.HashMap;
import java.util.Map;

public class Parser {
    static int pos;
    static int oldpos;
    static String token = "";
    static String text;

    static Map<String, Double> parseVariables(String variables) {
        Map<String, Double> variablesHashMap = new HashMap<>();
        String[] pairs = variables.split(";");
        for (String pair : pairs) {
            String[] keyValue = pair.split("=");
            if (keyValue.length == 2) {
                String key = keyValue[0].trim();
                double value = Double.parseDouble(keyValue[1].trim());
                variablesHashMap.put(key, value);
            }
        }
        return variablesHashMap;
    }

    static String readToken(String text) {
        while (text.charAt(pos) == ' ') {
            pos++;
        }

        if (pos == text.length() - 1) {
            token = "";
            return token;
        }
        char currentChar = text.charAt(pos);
        if (currentChar == '+' || currentChar == '-' || currentChar == '*' ||
                currentChar == '/' || currentChar == '(' ||
                currentChar == ')') {
            token = "";
            token += currentChar;
            pos++;
            return token;
        }
        token = "";
        while (Character.isDigit(text.charAt(pos)) || Character.isLetter(text.charAt(pos))) {

            token += text.charAt(pos++);
        }
        return token;
    }

    static String peekToken(String text) {
        oldpos = pos;
        readToken(text);
        pos = oldpos;
        return token;
    }

    static Expression parseExpr(String text) {

        Expression res = parseMonome(text);
        String temp = peekToken(text);
        while (temp.equals("+") || temp.equals("-")) {
            String oper = readToken(text);
            if (oper.equals("+")) {
                Expression value = parseMonome(text);
                res = new Add(res, value);
            } else if (oper.equals("-")) {
                Expression value = parseMonome(text);
                res = new Sub(res, value);
            }
            temp = peekToken(text);
        }
        return res;
    }

    static Expression parseMonome(String text) {
        Expression res = parseAtom(text);
        String temp = peekToken(text);
        while (temp.equals("*") || temp.equals("/")) {
            String oper = readToken(text);
            if (oper.equals("*")) {
                Expression value = parseAtom(text);
                res = new Mul(res, value);
            } else if (oper.equals("/")) {
                Expression value = parseAtom(text);
                res = new Div(res, value);
            }
            temp = peekToken(text);
        }
        return res;
    }

    static Expression parseAtom(String text) {
        String temp = peekToken(text);
        if (temp.equals("(")) {
            readToken(text);
            Expression res = parseExpr(text);
            readToken(text);
            return res;
        }
        temp = peekToken(text);
        if (temp.equals("-")) {
            readToken(text);
            String line = readToken(text);
            if (Character.isLetter(line.charAt(0))) {
                return new Variable("-" + line);
            } else {
                return new Number(-1 * Double.parseDouble(line));
            }
        } else {
            String line = readToken(text);
            if (Character.isLetter(line.charAt(0))) {
                return new Variable(line);
            } else {
                return new Number(Double.parseDouble(line));
            }
        }
    }
    static Expression completeExpression(String text) {
        Expression e;
        Expression res = parseExpr(text);
        pos = 0;
        oldpos = 0;
        token = "";
        return res;
    }
}