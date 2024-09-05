package ru.nsu.chepenkov;

import java.util.Scanner;

public class HeapSort {
    static void heapify(int[] array, int size, int i) {
        int maximum = i, temporary;
        int left = 2 * i + 1;
        int right = 2 * i + 2;

        if (left < size && array[left] > array[maximum]) {
            maximum = left;
        }
        if (right < size && array[right] > array[maximum]) {
            maximum = right;
        }
        if (maximum != i) {
            temporary = array[i];
            array[i] = array[maximum];
            array[maximum] = temporary;
            heapify(array, size, maximum);
        }
    }

    static int[] heapsort(int[] array, int size) {
        for (int i = size; i >= 0; i--) {
            heapify(array, size, i);
        }

        for (int i = size - 1; i > 0; i--) {
            int temporary = array[0];
            array[0] = array[i];
            array[i] = temporary;
            heapify(array, i, 0);
        }
        return array;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        String input_text = scanner.nextLine();
        String[] str_numbers = input_text.split(" ");
        int length = str_numbers.length;
        int[] array = new int[length];

        for (int i = 0; i < length; i++) {
            array[i] = Integer.parseInt(str_numbers[i]);
        }

        int[] result = heapsort(array, length);

        for (int i = 0; i < length; i++) {
            System.out.print(result[i] + " ");
        }
    }
}




