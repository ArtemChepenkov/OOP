package ru.nsu.chepenkov.primemultithread;


import java.util.Arrays;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.stream.IntStream;

public class Main {

    public static boolean isPrime(int num) {
        if (num < 2) return false;
        for (int i = 2; i * i <= num; i++) {
            if (num % i == 0) return false;
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

    public static boolean hasNonPrimeParallel(int[] numbers, int numThreads) throws InterruptedException, ExecutionException {
        AtomicBoolean foundNonPrime = new AtomicBoolean(false);
        ForkJoinPool pool = new ForkJoinPool(numThreads);

        pool.submit(() -> IntStream.range(0, numbers.length)
                .parallel()
                .filter(i -> !foundNonPrime.get())
                .forEach(i -> {
                    if (!isPrime(numbers[i])) {
                        foundNonPrime.set(true);
                    }
                })
        ).get();

        pool.shutdown();
        return foundNonPrime.get();
    }

    public static boolean hasNonPrimeParallelStream(int[] numbers) {
        return IntStream.of(numbers).parallel().anyMatch(num -> !isPrime(num));
    }

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        int[] test_arr = new int[1_00000_000_0];
        Arrays.fill(test_arr, 6);  // Все числа составные

        long startTime = System.nanoTime();
        hasNonPrimeSequential(test_arr);
        long endTime = System.nanoTime();
        System.out.println("Sequential: " + (endTime - startTime) / 1_000_000 + " ms");

        startTime = System.nanoTime();
        hasNonPrimeParallel(test_arr, Runtime.getRuntime().availableProcessors());
        endTime = System.nanoTime();
        System.out.println("Parallel: " + (endTime - startTime) / 1_0000_00 + " ms");
    }
}
