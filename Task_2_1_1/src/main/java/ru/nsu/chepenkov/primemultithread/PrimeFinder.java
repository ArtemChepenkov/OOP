package ru.nsu.chepenkov.primemultithread;

import java.util.Arrays;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.stream.IntStream;

/**
 * Класс, реализует решение задачи(может название не самое удачное).
 */

public class PrimeFinder{

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
        Thread[] threadArray = new Thread[numThreads];
        int len = numbers.length;
        for (int i = 0; i < numThreads; i++) {
            int finalI = i;
            Thread thread = new Thread(()->{
                for (int j = finalI; j < len && !foundNonPrime.get(); j+=numThreads) {
                    if (!isPrime(numbers[j])) {
                        foundNonPrime.set(true);
                    }
                }
            });
            thread.start();
            threadArray[i] = thread;
        }

        for (int i = 0; i < numThreads; i++) {
            threadArray[i].join();
        }

        return foundNonPrime.get();
    }

    /**
     * Тут просто готовая реализация.
     */
    public static boolean hasNonPrimeParallelStream(int[] numbers) {
        return IntStream.of(numbers).parallel().anyMatch(num -> !isPrime(num));
    }

    /*public static void main(String[] args) throws ExecutionException, InterruptedException {
        int[] arr = new int[(int)1e3 * 5];
        Arrays.fill(arr,  524287);
        long summa = 0;
            for (int j = 1; j <= 1000; j++) {
                long time = System.nanoTime();
                hasNonPrimeParallel(arr,8);
                //hasNonPrimeSequential(arr);
                //hasNonPrimeParallelStream(arr);
                long time1 = System.nanoTime();
                summa += time1 - time;
            }
            System.out.print(summa / 1000);
    }*/
}

