package ru.nsu.chepenkov.primemultithread;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.stream.IntStream;

/**
 * Класс, реализует решение задачи(может название не самое удачное).
 */

public class PrimeFinder {

    /**
     * Обычный поиск "за корень".
     */
    public static boolean isPrime(int num) {
        if (num < 2) {
            return false;
        }
        for (int i = 2; i * i <= num; i++) {
            if (num % i == 0) {
                return false;
            }
        }
        return true;
    }

    /**
     * Просто "пробегаемся" по массиву.
     */
    public static boolean hasNonPrimeSequential(int[] numbers) {
        for (int num : numbers) {
            if (!isPrime(num)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Параллельность на уровне реализации Stream'ов.
     */
    public static boolean hasNonPrimeParallel(int[] numbers, int numThreads)
            throws InterruptedException, ExecutionException {

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

    /**
     * Тут просто готовая реализация.
     */
    public static boolean hasNonPrimeParallelStream(int[] numbers) {
        return IntStream.of(numbers).parallel().anyMatch(num -> !isPrime(num));
    }
}
