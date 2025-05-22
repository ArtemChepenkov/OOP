package ru.nsu.chepenkov.prime;

import java.io.*;
import java.net.*;
import java.util.Arrays;
import java.util.concurrent.*;

public class WorkerNode {
    private static final int WORKER_PORT = 8081;
    private static final int MASTER_PORT = 8081;
    private static final String MASTER_HOST = "master";
    private static final int TIMEOUT_MS = 15000;

    private final ExecutorService executor;
    private final ServerSocket serverSocket;

    public WorkerNode() throws IOException {
        this.executor = Executors.newFixedThreadPool(10);
        this.serverSocket = new ServerSocket(WORKER_PORT);
        System.out.println("Worker node started on port " + WORKER_PORT);
        registerWithMaster();
    }

    private void registerWithMaster() {
        try (Socket socket = new Socket(MASTER_HOST, MASTER_PORT);
             ObjectOutputStream out = new ObjectOutputStream(socket.getOutputStream())) {

            socket.setSoTimeout(TIMEOUT_MS);
            out.writeObject("REGISTER");
            out.flush();

            System.out.println("Successfully registered with master");

        } catch (Exception e) {
            System.err.println("Failed to register with master: " + e.getMessage());
        }
    }

    public void start() {
        while (!serverSocket.isClosed()) {
            try {
                Socket socket = serverSocket.accept();
                executor.submit(() -> handleTask(socket));
            } catch (IOException e) {
                if (!serverSocket.isClosed()) {
                    System.err.println("Error accepting task: " + e.getMessage());
                }
            }
        }
    }

    private void handleTask(Socket socket) {
        try (ObjectInputStream in = new ObjectInputStream(socket.getInputStream());
             ObjectOutputStream out = new ObjectOutputStream(socket.getOutputStream())) {

            socket.setSoTimeout(TIMEOUT_MS);
            int[] numbers = (int[]) in.readObject();
            System.out.println("Worker processing: " + Arrays.toString(numbers));

            boolean result = MasterNode.hasNonPrimeSequential(numbers);
            out.writeBoolean(result);
            out.flush();

        } catch (Exception e) {
            System.err.println("Error processing task: " + e.getMessage());
        } finally {
            try {
                socket.close();
            } catch (IOException e) {
                System.err.println("Error closing task socket: " + e.getMessage());
            }
        }
    }

    public void stop() {
        try {
            serverSocket.close();
        } catch (IOException e) {
            System.err.println("Error closing worker socket: " + e.getMessage());
        }
        executor.shutdown();
    }

    public static void main(String[] args) throws InterruptedException {
        TimeUnit.SECONDS.sleep(2);
        try {
            WorkerNode worker = new WorkerNode();
            worker.start();
        } catch (IOException e) {
            System.err.println("Failed to start worker node: " + e.getMessage());
        }
    }
}
