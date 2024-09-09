package ru.nsu.chepenkov;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Arrays;
import java.util.Random;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;


/**
 * Класс для проверки алгоритма пирамидальной сортировки.
 *
 * @author ArtemChepenkov
 */
public class HeapSortTest {
    @Test
    @DisplayName("checkMain")
    void checkMain() {
        String[] testData = new String[]{};
        HeapSort.main(testData);
        assertTrue(true);
    }

    @Test
    @DisplayName("checkSmallArray")
    void checkSmallArray() {
        int[] testData = {5, 4, 3, 2, 1};
        int[] actualArray = HeapSort.heapsort(testData);
        int[] expectedArray = {1, 2, 3, 4, 5};
        assertArrayEquals(actualArray, expectedArray);
        assertTrue(actualArray != expectedArray);
    }

    @Test
    @DisplayName("checkBigArray")
    void checkBigArray() {
        Random random = new Random();
        int[] expectedArray = new int[10000];
        for (int i = 0; i < 10000; i++) {
            expectedArray[i] = random.nextInt();
        }
        int[] actualArray = HeapSort.heapsort(expectedArray);

        Arrays.sort(expectedArray);

        assertArrayEquals(actualArray, expectedArray);

        assertTrue(actualArray != expectedArray);
    }

    @Test
    @DisplayName("checkVeryBigArray")
    void checkVeryBigArray() {
        int[] expectedArray = new int[25000000]; //~95Mb
        Random random = new Random();
        for (int i = 0; i < 25000000; i++) {
            expectedArray[i] = random.nextInt();
        }
        int[] actualArray = HeapSort.heapsort(expectedArray);

        Arrays.sort(expectedArray);

        assertArrayEquals(actualArray, expectedArray);

        assertTrue(actualArray != expectedArray);
    }

    @Test
    @DisplayName("checkEmptyArray")
    void checkEmptyArray() {
        int[] testData = {};
        int[] actualArray = HeapSort.heapsort(testData);
        int[] expectedArray = {};
        assertArrayEquals(actualArray, expectedArray);
    }

    @Test
    @DisplayName("checkSingleElementArray")
    void checkSingleElementArray() {
        int[] testData = {5};
        int[] actualArray = HeapSort.heapsort(testData);
        int[] expectedArray = {5};
        assertArrayEquals(actualArray, expectedArray);
    }

    @Test
    @DisplayName("checkCloseToLimitsArray")
    void checkCloseToLimitsArray() {
        int[] testData = {Integer.MAX_VALUE, Integer.MIN_VALUE};
        int[] actualArray = HeapSort.heapsort(testData);
        int[] expectedArray = {Integer.MIN_VALUE, Integer.MAX_VALUE};
        assertArrayEquals(actualArray, expectedArray);
    }

    @Test
    @DisplayName("checkSortedArray")
    void checkSortedArray() {
        int[] testData = {1, 2, 3, 4, 5};
        int[] actualArray = HeapSort.heapsort(testData);
        assertArrayEquals(actualArray, testData);
    }

    @Test
    @DisplayName("checkArrayOfOneValue")
    void checkArrayOfOneValue() {
        int[] testData = {1, 1, 1, 1, 1, 1, 1};
        int[] actualArray = HeapSort.heapsort(testData);
        assertArrayEquals(actualArray, testData);
    }

    @Test
    @DisplayName("checkSortRevertArray")
    void checkSortRevertArray() {
        int[] testData = {5, 4, 3, 2, 1};
        int[] actualArray = HeapSort.heapsort(testData);
        int[] expectedArray = {1, 2, 3, 4, 5};
        assertArrayEquals(actualArray, expectedArray);
    }

    @Test
    @DisplayName("checkOnlyNegativeArray")
    void checkOnlyNegativeArray() {
        int[] testData = {-4, -10, -1, -5, -2};
        int[] actualArray = HeapSort.heapsort(testData);
        int[] expectedArray = {-10, -5, -4, -2, -1};
        assertArrayEquals(actualArray, expectedArray);

    }
}
