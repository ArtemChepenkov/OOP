package ru.nsu.chepenkov.prime;

import org.w3c.dom.ls.LSOutput;

import java.io.*;
import java.net.*;
import java.util.Scanner;

public class PrimeClient {
    private static final String SERVER_HOST = "master";
    private static final int SERVER_PORT = 8080;

    public static void main(String[] args) throws IOException {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Connecting to master node at " + SERVER_HOST + ":" + SERVER_PORT);

        try (Socket socket = new Socket(SERVER_HOST, SERVER_PORT);
             ObjectOutputStream out = new ObjectOutputStream(socket.getOutputStream());
             ObjectInputStream in = new ObjectInputStream(socket.getInputStream())) {

            System.out.println("Successfully connected to master");
        }
        //System.out.println("Prime Number Check Client");
       //System.out.println("Enter numbers separated by spaces (empty line to exit):");

//        while (true) {
            //System.out.print("> ");
        //String input = scanner.nextLine().trim();
        String input = "1 2 3";
        System.out.println("got input");
//            if (input.isEmpty()) {
//                break;
//            }
            try {
                System.out.println("parsing");
                int[] numbers = parseNumbers(input);
                System.out.println("Sending request");
                boolean result = sendRequest(numbers);
                System.out.println("Result: " + (result ? "Contains non-prime" : "All primes"));
            } catch (NumberFormatException e) {
                System.err.println("Invalid input: " + e.getMessage());
            } catch (IOException e) {
                System.err.println("Communication error: " + e.getMessage());
            }
        //}

        scanner.close();
    }

    private static int[] parseNumbers(String input) throws NumberFormatException {
        String[] parts = input.split("\\s+");
        int[] numbers = new int[parts.length];

        for (int i = 0; i < parts.length; i++) {
            numbers[i] = Integer.parseInt(parts[i]);
        }

        return numbers;
    }

    private static boolean sendRequest(int[] numbers) throws IOException {
        try (Socket socket = new Socket(SERVER_HOST, SERVER_PORT);

             ObjectOutputStream out = new ObjectOutputStream(socket.getOutputStream());
             ObjectInputStream in = new ObjectInputStream(socket.getInputStream())) {

            socket.setSoTimeout(10000);
            out.writeObject(numbers);
            out.flush();

            return in.readBoolean();
        }
    }
}