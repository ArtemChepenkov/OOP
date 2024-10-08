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

    static String peekToken() {
        oldpos = pos;
        readToken();
        pos = oldpos;
        return token;
    }

    static Expression parseExpr() {

        Expression res = parseMonome();
        String temp = peekToken();
        while (temp.equals("+") || temp.equals("-")) {
            String oper = readToken();
            if (oper.equals("+")) {
                Expression value = parseMonome();
                res = new Add(res, value);
            } else if (oper.equals("-")) {
                Expression value = parseMonome();
                res = new Sub(res, value);
            }
            temp = peekToken();
        }
        return res;
    }

    static Expression parseMonome() {
        Expression res = parseAtom();
        String temp = peekToken();
        while (temp.equals("*") || temp.equals("/")) {
            String oper = readToken();
            if (oper.equals("*")) {
                Expression value = parseAtom();
                res = new Mul(res, value);
            } else if (oper.equals("/")) {
                Expression value = parseAtom();
                res = new Div(res, value);
            }
            temp = peekToken();
        }
        return res;
    }

    static Expression parseAtom() {
        String temp = peekToken();
        if (temp.equals("(")) {
            readToken();
            Expression res = parseExpr();
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
        Expression e = parseExpr();
        e.print();
        System.out.println();
        double res = e.eval(parseVariables(variables));
        System.out.println(res);
        System.out.println("\nВведите по какой переменной брать производную");
        String derivativeVariable = scanner.nextLine();
        System.out.println(e.derivative(derivativeVariable));
        System.out.println(e.derivative(derivativeVariable).eval(parseVariables("x=1")));
    }
}