package ru.nsu.chepenkov;

import java.util.Arrays;
import org.junit.jupiter.api.DisplayName;
import java.util.Random;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

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
        //25000000
        int[] expectedArray = new int[25000000];//~95Mb
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
    void checkSortedArray(){
        int[] testData = {1,2,3,4,5};
        int[] actualArray = HeapSort.heapsort(testData);
        assertArrayEquals(actualArray, testData);
    }

    @Test
    @DisplayName("checkArrayOfOneValue")
    void checkArrayOfOneValue(){
        int[] testData = {1,1,1,1,1,1,1};
        int[] actualArray = HeapSort.heapsort(testData);
        assertArrayEquals(actualArray, testData);
    }

    @Test
    @DisplayName("checkSortRevertArray")
    void checkSortRevertArray(){
        int[] testData = {5,4,3,2,1};
        int[] actualArray = HeapSort.heapsort(testData);
        int[] expectedArray = {1,2,3,4,5};
        assertArrayEquals(actualArray, expectedArray);
    }
}
