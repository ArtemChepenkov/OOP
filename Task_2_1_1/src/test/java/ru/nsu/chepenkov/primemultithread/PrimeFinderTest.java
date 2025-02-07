package ru.nsu.chepenkov.primemultithread;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Arrays;
import java.util.concurrent.ExecutionException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;


/**
 * Здесь представлены простые тесты, замеры времени были сделаны не тут.
 */

public class PrimeFinderTest {

    @DisplayName("MainTestSequentialHasNotPrime")
    @Test
    void mainTestSequentialHasNotPrime() {
        int[] testArr = {4, 4, 4, 4, 4};
        Assertions.assertTrue(PrimeFinder.hasNonPrimeSequential(testArr));
    }

    @DisplayName("MainTestSequentialHasPrime")
    @Test
    void mainTestSequentialHasPrime() {
        int[] testArr = {2, 2, 2, 2, 2};
        Assertions.assertFalse(PrimeFinder.hasNonPrimeSequential(testArr));
    }


    @DisplayName("MainTestParallelHasNotPrime")
    @Test
    void mainTestParallelHasNotPrime() throws ExecutionException, InterruptedException {
        int[] testArr = {4, 4, 4, 4, 4};
        Assertions.assertTrue(PrimeFinder.hasNonPrimeParallel(testArr, 2));
    }

    @DisplayName("MainTestParallelHasPrime")
    @Test
    void mainTestParallelHasPrime() throws ExecutionException, InterruptedException {
        int[] testArr = {2, 2, 2, 2, 2};
        Assertions.assertFalse(PrimeFinder.hasNonPrimeParallel(testArr, 2));
    }

    @DisplayName("MainTestParallelStreamHasNotPrime")
    @Test
    void mainTestParallelStreamHasNotPrime() {
        int[] testArr = {4, 4, 4, 4, 4};
        Assertions.assertTrue(PrimeFinder.hasNonPrimeParallelStream(testArr));
    }

    @DisplayName("MainTestParallelStreamHasPrime")
    @Test
    void mainTestParallelStreamHasPrime() {
        int[] testArr = {2, 2, 2, 2, 2};
        Assertions.assertFalse(PrimeFinder.hasNonPrimeParallelStream(testArr));
    }

    @DisplayName("MainTestTimeCompare")
    @Test
    void mainTestTimeCompare() throws ExecutionException, InterruptedException {
        int size = (int) 1e4;
        int[] arr = new int[size];
        Arrays.fill(arr, 20319251); //check worst case

        long startTimeReference = System.nanoTime();
        PrimeFinder.hasNonPrimeSequential(arr);
        long endTimeReference = System.nanoTime();


        long startTime = System.nanoTime();
        PrimeFinder.hasNonPrimeParallel(arr, 8);
        long endTime = System.nanoTime();
        assertTrue(endTimeReference - startTimeReference > endTime - startTime);

        startTime = System.nanoTime();
        PrimeFinder.hasNonPrimeParallelStream(arr);
        endTime = System.nanoTime();

        assertTrue(endTimeReference - startTimeReference > endTime - startTime);

    }
}
