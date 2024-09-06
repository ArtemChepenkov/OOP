package ru.nsu.chepenkov;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import java.util.Arrays;
import java.util.Random;

import static org.junit.jupiter.api.Assertions.*;

public class HeapSortTest {
    @Test
    @DisplayName("checkMain")
    void checkMain() {
        String[] test_data = new String[]{};
        HeapSort.main(test_data);
        assertTrue(true);
    }

    @Test
    @DisplayName("checkSmallArray")
    void checkSmallArray() {
        int[] test_data = {5, 4, 3, 2, 1};
        int[] actual_array = HeapSort.heapsort(test_data);
        int[] expected_array = {1, 2, 3, 4, 5};
        assertArrayEquals(actual_array, expected_array);
        assertTrue(actual_array != expected_array);
    }

    @Test
    @DisplayName("checkBigArray")
    void checkBigArray() {
        Random random = new Random();
        int[] expected_array = new int[10000];
        for (int i = 0; i < 10000; i++) {
            expected_array[i] = random.nextInt();
        }
        int[] actual_array = HeapSort.heapsort(expected_array);

        Arrays.sort(expected_array);

        assertArrayEquals(actual_array, expected_array);

        assertTrue(actual_array != expected_array);
    }

    @Test
    @DisplayName("checkVeryBigArray")
    void checkVeryBigArray() {
        //25000000
        int[] expected_array = new int[100];//~95Mb
        Random random = new Random();
        for (int i = 0; i < 100; i++) {
            expected_array[i] = random.nextInt();
        }
        int[] actual_array = HeapSort.heapsort(expected_array);

        Arrays.sort(expected_array);

        assertArrayEquals(actual_array, expected_array);

        assertTrue(actual_array != expected_array);
    }

    @Test
    @DisplayName("checkEmptyArray")
    void checkEmptyArray() {
        int[] test_data = {};
        int[] actual_array = HeapSort.heapsort(test_data);
        int[] expected_array = {};
        assertArrayEquals(actual_array, expected_array);
    }

    @Test
    @DisplayName("checkSingleElementArray")
    void checkSingleElementArray() {
        int[] test_data = {5};
        int[] actual_array = HeapSort.heapsort(test_data);
        int[] expected_array = {5};
        assertArrayEquals(actual_array, expected_array);
    }

    @Test
    @DisplayName("checkCloseToLimitsArray")
    void checkCloseToLimitsArray() {
        int[] test_data = {Integer.MAX_VALUE, Integer.MIN_VALUE};
        int[] actual_array = HeapSort.heapsort(test_data);
        int[] expected_array = {Integer.MIN_VALUE, Integer.MAX_VALUE};
        assertArrayEquals(actual_array, expected_array);
    }

    @Test
    @DisplayName("checkSortedArray")
    void checkSortedArray(){
        int[] test_data = {1,2,3,4,5};
        int[] actual_data = HeapSort.heapsort(test_data);
        assertArrayEquals(actual_data, test_data);
    }
}
