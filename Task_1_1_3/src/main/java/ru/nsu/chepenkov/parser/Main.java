package ru.nsu.chepenkov.parser;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        String text;
        Scanner scanner = new Scanner(System.in);
        System.out.print("Введите выражение: ");
        text = scanner.nextLine();
        System.out.print("Введите значения переменных(x=1;y=2 и тд): ");
        String variables = scanner.nextLine();
        Expression e = Parser.parseExpr(text);
        e.print();
        System.out.println();
        double res = e.eval(Parser.parseVariables(variables));
        System.out.println(res);
        System.out.println("\nВведите по какой переменной брать производную");
        String derivativeVariable = scanner.nextLine();
        System.out.println(e.derivative(derivativeVariable));
        System.out.println(e.derivative(derivativeVariable).eval(Parser.parseVariables(variables)));
    }
}
