package ru.nsu.chepenkov.primemultithread;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.concurrent.ExecutionException;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class PrimeFinderTest {

    @DisplayName("MainTestSequentialHasNotPrime")
    @Test
    void mainTestSequentialHasNotPrime() {
        int[] test_arr = {4,4,4,4,4};
        Assertions.assertTrue(PrimeFinder.hasNonPrimeSequential(test_arr));
    }

    @DisplayName("MainTestSequentialHasPrime")
    @Test
    void mainTestSequentialHasPrime() {
        int[] test_arr = {2,2,2,2,2};
        Assertions.assertFalse(PrimeFinder.hasNonPrimeSequential(test_arr));
    }


    @DisplayName("MainTestParallelHasNotPrime")
    @Test
    void mainTestParallelHasNotPrime() throws ExecutionException, InterruptedException {
        int[] test_arr = {4,4,4,4,4};
        Assertions.assertTrue(PrimeFinder.hasNonPrimeParallel(test_arr, 2));
    }

    @DisplayName("MainTestParallelHasPrime")
    @Test
    void mainTestParallelHasPrime() throws ExecutionException, InterruptedException {
        int[] test_arr = {2,2,2,2,2};
        Assertions.assertFalse(PrimeFinder.hasNonPrimeParallel(test_arr, 2));
    }

    @DisplayName("MainTestParallelStreamHasNotPrime")
    @Test
    void mainTestParallelStreamHasNotPrime() {
        int[] test_arr = {4,4,4,4,4};
        Assertions.assertTrue(PrimeFinder.hasNonPrimeParallelStream(test_arr));
    }

    @DisplayName("MainTestParallelStreamHasPrime")
    @Test
    void mainTestParallelStreamHasPrime() {
        int[] test_arr = {2,2,2,2,2};
        Assertions.assertFalse(PrimeFinder.hasNonPrimeParallelStream(test_arr));
    }

    @DisplayName("MainTestTimeCompare")
    @Test
    void mainTestTimeCompare() throws ExecutionException, InterruptedException {
        int size = (int)1e4;
        int[] arr = new int[size];
        Arrays.fill(arr, 20319251); //check worst case

        long startTimeReference = System.nanoTime();
        PrimeFinder.hasNonPrimeSequential(arr);
        long endTimeReference = System.nanoTime();


        long startTime = System.nanoTime();
        PrimeFinder.hasNonPrimeParallel(arr, 4);
        long endTime = System.nanoTime();
        assertTrue(endTimeReference-startTimeReference > endTime - startTime);

        startTime = System.nanoTime();
        PrimeFinder.hasNonPrimeParallelStream(arr);
        endTime = System.nanoTime();

        assertTrue(endTimeReference-startTimeReference > endTime - startTime);

    }
}
