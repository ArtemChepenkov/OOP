package ru.nsu.chepenkov.prime;

import java.io.*;
import java.net.*;
import java.util.*;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicBoolean;

public class MasterNode {
    private static final int MASTER_PORT = 8080;
    private static final int WORKER_PORT = 8081;
    private static final int TIMEOUT_MS = 5000;
    private static final int MAX_RETRIES = 3;
    private static final int MAX_WORKERS = 10;

    private final ExecutorService executor = Executors.newCachedThreadPool();
    private final List<String> workerAddresses = new ArrayList<>();
    private final ServerSocket serverSocket;

    public MasterNode(int port) throws IOException {
        this.serverSocket = new ServerSocket(port);
        System.out.println("Master node started on port " + port);
    }

    public void start() {
        // Поток для регистрации рабочих узлов
        new Thread(this::registerWorkers).start();

        // Основной цикл обработки клиентских запросов
        while (!serverSocket.isClosed()) {
            try {
                Socket clientSocket = serverSocket.accept();
                executor.submit(() -> handleClient(clientSocket));
            } catch (IOException e) {
                if (!serverSocket.isClosed()) {
                    System.err.println("Error accepting client connection: " + e.getMessage());
                }
            }
        }
    }

    private void registerWorkers() {
        try (ServerSocket workerSocket = new ServerSocket(WORKER_PORT)) {
            while (!serverSocket.isClosed()) {
                try {
                    Socket socket = workerSocket.accept();
                    String workerAddress = socket.getInetAddress().getHostAddress();

                    synchronized (workerAddresses) {
                        if (workerAddresses.size() < MAX_WORKERS) {
                            workerAddresses.add(workerAddress);
                            System.out.println("Registered worker: " + workerAddress);
                        } else {
                            System.out.println("Worker limit reached, rejecting: " + workerAddress);
                            socket.close();
                        }
                    }
                } catch (IOException e) {
                    if (!workerSocket.isClosed()) {
                        System.err.println("Error registering worker: " + e.getMessage());
                    }
                }
            }
        } catch (IOException e) {
            System.err.println("Worker registration server failed: " + e.getMessage());
        }
    }

    private void handleClient(Socket clientSocket) {
        try (ObjectInputStream in = new ObjectInputStream(clientSocket.getInputStream());
             ObjectOutputStream out = new ObjectOutputStream(clientSocket.getOutputStream())) {

            int[] numbers = (int[]) in.readObject();
            System.out.println("Processing task with " + numbers.length + " numbers from " +
                    clientSocket.getInetAddress());

            boolean result = processTaskDistributed(numbers);
            out.writeBoolean(result);
            out.flush();

        } catch (Exception e) {
            System.err.println("Error handling client request: " + e.getMessage());
        } finally {
            try {
                clientSocket.close();
            } catch (IOException e) {
                System.err.println("Error closing client socket: " + e.getMessage());
            }
        }
    }

    private boolean processTaskDistributed(int[] numbers) {
        AtomicBoolean foundNonPrime = new AtomicBoolean(false);
        List<Future<Boolean>> futures = new ArrayList<>();

        synchronized (workerAddresses) {
            if (workerAddresses.isEmpty()) {
                System.out.println("No workers available, using local processing");
                return hasNonPrimeSequential(numbers);
            }

            // Разделяем задачу между рабочими узлами
            int chunkSize = (numbers.length + workerAddresses.size() - 1) / workerAddresses.size();

            for (int i = 0; i < workerAddresses.size() && !foundNonPrime.get(); i++) {
                int start = i * chunkSize;
                int end = Math.min(start + chunkSize, numbers.length);

                if (start >= end) continue;

                int[] chunk = Arrays.copyOfRange(numbers, start, end);
                String workerAddress = workerAddresses.get(i);

                futures.add(executor.submit(() ->
                        sendTaskToWorker(workerAddress, chunk, foundNonPrime)));
            }
        }

        // Ожидаем результаты от рабочих узлов
        for (Future<Boolean> future : futures) {
            try {
                if (future.get() != null && future.get()) {
                    foundNonPrime.set(true);
                    break;
                }
            } catch (Exception e) {
                System.err.println("Error getting result from worker: " + e.getMessage());
            }
        }

        return foundNonPrime.get();
    }

    private Boolean sendTaskToWorker(String workerAddress, int[] chunk, AtomicBoolean foundNonPrime) {
        for (int attempt = 0; attempt < MAX_RETRIES && !foundNonPrime.get(); attempt++) {
            try (Socket socket = new Socket()) {
                socket.connect(new InetSocketAddress(workerAddress, WORKER_PORT), TIMEOUT_MS);
                socket.setSoTimeout(TIMEOUT_MS);

                try (ObjectOutputStream out = new ObjectOutputStream(socket.getOutputStream());
                     ObjectInputStream in = new ObjectInputStream(socket.getInputStream())) {

                    out.writeObject(chunk);
                    out.flush();

                    return in.readBoolean();
                }
            } catch (Exception e) {
                System.err.println("Attempt " + (attempt + 1) + " failed for worker " +
                        workerAddress + ": " + e.getMessage());
                if (attempt == MAX_RETRIES - 1) {
                    removeWorker(workerAddress);
                }
            }
        }
        return null;
    }

    private void removeWorker(String workerAddress) {
        synchronized (workerAddresses) {
            workerAddresses.remove(workerAddress);
            System.out.println("Removed unavailable worker: " + workerAddress);
        }
    }

    public void stop() {
        try {
            serverSocket.close();
        } catch (IOException e) {
            System.err.println("Error closing server socket: " + e.getMessage());
        }
        executor.shutdown();
    }

    public static boolean isPrime(int num) {
        if (num < 2) return false;
        if (num % 2 == 0) return num == 2;
        if (num % 3 == 0) return num == 3;

        for (int i = 5; i * i <= num; i += 6) {
            if (num % i == 0 || num % (i + 2) == 0) {
                return false;
            }
        }
        return true;
    }

    public static boolean hasNonPrimeSequential(int[] numbers) {
        for (int num : numbers) {
            if (!isPrime(num)) {
                return true;
            }
        }
        return false;
    }

    public static void main(String[] args) {
        try {
            MasterNode server = new MasterNode(MASTER_PORT);
            server.start();
        } catch (IOException e) {
            System.err.println("Failed to start master node: " + e.getMessage());
        }
    }
}