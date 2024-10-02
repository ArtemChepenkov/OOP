package ru.nsu.chepenkov.parser;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Main {
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

    static String readToken() {
        while (text.charAt(pos) == ' ') {
            pos++;
        }

        if (pos == text.length() - 1) {
            token = "";
            return token;
        }
        if (text.charAt(pos) == '+' || text.charAt(pos) == '-' || text.charAt(pos) == '*' ||
                text.charAt(pos) == '/' || text.charAt(pos) == '(' ||
                text.charAt(pos) == ')') {
            token = "";
            token += text.charAt(pos++);
            return token;
        }
        token = "";
        while (Character.isDigit(text.charAt(pos)) || Character.isLetter(text.charAt(pos))) {

            token += text.charAt(pos++);
        }
        return token;
    }

    static String peekToken() {
        oldpos = pos;
        readToken();
        pos = oldpos;
        return token;
    }

    static Expression ParseExpr() {

        Expression res = ParseMonome();
        String temp = peekToken();
        while (temp.equals("+") || temp.equals("-")) {
            String oper = readToken();
            if (oper.equals("+")) {
                Expression value = ParseMonome();
                res = new Add(res, value);
            } else if (oper.equals("-")) {
                Expression value = ParseMonome();
                res = new Sub(res, value);
            }
            temp = peekToken();
        }
        return res;
    }

    static Expression ParseMonome() {
        Expression res = ParseAtom();
        String temp = peekToken();
        while (temp.equals("*") || temp.equals("/")) {
            String oper = readToken();
            if (oper.equals("*")) {
                Expression value = ParseAtom();
                res = new Mul(res, value);
            } else if (oper.equals("/")) {
                Expression value = ParseAtom();
                res = new Div(res, value);
            }
            temp = peekToken();
        }
        return res;
    }

    static Expression ParseAtom() {
        String temp = peekToken();
        if (temp.equals("(")) {
            readToken();
            Expression res = ParseExpr();
            readToken();
            return res;
        }
        temp = peekToken();
        if (temp.equals("-")) {
            readToken();
            String line = readToken();
            if (Character.isLetter(line.charAt(0))) {
                return new Variable("-" + line);
            } else {
                return new Number(-1 * Double.parseDouble(line));
            }
        } else {
            String line = readToken();
            if (Character.isLetter(line.charAt(0))) {
                return new Variable(line);
            } else {
                return new Number(Double.parseDouble(line));
            }
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Введите выражение: ");
        text = scanner.nextLine();
        System.out.print("Введите значения переменных(x=1;y=2 и тд): ");
        String variables = scanner.nextLine();
        Expression e = ParseExpr();
        e.print();
        System.out.println();
        double res = e.eval(parseVariables(variables));
        System.out.println(res);
    }
}